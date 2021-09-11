package com.example.notekeeper

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.notekeeper.databinding.ActivityMainBinding
import com.example.notekeeper.databinding.FragmentFirstBinding


class Person(val name: String, var weightKg: Double) {
    var weightLbs: Double
        get() = weightKg * 2.2
        set(value) {
            weightKg = value / 2.2
        }

    fun eatDessert(addedIceCream: Boolean = true) {
        weightLbs += if (addedIceCream) 4.0 else 2.0
    }

    fun calcGoalWeightLbs(lbsToLose: Double = 10.0): Double {
        return weightLbs - lbsToLose
    }
}


class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var firstBinding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        firstBinding = FragmentFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.test.spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
            intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

        displayNote()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }


    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val title = binding.test.textNoteTitle.text.toString()
        val noteText = binding.test.textNoteText.text.toString()
        val course = binding.test.spinnerCourses.selectedItem as CourseInfo
        if (notePosition == POSITION_NOT_SET) {
            if (title != "" || noteText != "") {
                DataManager.notes.add(NoteInfo(course, title, noteText))
                notePosition = DataManager.notes.lastIndex
            }
        } else {
            val note = DataManager.notes[notePosition]
            note.title = title
            note.text = noteText
            note.course = course
        }
    }

    private fun displayNote() {
        invalidateOptionsMenu()
        if (notePosition == POSITION_NOT_SET)
            return
        val note = DataManager.notes[notePosition]
        binding.test.textNoteTitle.setText(note.title)
        binding.test.textNoteText.setText(note.text)
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.test.spinnerCourses.setSelection(coursePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                move(DIRECTION_FORWARD)
                true
            }
            R.id.action_prev -> {
                move(DIRECTION_BACK)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun move(direction: Int) {
        notePosition += direction
        displayNote()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val nextMenuItem = menu?.findItem(R.id.action_next)
        val prevMenuItem = menu?.findItem(R.id.action_prev)
        if (nextMenuItem != null) {
            if (notePosition >= DataManager.notes.lastIndex) {
                nextMenuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                nextMenuItem.isEnabled = false
            } else {
                nextMenuItem.icon = getDrawable(R.drawable.ic_baseline_arrow_forward_24)
                nextMenuItem.isEnabled = true
            }
        }
        if (prevMenuItem != null) {
            if (notePosition <= 0) {
                prevMenuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                prevMenuItem.isEnabled = false
            } else {
                prevMenuItem.icon = getDrawable(R.drawable.ic_baseline_arrow_back_24)
                prevMenuItem.isEnabled = true
            }
        }

        // textName.text = p?.name ?: "XX" XX - default value
        return super.onPrepareOptionsMenu(menu)
    }
}
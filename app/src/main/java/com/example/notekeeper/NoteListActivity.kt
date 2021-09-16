package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeeper.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        binding.fab.setOnClickListener { view ->
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

        binding.editView.listItemsR.layoutManager = LinearLayoutManager(this)

        binding.editView.listItemsR.adapter = NoteRecyclerAdapter(this, DataManager.notes)

//        binding.editView.listNotes.adapter = ArrayAdapter<NoteInfo>(this,
//            android.R.layout.simple_list_item_1,
//            DataManager.notes
//        )
//
//        binding.editView.listNotes.setOnItemClickListener{parent, view, position, id ->
//            val activityIntent = Intent(this, MainActivity::class.java)
//            activityIntent.putExtra(NOTE_POSITION, position)
//            startActivity(activityIntent)
//        }

    }

    override fun onResume() {
        super.onResume()
        binding.editView.listItemsR.adapter?.notifyDataSetChanged()
//        (binding.editView.listItems.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }

}
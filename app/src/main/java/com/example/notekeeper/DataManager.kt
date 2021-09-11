package com.example.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    // initializer block
    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {

        var course = CourseInfo("android_intents", "Android programming with intents")
        courses.set(course.courseId, course)

        course = CourseInfo(courseId = "android_async", title = "Android Async programming and Services")
        courses[course.courseId] = course

    }

    private fun initializeNotes() {
        notes.add(NoteInfo(courses.values.toList()[0], title = "My first note", text = "Some note text"))
        notes.add(NoteInfo(courses.values.toList()[0], title = "My second note", text = "Some different note text"))
        notes.add(NoteInfo(courses.values.toList()[1], title = "My third note", text = "Some another different note text"))

    }

}
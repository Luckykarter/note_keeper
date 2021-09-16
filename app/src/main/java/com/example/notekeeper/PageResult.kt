package com.example.notekeeper

class PageResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Customer>
)
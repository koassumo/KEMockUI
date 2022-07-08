package com.example.kemockui.model.repository

import com.example.kemockui.entities.ItemHomework
import com.example.kemockui.entities.ItemLesson

object RepositoryRv {
    private val listLessons: List<ItemLesson> = listOf(
        ItemLesson("Literature", "09:00", "12:00", false),
        ItemLesson("Biology", "12:00", "16:00", true),
        ItemLesson("English", "16:00", "21:00", false),
        ItemLesson("History", "21:00", "23:00", false),
    )

    private val listHomeworks: List<ItemHomework> = listOf(
        ItemHomework("Literature", "09:00", "any text"),
        ItemHomework("Biology", "12:00", "any text"),
        ItemHomework("English", "16:00", "any text"),
    )

    fun getListRvLessons() = listLessons
    fun getListRvHomework() = listHomeworks

}
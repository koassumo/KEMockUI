package com.example.kemockui.model.repository

import com.example.kemockui.entities.ItemRv

object RepositoryRvHomework {
    private val listRvHomework: List<ItemRv> = listOf(
        ItemRv("Literature", "any text"),
        ItemRv("Biology", "any text"),
        ItemRv("English", "any text"),
    )

    fun getListRvHomework() = listRvHomework

}
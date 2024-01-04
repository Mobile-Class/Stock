package com.example.myapplication.domain.model

class News (
    val title: String?,
    val url: String?,
    val time_published: String?,
    val authors: List<String>?,
    val summary: String?,
    val banner_image: String?,
    val source: String?,
)
{
    companion object {
        private fun searchNewsInfo(searchTerm: String, fulltext: String): Int{
            return fulltext.indexOf(searchTerm, ignoreCase = true)
        }
    }

    fun searchFor(searchTerm: String):Boolean {
        val searchTitle = searchNewsInfo(searchTerm,title!!)
        val searchSummary = searchNewsInfo(searchTerm,summary!!)
        return searchTitle != -1 || searchSummary != -1
    }

}


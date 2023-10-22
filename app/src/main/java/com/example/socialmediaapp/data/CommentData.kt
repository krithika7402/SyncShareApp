package com.example.socialmediaapp.data

data class CommentData(
    val commentId: String? = null,
    val postId: String? = null,
    val username: String? = null,
    val text: String? = null,
    val timestamp: Long? = null
)
package com.example.major.ContentPosting

import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository: MongoRepository<Post, String> {
    fun findPostsByAuthorIdOrderByTimeOfPostingDesc(authorId: String): List<Post>
    fun findPostsByAuthorIdNotLike(id: String): List<Post>
}
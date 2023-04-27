package com.example.major.ContentPosting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping(path = ["content"])
@CrossOrigin(origins = ["http://localhost:3000"])
class PostController @Autowired constructor(var postService: PostService){
    @PostMapping(path = ["addPost"])
    fun addPost(@RequestBody post:Post) {
        post.timeOfPosting = System.currentTimeMillis()
        post.id = UUID.randomUUID().toString()
        postService.addPost(post)
    }

    @GetMapping(path = ["getUserPosts/{id}"])
    fun getUserPosts(@PathVariable("id") authorId: String): List<Post> {
        return postService.getUserPosts(authorId)
    }

    @GetMapping(path = ["getRelatedPosts/{id}"])
    fun getRelatedPosts(@PathVariable("id") id: String): List<Post> {
        return postService.getRelatedPosts(id)
    }

    @GetMapping(path = ["getAllPosts"])
    fun getAllPosts(): List<Post> {
        return postService.getAllPosts()
    }
}
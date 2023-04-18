package com.example.major.ContentPosting

import com.example.major.NetworkData.AuthorNetworkNode
import com.example.major.RegisteredUsers.RegistrationService
import com.example.major.SNAConcept.SNAService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class PostService @Autowired constructor(var postRepository: PostRepository, var snaService: SNAService, var registrationService: RegistrationService) {
    fun addPost(post: Post) {
        var user = registrationService.getUserById(post.authorId)
        post.username = user.username
        post.organization = user.organization
        postRepository.save(post)
        println(post.id)
        println(post.content)
        println(post.authorId)
        println(post.timeOfPosting)
        println(post.username)
    }

    fun getUserPosts(authorId: String): List<Post> {
        return postRepository.findPostsByAuthorIdOrderByTimeOfPostingDesc(authorId)
    }

    fun getRelatedPosts(userId: String): List<Post> {
        var posts:MutableList<Post> = postRepository.findPostsByAuthorIdNotLike(userId).toMutableList()
        var idToSimRank: HashMap<String, Double> = hashMapOf()
        var idToKCore: HashMap<String, Int> = hashMapOf()

        var userKCore = registrationService.getKCoreForUser(userId)

        posts.forEach { it ->
            if(!idToSimRank.containsKey(it.authorId)) {
                println(userId)
                println(it.authorId)
                idToSimRank[it.authorId] = snaService.getSimRankScoreForTwoUsers(userId, it.authorId)
                idToKCore[it.authorId] = abs(userKCore - registrationService.getKCoreForUser(it.authorId))
            }
        }

       return posts.sortedWith(compareBy({idToKCore[it.authorId]}, {1000000.0 - idToSimRank[it.authorId]!!}))
    }

    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }
}
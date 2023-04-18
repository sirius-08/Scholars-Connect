package com.example.major.ContentPosting

import org.bson.codecs.pojo.annotations.BsonProperty
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("posts")
class Post constructor(){
    @BsonProperty(value = "_id")
    var id:String = ""
    @BsonProperty(value = "timeOfPosting")
    var timeOfPosting:Long = 0L
    @BsonProperty(value = "authorId")
    var authorId: String = ""
    @BsonProperty(value = "content")
    var content: String = ""
    @BsonProperty
    var username: String = ""
    @BsonProperty
    var organization: String = ""

    constructor(id: String, timeOfPosting: Long, authorId: String, content: String, username: String, organization: String): this() {
        this.id = id
        this.timeOfPosting = timeOfPosting
        this.authorId = authorId
        this.content = content
        this.username = username
        this.organization = organization
    }

    constructor(authorId: String, content: String): this() {
        this.authorId = authorId
        this.content = content
        this.id = UUID.randomUUID().toString()
        this.timeOfPosting = System.currentTimeMillis()
    }
}
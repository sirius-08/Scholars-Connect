package com.example.major.OpeningsPosting

import com.example.major.RegisteredUsers.User
import org.bson.codecs.pojo.annotations.BsonProperty
import org.springframework.data.mongodb.core.mapping.Document

@Document("openings")
class Opening constructor(){
    @BsonProperty(value = "_id")
    var id: String = ""
    @BsonProperty(value = "authorId")
    var authorId: String = ""
    @BsonProperty(value = "username")
    var username: String = ""
    @BsonProperty(value = "organization")
    var organization: String = ""
    @BsonProperty(value = "timeOfPosting")
    var timeOfPosting: Long = 0
    @BsonProperty(value = "description")
    var description: String = ""
    @BsonProperty(value = "applicants")
    var applicants:MutableList<User> = mutableListOf()

    constructor(id: String, timeOfPosting: Long, authorId: String, description: String, username: String, organization: String, applicants: MutableList<User>): this() {
        this.id = id
        this.timeOfPosting = timeOfPosting
        this.authorId = authorId
        this.description = description
        this.username = username
        this.organization = organization
        this.applicants = applicants
    }
}
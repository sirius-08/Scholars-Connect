package com.example.major.OpeningsPosting

import com.example.major.RegisteredUsers.User
import com.fasterxml.jackson.annotation.JsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

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
    @BsonProperty(value = "title")
    var title: String = ""
    @BsonProperty(value = "duration")
    var duration: Duration = Duration()
    @BsonProperty(value = "mode")
    var mode:Mode = Mode.OFFLINE
    @BsonProperty("place_of_work")
    var placeOfWork: String = ""
    @BsonProperty("detailed_explanation")
    var detailedExplanation: String = ""
    @BsonProperty("preferences")
    var preferences: String = ""

//    @BsonProperty(value = "applicants")
//    var applicants:MutableList<User> = mutableListOf()

//    constructor(id: String, timeOfPosting: Long, authorId: String, description: String, username: String, organization: String, applicants: MutableList<User>): this() {
//        this.id = id
//        this.timeOfPosting = timeOfPosting
//        this.authorId = authorId
//        this.description = description
//        this.username = username
//        this.organization = organization
//        this.applicants = applicants
//    }

    @JsonCreator
    constructor(authorId: String, title: String, duration: Duration, mode: Mode, placeOfWork: String, detailedExplanation: String, preferences: String): this() {
        this.authorId = authorId
        this.title = title
        this.duration = duration
        this.mode = mode
        this.placeOfWork = placeOfWork
        this.detailedExplanation = detailedExplanation
        this.preferences = preferences
        this.id = UUID.randomUUID().toString()
        this.timeOfPosting = System.currentTimeMillis()
    }
}

data class Duration(var years:Int = 0, var months: Int = 0)
enum class Mode {
    OFFLINE, ONLINE, HYBRID
}
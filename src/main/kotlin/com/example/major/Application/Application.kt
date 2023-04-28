package com.example.major.Application

import com.fasterxml.jackson.annotation.JsonCreator
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.UUID

@Document("applications")
class Application constructor(){
    @Field(value = "_id")
    var id: String = ""
    @Field(value = "user_id")
    var userId:String = ""
    @Field(value = "opening_id")
    var openingId: String = ""
    @Field(value = "time_available_per_week")
    var timeAvailablePerWeek: Int = 0
    @Field(value = "motivation_for_applying")
    var motivationForApplying: String = ""
    @Field(value = "views_on_the_topic")
    var viewsOnTheTopic: String = ""
    @Field(value = "relevant_work")
    var relevantWork: String = ""
    @Field(value = "sim_rank_score")
    var simRankScore: Double = 0.0
    @Field(value = "application_status")
    var applicationStatus:ApplicationStatus = ApplicationStatus.UNDER_PROCESSING
    @Field(value = "opening_title")
    var openingTitle: String = ""
    @Field(value = "k_core_diff")
    var kCoreDiff: Int = 0
    @Field(value = "time_submitted")
    var timeSubmitted: Long = 0
    @Field(value = "username")
    var username: String = ""

    constructor(id: String, userId: String,openingId: String, timeAvailablePerWeek: Int, viewsOnTheTopic: String, relevantWork: String, simRankScore: Double, applicationStatus: ApplicationStatus,
        openingTitle: String, motivationForApplying: String, kCoreDiff: Int, timeSubmitted: Long, username: String): this() {
        this.id = id
        this.userId = userId
        this.openingId = openingId
        this.timeAvailablePerWeek = timeAvailablePerWeek
        this.viewsOnTheTopic = viewsOnTheTopic
        this.relevantWork = relevantWork
        this.simRankScore = simRankScore
        this.applicationStatus = applicationStatus
        this.openingTitle = openingTitle
        this.motivationForApplying = motivationForApplying
        this.kCoreDiff = kCoreDiff
        this.timeSubmitted = timeSubmitted
        this.username = username
        println("Constructor 2 called")
    }
    @JsonCreator
    constructor(userId: String,openingId: String, timeAvailablePerWeek: Int, viewsOnTheTopic: String, relevantWork: String,
                motivationForApplying: String): this() {
        this.id = UUID.randomUUID().toString()
        this.userId = userId
        this.openingId = openingId
        this.timeAvailablePerWeek = timeAvailablePerWeek
        this.viewsOnTheTopic = viewsOnTheTopic
        this.relevantWork = relevantWork
        //this.applicationStatus = ApplicationStatus.UNDER_PROCESSING
        //this.openingTitle = openingTitle
        this.motivationForApplying = motivationForApplying
        this.timeSubmitted = System.currentTimeMillis()
        println("Constructor called with opening Id " + this.openingId + " application id " + this.id)
    }
}

enum class ApplicationStatus{
    UNDER_PROCESSING, ACCEPTED, REJECTED
}
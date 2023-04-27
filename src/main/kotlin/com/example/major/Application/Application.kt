package com.example.major.Application

import com.fasterxml.jackson.annotation.JsonCreator
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("applications")
class Application constructor(){
    @BsonProperty(value = "_id")
    var id: String = ""
    @BsonProperty(value = "user_id")
    var userId:String = ""
    @BsonProperty(value = "opening_id")
    var openingId: String = ""
    @BsonProperty(value = "time_available_per_week")
    var timeAvailablePerWeek: Int = 0
    @BsonProperty(value = "motivation_for_applying")
    var motivationForApplying: String = ""
    @BsonProperty(value = "views_on_the_topic")
    var viewsOnTheTopic: String = ""
    @BsonProperty(value = "relevant_work")
    var relevantWork: String = ""
    @BsonProperty(value = "sim_rank_score")
    var simRankScore: Double = 0.0
    @BsonProperty(value = "application_status")
    var applicationStatus:ApplicationStatus = ApplicationStatus.UNDER_PROCESSING
    @BsonProperty(value = "opening_title")
    var openingTitle: String = ""

    @BsonCreator
    constructor(applicationId: String, userId: String,openingId: String, timeAvailablePerWeek: Int, viewsOnTheTopic: String, relevantWork: String, simRankScore: Double, applicationStatus: ApplicationStatus,
        openingTitle: String, motivationForApplying: String): this() {
        this.id = applicationId
        this.userId = userId
        this.openingId = openingId
        this.timeAvailablePerWeek = timeAvailablePerWeek
        this.viewsOnTheTopic = viewsOnTheTopic
        this.relevantWork = relevantWork
        this.simRankScore = simRankScore
        this.applicationStatus = applicationStatus
        this.openingTitle = openingTitle
        this.motivationForApplying = motivationForApplying
    }
    @JsonCreator
    constructor(userId: String,openingId: String, timeAvailablePerWeek: Int, viewsOnTheTopic: String, relevantWork: String, openingTitle: String,
                motivationForApplying: String): this() {
        this.id = UUID.randomUUID().toString()
        this.userId = userId
        this.openingId = openingId
        this.timeAvailablePerWeek = timeAvailablePerWeek
        this.viewsOnTheTopic = viewsOnTheTopic
        this.relevantWork = relevantWork
        this.applicationStatus = ApplicationStatus.UNDER_PROCESSING
        this.openingTitle = openingTitle
        this.motivationForApplying = motivationForApplying
        println("Constructor called with opening Id " + this.openingId + " application id " + this.id)
    }
}

enum class ApplicationStatus{
    UNDER_PROCESSING, ACCEPTED, REJECTED
}
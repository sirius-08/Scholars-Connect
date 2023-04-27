package com.example.major.Application

import com.fasterxml.jackson.annotation.JsonCreator
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("applications")
class Application constructor(){
    @BsonProperty("_id")
    var applicationId: String = ""
    @BsonProperty("user_id")
    var userId:String = ""
    @BsonProperty("opening_id")
    var openingId: String = ""
    @BsonProperty("time_available_per_week")
    var timeAvailablePerWeek: Int = 0
    @BsonProperty("motivation_for_applying")
    var motivationForApplying: String = ""
    @BsonProperty("views_on_the_topic")
    var viewsOnTheTopic: String = ""
    @BsonProperty("relevant_work")
    var relevantWork: String = ""
    @BsonProperty("sim_rank_score")
    var simRankScore: Double = 0.0

    @BsonCreator
    constructor(applicationId: String, userId: String,openingId: String, timeAvailablePerWeek: Int, viewsOnTheTopic: String, relevantWork: String, simRankScore: Double): this() {
        this.applicationId = applicationId
        this.userId = userId
        this.openingId = openingId
        this.timeAvailablePerWeek = timeAvailablePerWeek
        this.viewsOnTheTopic = viewsOnTheTopic
        this.relevantWork = relevantWork
        this.simRankScore = simRankScore
    }
    @JsonCreator
    constructor(userId: String,openingId: String, timeAvailablePerWeek: Int, viewsOnTheTopic: String, relevantWork: String): this() {
        this.applicationId = UUID.randomUUID().toString()
        this.userId = userId
        this.openingId = openingId
        this.timeAvailablePerWeek = timeAvailablePerWeek
        this.viewsOnTheTopic = viewsOnTheTopic
        this.relevantWork = relevantWork
    }
}
package com.example.major.NetworkData

import org.bson.codecs.pojo.annotations.BsonProperty
import org.springframework.data.mongodb.core.mapping.Document

@Document("networkMRectified")
class AuthorNetworkNode constructor() {
    //@Id
    @BsonProperty(value = "_id")
    var id: String = ""
    @BsonProperty(value = "name")
    var name: String = ""
    @BsonProperty(value = "coAuthors")
    var coAuthors: List<String> = listOf()

    constructor(id: String, name: String, coAuthors: List<String>): this() {
        this.id = id
        this.name = name
        this.coAuthors = coAuthors
    }

//    override fun toString(): String {
//        return ""
//    }
}



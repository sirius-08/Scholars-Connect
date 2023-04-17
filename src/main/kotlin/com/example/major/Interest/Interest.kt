package com.example.major.Interest

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table
class Interest (){
    @Id
    @SequenceGenerator(
        name = "interest_sequence",
        sequenceName = "interest_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "interest_sequence"
    )
    var id: Long = 0
    var interest: String = ""
    var userId: String = ""

    constructor(id: Long, interest: String, userId: String) : this() {
        this.id = id
        this.interest = interest
        this.userId = userId
    }

    constructor(interest: String, userId: String) : this() {
        this.interest = interest
        this.userId = userId
    }

    override fun toString(): String {
        return "Interests(id=$id, interest='$interest', userId=$userId)"
    }
}

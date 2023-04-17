package com.example.major.RegisteredUsers

import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "users")
class User constructor() {
    @Id
    @NotNull
//    @SequenceGenerator(
//        name = "user_sequence",
//        sequenceName = "user_sequence",
//        allocationSize = 1
//    )
//    @GeneratedValue(
//        strategy = GenerationType.SEQUENCE,
//        generator = "user_sequence"
//    )
    var id: String = ""
    var email:String = ""
    var username: String = ""
    var password: String = ""
    var organization: String = ""
    var department: String = ""
    var designation:String = ""

    constructor(id: String, email: String, password: String) : this() {
        this.id = id
        this.password = password
    }

//    constructor(email: String, password: String) : this() {
//        this.password = password
//    }

    constructor(username: String, password: String): this() {
        this.id = username
        this.password = password
        println("Constructor 2 called")
    }

    constructor(id: String, username: String, password: String, organization: String, department: String, email: String, designation: String) : this() {
        println("Constructor 3 called")
        this.username = username
        this.id = id
        this.email = email
        this.designation = designation
        this.password = password
        this.organization = organization
        this.department = department
    }

    constructor(username: String, password: String, organization: String, department: String, email: String, designation: String) : this() {
        println("Constructor 4 called")
        this.username = username
        this.password = password
        this.organization = organization
        this.department = department
        this.email = email
        this.designation = designation
    }

    override fun toString(): String {
        return "User(id=$id, username='$username', password ='$password')"
    }
}

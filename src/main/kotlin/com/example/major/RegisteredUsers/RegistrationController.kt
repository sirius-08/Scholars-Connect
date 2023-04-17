package com.example.major.RegisteredUsers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["user"])
class RegistrationController @Autowired constructor(var registrationService: RegistrationService) {

    @PostMapping(path = ["login"])
    fun loginUser(@RequestBody user: User): RegistrationLoginResponse {
        user.id  = user.username
        println("Login Request Received ${user.id} ${user.password}")
        return registrationService.loginUser(user)
    }
    @PostMapping(path = ["register"])
    fun registerUser(@RequestBody user: User): RegistrationLoginResponse {
        return registrationService.registerUser(user)
    }
//    @GetMapping(path = ["getUser/{username}"])
//    fun getUser(@PathVariable("username") username: String) : User {
//        return registrationService.getUser(username)
//    }
    @GetMapping(path = ["getUserById/{id}"])
    fun getUserById(@PathVariable("id") id: String) : User {
        return registrationService.getUserById(id)
    }
    @GetMapping(path = ["searchUserByUsername/{username}"])
    fun getUserByUsername(@PathVariable("username") username: String): List<User> {
        return registrationService.getUserByUsername(username)
    }
    @GetMapping(path = ["searchUserByUsername/{firstName}/{lastName}"])
    fun getUserByFirstNameAndLastName(@PathVariable("firstName") firstName: String, @PathVariable("lastName") lastName: String): List<User> {
        return registrationService.getUserByUsername((firstName + " " + lastName))
    }
}

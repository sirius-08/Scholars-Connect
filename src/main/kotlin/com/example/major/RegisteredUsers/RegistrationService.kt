package com.example.major.RegisteredUsers

import com.example.major.Networking.NetworkClient
import com.shapesecurity.salvation2.Values.Hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegistrationService @Autowired constructor(val registrationRepository: RegistrationRepository) {

    fun loginUser(user: User): RegistrationLoginResponse {
        println(user.email)
        println(user.password)
        println(user.username)
        var userById: Optional<User> = registrationRepository.findUserById(user.id)
        if (userById.isEmpty() || userById.get().password != user.password) {
            return RegistrationLoginResponse("Failure", "Invalid")
        } else
            return RegistrationLoginResponse("Success", userById.get().id)
    }

    fun registerUser(user: User): RegistrationLoginResponse {
        var userById: Optional<User> = registrationRepository.findUserById(user.id)
        if (userById.isPresent)
            return RegistrationLoginResponse("Failure", "Invalid")

        var registerdUser: User = registrationRepository.save(user)
        return RegistrationLoginResponse("Success", registerdUser.id)
    }

//    fun getUser(username: String): User {
//        var userByUsername: Optional<User> = registrationRepository.findUserByUsername(username)
//        if (userByUsername.isPresent)
//            return userByUsername.get()
//        else {
//            var tempUser = User()
//            tempUser.id = "Invalid"
//            return tempUser
//        }
//    }

    fun getUserById(id: String): User {
        var userById =  registrationRepository.findUserById(id)
        if(userById.isEmpty)
            return User()
        else
            return userById.get()
    }

    fun getUserByUsername(username: String): List<User> {
        return registrationRepository.findUsersByUsernameContainingIgnoreCase(username)
    }

    fun getKCoreForUser(id: String): Int {
        var user = registrationRepository.findUserById(id)
        if(user.isPresent)
            return user.get().kcore
        else
            return 0
    }
}

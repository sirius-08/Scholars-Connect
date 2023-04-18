package com.example.major.RegisteredUsers

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface RegistrationRepository: JpaRepository<User, String> {
    fun findUserByEmail(email: String): Optional<User>
    fun findUserByUsername(username: String): Optional<User>
    fun findUsersByDepartmentAndOrganization(department: String, organization: String): List<User>
    fun findUserById(id: String): Optional<User>
    fun findUsersByUsernameContainingIgnoreCase(username: String): List<User>
    fun findUsersByKcore(kcore: Int): List<User>
}
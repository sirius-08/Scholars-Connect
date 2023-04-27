package com.example.major.Application

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface ApplicationRepository: MongoRepository<Application, String> {
    fun getApplicationsByUserId(id: String): List<Application>
    fun getApplicationsByOpeningId(id: String): List<Application>
    fun getApplicationById(id: String): Optional<Application>
    fun getApplicationByUserIdAndOpeningId(userId: String, openingId: String): Optional<Application>
}
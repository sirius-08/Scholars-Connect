package com.example.major.OpeningsPosting

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface OpeningsRepository: MongoRepository<Opening, String> {
    fun findAllByAuthorId(authorId: String): List<Opening>
    fun findOpeningById(id: String): Optional<Opening>
}
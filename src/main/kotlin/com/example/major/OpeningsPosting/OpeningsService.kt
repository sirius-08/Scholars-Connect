package com.example.major.OpeningsPosting

import com.example.major.RegisteredUsers.RegistrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OpeningsService @Autowired constructor(var openingsRepository: OpeningsRepository,var registrationService: RegistrationService){
    fun postOpening(opening: Opening) {
        opening.id = UUID.randomUUID().toString()
        opening.timeOfPosting = System.currentTimeMillis()
        var user = registrationService.getUserById(opening.authorId)

        opening.username = user.username
        opening.organization = user.organization
        openingsRepository.save(opening)
    }

    fun getPostedOpenings(userId: String): List<Opening> {
        return openingsRepository.findAllByAuthorId(userId)
    }

    fun applyToOpening(id: String, userId: String) {
        var opening = openingsRepository.findOpeningById(id)
        if(opening.isPresent) {
            opening.get().applicants.add(registrationService.getUserById(userId))
            openingsRepository.save(opening.get())
        }
    }
}
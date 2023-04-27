package com.example.major.OpeningsPosting

import com.example.major.ContentPosting.Post
import com.example.major.RegisteredUsers.RegistrationService
import com.example.major.RegisteredUsers.User
import com.example.major.SNAConcept.SNAService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.math.abs

@Service
class OpeningsService @Autowired constructor(var openingsRepository: OpeningsRepository,var registrationService: RegistrationService, var snaService: SNAService){
    fun postOpening(opening: Opening): Opening {
        var user = registrationService.getUserById(opening.authorId)

        opening.username = user.username
        opening.organization = user.organization
        return openingsRepository.save(opening)
    }

    fun getPostedOpenings(userId: String): List<Opening> {
        return openingsRepository.findAllByAuthorId(userId)
    }


    fun getIdOfOwner(openingId: String): String {
        return openingsRepository.findOpeningById(openingId).get().authorId
    }

    fun getAvailableOpenings(userId: String): List<Opening> {
        var openings:MutableList<Opening> = openingsRepository.findOpeningsByAuthorIdNotLike(userId).toMutableList()
        val idToSimRank: HashMap<String, Double> = hashMapOf()
        val idToKCore: HashMap<String, Int> = hashMapOf()

        val userKCore = registrationService.getKCoreForUser(userId)

        openings.forEach { it ->
            if(!idToSimRank.containsKey(it.authorId)) {
                println(userId)
                println(it.authorId)
                idToSimRank[it.authorId] = snaService.getSimRankScoreForTwoUsers(userId, it.authorId)
                idToKCore[it.authorId] = abs(userKCore - registrationService.getKCoreForUser(it.authorId))
            }
        }

        return openings.sortedWith(compareBy({idToKCore[it.authorId]}, {1000000.0 - idToSimRank[it.authorId]!!}))
    }

//    fun getApplicants(id: String, userId: String): List<User> {
//        var applicants = openingsRepository.findOpeningById(id).get().applicants.toMutableList()
//
//        var idToSimRank: HashMap<String, Double> = hashMapOf()
//        var idToKCore: HashMap<String, Int> = hashMapOf()
//
//        var userKCore = registrationService.getKCoreForUser(userId)
//
//        applicants.forEach { it ->
//            if(!idToSimRank.containsKey(it.id)) {
//                idToSimRank[it.id] = snaService.getSimRankScoreForTwoUsers(userId, it.id)
//                idToKCore[it.id] = abs(userKCore - registrationService.getKCoreForUser(it.id))
//            }
//        }
//
//        return applicants.sortedWith(compareBy({idToKCore[it.id]}, {1000000.0 - idToSimRank[it.id]!!}))
//    }
}
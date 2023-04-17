package com.example.major.Interest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InterestsService @Autowired constructor(val interestsRepository: InterestsRepository) {
    fun saveInterests(interestRequest: InterestRequest) {
        interestRequest.interests.forEach {
            interestsRepository.save(Interest(it, interestRequest.id))
        }
    }

    fun getInterests(id: String): List<Interest> {
        return interestsRepository.findAllByUserId(id)
    }
}

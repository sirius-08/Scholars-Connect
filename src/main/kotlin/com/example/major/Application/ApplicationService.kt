package com.example.major.Application

import com.example.major.OpeningsPosting.OpeningsService
import com.example.major.SNAConcept.SNAService
import org.apache.xpath.operations.Bool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin

@Service
@CrossOrigin()
class ApplicationService @Autowired constructor(var applicationRepository: ApplicationRepository, var openingsService: OpeningsService, var snaService: SNAService){
    fun getUserApplications(id: String): List<Application> {
        return applicationRepository.getApplicationsByUserId(id)
    }

    fun getOpeningApplications(id: String): List<Application> {
        return applicationRepository.getApplicationsByOpeningId(id)
    }

    fun applyToOpening(application: Application): Application {
        var ownerId: String = openingsService.getIdOfOwner(application.openingId)
        application.simRankScore = snaService.getSimRankScoreForTwoUsers(application.userId, ownerId)
        return applicationRepository.save(application)
    }

    fun acceptOpening(id: String): Application{
        var application = applicationRepository.getApplicationById(id).get()
        application.applicationStatus = ApplicationStatus.ACCEPTED
        return applicationRepository.save(application)
    }

    fun rejectOpening(id: String): Application{
        var application = applicationRepository.getApplicationById(id).get()
        application.applicationStatus = ApplicationStatus.REJECTED
        return applicationRepository.save(application)
    }

    fun editOpening(application: Application): Application {
        println(application.id)
        return applicationRepository.save(application)
    }

    fun checkApplicationExists(userId: String, openingId: String): Boolean {
        return applicationRepository.getApplicationByUserIdAndOpeningId(userId, openingId).isPresent
    }
}
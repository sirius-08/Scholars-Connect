package com.example.major.Application

import com.example.major.CitationsAndPapers.CitationsService
import com.example.major.CitationsAndPapers.ExcelData
import com.example.major.OpeningsPosting.OpeningsService
import com.example.major.RegisteredUsers.RegistrationService
import com.example.major.SNAConcept.SNAService
import org.apache.xpath.operations.Bool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin
import kotlin.math.abs

@Service
@CrossOrigin()
class ApplicationService @Autowired constructor(var applicationRepository: ApplicationRepository, var openingsService: OpeningsService, var snaService: SNAService,
                                                var registrationService: RegistrationService, var citationsService: CitationsService){
    fun getUserApplications(id: String): List<Application> {
        return applicationRepository.getApplicationsByUserId(id)
    }

    fun getOpeningApplications(id: String): List<Application> {
        var applications = applicationRepository.getApplicationsByOpeningId(id).toMutableList()
        return applications.sortedWith(compareBy({it.kCoreDiff}, {1000000.0 - it.simRankScore}))
    }

    fun applyToOpening(application: Application): Application {
        var ownerId: String = openingsService.getIdOfOwner(application.openingId)
        application.simRankScore = snaService.getSimRankScoreForTwoUsers(application.userId, ownerId)
        application.kCoreDiff = abs(registrationService.getKCoreForUser(application.userId) - registrationService.getKCoreForUser(ownerId))
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

    fun getApplicantsCitationsData(openingId: String): List<ExcelData> {
        var applications = applicationRepository.getApplicationsByOpeningId(openingId)
        var excelData: MutableList<ExcelData> = mutableListOf()
        applications.forEach {
            excelData.add(citationsService.getExcelDataForUser(it.userId))
        }
        return excelData
    }
}
package com.example.major.Application

import com.example.major.CitationsAndPapers.ExcelData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["applications"])
class ApplicationController @Autowired constructor(var applicationService: ApplicationService) {
    @GetMapping("userApplicationsAll/{id}")
    fun getUserApplications(@PathVariable("id")id: String): List<Application> {
        return applicationService.getUserApplications(id)
    }

    @GetMapping("openingApplicationsAll/{id}")
    fun getOpeningApplications(@PathVariable("id")id: String): List<Application> {
        return applicationService.getOpeningApplications(id)
    }

    @PostMapping("applyToOpening")
    fun applyToOpening(@RequestBody application: Application): Application {
        return applicationService.applyToOpening(application)
    }

    @GetMapping("acceptApplication/{id}")
    fun acceptOpening(@PathVariable("id") id: String): Application {
        return applicationService.acceptOpening(id)
    }

    @GetMapping("rejectApplication/{id}")
    fun rejectOpening(@PathVariable("id") id: String):  Application {
        return applicationService.rejectOpening(id)
    }

    @PostMapping("editApplication")
    fun editOpening(@RequestBody application: Application): Application {
        println(application.id)
        return applicationService.editOpening(application)
    }

    @GetMapping("checkApplicationExists")
    fun checkApplicationExists(@RequestParam userId: String, @RequestParam openingId: String): Boolean {
        return applicationService.checkApplicationExists(userId, openingId)
    }

    @GetMapping("applicantsCitationsData/{openingId}")
    fun getApplicantsCitationsData(@PathVariable("openingId") openingId: String): List<ExcelData> {
        return applicationService.getApplicantsCitationsData(openingId)
    }
}
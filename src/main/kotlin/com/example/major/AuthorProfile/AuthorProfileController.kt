package com.example.major.AuthorProfile

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["authorProfile"])
class AuthorProfileController @Autowired constructor(var authorProfileService: AuthorProfileService){
    @GetMapping(path = ["basicInfo/{id}"])
    fun getBasicInfo(@PathVariable("id") id: String): BasicProfileInfo {
        return authorProfileService.getBasicProfileInfo(id)
    }
    @GetMapping(path = ["publications/{id}"])
    fun getPublications(@PathVariable("id") id: String): List<Publication> {
        return authorProfileService.getPublications(id)
    }
}
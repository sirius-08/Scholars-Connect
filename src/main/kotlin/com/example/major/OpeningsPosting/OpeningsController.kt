package com.example.major.OpeningsPosting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import retrofit2.http.Path

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["openings"])
class OpeningsController @Autowired constructor(var openingsService: OpeningsService){
    @PostMapping(path = ["postOpening"])
    fun postOpening(@RequestBody opening: Opening): Opening {
        return openingsService.postOpening(opening)
    }

    @GetMapping(path = ["getUserOpenings/{userId}"])
    fun getUserOpenings(@PathVariable("userId") userId: String): List<Opening> {
        return openingsService.getPostedOpenings(userId)
    }

    @GetMapping(path = ["viewOpenings/{userId}"])
    fun getRelatedOpenings(@PathVariable("userId") userId: String): List<Opening> {
        return openingsService.getAvailableOpenings(userId)
    }

}
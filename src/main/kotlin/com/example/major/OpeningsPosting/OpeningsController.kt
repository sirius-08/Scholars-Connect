package com.example.major.OpeningsPosting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["openings"])
class OpeningsController @Autowired constructor(var openingsService: OpeningsService){
    @PostMapping(path = ["postOpening"])
    fun postOpening(@RequestBody opening: Opening) {
        openingsService.postOpening(opening)
    }

    @GetMapping(path = ["getUserOpenings/{userId}"])
    fun getUserOpenings(@PathVariable("userId") userId: String): List<Opening> {
        return openingsService.getPostedOpenings(userId)
    }

    @PostMapping(path = ["applyToOpening/{id}/{userId}"])
    fun applyToOpening(@PathVariable("id") id: String, @PathVariable("userId") userId: String) {
        openingsService.applyToOpening(id, userId)
    }
}
package com.example.major.Interest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["interests"])

class InterestsController @Autowired constructor(val interestsService: InterestsService){
    @PostMapping(path = ["add"])
    fun addInterests(@RequestBody interestRequest: InterestRequest) {
        interestsService.saveInterests(interestRequest)
    }

    @GetMapping(path = ["get/{id}"])
    fun getInterests(@PathVariable("id") id: String): List<Interest> {
        return interestsService.getInterests(id)
    }
}
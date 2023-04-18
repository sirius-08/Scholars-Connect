package com.example.major.SNAConcept

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["SNA"])
class SNAController @Autowired constructor(val snaService: SNAService){
    @GetMapping(path = ["getCollaborators/{id}"])
    fun getCollaborators(@PathVariable("id") id: String): List<CollaboratorResult> {
        return snaService.getCollaborators(id).subList(0,5)
    }

    @GetMapping(path = ["getInfluentialAuthors"])
    fun getInfluentialAuthors(): List<CollaboratorResult> {
        return snaService.getInfluentialAuthors()
    }

    @GetMapping(path = ["getSimRankScore/{id1}/{id2}"])
    fun getSimRankScore(@PathVariable("id1") id1: String, @PathVariable("id2") id2: String): Double {
        return snaService.getSimRankScoreForTwoUsers(id1, id2)
    }
}
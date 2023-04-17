package com.example.major.NetworkData

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["networkData"])
class NetworkDataController @Autowired constructor(val networkDataService: NetworkDataService){
    @GetMapping(path = ["getCoAuthorIds/{id}"])
    fun getCoAuthorIds(@PathVariable("id") id: String): AuthorNetworkNode {
        return networkDataService.getCoAuthorIds(id)
    }
}
package com.example.major.NetworkData

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NetworkDataService @Autowired constructor(val networkDataRepository: NetworkDataRepository) {
    fun getCoAuthorIds(id: String): AuthorNetworkNode {
        var tempNode = networkDataRepository.findAuthorNetworkNodeById(id)
        if(tempNode.isPresent)
            return tempNode.get()
        else
            return AuthorNetworkNode("","", listOf())
        //return networkDataRepository.findAll()[0]
    }

    fun getAllAuthors(): List<AuthorNetworkNode> {
        return networkDataRepository.findAll()
    }
}
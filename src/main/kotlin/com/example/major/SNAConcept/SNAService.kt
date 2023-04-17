package com.example.major.SNAConcept

import com.example.major.NetworkData.NetworkDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SNAService @Autowired constructor(val networkDataService: NetworkDataService){

    fun getCollaborators(id: String): List<CollaboratorResult> {
        var G = networkDataService.getAllAuthors()
        var index = 0
        G.forEachIndexed { i, authorNetworkNode ->
            if(authorNetworkNode.id == id)
                index = i
        }
        return getDICNScores(G, index)
    }

    fun getInfluentialAuthors(): List<CollaboratorResult> {
        var G = networkDataService.getAllAuthors()
        var result = findEigenCentrality(G)
        return result.subList(0, 20)
    }
}
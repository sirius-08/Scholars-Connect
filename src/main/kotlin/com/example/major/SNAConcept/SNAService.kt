package com.example.major.SNAConcept

import com.example.major.NetworkData.AuthorNetworkNode
import com.example.major.NetworkData.NetworkDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
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

    fun getSimRankScoreForTwoUsers(id1: String, id2: String): Double {
        var G = networkDataService.getAllAuthors()
        var index2 = G.indexOfFirst { it -> it.id == id2 }
        println(id2)
        println(index2)
        return getSimRankScoresForUser(id1)[index2]
    }

    fun getSimRankScoresForUser(id: String): DoubleArray {
        var G = networkDataService.getAllAuthors()
        var src = G.indexOfFirst { it -> it.id == id }
        return getMatrixSK(0.6, 100, G, src).toDoubleVector()
    }

    fun getGraph(): List<AuthorNetworkNode> {
        return networkDataService.getAllAuthors()
    }
}
package com.example.major.NetworkData

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface NetworkDataRepository: MongoRepository<AuthorNetworkNode,String> {
    fun findAuthorNetworkNodeById(id: String): Optional<AuthorNetworkNode>
}
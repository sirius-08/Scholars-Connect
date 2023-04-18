package com.example.major.SNATests

import com.example.major.NetworkData.AuthorNetworkNode
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertTrue


@SpringBootTest
class SimRankTests {
    var G: List<AuthorNetworkNode> = listOf(
        AuthorNetworkNode("a", "a", listOf("b", "d", "e")) ,
        AuthorNetworkNode("b", "b", listOf("c", "f", "g", "i")),
        AuthorNetworkNode("c", "c", listOf()),
        AuthorNetworkNode("d", "d", listOf("c", "g", "i")),
        AuthorNetworkNode("e", "e", listOf("h", "i")),
        AuthorNetworkNode("f", "f", listOf("d")),
        AuthorNetworkNode("g", "g", listOf()),
        AuthorNetworkNode("h", "h", listOf()),
        AuthorNetworkNode("i", "i", listOf())
    )

    @Test
    fun getMatrixMTest() {

    }
}
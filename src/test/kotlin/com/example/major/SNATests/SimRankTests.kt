package com.example.major.SNATests

import com.example.major.NetworkData.AuthorNetworkNode
import com.example.major.SNAConcept.getMatrixM
import com.example.major.SNAConcept.getMatrixQ
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.nd4j.linalg.api.ndarray.INDArray


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

    var K = 3
    var C = 0.6
    var src = 4

//    @Test
//    fun getMatrixMTest() {
//        var M = getMatrixM(C, src, K, getMatrixQ(G))
//        var idealValue: INDArray = [[0.9], [0.0]]
//    }
}
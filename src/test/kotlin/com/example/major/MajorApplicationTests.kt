package com.example.major

import com.example.major.NetworkData.AuthorNetworkNode
import com.example.major.SNAConcept.getMatrixM
import com.example.major.SNAConcept.getMatrixQ
import com.example.major.SNAConcept.getMatrixU
import org.junit.jupiter.api.Test
import org.nd4j.linalg.factory.Nd4j
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MajorApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun getMatrixQTest() {
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

		var Q = Nd4j.zeros(2, 2)
		Q.put(0, 0, 1)
		println(Q)
	}

	@Test
	fun getMatrixMTest() {
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

		var M = com.example.major.SNAConcept.getMatrixM(0.6, 4, 3, getMatrixQ(G))
		println(M[1][4])
	}

	@Test
	fun getMatrixUTest() {
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
		var Q = getMatrixQ(G)
		var U = getMatrixU(0.6, getMatrixM(0.6, 4, 3, Q), 3, Q)
		println(U[3])
	}
}

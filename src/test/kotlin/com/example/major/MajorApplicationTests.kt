package com.example.major

import com.example.major.NetworkData.AuthorNetworkNode
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MajorApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun getMatrixQ() {
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


	}

}

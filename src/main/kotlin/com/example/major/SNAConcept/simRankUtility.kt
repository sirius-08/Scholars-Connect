package com.example.major.SNAConcept

import com.example.major.NetworkData.AuthorNetworkNode
import jdk.internal.org.jline.utils.Colors.s

fun getMatrixQ(G: List<AuthorNetworkNode>): Array<DoubleArray> {
    val stringIdToIntId: HashMap<String, Int> = HashMap<String, Int> ()
    G.forEachIndexed{ index, it ->
        stringIdToIntId.put(it.id, index)
    }

    val V:Int = G.size
    var Q = Array(V) {i -> DoubleArray(V) {j -> 0.0} }

    G.forEachIndexed { index, authorNetworkNode ->
        authorNetworkNode.coAuthors.forEach { it ->
            if (stringIdToIntId.containsKey(it)) {
                Q[stringIdToIntId[it]!!][index] = 1.0 / G[stringIdToIntId[it]!!].coAuthors.size
            }
        }
    }

    return Q
}
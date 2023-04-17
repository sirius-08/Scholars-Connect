package com.example.major.SNAConcept

import com.example.major.NetworkData.AuthorNetworkNode
import java.lang.Math.sqrt
import kotlin.math.sqrt

fun findEigenCentrality(G: List<AuthorNetworkNode>): MutableList<CollaboratorResult> {
    var map: HashMap<String, Int> = hashMapOf<String, Int>()
    G.forEachIndexed { index, authorNetworkNode ->
        map.put(authorNetworkNode.id, index)
    }

    var X: MutableList<Double> = mutableListOf()
    G.forEach {
        X.add(it.coAuthors.size.toDouble())
    }

    repeat(100) {
        X = calculateX(G, X, map)
        X = normalize(X)
    }

    var result: MutableList<CollaboratorResult> = mutableListOf()
    G.forEachIndexed { index, authorNetworkNode ->
        result.add(CollaboratorResult(X[index], authorNetworkNode.name, authorNetworkNode.id))
    }

    result = result.sortedWith(compareBy({it.score}, {it.name})).asReversed().toMutableList()
    return result
}

fun calculateX(G: List<AuthorNetworkNode>, X: List<Double>, map: HashMap<String, Int>): MutableList<Double> {
    var Y: MutableList<Double> = X.toMutableList()
    Y.forEachIndexed { index, fl ->
        G[index].coAuthors.forEach {
            if (map.contains(it)) {
                Y[index] += X[map[it]!!]
            }
        }
    }
    return Y
}

fun normalize(X: List<Double>) : MutableList<Double> {
    var sum: Double = 0.0
    X.forEach {
        sum += it*it
    }

    sum = kotlin.math.sqrt(sum)

    var Y: MutableList<Double> = X.map { it/sum }.toMutableList()
    return Y
}
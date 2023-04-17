package com.example.major.SNAConcept

import com.example.major.NetworkData.AuthorNetworkNode
import kotlin.math.sqrt

fun getDICNScores(G: List<AuthorNetworkNode>, V: Int) : List<CollaboratorResult> {
    var NMatrix: MutableList<List<Double>> = mutableListOf()
    var UMatrix: MutableList<List<Int>> = mutableListOf()

    var neighbors:HashSet<String> = hashSetOf()
    G[V].coAuthors.forEach { neighbors.add(it) }

    G.forEachIndexed { index, authorNetworkNode ->
        NMatrix.add(getNeighborhoodVector(G, index))
    }

    NMatrix.forEach {
        UMatrix.add(getUnionNeighborHoodSet(it,NMatrix[V]))
    }

    var scores:MutableList<CollaboratorResult> = mutableListOf()
    G.forEachIndexed { index, authorNetworkNode ->
        if(index != V) {
            var coeff = calculatePearsonCorrelationCoefficient(NMatrix[index], NMatrix[V], UMatrix[index])
            var CN = NMatrix[V][index]

            if(!neighbors.contains(authorNetworkNode.id)) {
                var  score = (1F + CN) * (1F + coeff)
                if(!score.isNaN())
                    scores.add(CollaboratorResult(score, authorNetworkNode.name, authorNetworkNode.id))
            }
        }
    }

    var sortedScores = scores.sortedWith(compareBy({it.score}, {it.name})).asReversed()

    return sortedScores
}

fun getNeighborhoodVector(G: List<AuthorNetworkNode>, V: Int): List<Double> {
    var neighbors: HashSet<String> = hashSetOf()
    G[V].coAuthors.forEach { neighbors.add(it) }

    var N: MutableList<Double> = mutableListOf()

    G.forEach { it ->
        var cnt: Double = 0.0
        if(it.id != G[V].id) {
            it.coAuthors.forEach { a ->
                if(neighbors.contains(a))
                    cnt += 1F
            }
            if(neighbors.contains(it.id))
                cnt += 1F
        }
        else
            cnt = neighbors.size.toDouble()
        N.add(cnt)
    }

    return N
}

fun getUnionNeighborHoodSet(a: List<Double>,b : List<Double>): List<Int> {
    var U:MutableList<Int> = mutableListOf()
    a.zip(b).forEachIndexed { index, pair ->
        if(pair.first > 0F || pair.second > 0F)
            U.add(index)
    }
    return U
}

fun getMeanUnionNeighborHood(N: List<Double>, U:List<Int>): Double {
    var sum:Double = 0.0
    U.forEach {
        sum += N[it]
    }
    return sum/U.size.toDouble()
}

fun calculateDenominatorTerm(N: List<Double>, U: List<Int>, NMean: Double): Double {
    var sum: Double = 0.0
    U.forEach {
        sum += (N[it] - NMean) * (N[it] - NMean)
    }
    return sum
}

fun calculatePearsonCorrelationCoefficient(N1: List<Double>, N2: List<Double>, U: List<Int>): Double {
    var N1Mean = getMeanUnionNeighborHood(N1, U)
    var N2Mean = getMeanUnionNeighborHood(N2, U)

    var den1 = calculateDenominatorTerm(N1, U, N1Mean)
    var den2 = calculateDenominatorTerm(N2, U, N2Mean)

    var den = den1 * den2
    var num: Double = 0.0

    U.forEach {
        num += (N1[it] - N1Mean) * (N2[it] - N2Mean)
    }

    var coeff = num/den
    return coeff
}
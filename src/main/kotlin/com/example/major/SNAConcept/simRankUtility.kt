package com.example.major.SNAConcept

import com.example.major.NetworkData.AuthorNetworkNode
import org.nd4j.linalg.api.ndarray.INDArray
//import jdk.internal.org.jline.utils.Colors.s
import org.nd4j.linalg.factory.Nd4j

fun getMatrixQ(G: List<AuthorNetworkNode>): INDArray{
    val stringIdToIntId: HashMap<String, Int> = HashMap<String, Int> ()
    G.forEachIndexed{ index, it ->
        stringIdToIntId.put(it.id, index)
    }

    val V:Int = G.size
    //var Q = Array(V) {i -> DoubleArray(V) {j -> 0.0} }
    var Q = Nd4j.zeros(V, V)
    var inDegree = Array(G.size) {i -> 0}

    G.forEach { it ->
        it.coAuthors.forEach { coAuthor ->
            inDegree[stringIdToIntId[coAuthor]!!] += 1
        }
    }

    G.forEachIndexed { index, authorNetworkNode ->
        authorNetworkNode.coAuthors.forEach { it ->
            if (stringIdToIntId.containsKey(it)) {
                //Q[stringIdToIntId[it]!!][index] = 1.0 / G[stringIdToIntId[it]!!].coAuthors.size
                Q.put(stringIdToIntId[it]!!, index, 1.0 / inDegree[stringIdToIntId[it]!!])
            }
        }
    }

    return Q
}

fun getMatrixM(C: Double, src: Int, K: Int, Q:INDArray): List<List<INDArray>> {
    val q =  Nd4j.zeros(Q.shape()[0], 1)
    q.put(src, 0, 1)

//    println(q)

    var M: MutableList<MutableList<INDArray>> = mutableListOf()
    var QTranspose = Q.transpose()

//    println(QTranspose)

    for(j in 0..K) {
        M.add(mutableListOf())
        for (i in 0 .. j)
            M[j].add(Nd4j.zeros(Q.shape()[0], 1))
        for(i in j + 1..K + 1) {

            var term1 = QTranspose.mmul(M[j][i - 1]).mul(C/2.0)
            if(j == 0)
                M[j].add(term1.add(q))
            else
                M[j].add(term1.add(M[j - 1][i - 1]))
        }
    }

    return M
}

fun getMatrixU(C: Double, M: List<List<INDArray>>, K: Int, Q: INDArray): List<INDArray> {
    var U = mutableListOf<INDArray>(M[K][K + 1])
    for(i in 1..K) {
        U.add(M[K - i][K + 1].add(Q.mmul(U[i - 1]).mul(C/2.0)))
    }

    return U
}

fun getMatrixSK(C:Double, K:Int, G: List<AuthorNetworkNode>, src: Int): INDArray {
    var Q = getMatrixQ(G)
    var U = getMatrixU(C, getMatrixM(C, src, K, Q), K, Q)
    return U[K].mul(1.0 - C)
}


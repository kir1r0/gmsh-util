package com.romanov.gmsh

import com.romanov.gmsh.model.Node
import com.romanov.gmsh.model.ParsedElement
import com.romanov.gmsh.util.VectorUtil.findVectors
import com.romanov.gmsh.util.VectorUtil.mixedVectorProduct

private const val COEFFICIENT = 1.0 / 6.0

class VolumeCalculator {

    fun calculateVolume(parsedElement: ParsedElement): Double {
        var sumAllVolumes = 0.0
        parsedElement.elements.forEach {
            val nodes = mutableListOf<Node>()
            it.nodes.forEach { elemNode ->
                nodes.add(parsedElement.nodes.find { node -> node.id == elemNode }
                    ?: throw IllegalArgumentException("Node with id: $it not found!"))
            }
            val vectors = findVectors(nodes)
            val tetrahedronVolume = COEFFICIENT * mixedVectorProduct(vectors)
            sumAllVolumes += tetrahedronVolume
        }
        return sumAllVolumes
    }
}


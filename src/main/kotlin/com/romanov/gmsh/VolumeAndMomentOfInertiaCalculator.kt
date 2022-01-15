package com.romanov.gmsh

import com.romanov.gmsh.model.Node
import com.romanov.gmsh.model.ParsedElement
import com.romanov.gmsh.model.VolumeAndInertiaMoment
import com.romanov.gmsh.util.Constant.NODE_OF_INERTIA
import com.romanov.gmsh.util.Constant.VOLUME_COEFFICIENT
import com.romanov.gmsh.util.Constant.STEEL_DENSITY
import com.romanov.gmsh.util.VectorUtil.findTetrahedronCenter
import com.romanov.gmsh.util.VectorUtil.findVectors
import com.romanov.gmsh.util.VectorUtil.mixedVectorProduct
import kotlin.math.sqrt


class VolumeAndMomentOfInertiaCalculator {

    fun calculateVolumeAndInertiaMoment(parsedElement: ParsedElement): VolumeAndInertiaMoment {
        var sumAllVolumes = 0.0
        var sumAllInertiaMoments = 0.0
        parsedElement.elements.forEach {
            val nodes = mutableListOf<Node>()
            it.nodes.forEach { elemNode ->
                nodes.add(parsedElement.nodes.find { node -> node.id == elemNode }
                    ?: throw IllegalArgumentException("Node with id: $elemNode not found! Element: $it"))
            }
            val vectors = findVectors(nodes)
            val tetrahedronVolume = VOLUME_COEFFICIENT * mixedVectorProduct(vectors) / 1_000_000_000 // M^3
            sumAllVolumes += tetrahedronVolume

            val tetrahedronMass = STEEL_DENSITY * tetrahedronVolume // Kg
            val center = findTetrahedronCenter(vectors)
            val distance =
                sqrt(
                    (center.x - NODE_OF_INERTIA.x) * (center.x - NODE_OF_INERTIA.x)
                            + (center.y - NODE_OF_INERTIA.y) * (center.y - NODE_OF_INERTIA.y)
                            + (center.z - NODE_OF_INERTIA.z) * (center.z - NODE_OF_INERTIA.z)
                ) / 1_000 // M
            val inertiaMoment = distance * distance * tetrahedronMass
            sumAllInertiaMoments += inertiaMoment
        }
        return VolumeAndInertiaMoment(volume = sumAllVolumes, moment = sumAllInertiaMoments)
    }
}


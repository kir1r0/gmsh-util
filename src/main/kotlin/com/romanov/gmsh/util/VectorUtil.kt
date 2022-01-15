package com.romanov.gmsh.util

import com.romanov.gmsh.model.Node
import com.romanov.gmsh.model.Vector3

object VectorUtil {
    fun findVectors(nodes: List<Node>): List<Vector3> = listOf(
        Vector3(nodes[1].x - nodes[0].x, nodes[1].y - nodes[0].y, nodes[1].z - nodes[0].z),
        Vector3(nodes[2].x - nodes[0].x, nodes[2].y - nodes[0].y, nodes[2].z - nodes[0].z),
        Vector3(nodes[3].x - nodes[0].x, nodes[3].y - nodes[0].y, nodes[3].z - nodes[0].z)
    )

    fun mixedVectorProduct(vectors: List<Vector3>): Double = vectors[0].a * vectors[1].b * vectors[2].c +
            vectors[2].a * vectors[0].b * vectors[1].c +
            vectors[1].a * vectors[2].b * vectors[0].c -
            vectors[2].a * vectors[1].b * vectors[0].c -
            vectors[0].a * vectors[2].b * vectors[1].c -
            vectors[1].a * vectors[0].b * vectors[2].c

    fun findTetrahedronCenter(vectors: List<Vector3>): Node =
        Node(
            Int.MIN_VALUE,
            Constant.CENTER_COEFFICIENT * (vectors[0].a + vectors[1].a + vectors[2].a),
            Constant.CENTER_COEFFICIENT * (vectors[0].b + vectors[1].b + vectors[2].b),
            Constant.CENTER_COEFFICIENT * (vectors[0].c + vectors[1].c + vectors[2].c),
        )
}

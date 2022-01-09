package com.romanov.gmsh.model

enum class TetrahedronType(val value: Int) {
    FOUR_NODE_TETRAHEDRON_TYPE(4),
    TEN_NODE_TETRAHEDRON_TYPE(11), // 4 nodes associated with the vertices and 6 with the edges
    TWENTY_NODE_TETRAHEDRON_TYPE(29), // 4 nodes associated with the vertices, 12 with the edges, 4 with the faces
    THIRTY_FIVE_NODE_TETRAHEDRON_TYPE(30), // 4 nodes associated with the vertices, 18 with the edges, 12 with the faces, 1 in the volume
    FIFTY_SIX_NODE_TETRAHEDRON_TYPE(31), // 4 nodes associated with the vertices, 24 with the edges, 24 with the faces, 4 in the volume
}
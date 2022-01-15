package com.romanov.gmsh.util

import com.romanov.gmsh.model.Node

object Constant {
    const val STEEL_DENSITY = 7800
    const val VOLUME_COEFFICIENT = 1.0 / 6.0
    const val CENTER_COEFFICIENT = 1.0 / 4.0
    val NODE_OF_INERTIA = Node(Int.MAX_VALUE, 1.750018953575091, 4.2499999999945, 10.0)
}


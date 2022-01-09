package com.romanov.gmsh.model

data class Node(val id: Int, val x: Double, val y: Double, val z: Double)

data class Element(val id: Int, val type: Int, val nodes: List<Int>)

data class ParsedElement(val nodes: List<Node>, val elements: List<Element>)

data class Vector3(val a: Double, val b: Double, val c: Double)
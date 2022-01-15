package com.romanov.gmsh

import com.romanov.gmsh.model.*
import org.slf4j.LoggerFactory
import java.io.File

private const val DELIMITER = " "

class MeshFileParser {

    fun parseMeshFileVersion2(file: File): ParsedElement {
        val lines = file.readLines()

        if (!lines.contains("2.2 0 8")) {
            throw IllegalArgumentException("Only mesh version 2.2 0 8 is supported!")
        }

        val parsingState = ParsingState()
        val nodes = mutableListOf<Node>()
        val tetrahedronElements = mutableListOf<Element>()

        for (i in lines.indices) {
            when {
                lines[i] == "\$ParametricNodes" || lines[i] == "\$Nodes" -> {
                    parsingState.isParametricNodesParsing = true
                    logger.info("ParametricNodes: ${lines[i + 1]}")
                    continue
                }
                lines[i] == "\$EndParametricNodes" || lines[i] == "\$EndNodes" -> {
                    parsingState.isParametricNodesParsing = false
                }
                lines[i] == "\$Elements" -> {
                    parsingState.isElementsParsing = true
                    logger.info("Elements: ${lines[i + 1]}")
                    continue
                }
                lines[i] == "\$EndElements" -> {
                    parsingState.isElementsParsing = false
                }
            }

            if (parsingState.isParametricNodesParsing) {
                val split = lines[i].split(DELIMITER)
                if (split.size == 1) {
                    continue
                }
                val node = Node(
                    id = split[0].toInt(),
                    x = split[1].toDouble(),
                    y = split[2].toDouble(),
                    z = split[3].toDouble()
                )
                logger.debug("Node: $node")
                nodes.add(node)
            }

            if (parsingState.isElementsParsing) {
                val split = lines[i].split(DELIMITER)
                if (split.size == 1) {
                    continue
                }

                val type = split[1].toInt()
                val typeValues = TetrahedronType.values().map { it.value }
                if (typeValues.contains(type)) {
                    val element = Element(id = split[0].toInt(), type = type,
                        nodes = split.subList(5, 9)
                            .map { it.toInt() }
                    )
                    logger.debug("Element: $element")
                    tetrahedronElements.add(element)
                }
            }
        }

        return ParsedElement(nodes, tetrahedronElements)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MeshFileParser::class.java)
    }
}


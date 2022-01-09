package com.romanov.gmsh

import org.slf4j.LoggerFactory
import java.io.File

fun main() {
    logger.info("Start")
    try {
        val meshFilePath = getFilePath()
        val meshFile = File(meshFilePath)
        val meshFileParser = MeshFileParser()
        val parsedElement = meshFileParser.parseMeshFileVersion2(meshFile)
        val volumeCalculator = VolumeCalculator()
        val volume = volumeCalculator.calculateVolume(parsedElement)
        logger.info("Volume is $volume")
    } catch (e: Exception) {
        logger.error("Something went wrong", e)
    } finally {
        logger.info("Finish, please press any key")
        System.`in`.read()
    }
}

private fun getFilePath(): String {
    logger.info("Input mesh file path")
    val path = readLine()
    return if (path.isNullOrBlank()) {
        "src/main/resources/mesh_2v"
    } else {
        path
    }
}

private val logger = LoggerFactory.getLogger("App.kt")
package com.romanov.gmsh

import com.romanov.gmsh.util.Constant.STEEL_DENSITY
import org.slf4j.LoggerFactory
import java.io.File

fun main() {
    logger.info("Start")
    try {
        val meshFilePath = getFilePath()
        val meshFile = File(meshFilePath)
        val meshFileParser = MeshFileParser()
        val parsedElement = meshFileParser.parseMeshFileVersion2(meshFile)

        val volumeAndMomentOfInertiaCalculator = VolumeAndMomentOfInertiaCalculator()

        val volumeAndInertiaMoment = volumeAndMomentOfInertiaCalculator.calculateVolumeAndInertiaMoment(parsedElement)
        logger.info("Volume is ${volumeAndInertiaMoment.volume} M^3")

        val mass = (STEEL_DENSITY * volumeAndInertiaMoment.volume)
        logger.info("Mass is $mass Kg")

        val inertiaMoment = volumeAndInertiaMoment.moment
        logger.info("Inertia moment is $inertiaMoment Kg/M^2")
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
        "src/main/resources/crank.msh"
    } else {
        path
    }
}

private val logger = LoggerFactory.getLogger("App.kt")
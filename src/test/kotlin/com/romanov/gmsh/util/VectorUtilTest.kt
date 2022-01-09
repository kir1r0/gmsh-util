package com.romanov.gmsh.util

import com.romanov.gmsh.model.Vector3
import kotlin.test.Test


internal class VectorUtilTest {

    @Test
    fun findVectors() {
        // TODO complete test
    }

    @Test
    fun mixedVectorProduct() {
        val vectors = listOf(
            Vector3(1.0, -2.0, 3.0),
            Vector3(4.0, 0.0, 6.0),
            Vector3(-7.0, 8.0, 9.0)
        )
        val product = VectorUtil.mixedVectorProduct(vectors)
        assert(204.0 == product)
    }
}
package club.nito.feature.top

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

/**
 * Sample Test
 */
class DataDrivenTest : FunSpec() {
    init {
        withData(
            PythagTriple(3, 4, 5),
            PythagTriple(6, 8, 10),
        ) { (a, b, c) ->
            a * a + b * b shouldBe c * c
        }
    }
}

data class PythagTriple(val a: Int, val b: Int, val c: Int)

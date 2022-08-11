import com.fadda.math.Parity
import com.fadda.math.Parity.PAIR
import com.fadda.math.Parity.UNPAIR
import com.fadda.recursivetypes.BinaryTree
import com.google.common.io.Files
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

class TestExercise5 {
    private var lines: List<BinaryTree<Int>>? = null
    private var correctResults: List<Map<Parity, List<Int>>> = listOf(
        mapOf(PAIR to listOf(2)),
        mapOf(PAIR to listOf(4, 2), UNPAIR to listOf(11)),
        mapOf(PAIR to listOf(10)),
        mapOf(PAIR to listOf(2, 4)),
        mapOf(UNPAIR to listOf(3)),
        mapOf(),
        mapOf(PAIR to listOf(8), UNPAIR to listOf(5)),
        mapOf(UNPAIR to listOf(5)),
        mapOf(UNPAIR to listOf(5, 11), PAIR to listOf(8)),
        mapOf(PAIR to listOf(2), UNPAIR to listOf(5, 25)),
    )

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI3E5_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8).map { BinaryTree.parse(it, Integer::parseInt) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testFunctional() {
        val result = lines!!.map { Exercise5.functional(it) }
        for (i in result.indices) {
            Assertions.assertEquals(
                correctResults[i][UNPAIR]?.sortedBy { it },
                result[i][UNPAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
            Assertions.assertEquals(
                correctResults[i][PAIR]?.sortedBy { it },
                result[i][PAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
        }

    }

    @Test
    fun testRecursiveFinal() {
        val result = lines!!.map { Exercise5.recursiveFinal(it) }
        for (i in result.indices) {
            Assertions.assertEquals(
                correctResults[i][UNPAIR]?.sortedBy { it },
                result[i][UNPAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
            Assertions.assertEquals(
                correctResults[i][PAIR]?.sortedBy { it },
                result[i][PAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
        }
    }

    @Test
    fun testRecursiveNoFinal() {
        val result = lines!!.map { Exercise5.recursiveNoFinal(it) }
        for (i in result.indices) {
            Assertions.assertEquals(
                correctResults[i][UNPAIR]?.sortedBy { it },
                result[i][UNPAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
            Assertions.assertEquals(
                correctResults[i][PAIR]?.sortedBy { it },
                result[i][PAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
        }
    }

    @Test
    fun testIterativeFor() {
        val result = lines!!.map { Exercise5.iterativeFor(it) }
        for (i in result.indices) {
            Assertions.assertEquals(
                correctResults[i][UNPAIR]?.sortedBy { it },
                result[i][UNPAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
            Assertions.assertEquals(
                correctResults[i][PAIR]?.sortedBy { it },
                result[i][PAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
        }
    }

    @Test
    fun testIterativeWhile() {
        val result = lines!!.map { Exercise5.iterativeWhile(it) }
        for (i in result.indices) {
            Assertions.assertEquals(
                correctResults[i][UNPAIR]?.sortedBy { it },
                result[i][UNPAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
            Assertions.assertEquals(
                correctResults[i][PAIR]?.sortedBy { it },
                result[i][PAIR]?.sortedBy { it },
                "Functional doesn't work on line $i."
            )
        }
    }

}

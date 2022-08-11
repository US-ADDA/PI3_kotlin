import com.fadda.recursivetypes.Tree
import com.google.common.io.Files
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.function.Predicate

class TestExercise1 {

    private var lines: List<Tree<Int>>? = null
    private var predicates: List<Predicate<Int>> = listOf(
        Predicate { it % 2 == 0 },
        Predicate { it < 5 },
    )
    private var correctResults: List<List<Set<Int>>> = listOf(
        listOf(
            setOf(2, 8),
            setOf(4, 8, 12),
            setOf(2, 4, 14),
            setOf(2, 20, 8, 14),
            setOf(2, 22, 12),
            setOf(2),
            setOf(8),
            setOf(8, 10)
        ),
        listOf(setOf(2), setOf(4), setOf(2, 4), setOf(2), setOf(2, 3), setOf(2), setOf(), setOf())
    )
    private var test: Int = 1

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI3E1_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8).map { Tree.parse(it, Integer::parseInt) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    @Test
    fun testFunctional() {
        val result = lines!!.map { Exercise1.functional(it, predicates[test - 1]) }
        for (i in result.indices) {
            assertEquals(correctResults[test - 1][i], result[i], "Functional doesn't work on line $i.")
        }
    }

    @Test
    fun testIterativeWhile() {
        val result = lines!!.map { Exercise1.iterativeWhile(it, predicates[test - 1]) }
        for (i in result.indices) {
            assertEquals(correctResults[test - 1][i], result[i], "Iterative while doesn't work on line $i.")
        }
    }

    @Test
    fun testIterativeFor() {
        val result = lines!!.map { Exercise1.iterativeFor(it, predicates[test - 1]) }
        for (i in result.indices) {
            assertEquals(correctResults[test - 1][i], result[i], "Iterative for doesn't work on line $i.")
        }
    }

    @Test
    fun testRecursiveFinal() {
        val result = lines!!.map { Exercise1.recursiveFinal(it, predicates[test - 1]) }
        for (i in result.indices) {
            assertEquals(correctResults[test - 1][i], result[i], "Recursive final doesn't work on line $i.")
        }
    }

    @Test
    fun testRecursiveNoFinal() {
        val result = lines!!.map { Exercise1.recursiveNoFinal(it, predicates[test - 1]) }
        for (i in result.indices) {
            assertEquals(correctResults[test - 1][i], result[i], "Recursive no final doesn't work on line $i.")
        }
    }
}

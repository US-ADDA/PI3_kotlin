import com.fadda.recursivetypes.BinaryTree
import com.google.common.io.Files
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

class TestExercise3 {
    private var lines: List<BinaryTree<Int>>? = null
    private var correctResults: List<Pair<List<Int>, Int>> = listOf(
        Pair(listOf(10, 7), 70),
        Pair(listOf(8, 7, 5), 280),
        Pair(listOf(11, 6, 5, 3), 990),
        Pair(listOf(22, 2, 5, 4), 880),
        Pair(listOf(9, 2, 5, 3), 270),
        Pair(listOf(1, 10, -5, 7, -2), 700),
        Pair(listOf(3, 20, 5), 300),
        Pair(listOf(1, 3, 5, 9), 135)
    )

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI3E3_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8).map { BinaryTree.parse(it, Integer::parseInt) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testFunctional() {
        val result = lines!!.map { Exercise3.functional(it) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "Functional doesn't work on line $i.")
    }

    @Test
    fun testRecursiveFinal() {
        val result = lines!!.map { Exercise3.recursiveFinal(it) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "RecursiveFinal doesn't work on line $i.")
    }

    @Test
    fun testRecursiveFinal2() {
        val result = lines!!.map { Exercise3.recursiveFinal2(it) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "RecursiveFinal2 doesn't work on line $i.")
    }

    @Test
    fun testRecursiveNoFinal2() {
        val result = lines!!.map { Exercise3.recursiveNoFinal2(it) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "RecursiveNoFinal2 doesn't work on line $i.")
    }

    @Test
    fun testIterativeFor2() {
        val result = lines!!.map { Exercise3.iterativeFor2(it) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "IterativeFor2 doesn't work on line $i.")
    }

    @Test
    fun testIterativeWhile2() {
        val result = lines!!.map { Exercise3.iterativeWhile2(it) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "IterativeWhile2 doesn't work on line $i.")
    }
}

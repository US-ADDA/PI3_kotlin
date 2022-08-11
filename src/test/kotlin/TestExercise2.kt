import com.fadda.recursivetypes.BinaryTree
import com.google.common.io.Files
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

class TestExercise2 {

    private var lines: List<Pair<BinaryTree<Int>, Int>>? = null
    private var correctResults: List<Set<Int>> = listOf(
        setOf(4, 5, 11, 12),
        setOf(1, 2, 3, 4, 5, 8, 9),
        setOf(20, 200, 250, 30),
        setOf(10, 14),
        setOf(200, 120, 105, 250, 220, 300),
        setOf(17, 20, 25, 15),
        setOf(180, 150, 200, 155),
        setOf(180, 200)
    )
    private var sep: String = "#"

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI3E2_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8).map {
                val info = it.split(sep)
                Pair(BinaryTree.parse(info[0], Integer::parseInt), info[1].toInt())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testFunctional() {
        val result = lines!!.map { Exercise2.functional(it.first, it.second) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "Functional doesn't work on line $i.")
    }

    @Test
    fun recursiveFinal() {
        val result = lines!!.map { Exercise2.recursiveFinal(it.first, it.second) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "RecursiveFinal doesn't work on line $i.")
    }

    @Test
    fun recursiveNoFinal() {
        val result = lines!!.map { Exercise2.recursiveNoFinal(it.first, it.second) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "RecursiveNoFinal doesn't work on line $i.")
    }

    @Test
    fun iterativeWhile() {
        val result = lines!!.map { Exercise2.iterativeWhile(it.first, it.second) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "IterativeWhile doesn't work on line $i.")
    }

    @Test
    fun iterativeFor() {
        val result = lines!!.map { Exercise2.iterativeFor(it.first, it.second) }
        for (i in result.indices)
            assertEquals(correctResults[i], result[i], "IterativeFor doesn't work on line $i.")
    }
}

import com.fadda.recursivetypes.Tree
import com.google.common.io.Files
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

class TestExercise4 {

    private var lines: List<Tree<String>>? = null
    private var correctResults: List<Set<String>> = listOf(
        setOf(),
        setOf("ana"),
        setOf("oso", "oro"),
        setOf("ama", "ana"),
        setOf("radar", "rapar"),
        setOf(),
        setOf(),
        setOf("aia", "aea")
    )

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI3E4_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8).map { Tree.parse(it) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testFunctional2() {
        val result = lines!!.map { Exercise4.functional2(it) }
        for (i in result.indices)
            Assertions.assertEquals(correctResults[i], result[i], "Functional doesn't work on line $i.")
    }

    @Test
    fun testRecursiveFinal() {
        val result = lines!!.map { Exercise4.recursiveFinal(it) }
        for (i in result.indices)
            Assertions.assertEquals(correctResults[i], result[i], "RecursiveFinal doesn't work on line $i.")
    }

    @Test
    fun testRecursiveFinal2() {
        val result = lines!!.map { Exercise4.recursiveFinal2(it) }
        for (i in result.indices)
            Assertions.assertEquals(correctResults[i], result[i], "RecursiveFinal2 doesn't work on line $i.")
    }

    @Test
    fun testRecursiveNoFinal2() {
        val result = lines!!.map { Exercise4.recursiveNoFinal2(it) }
        for (i in result.indices)
            Assertions.assertEquals(correctResults[i], result[i], "RecursiveNoFinal2 doesn't work on line $i.")
    }

    @Test
    fun iterativeFor2() {
        val result = lines!!.map { Exercise4.iterativeFor2(it) }
        for (i in result.indices)
            Assertions.assertEquals(correctResults[i], result[i], "IterativeFor2 doesn't work on line $i.")
    }

    @Test
    fun iterativeWhile2() {
        val result = lines!!.map { Exercise4.iterativeWhile2(it) }
        for (i in result.indices)
            Assertions.assertEquals(correctResults[i], result[i], "IterativeWhile2 doesn't work on line $i.")
    }
}

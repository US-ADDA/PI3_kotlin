import com.fadda.recursivetypes.BinaryTree
import com.fadda.recursivetypes.BinaryTree.BinaryType.*

class Exercise3 {

    companion object {
        fun recursiveFinal(tree: BinaryTree<Int>): Pair<List<Int>, Int> {
            val res = recursiveFinal(tree, mutableListOf(), mutableMapOf()).maxBy { it.value }
            return Pair(res.key, res.value)
        }

        fun recursiveFinal(
            tree: BinaryTree<Int>,
            path: MutableList<Int>,
            memoryPath: MutableMap<List<Int>, Int>
        ): MutableMap<List<Int>, Int> {
            val auxPath: MutableList<Int> = mutableListOf()
            when (tree.type!!) {
                Empty -> {}
                Leaf -> {
                    auxPath.addAll(path + tree.label)
                    memoryPath[auxPath] = auxPath.reduce { acc, next -> acc * next }
                }

                Binary -> {
                    auxPath.addAll(path + tree.label)
                    recursiveFinal(tree.left, auxPath, memoryPath)
                    recursiveFinal(tree.right, auxPath, memoryPath)
                }
            }
            return memoryPath
        }

        // -------------------------------------------------------------------//
        // Utilizando el método path que falta en el original de Miguel Toro. //
        // -------------------------------------------------------------------//

        fun recursiveFinal2(tree: BinaryTree<Int>): Pair<List<Int>, Int> {
            val res = recursiveFinal2(tree.iterator(), mutableMapOf()).maxBy { it.value }
            return Pair(res.key, res.value)
        }

        fun recursiveFinal2(
            iterator: MutableIterator<BinaryTree<Int>>,
            memoryPath: MutableMap<List<Int>, Int>
        ): MutableMap<List<Int>, Int> {
            if (!iterator.hasNext()) return memoryPath
            val newTree = iterator.next()
            when (newTree.type!!) {
                Leaf -> {
                    memoryPath[newTree.path] = newTree.path.reduce { acc, next -> acc * next }
                    recursiveFinal2(iterator, memoryPath)
                }

                else -> recursiveFinal2(iterator, memoryPath)
            }
            return memoryPath
        }

        fun recursiveNoFinal2(tree: BinaryTree<Int>): Pair<List<Int>, Int> {
            val res = recursiveNoFinal2(tree.iterator()).maxBy { it.value }
            return Pair(res.key, res.value)
        }

        fun recursiveNoFinal2(iterator: MutableIterator<BinaryTree<Int>>): MutableMap<List<Int>, Int> {
            if (!iterator.hasNext()) return mutableMapOf()
            val newTree = iterator.next()
            val memoryPath = recursiveNoFinal2(iterator)
            when (newTree.type!!) {
                Leaf -> memoryPath[newTree.path] = newTree.path.reduce { acc, next -> acc * next }
                else -> recursiveNoFinal2(iterator)
            }
            return memoryPath
        }

        fun iterativeFor2(tree: BinaryTree<Int>): Pair<List<Int>, Int> {
            val iterator = tree.iterator()
            val memoryPath = mutableMapOf<List<Int>, Int>()
            while (iterator.hasNext()) {
                val newTree = iterator.next()
                if (newTree.isLeaf)
                    memoryPath[newTree.path] = newTree.path.reduce { acc, next -> acc * next }
            }
            val res = memoryPath.maxBy { it.value }
            return Pair(res.key, res.value)
        }

        fun iterativeWhile2(tree: BinaryTree<Int>): Pair<List<Int>, Int> {
            val iterator = tree.iterator()
            val memoryPath = mutableMapOf<List<Int>, Int>()
            while (iterator.hasNext()) {
                val newTree = iterator.next()
                if (newTree.isLeaf)
                    memoryPath[newTree.path] = newTree.path.reduce { acc, next -> acc * next }
            }
            val res = memoryPath.maxBy { it.value }
            return Pair(res.key, res.value)
        }

        fun functional(tree: BinaryTree<Int>): Pair<List<Int>, Int> {
            val res = tree.filter { it.isLeaf }
                .map { Pair(it.path, it.path.reduce { acc, next -> acc * next }) }
                .maxBy { it.second }
            return Pair(res.first, res.second)
        }
    }
}

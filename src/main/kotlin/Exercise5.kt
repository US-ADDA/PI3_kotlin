import com.fadda.math.Parity
import com.fadda.recursivetypes.BinaryTree
import com.fadda.recursivetypes.BinaryTree.BinaryTreeLevel

class Exercise5 {

    companion object {
        fun recursiveFinal(tree: BinaryTree<Int>): Map<Parity, List<Int>> {
            return recursiveFinal(tree.byLevel(), mutableMapOf())
        }

        fun recursiveFinal(
            iterator: MutableIterator<BinaryTreeLevel<Int>>,
            map: MutableMap<Parity, MutableList<Int>>
        ): MutableMap<Parity, MutableList<Int>> {
            if (!iterator.hasNext()) return map
            val newTree = iterator.next().tree
            if (newTree.isBinary) {
                val left = newTree.left
                val right = newTree.right
                val number = newTree.label
                if (!right.isEmpty && !left.isEmpty && number > left.label && number < right.label) {
                    val parity = Parity.get(number)
                    if (map.containsKey(parity))
                        map[parity]!! += number
                    else
                        map[parity] = mutableListOf(number)
                }
            }
            return recursiveFinal(iterator, map)
        }

        fun recursiveNoFinal(tree: BinaryTree<Int>): Map<Parity, List<Int>> {
            return recursiveNoFinal(tree.byLevel())
        }

        fun recursiveNoFinal(iterator: MutableIterator<BinaryTreeLevel<Int>>): MutableMap<Parity, MutableList<Int>> {
            if (!iterator.hasNext()) return mutableMapOf()
            val newTree = iterator.next().tree
            val map = recursiveNoFinal(iterator)
            if (newTree.isBinary) {
                val left = newTree.left
                val right = newTree.right
                val number = newTree.label
                if (!right.isEmpty && !left.isEmpty && number > left.label && number < right.label) {
                    val parity = Parity.get(number)
                    if (map.containsKey(parity))
                        map[parity]!! += number
                    else
                        map[parity] = mutableListOf(number)
                }
            }
            return map
        }

        fun iterativeFor(tree: BinaryTree<Int>): MutableMap<Parity, MutableList<Int>> {
            val map = mutableMapOf<Parity, MutableList<Int>>()
            val iterator = tree.byLevel()
            for (level in iterator) {
                val newTree = level.tree
                if (newTree.isBinary) {
                    val left = newTree.left
                    val right = newTree.right
                    val number = newTree.label
                    if (!right.isEmpty && !left.isEmpty && number > left.label && number < right.label) {
                        val parity = Parity.get(number)
                        if (map.containsKey(parity))
                            map[parity]!! += number
                        else
                            map[parity] = mutableListOf(number)
                    }
                }
            }
            return map
        }

        fun iterativeWhile(tree: BinaryTree<Int>): MutableMap<Parity, MutableList<Int>> {
            val map = mutableMapOf<Parity, MutableList<Int>>()
            val iterator = tree.byLevel()
            while (iterator.hasNext()) {
                val newTree = iterator.next().tree
                if (newTree.isBinary) {
                    val left = newTree.left
                    val right = newTree.right
                    val number = newTree.label
                    if (!right.isEmpty && !left.isEmpty && number > left.label && number < right.label) {
                        val parity = Parity.get(number)
                        if (map.containsKey(parity))
                            map[parity]!! += number
                        else
                            map[parity] = mutableListOf(number)
                    }
                }
            }
            return map
        }

        fun functional(tree: BinaryTree<Int>): Map<Parity, List<Int>> {
            return tree.filter { it.isBinary && !it.left.isEmpty && !it.right.isEmpty && it.label > it.left.label && it.label < it.right.label }
                .groupBy { Parity.get(it.label) }
                .mapValues { map -> map.value.map { it.label } }
        }


    }
}

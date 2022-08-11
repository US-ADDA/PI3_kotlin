import com.fadda.recursivetypes.BinaryTree
import com.fadda.recursivetypes.BinaryTree.BinaryTreeLevel

class Exercise2 {

    companion object {
        fun recursiveNoFinal(tree: BinaryTree<Int>, n: Int): Set<Int> {
            return recursiveNoFinal(tree.byLevel(), n)
        }

        fun recursiveNoFinal(iterator: Iterator<BinaryTreeLevel<Int>>, n: Int): MutableSet<Int> {
            if (!iterator.hasNext()) return mutableSetOf()
            val newTree = iterator.next().tree
            val set = recursiveNoFinal(iterator, n)
            if (!newTree.isEmpty && newTree.label >= n) set.add(newTree.label)
            return set
        }

        fun recursiveFinal(tree: BinaryTree<Int>, n: Int): Set<Int> {
            return recursiveFinal(tree.byLevel(), n, mutableSetOf())
        }

        fun recursiveFinal(tree: Iterator<BinaryTreeLevel<Int>>, n: Int, set: MutableSet<Int>): MutableSet<Int> {
            if (!tree.hasNext()) return set
            val newTree = tree.next().tree
            if (!newTree.isEmpty && newTree.label >= n) set.add(newTree.label)
            return recursiveFinal(tree, n, set)
        }

        fun iterativeFor(tree: BinaryTree<Int>, n: Int): Set<Int> {
            val set = mutableSetOf<Int>()
            val iterator = tree.byLevel()
            for (level in iterator) {
                val newTree = level.tree
                if (!newTree.isEmpty && newTree.label >= n) set.add(newTree.label)
            }
            return set
        }

        fun iterativeWhile(tree: BinaryTree<Int>, n: Int): Set<Int> {
            val set = mutableSetOf<Int>()
            val iterator = tree.byLevel()
            while (iterator.hasNext()) {
                val newTree = iterator.next().tree
                if (!newTree.isEmpty && newTree.label >= n) set.add(newTree.label)
            }
            return set
        }

        fun functional(tree: BinaryTree<Int>, n: Int): Set<Int> {
            return tree.byLevel().asSequence().map { it.tree }.filter { !it.isEmpty && it.label >= n }.map { it.label }
                .toSet()
        }
    }
}

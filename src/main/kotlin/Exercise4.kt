import com.fadda.common.extension.Set2.addIfPalindrome
import com.fadda.common.extension.String2
import com.fadda.recursivetypes.Tree
import com.fadda.recursivetypes.Tree.TreeType.*

class Exercise4 {

    companion object {
        fun recursiveFinal(tree: Tree<String>): Set<String> {
            return recursiveFinal(tree, "", mutableSetOf())
        }

        private fun recursiveFinal(tree: Tree<String>, word: String, set: MutableSet<String>): Set<String> {
            return when (tree.type!!) {
                Nary -> {
                    tree.children.forEach { recursiveFinal(it, word + tree.label, set) }
                    set
                }

                Empty -> set
                Leaf -> addIfPalindrome(word + tree.label, set)
            }
        }

        // -------------------------------------------------------------------//
        // Utilizando el método path que falta en el original de Miguel Toro. //
        // -------------------------------------------------------------------//

        fun recursiveFinal2(tree: Tree<String>): Set<String> {
            // val iterator = tree.iterator()
            // System.out.println(iterator.next().father)
            // System.out.println(iterator.next().father)
            // System.out.println(iterator.next().father)
            return recursiveFinal2(tree.iterator(), mutableSetOf())
        }

        private fun recursiveFinal2(iterator: Iterator<Tree<String>>, set: MutableSet<String>): Set<String> {
            if (iterator.hasNext()) {
                val newTree = iterator.next()
                recursiveFinal2(
                    iterator,
                    if (newTree.isLeaf) addIfPalindrome(newTree.path.joinToString(""), set) else set
                )
            }
            return set
        }

        fun recursiveNoFinal2(tree: Tree<String>): Set<String> {
            return recursiveNoFinal2(tree.iterator())
        }

        private fun recursiveNoFinal2(iterator: Iterator<Tree<String>>): Set<String> {
            if (!iterator.hasNext()) return mutableSetOf()
            val newTree = iterator.next()
            val set = recursiveNoFinal2(iterator)
            return if (newTree.isLeaf) addIfPalindrome(newTree.path.joinToString(""), set) else set
        }

        fun iterativeFor2(tree: Tree<String>): Set<String> {
            var set = mutableSetOf<String>()
            for (newTree in tree)
                if (newTree.isLeaf)
                    set = addIfPalindrome(newTree.path.joinToString(""), set)

            return set
        }

        fun iterativeWhile2(tree: Tree<String>): Set<String> {
            var set = mutableSetOf<String>()
            val iterator = tree.iterator()
            while (iterator.hasNext()) {
                val newTree = iterator.next()
                if (newTree.isLeaf)
                    set = addIfPalindrome(newTree.path.joinToString(""), set)
            }
            return set
        }

        fun functional2(tree: Tree<String>): Set<String> {
            return tree.filter { it.isLeaf }.map { it.path.joinToString("") }.filter(String2::isPalindrome).toSet()
        }
    }
}

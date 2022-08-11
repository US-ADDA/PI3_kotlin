import com.fadda.recursivetypes.Tree
import com.fadda.recursivetypes.Tree.TreeLevel
import com.fadda.recursivetypes.Tree.TreeType.Leaf
import java.util.function.Predicate
import java.util.stream.Collectors

class Exercise1 {
    companion object {
        fun <E> recursiveFinal(tree: Tree<E>, predicate: Predicate<E>): Set<E> {
            return recursiveFinal(tree.byLevel(), predicate, mutableSetOf())
        }

        fun <E> recursiveFinal(
            iterator: Iterator<TreeLevel<E>>,
            predicate: Predicate<E>,
            set: MutableSet<E>
        ): MutableSet<E> {
            if (!iterator.hasNext()) return set
            val newTree = iterator.next().tree
            when (newTree.type!!) {
                Leaf -> if (predicate.test(newTree.label)) set.add(newTree.label!!)
                else -> {}
            }
            return recursiveFinal(iterator, predicate, set)
        }

        fun <E> recursiveNoFinal(tree: Tree<E>, predicate: Predicate<E>): Set<E> {
            return recursiveNoFinal(tree.byLevel(), predicate)
        }

        fun <E> recursiveNoFinal(iterator: Iterator<TreeLevel<E>>, predicate: Predicate<E>): MutableSet<E> {
            if (!iterator.hasNext()) return mutableSetOf()
            val newTree = iterator.next().tree
            val set = recursiveNoFinal(iterator, predicate)
            when (newTree.type!!) {
                Leaf -> if (predicate.test(newTree.label)) set.add(newTree.label!!)
                else -> {}
            }
            return set
        }

        fun <E> iterativeWhile(tree: Tree<E>, predicate: Predicate<E>): Set<E> {
            val set = mutableSetOf<E>()
            val iterator = tree.byLevel()
            while (iterator.hasNext()) {
                val newTree = iterator.next().tree
                when (newTree.type!!) {
                    Leaf -> if (predicate.test(newTree.label)) set.add(newTree.label!!)
                    else -> {}
                }
            }
            return set
        }

        fun <E> iterativeFor(tree: Tree<E>, predicate: Predicate<E>): Set<E> {
            val set = mutableSetOf<E>()
            val iterator = tree.byLevel()
            for (level in iterator) {
                val newTree = level.tree
                when (newTree.type!!) {
                    Leaf -> if (predicate.test(newTree.label)) set.add(newTree.label!!)
                    else -> {}
                }
            }
            return set
        }

        fun <E> functional(tree: Tree<E>, predicate: Predicate<E>): Set<E> {
            return tree.byLevel.stream().filter { it.type == Leaf && predicate.test(it.label) }.map { it.label }
                .collect(Collectors.toSet())
        }
    }

}

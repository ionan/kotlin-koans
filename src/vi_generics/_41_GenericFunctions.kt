package vi_generics

import util.TODO
import java.util.*

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun <T, C : MutableCollection<T>> Collection<T>.partitionTo(c1: C, c2: C, predicate: (T) -> Boolean): Pair<C, C> {
    for (elem in this) {
        if (predicate(elem)) c1.add(elem)
        else c2.add(elem)
    }
    return Pair(c1, c2)
}

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> =
    partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> =
    partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}

// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.LinkedListMultimap

/**
 * @return 
 *   Map of counts of how many times given elements appears in this receiver [Iterable].
 */
val <T> Iterable<T>.frequencies: Map<T, Int> get() {
  val grouped: Map<T, List<T>> = this.groupBy { it }
  val frequencies: Map<T, Int> = grouped.mapValues { it.value.size }
  return frequencies
}

/**
 * @return 
 *   A map from unique items to the index of first element in the receiver [Iterable] from which given unique item was
 *   obtained. The indexing starts at 0.
 * 
 * @param extractItems 
 *   A function that is applied to each element of the receiver iterable, converting it to an iterable of items.
 * 
 * @param extractUniqueString 
 *   A function used to remove duplicates from all the items extracted from receiver iterable using [extractItems].
 * 
 */
fun <T, TItem> Iterable<T>.uniqueItemsWithFirstOccurrenceIndex(
  extractItems: (T) -> Iterable<TItem>,
  extractUniqueString: (TItem) -> String
): Map<TItem, Int> {

  return this.foldIndexed(

    mapOf<String, Pair<TItem, Int>>(), { index, accumulatedMap, elem ->

    val uniqueStringsToItemsWithIndexes: Map<String, Pair<TItem, Int>> =
      extractItems(elem).associate {
        Pair(
          extractUniqueString(it),
          Pair(it, index + 1)
        )
      }

    val newUniqueStrings = uniqueStringsToItemsWithIndexes.keys.subtract(accumulatedMap.keys)

    val uniqueStringsToNewItemsWithIndexes = uniqueStringsToItemsWithIndexes.filterKeys { it in newUniqueStrings }

    accumulatedMap.plus(uniqueStringsToNewItemsWithIndexes)
    
  }).map { it.value }.toMap()
}

inline fun <T, K, V> Iterable<T>.associateMany(transform: (T) -> Pair<K, V>): Map<K, Iterable<V>> {
  val multimap = ArrayListMultimap.create<K, V>()
  this.forEach { val pair = transform(it); multimap.put(pair.first, pair.second) }
  return multimap.asMap()
}

fun <K, V> Iterable<Map<K, Iterable<V>>>.flatten(): Map<K, Iterable<V>> {
  val multimap = LinkedListMultimap.create<K, V>()
  this.forEach { map ->
    map.forEach { multimap.putAll(it.key, it.value) }
  }
  return multimap.asMap()
}
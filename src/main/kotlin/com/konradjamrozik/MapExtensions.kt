// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

val <K, V> Map<K, V>.transpose: Map<V, Set<K>> get() {
  val pairs: List<Pair<V, K>> = this.map { Pair(it.value, it.key) }
  return pairs.fold(
    initial = mutableMapOf(),
    operation = { acc: MutableMap<V, MutableSet<K>>, pair: Pair<V, K> ->
      if (!acc.containsKey(pair.first))
        acc.put(pair.first, mutableSetOf())
      acc[pair.first]!!.add(pair.second)
      acc
    }
  )
}
// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.github.konrad_jamrozik

class MapExtensions
{
  public static <K1, K2, E> Map<K2, Map<K1, E>> flipKeysNesting(Map<K1, Map<K2, E>> self)
  {
    // Running example.
    // self:
    // a: [x: 1, y: 2]
    // b: [x: 3, y: 4]

    Collection<Map<K2, Map<K1, E>>> coll = self.collect {k1, nestedMap ->
      (Map<K2, Map<K1, E>>) nestedMap.collectEntries {k2, e -> [(k2): [(k1): e]]}
    }
    // coll:
    // x: [a: 1]
    // y: [a: 2]
    // x: [b: 3]
    // y: [b: 4]

    Map<K2, Collection<Map<K1,E>>> map = coll.groupValues()
    // map:
    // x: [[a: 1], [b: 3]]
    // y: [[a: 2], [b: 4]]

    (Map<K2, Map<K1,E>>) map.collectEntries { k2, collOfMap ->
      [(k2): collOfMap.collectEntries { it }]
    }
    // x: [a: 1, b: 3]
    // y: [a: 2, b: 4]

  }

  public static <K, E> Map<K, Collection<E>> groupValues(Iterable<Map<K, E>> self)
  {
    self.inject([:] as Map<K, Collection<E>>) {Map<K, Collection<E>> mergedMap, Map<K, E> map ->

      map.each {K key, E e ->

        mergedMap.putIfAbsent(key, [])
        mergedMap.get(key).add(e)
      }

      mergedMap
    }
  }


}

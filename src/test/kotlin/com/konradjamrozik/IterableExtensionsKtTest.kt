// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import kotlin.test.assertEquals

class IterableExtensionsKtTest {
  
  @Test
  fun `frequencies test`() {
    assertThat(
      listOf(
        "a", "a", "b", "a", "x", "x", "x", "X", "e", "f", "e", "x")
        .frequencies,
      equalTo(
        mapOf(
          Pair("a", 3),
          Pair("b", 1),
          Pair("x", 4),
          Pair("X", 1),
          Pair("e", 2),
          Pair("f", 1))
      )
    )
  }

  @Test
  fun `uniqueItemsWithFirstOccurrenceIndex test`() {

    val items = listOf(
      listOf(
        Pair("item1", "ignored1"),
        Pair("ITEM1", "ignored2"),
        Pair("item2", "ignored2"),
        Pair("ITEM2", "ignored3"),
        Pair("item3", "ignored4")
      ),
      listOf(
        Pair("item1", "ignored1"),
        Pair("ITEM2", "ignored1"),
        Pair("item4", "ignored3")
      ),
      listOf(
        Pair("ITEM5", "ignored1"),
        Pair("item3", "ignored3"),
        Pair("item4", "ignored2")
      )

    )
    val expected = mapOf(
      Pair("ITEM1", 1),
      Pair("ITEM2", 1),
      Pair("item3", 1),
      Pair("item4", 2),
      Pair("ITEM5", 3)
    )

    // Act
    val actual: Map<String, Int> = items.uniqueItemsWithFirstOccurrenceIndex(
      extractItems = { it.map { it.first } },
      extractUniqueString = { it.toLowerCase() }
    )

    assertEquals(expected, actual)
  }

  @Test
  fun `associateMany test`() {

    val actual: Map<Int, Iterable<String>> = listOf("1-a", "2-b", "3-c", "2-c", "1-b", "1-a", "4-d", "3-c")
      // Act
      .associateMany { Pair(it[0].toString().toInt(), it[2].toString()) }

    val expected: Map<Int, Iterable<String>> = mapOf(
      Pair(1, listOf("a", "b", "a")),
      Pair(2, listOf("b", "c")),
      Pair(3, listOf("c", "c")),
      Pair(4, listOf("d"))
    )
    assertEquals(expected, actual)
  }


  @Test
  fun `flatten test`() {

    val fixture: Iterable<Map<Int, Iterable<String>>> = listOf(
      mapOf(
        Pair(1, listOf("a")),
        Pair(3, listOf("b"))
      ),
      mapOf(
        Pair(3, listOf("a"))
      ),
      mapOf(
        Pair(3, listOf("x", "a", "b")),
        Pair(2, listOf("a", "c", "b")),
        Pair(1, listOf("b"))
      )
    )

    // Act
    val actual = fixture.flatten()

    val expected: Map<Int, Iterable<String>> = mapOf(
      Pair(1, listOf("a", "b")),
      Pair(2, listOf("a", "c", "b")),
      Pair(3, listOf("b", "a", "x", "a", "b"))
    )

    assertEquals(expected, actual)
  }
  
}
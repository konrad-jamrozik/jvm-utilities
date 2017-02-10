// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

internal class MapExtensionsKtTest {

  @Test
  fun transposeTest() {
    assertThat(
      mapOf(
        Pair("abc", 4),
        Pair("def", 4),
        Pair("x", 1)
      ).transpose,
      equalTo(
        mapOf(
          Pair(4, setOf("abc", "def")),
          Pair(1, setOf("x"))
        )
      )
    )
  }

}


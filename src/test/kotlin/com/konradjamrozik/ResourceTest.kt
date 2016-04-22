// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import java.io.IOException

class ResourceTest {

  @Test
  fun ResourceTest() {

    // act
    val oneFixture = Resource("oneFixture.txt")
    assertTrue(oneFixture.urls.size == 1)

    // act
    Resource("oneFixture.txt", allowAmbiguity = true)
    // assert: no exception

    try {
      // act
      Resource("<path_that doesn't exist>")
      fail("Non-existing resources should eagerly throw exception.")
    } catch(e: IOException) {
      // assert: exception
    }

    // act
    val ambigFixture = Resource("fixture.txt", allowAmbiguity = true)
    assertTrue(ambigFixture.urls.size == 2)
    ambigFixture.urls.forEach { assertTrue(it.text.length > 0) }

    try {
      // act
      Resource("fixture.txt")
      fail("Ambiguity should be forbidden by default.")
    } catch(e: IOException) {
      // assert: exception
    }
  }
}
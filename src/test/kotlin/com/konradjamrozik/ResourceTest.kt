// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class ResourceTest {

  private val ambiguous = "ambiguous.txt"
  private val unique_standalone = "unique_standalone.txt"
  private val unique_jarred = "unique_jarred.txt"
  
  @Test
  fun resolves_and_reads_resources() {

    // act
    val standalone_unique_res = Resource(unique_standalone)
    assertTrue(standalone_unique_res.urls.size == 1)
    assertTrue(standalone_unique_res.text.length > 0)

    // act
    Resource(unique_standalone, allowAmbiguity = true)
    // assert: no exception

    try {
      // act
      Resource("<path_that doesn't exist>")
      fail("Non-existing resources should eagerly throw exception.")
    } catch(e: IOException) {
      // assert: exception
    }

    // act
    val ambigFixture = Resource(ambiguous, allowAmbiguity = true)
    ambigFixture.urls.forEach { println(it) }
    ambigFixture.urls.forEach { assertTrue(it.text.length > 0) }
    assertEquals(2, ambigFixture.urls.size)

    try {
      // act
      Resource(ambiguous)
      fail("Ambiguity should be forbidden by default.")
    } catch(e: IOException) {
      // assert: exception
    }
  }

  @Test
  fun extracts_standalone_file_beside_container() {
    val file = Resource(unique_standalone).standaloneFile
    // KJA current work
    //val file2 = Resource(unique_jarred).standaloneFile
  }
}
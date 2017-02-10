// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.nio.file.Path
import java.nio.file.Paths

internal class ResourceTest {

  private val ambiguous = "ambiguous.txt"
  private val unique_standalone = "unique_standalone.txt"
  private val deeply_jarred = "deeply_jarred.txt"
  private val nested = "nested.jar"
  private val toplevel = "toplevel.jar"
  
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
  fun does_not_extract_path_from_plain_file()
  {
    val res = Resource(unique_standalone)
    
    // act
    res.withExtractedPath { assertEquals(res.path, this) }
    
    assertTrue("Deleted a standalone file that didn't had to be extracted from an archive, and thus, deleted.", 
      res.path.isRegularFile)

  }
  
  @Test
  fun extracts_path_from_jar_to_the_same_dir() 
  {
    var extractedPath: Path? = null
    val res = Resource(nested)
    
    // act
    res.withExtractedPath {
    
      extractedPath = this
      assertTrue(this.isRegularFile)
      assertNotEquals(res.url, this.toUri().toURL())
    }
    assertNotNull(extractedPath)
    assertFalse(extractedPath!!.isRegularFile)
  }

  @Test
  fun extracts_plain_file_by_just_copying() {
    val res = Resource(unique_standalone)
    val extracted: Path = res.extractTo(Paths.get("./temp_resources_extracted_from_jars"))
    assertTrue(extracted.isRegularFile)
    assertEquals(extracted.fileName.toString(), res.name)
  }
  
  @Test
  fun extracts_path_from_jar_to_dir() {
    val res = Resource(nested)
    val extracted: Path = res.extractTo(Paths.get("./temp_resources_extracted_from_jars"))
    assertTrue(extracted.isRegularFile)
    assertEquals(extracted.fileName.toString(), res.name)
  }
}
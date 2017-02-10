// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test
import java.nio.file.Path
import java.nio.file.Paths

internal class PathExtensionsKtTest
{
  @Test
  fun assertWithHamcrestMatcher() {
    // KJA curr work
    // prepare in-memory file system with files and contents
    val dir: Path = mapOf(
      "file1" to "contents 1\nabc-contentsX-cde 2", 
      "file2" to "3contentscont\nents3")
      .toInMemoryDir()
    
    // Act
    dir.replaceTextInAllFiles("contents","X")
    
    // assertThat(Arrays.asList("foo", "bar"), contains(Arrays.asList(equalTo("foo"), equalTo("bar"))))
    assertThat(dir.allFilesText, contains("X 1\nabc-XX-cde 2", "3Xcont\nents3"))
  }
}

private val Path.allFilesText: List<String>
  get() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

private fun  Path.replaceTextInAllFiles(sourceText: String, replacementTest: String) {}

private fun  <K, V> Map<K, V>.toInMemoryDir(): Path {
  return Paths.get("temp")
}

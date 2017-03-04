// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.core.IsEqual
import org.junit.jupiter.api.Test
import java.nio.file.Path

internal class PathExtensionsKtTest
{
  @Test
  fun `removes second column`() {
    val fooText = """
        |headerText_no1 second.column.title Third-Column:sub.title
        |           0.0   0 0.1
        |          20.0  10  .7
        |         300.0  50  33

       """.trimMargin()
    
    // Act
    val actual = fooText.removeColumn(2)
    
    assertThat(actual, IsEqual("""
        |headerText_no1 Third-Column:sub.title
        |           0.0 0.1
        |          20.0  .7
        |         300.0  33

       """.trimMargin()))
  }
  @Test
  fun `replaces text in all files in dir`() {
    
    val fooText = "contents 1\nabc-contentsX-cde 2"
    val barText = "3contentscont\nents3"
    val quxText = "do not replace contents"
    val dir: Path = mapOf(
      "foo.txt" to fooText, 
      "bar.txt" to barText,
      "subdir/qux.txt" to quxText)
      .toInMemoryDir()
    
    // Act
    dir.replaceTextInAllFiles("contents","X")
    
    assertThat(dir.allFilesTexts, 
      containsInAnyOrder(
        fooText.replace("contents", "X"),
        barText.replace("contents", "X")))
    val actualQuxText = dir.resolveRegularFile("subdir/qux.txt").text
    assertThat(actualQuxText, IsEqual(quxText))
  }
}


// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.codehaus.groovy.runtime.NioGroovyMethods
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test
import java.nio.file.Path

internal class PathExtensionsKtTest
{
  @Test
  fun `replaces text in all files`() {
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

private fun Map<String, String>.toInMemoryDir(): Path {
  
  val inMemoryFS = Jimfs.newFileSystem(Configuration.unix())
  val workingDir = inMemoryFS.getPath("/work")
  
  this.forEach { fileName, text ->
    val file = workingDir.resolve(fileName)
    file.createWithTextCreatingParentDirsIfNecessary(text)
  }
  
  return workingDir
}

private fun Path.replaceTextInAllFiles(sourceText: String, replacementText: String) {
  
  check(this.isDirectory)
  this.files.forEach { it.replaceText(sourceText, replacementText) }
}

fun Path.replaceText(sourceText: String, replacementText: String) {
  check(this.isRegularFile)
  this.text.replace(sourceText, replacementText)
}

val Path.text: String
  get() {
    check(this.isRegularFile)
    return NioGroovyMethods.getText(this)
  }

private val Path.allFilesText: List<String>
  get() {
    return emptyList() // KJA to implement
  }

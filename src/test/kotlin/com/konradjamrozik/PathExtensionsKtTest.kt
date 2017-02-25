// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.codehaus.groovy.runtime.NioGroovyMethods
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Test
import java.nio.file.Path
// KJA to clean up this file
internal class PathExtensionsKtTest
{
  @Test
  fun `replaces text in all files`() {
    
    val fooContents = "contents 1\nabc-contentsX-cde 2"
    val barContents = "3contentscont\nents3"
    
    val dir: Path = mapOf(
      "foo.txt" to fooContents, 
      "bar.txt" to barContents)
      .toInMemoryDir()
    
    // Act
    dir.replaceTextInAllFiles("contents","X")
    
    // assertThat(Arrays.asList("foo", "bar"), contains(Arrays.asList(equalTo("foo"), equalTo("bar"))))
    assertThat(dir.allFilesTexts, 
      containsInAnyOrder(
        fooContents.replace("contents","X"), 
        barContents.replace("contents","X")))
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
  this.writeText(this.text.replace(sourceText, replacementText))
}

val Path.text: String
  get() {
    check(this.isRegularFile)
    return NioGroovyMethods.getText(this)
  }

private val Path.allFilesTexts: Iterable<String>
  get() {
    check(this.isDirectory)
    return this.files.map(Path::text)
  }

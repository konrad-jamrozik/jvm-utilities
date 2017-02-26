// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.codehaus.groovy.runtime.IOGroovyMethods
import org.codehaus.groovy.runtime.ResourceGroovyMethods
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

val InputStream.text: String 
  get() = IOGroovyMethods.getText(this)

val URL.text: String
  get() = ResourceGroovyMethods.getText(this)

fun Map<String, String>.toInMemoryDir(): Path {
  
  val inMemoryFS = Jimfs.newFileSystem(Configuration.unix())
  val workingDir = inMemoryFS.getPath("/work")
  
  this.forEach { fileName, text ->
    val file = workingDir.resolve(fileName)
    file.createWithTextCreatingParentDirsIfNecessary(text)
  }
  
  return workingDir
}
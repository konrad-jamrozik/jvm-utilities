// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.github.konrad_jamrozik

import java.nio.file.Files
import java.nio.file.Path

/**
 * Returns [Path] pointing to a regular file denoted by the [file], resolved against the receiver.
 *
 * Throws an [IllegalStateException] if any of the assumptions is violated.
 */
fun Path.resolveRegularFile(file: String): Path
{
  check(this.isDirectory, { "$this.isDirectory" })
  
  checkNotNull(file)
  check(file.length > 0, { "file.length > 0" })
  
  val resolvedFile = this.resolve(file)

  check(resolvedFile.isRegularFile, { "$resolvedFile.isRegularFile" })
  
  return resolvedFile
}

/**
 * Calls [Files.isRegularFile] with the receiver.
 */
val Path.isRegularFile: Boolean
  
  get() = Files.isRegularFile(this)

/**
 * Calls [Files.isDirectory] with the receiver.
 */
val Path.isDirectory: Boolean
  get() = Files.isDirectory(this)
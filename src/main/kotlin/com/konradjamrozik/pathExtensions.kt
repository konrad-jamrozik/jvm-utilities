// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import java.nio.file.Files
import java.nio.file.Path

/**
 * Returns [Path] pointing to a regular file denoted by the [file], resolved against the receiver.
 *
 * Throws an [IllegalStateException] if any of the assumptions are violated.
 */
fun Path.resolveRegularFile(file: String): Path
{
  check(this.isDirectory, { "Failed check: receiver.isDirectory, where receiver is: $this" })
  
  checkNotNull(file)
  check(file.length > 0, { "Failed check: file.length > 0, where file is: '$file'" })
  
  val resolvedFile = this.resolve(file)

  check(resolvedFile.isRegularFile, { "Failed check: resolvedFile.isRegularFile, where resolvedFile is: $resolvedFile" })
  
  return resolvedFile
}


/**
 * Returns [Path] pointing to a dir denoted by the [dir], resolved against the receiver.
 *
 * Throws an [IllegalStateException] if any of the assumptions are violated.
 */
fun Path.resolveDir(dir: String): Path
{
  check(this.isDirectory, { "Failed check: receiver.isDirectory, where receiver is: $this" })

  checkNotNull(dir)
  check(dir.length > 0, { "Failed check: dir.length > 0, where dir is: '$dir'" })

  val resolvedDir = this.resolve(dir)

  check(resolvedDir.isDirectory, { "Failed check: resolvedDir.isDirectory, where resolvedDir is: $resolvedDir" })

  return resolvedDir
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

/**
 * Calls [Files.createDirectories] with the receiver's parent.
 */

fun Path.mkdirs(): Path? {
  check (!this.isDirectory)
  return Files.createDirectories(this.parent)
}

fun Path.createDirIfNotExists(): Boolean {
  if (Files.notExists(this)) {
    Files.createDirectory(this)
    check(this.isDirectory)
    return true
  }
  return false
}
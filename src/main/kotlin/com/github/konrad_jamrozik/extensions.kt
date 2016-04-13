package com.github.konrad_jamrozik

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun Path.resolveRegularFile(file: String): Path
{
  check(Files.isDirectory(this), {"Files.isDirectory($this)"})
  check(file.length > 0, {"file.length > 0"})
  
  val resolvedFile = this.resolve(file)
  
  check(Files.isRegularFile(resolvedFile), {"Files.isRegularFile($resolvedFile)"})
  
  return resolvedFile
}

/**
 * Treats the receiver as a system environment variable pointing to an existing directory in the default file system.
 * 
 * Returns [Path] pointing to this directory.
 */
fun String.asEnvironmentDir(): Path
{
  val value = System.getenv(this)
  
  checkNotNull(value, {"System.getenv($this) should denote a directory. It is instead null" })
  check(value.length > 0, {"System.getenv($this) should denote a directory. It is instead an empty string."})
  
  val dir = Paths.get(value)
  
  check(Files.isDirectory(dir), {"System.getenv($this) should be a path pointing to an existing directory. " +
      "The faulty path: ${dir.toString()}"})
  
  return dir
}
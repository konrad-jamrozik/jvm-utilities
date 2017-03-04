// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Treats the receiver [String] as a system environment variable pointing to an existing directory in the default [FileSystem].
 *
 * Returns [Path] pointing to this directory.
 *
 * Throws an [IllegalStateException] if any of the assumptions is violated.
 */
val String.asEnvDir: Path get() {
  val value = System.getenv(this)

  checkNotNull(value, { "System.getenv($this) should denote a directory. It is instead null." })
  check(value.isNotEmpty(), { "System.getenv($this) should denote a directory. It is instead an empty string." })

  val dir = Paths.get(value)

  check(dir.isDirectory, {
    "System.getenv($this) should be a path pointing to an existing directory. " +
      "The faulty path: $dir"
  })

  return dir
}

fun String.removeColumn(column: Int): String {
  return this.lines().map { line ->
    val columns = Regex("\\s*\\S+").findAll(line).map { it.value }.toList()

    columns.filterIndexed { index, _ -> index+1 != column }.joinToString(separator = "")
  }.joinToString(separator = "\n")
}
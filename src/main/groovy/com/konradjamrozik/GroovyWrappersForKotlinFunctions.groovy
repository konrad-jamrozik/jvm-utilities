// Author: Konrad Jamrozik, github.com/konrad-jamrozik
package com.konradjamrozik

import kotlin.text.StringsKt

import java.nio.file.Path

class GroovyWrappersForKotlinFunctions
{
  /**
   * Returns StringExtensionsKt.getAsEnvDir(self)
   */
  static Path getAsEnvDir(String self)
  {
    return StringExtensionsKt.getAsEnvDir(self)
  }

  /**
   * Returns PathExtensionsKt.resolveRegularFile(self, file)
   */
  static Path resolveRegularFile(Path self, String file)
  {
    return PathExtensionsKt.resolveRegularFile(self, file)
  }

  /**
   * Returns PathExtensionsKt.resolveDir(self, dir)
   */
  static Path resolveDir(Path self, String dir)
  {
    return PathExtensionsKt.resolveDir(self, dir)
  }

  /**
   * Returns PathExtensionsKt.isRegularFile(self)
   */
  static boolean isRegularFile(Path self)
  {
    return PathExtensionsKt.isRegularFile(self)
  }

  /**
   * Returns PathExtensionsKt.isDirectory(self)
   */
  static boolean isDirectory(Path self)
  {
    return PathExtensionsKt.isDirectory(self)
  }

  /**
   * Returns PathExtensionsKt.createDirIfNotExists(self)
   */
  static Boolean createDirIfNotExists(Path self)
  {
    return PathExtensionsKt.createDirIfNotExists(self)
  }
  
  static String withoutExtension(String self)
  {
    return StringsKt.substringBeforeLast(self, ".", self)
  }

  static void mkdirs(Path self)
  {
    PathExtensionsKt.mkdirs(self)
  }
}

// Author: Konrad Jamrozik, github.com/konrad-jamrozik
package com.github.konrad_jamrozik

import java.nio.file.Path

class KotlinExtensionsWrappers
{
  /**
   * Returns StringExtensionsKt.envDir(self)
   */
  public static Path getAsEnvDir(String self)
  {
    return StringExtensionsKt.getAsEnvDir(self)
  }

  /**
   * Returns PathExtensionsKt.resolveRegularFile(self, file)
   */
  public static Path resolveRegularFile(Path self, String file)
  {
    return PathExtensionsKt.resolveRegularFile(self, file)
  }

  /**
   * Returns PathExtensionsKt.isRegularFile(self)
   */
  public static boolean isRegularFile(Path self)
  {
    return PathExtensionsKt.isRegularFile(self)
  }

  /**
   * Returns PathExtensionsKt.isDirectory(self)
   */
  public static boolean isDirectory(Path self)
  {
    return PathExtensionsKt.isDirectory(self)
  }
}

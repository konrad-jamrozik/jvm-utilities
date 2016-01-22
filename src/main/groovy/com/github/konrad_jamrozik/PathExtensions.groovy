// Author: Konrad Jamrozik, github.com/konrad-jamrozik
//
package com.github.konrad_jamrozik

import org.apache.commons.io.FilenameUtils

import java.nio.file.Path

class PathExtensions
{
  public static String getExtension(Path self)
  {
    FilenameUtils.getExtension(self.fileName.toString())
  }

  public static void copyDirRecursivelyToDirInDifferentFileSystem(Path self, Path destDir)
  {
    new FileSystemsOperations().copyDirRecursivelyToDirInDifferentFileSystem(self, destDir)
  }

  public static void copyDirContentsRecursivelyToDirInDifferentFileSystem(Path self, Path destDir)
  {
    new FileSystemsOperations().copyDirContentsRecursivelyToDirInDifferentFileSystem(self, destDir)
  }

}


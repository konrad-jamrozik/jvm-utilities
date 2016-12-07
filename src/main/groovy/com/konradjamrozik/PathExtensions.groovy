// Author: Konrad Jamrozik, github.com/konrad-jamrozik


package com.konradjamrozik

import org.apache.commons.io.FilenameUtils

import java.nio.file.Path

class PathExtensions
{
  static String getExtension(Path self)
  {
    FilenameUtils.getExtension(self.fileName.toString())
  }
  
  static void copyDirRecursivelyToDirInDifferentFileSystem(Path self, Path destDir)
  {
    new FileSystemsOperations().copyDirRecursivelyToDirInDifferentFileSystem(self, destDir)
  }

  static void copyDirContentsRecursivelyToDirInDifferentFileSystem(Path self, Path destDir)
  {
    new FileSystemsOperations().copyDirContentsRecursivelyToDirInDifferentFileSystem(self, destDir)
  }

  static void copyFilesToDirInDifferentFileSystem(List<Path> self, Path destDir)
  {
    new FileSystemsOperations().copyFilesToDirInDifferentFileSystem(self, destDir)
  }

}


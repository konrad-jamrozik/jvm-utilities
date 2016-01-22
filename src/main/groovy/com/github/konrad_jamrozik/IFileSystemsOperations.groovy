package com.github.konrad_jamrozik

import java.nio.file.Path

interface IFileSystemsOperations
{
  void copyDirRecursivelyToDirInDifferentFileSystem(Path dir, Path dest)

}
package com.github.konrad_jamrozik

import java.nio.file.Files
import java.nio.file.Path

class FileSystemsOperations implements IFileSystemsOperations
{
  @Override
  void copyDirRecursivelyToDirInDifferentFileSystem(Path copiedDir, Path destDir)
  {
    assert Files.isDirectory(copiedDir)
    assert Files.isDirectory(destDir)
    assert copiedDir.fileSystem != destDir.fileSystem

    copyPath(copiedDir, destDir)
    copiedDir.eachFileRecurse {Path it -> copyPath(it, destDir) }
  }

  private static Path copyPath(Path it, Path dest)
  {
    Path itInDest = dest.resolve(it.toString())

    assert !Files.exists(itInDest)

    if (Files.isDirectory(it))
    {
      Files.createDirectory(itInDest)

    } else if (Files.isRegularFile(it))
    {
      Files.copy(it, itInDest)

    } else
      assert false

    return itInDest
  }
}

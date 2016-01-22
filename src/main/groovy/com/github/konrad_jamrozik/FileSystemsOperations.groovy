package com.github.konrad_jamrozik

import java.nio.file.Files
import java.nio.file.Path

class FileSystemsOperations implements IFileSystemsOperations
{
  @Override
  List<String> copy(Path dir, Path dest)
  {
    assert Files.isDirectory(dir)
    assert Files.isDirectory(dest)
    assert dir.fileSystem != dest.fileSystem

    List<String> destPaths = []

    destPaths += copyPath(dir, dest).toAbsolutePath().toString()
    dir.eachFileRecurse {Path it ->
      destPaths += copyPath(it, dest).toAbsolutePath().toString()
    }

    return destPaths
  }

  private static Path copyPath(Path it, Path dest)
  {
    Path itInDest = dest.resolve(it.toString())

    if (Files.isDirectory(it))
    {
      Files.createDirectories(itInDest)

    } else if (Files.isRegularFile(it))
    {
      Files.createDirectories(itInDest.parent)
      Files.copy(it, itInDest)

    } else throw new AssertionError()

    return itInDest
  }
}

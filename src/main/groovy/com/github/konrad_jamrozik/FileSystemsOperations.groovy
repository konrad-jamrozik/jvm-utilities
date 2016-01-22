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
    assert copiedDir.parent != null

    copyPath(copiedDir, copiedDir.parent, destDir)
    copiedDir.eachFileRecurse {Path it -> copyPath(it, copiedDir.parent, destDir) }
  }

  @Override
  void copyDirContentsRecursivelyToDirInDifferentFileSystem(Path srcDir, Path destDir)
  {
    assert Files.isDirectory(srcDir)
    assert Files.isDirectory(destDir)
    assert srcDir.fileSystem != destDir.fileSystem

    srcDir.eachFileRecurse {Path it ->
      copyPath(it, srcDir, destDir) }
  }


  private static Path copyPath(Path it, Path src, Path dest)
  {
    assert it != null
    assert Files.isDirectory(src)
    assert Files.isDirectory(dest)

    Path itInDest = mapToTarget(it, src, dest)

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

  private static Path mapToTarget(Path path, Path srcDir, Path destDir)
  {
    return destDir.resolve(srcDir.relativize(path).toString().replace(srcDir.fileSystem.separator, destDir.fileSystem.separator))
  }
}

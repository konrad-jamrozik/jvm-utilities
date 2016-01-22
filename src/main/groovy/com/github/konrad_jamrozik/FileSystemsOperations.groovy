package com.github.konrad_jamrozik

import java.nio.file.Files
import java.nio.file.Path

class FileSystemsOperations implements IFileSystemsOperations
{
  @Override
  void copy(Path target, Path dest)
  {
    assert target != null
    assert dest != null
    assert target.fileSystem != dest.fileSystem
    assert Files.exists(target)
    assert Files.exists(dest)

    // KJA current work
    assert false: "Not yet implemented!"
  }
}

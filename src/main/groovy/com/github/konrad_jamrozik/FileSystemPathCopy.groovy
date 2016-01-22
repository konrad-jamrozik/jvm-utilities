package com.github.konrad_jamrozik

import java.nio.file.Path

class FileSystemPathCopy implements IFileSystemPathCopy
{
  Path target
  Path src
  Path dest

  FileSystemPathCopy(Path target, Path src, Path dest)
  {
    this.target = target
    this.src = src
    this.dest = dest
  }

  @Override
  void execute()
  {
    // KJA current work
    assert false: "Not yet implemented!"
  }
}

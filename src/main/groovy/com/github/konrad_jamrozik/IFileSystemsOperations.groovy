package com.github.konrad_jamrozik

import java.nio.file.Path

interface IFileSystemsOperations
{
  List<String> copy(Path dir, Path dest)

}
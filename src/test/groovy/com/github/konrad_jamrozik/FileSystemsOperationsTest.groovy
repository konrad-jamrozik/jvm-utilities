package com.github.konrad_jamrozik

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters

import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4)
public class FileSystemsOperationsTest
{

  @Test
  void "Copies directory to dir in a different file system"()
  {
    Path dir = buildFixture()
    Path dest = buildDestFs()

    // Act
    dir.copyDirRecursivelyToDirInDifferentFileSystem(dest)

    def expectedPaths = [
      "/work/dest/dir",
      "/work/dest/dir/data1.txt",
      "/work/dest/dir/subdir",
      "/work/dest/dir/subdir/data2.txt"
    ]

    expectedPaths.each { assert Files.exists(dest.getFileSystem().getPath(it)) }
    assert dest.resolve("dir/data1.txt").text == "123"
    assert dest.resolve("dir/subdir/data2.txt").text == "abc"
  }

  @Test
  void "Copies directory contents to dir in a different file system"()
  {
    Path dir = buildFixture()
    Path dest = buildDestFs()

    // Act
    dir.copyDirContentsRecursivelyToDirInDifferentFileSystem(dest)

    def expectedPaths = [
      "/work/dest/",
      "/work/dest/data1.txt",
      "/work/dest/subdir",
      "/work/dest/subdir/data2.txt"
    ]

    expectedPaths.each { assert Files.exists(dest.getFileSystem().getPath(it)) }
    assert dest.resolve("data1.txt").text == "123"
    assert dest.resolve("subdir/data2.txt").text == "abc"
  }

  private static Path buildFixture()
  {
    FileSystem sourceFs = Jimfs.newFileSystem(Configuration.unix())
    Path dir = sourceFs.getPath("/work/dir")
    Path subdir = dir.resolve("subdir")
    Path data1 = dir.resolve("data1.txt")
    Path data2 = subdir.resolve("data2.txt")
    Files.createDirectories(subdir)
    Files.createFile(data1)
    data1.write("123")
    Files.createFile(data2)
    data2.write("abc")

    // @formatter:off
    assert dir     .toRealPath().toString() == "/work/dir"
    assert data1   .toRealPath().toString() == "/work/dir/data1.txt"
    assert subdir  .toRealPath().toString() == "/work/dir/subdir"
    assert data2   .toRealPath().toString() == "/work/dir/subdir/data2.txt"
    // @formatter:on

    assert data1.text == "123"
    assert data2.text == "abc"
    return dir
  }

  private static Path buildDestFs()
  {
    FileSystem targetFs = Jimfs.newFileSystem(Configuration.unix())
    Path dest = targetFs.getPath("dest")
    Files.createDirectories(dest)
    assert Files.isDirectory(dest)
    return dest
  }


}
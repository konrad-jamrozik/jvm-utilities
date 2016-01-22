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
  void "Transfers directory with contents"()
  {
    FileSystem sourceFs = Jimfs.newFileSystem(Configuration.unix())
    Path dir = sourceFs.getPath("dir")
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

    FileSystem targetFs = Jimfs.newFileSystem(Configuration.unix())
    Path dest = targetFs.getPath("dest")
    Files.createDirectories(dest)
    assert Files.isDirectory(dest)

    // Act
    List<String> destPaths = new FileSystemsOperations().copy(dir, dest)

    def expectedPaths = [
      "/work/dest/dir",
      "/work/dest/dir/data1.txt",
      "/work/dest/dir/subdir",
      "/work/dest/dir/subdir/data2.txt"
    ]

    expectedPaths.each { assert it in destPaths }
    destPaths.each { assert it in expectedPaths }


    assert dest.resolve("dir/data1.txt").text == "123"
    assert dest.resolve("dir/subdir/data2.txt").text == "abc"
  }

}
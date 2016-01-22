package com.github.konrad_jamrozik

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.FixMethodOrder
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters

import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4)
public class FileSystemPathCopyTest
{

  @Test
  @Ignore("not yet passing")
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

    def sut = new FileSystemPathCopy(dir, sourceFs.getPath("."), targetFs.getPath("destination"))

    // Act
    sut.execute()

    [
      "/work/destination/dir",
      "/work/destination/subdir"
    ].each {
      assert Files.isDirectory(targetFs.getPath(it))
    }

    [
      "/work/destination/dir/data1.txt",
      "/work/destination/subdir/data2.txt"
    ].each {
      assert Files.isRegularFile(targetFs.getPath(it))
    }
  }

}
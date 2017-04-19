// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import com.google.common.base.Stopwatch
import org.junit.jupiter.api.Test
import java.io.FileWriter
import java.util.concurrent.TimeUnit

internal class KotlinScratchpadTest {

  @Test
  fun MainTest() {
    // val dir = Paths.get("""""")
    //dir.replaceTextInAllFiles("a","b")
  }

  @Test
  fun PerfTest() {
    FileWriter("./temp.txt", false).use { file ->

      val x = -1;
      val stopwatch = Stopwatch.createStarted()
      (1..100000).forEach {
        file.write("this is log msg $it\n")
      }
      println(stopwatch.elapsed(TimeUnit.MILLISECONDS))
      stopwatch.reset()
      stopwatch.start()
      (1..10000000).forEach {
        invariant(x != it)
      }
      println(stopwatch.elapsed(TimeUnit.MILLISECONDS))

    }
  }

  fun invariant(inv: Boolean) {
    if (!inv) {
      throw Exception()
    }
  }
}

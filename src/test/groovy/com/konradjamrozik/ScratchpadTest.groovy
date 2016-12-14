// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4)
 class ScratchpadTest
{

  @Test
  void "test"()
  {
    def sw = new StringWriter()
    sw.withWriter {wr ->

      wr.write("widgets ")

    }
    println sw.toString()
  }
}


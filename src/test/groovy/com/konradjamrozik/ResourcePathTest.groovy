// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4)
public class ResourcePathTest
{

  @Test
  void "Constructs resource path"()
  {
    ResourcePath fixture = new ResourcePath("fixture.txt")
    assert fixture.url.toString().startsWith("file:")
    assert fixture.alternativeUrls.findSingle().toString().startsWith("jar:")
  }
}
// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik;

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4)
public class MapExtensionsTest
{
  @Test
  void "flips keys nesting"()
  {
    assert [
      a: [x: 1, y: 2],
      b: [x: 3, y: 4]
    ].flipKeysNesting() == [
      x: [a: 1, b: 3],
      y: [a: 2, b: 4]
    ]
  }

}
// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import kotlin.test.assertFailsWith

class FirstMatchFirstGroupTest {

  @Test
  fun `matches`() {

    assertFailsWith(Exception::class) { FirstMatchFirstGroup("target", "target")}
    assertThat(FirstMatchFirstGroup("target", "(target)").value, `is`("target"))
    assertThat(FirstMatchFirstGroup("target", "()", "(target)", "target").value, `is`("target"))
    assertThat(FirstMatchFirstGroup("target", "target", "(target)", "()").value, `is`("target"))
    
    assertThat(FirstMatchFirstGroup("label='xyz'", "label='(.*)'").value, `is`("xyz"))
    assertFailsWith(Exception::class) { FirstMatchFirstGroup("label=''", "label='(.*)'")}
  }
}
  

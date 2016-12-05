// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class FirstMatchTest {

  @Test
  fun `matches`() {

    assertThat(FirstMatch("target", "target").successful, `is`(false))
    assertThat(FirstMatch("target", "(target)").successful, `is`(true))
    assertThat(FirstMatch("target", "(target)", "target").successful, `is`(true))
    assertThat(FirstMatch("target", "target","(target)").successful, `is`(false))
  }
}
  

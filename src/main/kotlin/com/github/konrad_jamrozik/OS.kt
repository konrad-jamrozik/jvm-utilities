// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.github.konrad_jamrozik

class OS 
{
  companion object {
    val isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows")
  }
}
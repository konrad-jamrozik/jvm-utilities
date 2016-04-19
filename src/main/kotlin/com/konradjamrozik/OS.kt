// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

class OS 
{
  companion object {
    val isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows")
  }
}
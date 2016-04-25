// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import java.io.IOException
import java.net.URL

class Resource @JvmOverloads constructor(val path: String, allowAmbiguity: Boolean = false) {

  val urls: List<URL> = {

    val urls = ClassLoader.getSystemResources(path).toList()
    
    if (urls.isEmpty())
      throw IOException("No resource URLs found for path \"$path\"")
    
    if (!allowAmbiguity && urls.size > 1)
      throw IOException("More than one resource URL found for path $path. " +
        "The found URLs:\n${urls.joinToString(separator = "\n")}")
    
    urls
  }()
  
  val text: String by lazy { urls.first().text }
}
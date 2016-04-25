// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import java.io.IOException
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths

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
  
  val standaloneFile: Path by lazy {
    
    check(!allowAmbiguity, { "check failed: !allowAmbiguity"})
    val url = urls.single()
    
    if (url.protocol == "file")
      Paths.get(url.toURI())
    else
      copyBesideContainer(url)
  }

  private fun copyBesideContainer(url: URL): Path {
    // KJA to implement method stub
    throw UnsupportedOperationException("not implemented")
  }
}
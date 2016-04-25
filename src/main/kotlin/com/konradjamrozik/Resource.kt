// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import java.io.File
import java.io.IOException
import java.net.JarURLConnection
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class Resource @JvmOverloads constructor(val name: String, val allowAmbiguity: Boolean = false) {

  val urls: List<URL> = {

    val urls = ClassLoader.getSystemResources(name).toList()
    
    if (urls.isEmpty())
      throw IOException("No resource URLs found for path \"$name\"")
    
    if (!allowAmbiguity && urls.size > 1)
      throw IOException("More than one resource URL found for path $name. " +
        "The found URLs:\n${urls.joinToString(separator = "\n")}")
    
    urls
  }()

  val text: String by lazy {
    url.text
  }

  val url: URL by lazy {
    check(!allowAmbiguity, { "check failed: !allowAmbiguity" })
    urls.single()
  }

  val path: Path by lazy {
    check(url.protocol == "file", {
      "cannot get path on a resource whose protocol is not 'file'. " +
        "The protocol is instead '${urls.single().protocol}'"
    })
    Paths.get(urls.single().toURI())
  }

  val file: File by lazy {
    check(!allowAmbiguity, { "check failed: !allowAmbiguity" })
    File(url.toURI())
  }

  private fun copyBesideContainer(url: URL): Path {
    // Example url: jar:file:/C:/my/local/repos/github/utilities/build/resources/test/toplevel.jar!/nested.jar

    // KJA it is here: jar:file:/C:/my/local/repos/github/droidmate/dev/droidmate/projects/core/build/libs/core-dev.jar!/uiautomator-daemon.jar
    // Extract it to  jar:file:/C:/my/local/repos/github/droidmate/dev/droidmate/projects/core/build/libs/uiautomator-daemon.jar
    // Reuse existing Resource method and see this: http://stackoverflow.com/questions/11472408/extracting-a-file-from-the-currently-running-jar-through-code

    val jarUrlConnection = url.openConnection() as JarURLConnection


    val jarFile = File(jarUrlConnection.jarFileURL.toURI())
    // Example jarFile: C:\my\local\repos\github\utilities\build\resources\test\toplevel.jar
    val jarDir = jarFile.parent
    // Example jarDir: C:\my\local\repos\github\utilities\build\resources\test
    val jarEntry = jarUrlConnection.jarEntry.toString()
    // Example jarEntry: nested.jar
    val targetPath = Paths.get(jarDir, jarEntry)
    // Example targetPath: C:\my\local\repos\github\utilities\build\resources\test\nested.jar
    Files.copy(url.openStream(), targetPath)
    return targetPath
  }

  fun withExtractedPath(block: Path.() -> Unit) {

    if (url.protocol == "file")
      Paths.get(url.toURI()).block()
    else {

      val extractedPath = copyBesideContainer(url)

      extractedPath.block()

      check (extractedPath.isRegularFile,
        { ("Failure: extracted path $extractedPath has been deleted while being processed in the 'withExtractedPath' block.") })

      Files.delete(extractedPath)
    }
  }
}
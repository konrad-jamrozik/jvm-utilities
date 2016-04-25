// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.codehaus.groovy.runtime.IOGroovyMethods
import org.codehaus.groovy.runtime.ResourceGroovyMethods
import java.io.InputStream
import java.net.URL

val InputStream.text: String 
  get() = IOGroovyMethods.getText(this)

val URL.text: String
  get() = ResourceGroovyMethods.getText(this)
// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import org.slf4j.LoggerFactory

fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)

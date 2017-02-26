// Author: Konrad Jamrozik, github.com/konrad-jamrozik

package com.konradjamrozik

import java.util.stream.Collectors
import java.util.stream.Stream

fun <T> Stream<T>?.toList(): MutableList<T> = this?.collect(Collectors.toList()) ?: mutableListOf()
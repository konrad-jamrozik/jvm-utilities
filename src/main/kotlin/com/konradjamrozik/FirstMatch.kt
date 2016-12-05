package com.konradjamrozik

class FirstMatch(val target: String, vararg regexps: String)
{
  val value: String?
  val successful: Boolean
  val exception: Exception?
  
  init {
    require(regexps.isNotEmpty())
    
    val firstMatchingRegexp = regexps.map(::Regex).find { it.containsMatchIn(target) }
    if (firstMatchingRegexp == null) {
      value = null
      successful = false
      exception = Exception("None of the regexps matches the target, which is: $target") 
    }
    else 
    {
      val firstMatchResult = firstMatchingRegexp.find(target)!!
      if (firstMatchResult.groupValues.size < 2)
      {
        value = null
        successful = false
        exception = Exception("First matching regexp has no groups. At least one is necessary. Matching regexp: $firstMatchingRegexp Target: $target")
      }
      else {
        value = firstMatchResult.groupValues[1]
        successful = true
        exception = null
      }
    }
  }

  
}


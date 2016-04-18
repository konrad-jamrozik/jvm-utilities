# Utilities [![Build Status](https://travis-ci.org/konrad-jamrozik/utilities.svg?branch=master)](https://travis-ci.org/konrad-jamrozik/utilities)
  
  Author: Konrad Jamrozik, github.com/konrad-jamrozik


A library of code I find useful in my projects.

### Depending on this library

In Gradle:

```Gradle
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}

ext.utilities_version = '2b983aad11' // replace with the desired commit 

dependencies {
    compile "com.github.konrad-jamrozik:utilities:$utilities_version"
}
```

Please refer to [this project on jitpack.io](https://jitpack.io/#konrad-jamrozik/utilities) for build logs, information how to 
use other build systems, how to depend on branches, etc.

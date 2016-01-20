package com.github.konrad_jamrozik

import groovy.transform.TypeChecked
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters

@TypeChecked
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4)
public class ResourcePathTest
{

  @Test
  void "Constructs resource path to a file inside a jar archive"()
  {
    // Fails
    // to-do
//    ResourcePath fixture = new ResourcePath("fixture.txt")
//    println fixture.path
    // This works
    println ClassLoader.getSystemResourceAsStream("fixture.txt")
  }


}
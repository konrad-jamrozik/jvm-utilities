// Author: Konrad Jamrozik, github.com/konrad-jamrozik
//
package com.github.konrad_jamrozik

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam

@SuppressWarnings("GroovyUnusedDeclaration")
class IterableExtensions
{
  public static <T> String truncateAndPrint(Iterable<T> self, int max)
  {
    String out = self.take(max)*.toString().join("\n")

    if (self.size() > max)
    {
      int remainder = self.size() - max
      assert remainder > 0
      String msg = "...(left out $remainder items)"
      out += "\n" + msg
    }

    return out
  }

  public static <T> T findSingle(Iterable<T> self, @ClosureParams(FirstParam.FirstGenericType.class) Closure closure)
  {
    def result = self.findAll(closure)
    assert result.size() == 1
    return result[0]
  }

  public static <T> T findSingle(Iterable<T> self)
  {
    assert self.size() == 1
    return self[0]
  }

  public static <T> T findSingleOrDefault(Iterable<T> self, T defaultVal)
  {
    assert self.size() <= 1

    if (self.size() == 1)
      return self[0]
    else
      return defaultVal
  }


  public static <T> boolean noDuplicates(Iterable<T> self)
  {
    return self.size() == self.toUnique().size()
  }

  public static <T> boolean allUnique(Iterable<T> self)
  {
    return self.noDuplicates()
  }

  public static <T> boolean isSubset(Iterable<T> left, Iterable<T> right)
  {
    assert left.noDuplicates()
    assert right.noDuplicates()

    Set<T> intersectionSet = left.intersect(right) as Set

    return left as Set == intersectionSet

  }
}

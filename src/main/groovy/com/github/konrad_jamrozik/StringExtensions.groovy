// Author: Konrad Jamrozik, github.com/konrad-jamrozik
//
package com.github.konrad_jamrozik

@SuppressWarnings("GroovyUnusedDeclaration")
class StringExtensions
{

  public static String truncate(String self, int max)
  {
    String msg = "...(trunc.)"
    assert max > msg.size()

    if (self.size() > max)
    {
      String truncatedSelf = self.take(max - msg.size()) + msg
      assert truncatedSelf.size() <= max
      return truncatedSelf
    } else
      return self.size()
  }

  /**
   * Wrapper over com.github.konrad_jamrozik.asEnvironmentDir
   */
  public static String asEnvironmentDirGroovy(String self)
  {
    return ExtensionsKt.asEnvironmentDir(self)
  }
}

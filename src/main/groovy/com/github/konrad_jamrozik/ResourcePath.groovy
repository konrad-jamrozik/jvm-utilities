// Author: Konrad Jamrozik, github.com/konrad-jamrozik
//
package com.github.konrad_jamrozik

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * <p>
 * A {@link Path} representing an existing system resource.
 *
 * </p><p>
 *
 * In gradle-based projects, the resources dirs that are searched are:
 * <pre><code>
 *   build/resources/main
 *   build/resources/test
 * </code></pre>
 *
 * These directories are populated during gradle {@code process (Test)Resources} task from following dirs:
 * <pre><code>
 *   [proj_root_dir]/src/main/resources
 *   [proj_root_dir]/src/test/resources
 * </code></pre>
 * so if you want to ensure a resource is available, put it in one of the dirs listed directly above and then rerun
 * {@code process (Test)resources} gradle task.
 *
 * <br/>{@code ==========}
 *
 * </p><p>
 *
 * Example: when this class is constructed with a constructor param of:
 *
 * <pre><code>
 *   generated/Monitor.java
 * </code></pre>
 *
 * and the classpath is for the project located in:
 *
 * <pre><code>
 *   [repo_root]\dev\droidmate\projects\monitor-generator
 * </code></pre>
 *
 * then {@code Path} represented by this object will be:
 *
 * <pre><code>
 *   [repo_root]\dev\droidmate\projects\monitor-generator\build\resources\test\generated\Monitor.java
 * </code></pre>
 *
 * </p><p>
 * Known limitation: doesn't support jar files. See:
 * <a href="
 * http://stackoverflow.com/questions/22605666/java-access-files-in-jar-causes-java-nio-file-filesystemnotfoundexception
 * ">this SO question</a>.
 */
class ResourcePath
{

  @Delegate
  Path path

  public ResourcePath(String pathString)
  {
    URL resourceURL = ClassLoader.getSystemResource(pathString)
    assert resourceURL != null: "The call to ClassLoader.getSystemResource(\"$pathString\") was expected to return non-null " +
      "resource URL but it returned null instead"

    Path path = Paths.get(resourceURL.toURI())
    assert Files.isRegularFile(path) || Files.isDirectory(path):
      "Resource named '$path' is not a regular file nor directory.\n" +
        "Path to the resource: ${path.toAbsolutePath()}"

    this.path = path
  }
}

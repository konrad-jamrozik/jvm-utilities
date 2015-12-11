// Copyright (c) 2013-2015 Saarland University Software Engineering Chair.
// All right reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org
package com.github.konrad_jamrozik

import org.apache.commons.io.FilenameUtils

import java.nio.file.Path

@SuppressWarnings("GroovyUnusedDeclaration")
class PathExtensions
{
  public static String getExtension(Path self)
  {
    FilenameUtils.getExtension(self.fileName.toString())
  }
}


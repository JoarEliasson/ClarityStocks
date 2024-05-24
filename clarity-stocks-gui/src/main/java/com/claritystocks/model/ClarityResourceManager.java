package com.claritystocks.model;

import java.net.URL;

public class ClarityResourceManager {
  public static String getCssResource(String fileName) {
    URL resource = ClarityResourceManager.class.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("Resource not found: " + fileName);
    }
    return resource.toExternalForm();
  }
}

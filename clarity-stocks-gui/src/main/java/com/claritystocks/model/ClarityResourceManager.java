package com.claritystocks.model;

import java.net.URL;

/**
 * This class is responsible for managing the resources used in the application.
 * <p>
 * The class provides a method for getting the URL of a CSS resource.
 * </p>
 *
 * @author Joar Eliasson
 */
public class ClarityResourceManager {
  public static String getCssResource(String fileName) {
    URL resource = ClarityResourceManager.class.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("Resource not found: " + fileName);
    }
    return resource.toExternalForm();
  }
}

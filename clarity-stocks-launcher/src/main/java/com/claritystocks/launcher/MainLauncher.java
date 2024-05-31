package com.claritystocks.launcher;

import com.claritystocks.view.GUIMainApplication;

/**
 * This class is the main launcher for the application.
 * <p>
 * Clarity Stocks is run from this class in a separate module, to maintain a clean separation
 * between the GUI and the rest of the application, including build and packaging logic.
 * </p>
 *
 * @author Joar Eliasson
 */
public class MainLauncher {

  public static void main(String[] args) {
    GUIMainApplication.main(args);
  }
}
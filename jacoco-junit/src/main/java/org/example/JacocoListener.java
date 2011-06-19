package org.example;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class JacocoListener extends RunListener {

  public JacocoListener() throws Exception {
    System.out.println("Using JaCoCo " + JacocoController.getVersion());
  }

  @Override
  public void testStarted(Description description) throws Exception {
    String name = getName(description);
    System.out.println("Test " + name + " started");

    JacocoController.setSessionId(name);
    JacocoController.reset();
  }

  @Override
  public void testFinished(Description description) throws Exception {
    dump("target/jacoco.exec", true, true);

    String name = getName(description);
    System.out.println("Test " + name + " finished");
  }

  private static String getName(Description description) {
    return description.getClassName() + "." + description.getMethodName();
  }

  private static void dump(String destfile, boolean append, boolean reset) throws Exception {
    byte[] dump = JacocoController.dump(reset);
    // Save dump
    new File(destfile).getParentFile().mkdirs();
    OutputStream output = new BufferedOutputStream(new FileOutputStream(destfile, true));
    output.write(dump);
    output.close();
  }
}

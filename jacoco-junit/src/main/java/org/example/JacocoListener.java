package org.example;

import org.jacoco.core.runtime.RemoteControlReader;
import org.jacoco.core.runtime.RemoteControlWriter;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class JacocoListener extends RunListener {

  private static final String ADDRESS = "localhost";
  private static final int PORT = 6300;

  @Override
  public void testStarted(Description description) throws Exception {
    System.out.println("Test " + getName(description) + " started");
  }

  @Override
  public void testFinished(Description description) throws Exception {
    System.out.println("Test " + getName(description) + " finished");
    save("target/jacoco/" + getName(description) + ".exec");
  }

  private String getName(Description description) {
    return description.getClassName() + "." + description.getMethodName();
  }

  private void save(String filename) throws IOException {
    new File(filename).getParentFile().mkdirs();
    final FileOutputStream localFile = new FileOutputStream(filename);
    final RemoteControlWriter localWriter = new RemoteControlWriter(localFile);

    // Open a socket to the coverage agent:
    final Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
    final RemoteControlWriter writer = new RemoteControlWriter(socket.getOutputStream());
    final RemoteControlReader reader = new RemoteControlReader(socket.getInputStream());
    reader.setSessionInfoVisitor(localWriter);
    reader.setExecutionDataVisitor(localWriter);

    // Send a dump and reset commands and read the response:
    writer.visitDumpCommand(true, true);
    reader.read();

    socket.close();
    localFile.close();
  }
}

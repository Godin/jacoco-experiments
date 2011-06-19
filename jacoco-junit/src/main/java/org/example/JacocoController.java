package org.example;

import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.ObjectName;

public final class JacocoController {

  private static final String OBJECT_NAME = "org.jacoco:type=Runtime";

  private static final String VERSION_ATTRIBUTE = "Version";
  private static final String SESSION_ID_ATTRIBUTE = "SessionId";

  private static final String DUMP_OPERATION = "dump";
  private static final String RESET_OPERATION = "reset";

  public static String getVersion() throws Exception {
    return (String) ManagementFactory.getPlatformMBeanServer()
        .getAttribute(new ObjectName(OBJECT_NAME), VERSION_ATTRIBUTE);
  }

  public static String getSessionId() throws Exception {
    return (String) ManagementFactory.getPlatformMBeanServer()
        .getAttribute(new ObjectName(OBJECT_NAME), SESSION_ID_ATTRIBUTE);
  }

  public static void setSessionId(String id) throws Exception {
    ManagementFactory.getPlatformMBeanServer()
        .setAttribute(new ObjectName(OBJECT_NAME), new Attribute(SESSION_ID_ATTRIBUTE, id));
  }

  public static byte[] dump(boolean reset) throws Exception {
    return (byte[]) ManagementFactory.getPlatformMBeanServer()
        .invoke(new ObjectName(OBJECT_NAME), DUMP_OPERATION, new Object[] { reset }, new String[] { boolean.class.getName() });
  }

  public static void reset() throws Exception {
    ManagementFactory.getPlatformMBeanServer()
        .invoke(new ObjectName(OBJECT_NAME), RESET_OPERATION, null, null);
  }

  private JacocoController() {
  }

}

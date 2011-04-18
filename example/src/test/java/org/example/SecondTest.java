package org.example;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

public class SecondTest {

  @Test
  public void test() {
    assertFalse(new App().second());
  }

}

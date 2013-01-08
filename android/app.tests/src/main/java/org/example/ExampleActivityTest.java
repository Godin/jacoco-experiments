package org.example;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class ExampleActivityTest extends ActivityInstrumentationTestCase2<ExampleActivity> {

  private Solo solo;

  public ExampleActivityTest() {
    super("org.example", ExampleActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }

  @Override
  public void tearDown() throws Exception {
    solo.finishOpenedActivities();
  }

  public void testMainActivityAppears() {
    assertTrue(solo.waitForActivity("ExampleActivity", 20000));
  }

  public void testMainActivityTextAppears() {
    assertTrue(solo.searchText("Hello World"));
  }

}

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

public class AppTest {

  @Mocked
  Utils unused;

  @Test
  public void test() {
    new Expectations() {{
      Utils.say("Hello");
    }};
    new App().sayHello();
  }

}

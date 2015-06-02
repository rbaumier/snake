package tests.models;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public class GameTest extends AndroidTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  public void testSomething() throws Throwable {
    Assert.assertTrue(1 + 1 == 2);
  }

  public void testSomethingElse() throws Throwable {
    Assert.assertTrue(1 + 1 == 3);
  }
}

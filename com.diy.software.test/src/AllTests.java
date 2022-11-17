import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddItemByScanningTests.class, CustomerUITest.class, PayByCreditCardTest.class })
public class AllTests {

}

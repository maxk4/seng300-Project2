import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddItemByScanningTests.class, AttendantUITests.class, CashPaymentTest.class, CustomerUITest.class,
		PayByCreditCardTest.class, PayByDebitCardTest.class })
public class AllTests {

}

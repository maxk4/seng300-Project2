
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

/*
- test where input is less than 8 = fail
- test where input is greater than 8 = fail
- test where input is exactly 8 = pass
- test where input is null (empty) = fail
- test where input is exactly 8 but the number is wrong = fail
 */

public class MembershipNumberTest {

    HashMap<String, Integer> MapActual = new HashMap<>();
    HashMap<String, Integer> expected = new HashMap<>();

    /*
    this test checks if the length of the number is equal to 8, which it is not.
    So, the test will fail and give since the membership number is less than 8 digits long.
     */
    @Test
    public void NumberIsLessThanEightDigits(){
            MapActual = new HashMap<>();
            MapActual.put("Nathan", 3749);

            expected = new HashMap<>();
            expected.put("Nathan", 37498103);

            assertEquals(expected.get("Nathan"), MapActual.get("Nathan"));
    }

    /*
    this test checks if the length of the number is equal to 8, which it is not.
    So, the test will fail since the membership number is greater than 8 digits long.
     */
    @Test
    public void NumberIsGreaterThanEightDigits(){
            MapActual = new HashMap<>();
            MapActual.put("Emily", 730182938);

            expected = new HashMap<>();
            expected.put("Emily", 73018293);

            assertEquals(expected.get("Emily"), MapActual.get("Emily"));
    }

    /*
    this test checks if the length of the number is equal to 8, which it is.
    So, this test passes since the number is 8 digits long
     */
    @Test
    public void CorrectMembershipNumber(){
            MapActual = new HashMap<>();
            MapActual.put("Matt", 20476519);

            expected = new HashMap<>();
            expected.put("Matt", 20476519);

            assertEquals(expected.get("Matt"), MapActual.get("Matt"));
    }

    /*
    this test checks if a number has been entered or not.
    Since, it's null this test fails.
     */
    @Test
    public void NullTest(){
            MapActual = new HashMap<>();
            MapActual.put("Anthony", null);

            expected = new HashMap<>();
            expected.put("Anthony", 12345678);

            assertEquals(expected.get("Anthony"), MapActual.get("Anthony"));
    }

    /*
    this test checks if the number is correct when the length of the number is correct.
    the test fails since the number is wrong.
     */
    @Test
    public void CorrectLengthButWrongNumber(){
            MapActual = new HashMap<>();
            MapActual.put("Hannah", 47207685);

            expected = new HashMap<>();
            expected.put("Hannah", 47204185);

            assertEquals(expected.get("Hannah"), MapActual.get("Hannah"));
    }
}
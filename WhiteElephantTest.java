import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
public class WhiteElephantTest{
    WhiteElephant test = new WhiteElephant("test");
    @Test
    public void parseTestWithSpaceName(){
        String str = "a, c, g, tt,   ";

        String[] expected = {"a", "c", "g", "tt"};
        String[] result = test.parse(str);

        assertArrayEquals(expected, result);
    }

    @Test
    public void parseTestWithEmptyName(){
        String str = "a, c, g,,,, tt,,,";

        String[] expected = {"a", "c", "g", "tt"};
        String[] result = test.parse(str);

        assertArrayEquals(expected, result);
    }

    @Test
    public void parseTestWithNull(){
        String str = null;

        String[] expected = null;
        String[] result = test.parse(str);

        assertArrayEquals(expected, result);
    }

    @Test
    public void parseTestWithSpaceString(){
        String str = "   ";

        String[] expected = {""};
        String[] result = test.parse(str);

        assertArrayEquals(expected, result);
    }

    @Test
    public void parseTestWithTab(){
        String str = "  Adam Smith, 	 Denyse Depaoli, Jr. Douglas Loberg, Eveline Crone, Delora Mondragon, Darlena Outlaw, Molly Wollman	 , Lovella Massengale, Norine Jaqua, Alesia Epley, Sheryl Michelson";

        String[] expected = {"Adam Smith", "Denyse Depaoli", "Jr. Douglas Loberg", "Eveline Crone", "Delora Mondragon", "Darlena Outlaw", "Molly Wollman", "Lovella Massengale", "Norine Jaqua", "Alesia Epley", "Sheryl Michelson"};
        String[] result = test.parse(str);

        assertArrayEquals(expected, result);
    }

    @Test
    public void updateNamesTestWithDuplicateName(){
        String[] names = {"a", "aa", "ac", "ac", "b", "bb"};
        int len = names.length;

        String[] expected = {"a", "aa", "ac", "ac_1", "b", "bb"};
        test.updateNames(names, len);

        assertArrayEquals(expected, names);
    }

    @Test
    public void updateNamesTest(){
        String[] names = {"a", "b", "c"};
        int len = names.length;

        String[] expected = {"a", "b", "c"};
        test.updateNames(names, len);

        assertArrayEquals(expected, names);
    }

    @Test
    public void updateNamesTestWithOneName(){
        String[] names = {"a"};
        int len = names.length;

        String[] expected = {"a"};
        test.updateNames(names, len);

        assertArrayEquals(expected, names);
    }

    @Test
    public void updateNamesTestWithOneDuplicate(){
        String[] names = {"a","a", "a", "a", "a" ,"a",  "a"  ,"a"};
        int len = names.length;

        String[] expected = {"a","a_1", "a_2", "a_3", "a_4" ,"a_5","a_6","a_7"};
        test.updateNames(names, len);

        assertArrayEquals(expected, names);
    }

    @Test
    public void generateAssignmentsTest(){
        String[] participants = {"a", "a_1", "b", "c", "d", "e"};

        String[] recipients = test.generateAssignments(participants);

        for(int i = 0; i < participants.length; i++){
            assertThat(participants[i], not(equalTo(recipients[i])));
        }
    }

    @Test
    public void generateAssignmentsTest2(){
        String[] participants = {"a", "b", "c"};

        String[] recipients = test.generateAssignments(participants);

        for(int i = 0; i < participants.length; i++){
            assertThat(participants[i], not(equalTo(recipients[i])));
        }
    }

    @Test
    public void generateAssignmentsTestWithTwo(){
        String[] participants = {"a", "b"};

        String[] recipients = test.generateAssignments(participants);

        assertNull(recipients);
    }

    @Test
    public void generateAssignmentsTestWithOne(){
        String[] participants = {"a"};

        String[] recipients = test.generateAssignments(participants);

        assertNull(recipients);
    }

    @Test
    public void generateAssignmentsTestWithNull(){
        String[] participants = null;

        String[] recipients = test.generateAssignments(participants);

        assertNull(recipients);
    }
}

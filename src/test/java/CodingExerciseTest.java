import org.junit.*;

import java.util.Arrays;

public class CodingExerciseTest {
    private CodingExercise codingExercise;
    private String[][] input;

    @Before
    public void setup(){
        codingExercise = new CodingExercise();
        input = new String[][]{{"+ 5", "X", "- 15", "- 11", "* 5"},
                                {"/ 10", "* 2", "/ 4", "X", "+ 2"},
                                {"X", "- 12", "+ 3", "- 6", "* 1"},
                                {"- 1", "* 3", "- 9", "+ 5", "/ -1"},
                                {"+ 11", "X", "+ 2", "* -2", "- 8"}};
    }

    @Test
    public void take_the_long_way_home_test(){
        String actual = codingExercise.takeTheLongWayHome(input);
        String expected = "4, 3, 4, 5, 1";
        Assert.assertTrue(actual.equals(expected));
    }

}

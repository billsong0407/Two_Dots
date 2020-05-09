/**
 * @file: TestEndByTime.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: Tests the access rountines of EndByTime.java
 */

package src;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

public class TestEndByTime{

    private EndByTime cond;

    @Before
    public void setup(){
        cond = new EndByTime();
    }

    @After 
    public void tearDown(){
        cond = null;
    }

    @Test 
    public void testGameStatus(){
        assertTrue(cond.gameStatus(2, CellType.O, 2));
        
    }

    @Test 
    public void testMessage(){
        assertEquals(cond.message(10),"Target: # of Red Dots left: 5 , # of Orange Dots left: 5\n Time left(sec): 60");
    }

    @Test 
    public void setTime(){
        cond.setTime(70);
        assertEquals(cond.message(10),"Target: # of Red Dots left: 5 , # of Orange Dots left: 5\n Time left(sec): 70");
    }
}
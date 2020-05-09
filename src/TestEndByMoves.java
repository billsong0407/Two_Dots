/**
 * @file: TestEndByMoves.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: Tests the access routines of EndByMoves.java
 */

package src;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

public class TestEndByMoves{

    private EndByMoves cond;

    @Before
    public void setup(){
        cond = new EndByMoves();
    }

    @After 
    public void tearDown(){
        cond = null;
    }

    @Test 
    public void testGameStatus(){
        assertTrue(cond.gameStatus(2, CellType.O, 2));
        assertFalse(cond.gameStatus(2, CellType.O, 10));
        
    }

    @Test 
    public void testGetString(){
        assertEquals(cond.message(2),"Target: # of Red Dots left: 5 , # of Orange Dots left: 5\n Number of moves left: 8");
    }
}
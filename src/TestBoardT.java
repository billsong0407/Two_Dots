/**
 * @file: TestBoardT.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: Tests the public interfaces of BoardT
 */

package src;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

public class TestBoardT{

    private BoardT board1;

    private ArrayList<String> input;

    @Before
    public void setUp(){
        ArrayList<ArrayList<CellType>> board = new ArrayList<ArrayList<CellType>>();
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.B, CellType.G, CellType.G, CellType.R, CellType.O, CellType.O, CellType.P, CellType.B)));
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.O, CellType.G, CellType.G, CellType.G, CellType.B, CellType.R, CellType.P, CellType.O)));
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.O, CellType.P, CellType.R, CellType.P, CellType.O, CellType.R, CellType.R, CellType.R)));
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.R, CellType.B, CellType.G, CellType.G, CellType.P, CellType.P, CellType.P, CellType.G)));
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.P, CellType.O, CellType.R, CellType.B, CellType.P, CellType.P, CellType.G, CellType.B)));
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.B, CellType.G, CellType.B, CellType.R, CellType.G, CellType.G, CellType.R, CellType.B)));
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.B, CellType.R, CellType.R, CellType.B, CellType.O, CellType.B, CellType.P, CellType.O)));
        board.add(new ArrayList<CellType>(Arrays.asList(CellType.B, CellType.O, CellType.G, CellType.R, CellType.R, CellType.O, CellType.B, CellType.O)));
        this.board1 = new BoardT(board);
    }

    @After
    public void tearDown(){
        this.board1 = null;
    }

    @Test 
    public void testGetCell(){
        assertEquals(board1.getCell(0, 0), CellType.B);
        assertEquals(board1.getCell(0, 1), CellType.O);
        assertEquals(board1.getCell(3, 2), CellType.O);
        assertFalse(board1.getCell(7, 1) == CellType.B);
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCellException(){
        board1.getCell(9, 0);
    }

    @Test 
    public void testGetStatus(){
        assertTrue(board1.getStatus());
    }

    @Test 
    public void testSetCondition(){
        board1.setEndCondition(new EndByMoves());
        assertEquals(board1.getMessage(),"Target: # of Red Dots left: 5 , # of Orange Dots left: 5\n Number of moves left: 10");
    }

    @Test 
    public void testSetCondition1(){
        board1.setEndCondition(new EndByTime());
        assertEquals(board1.getMessage(),"Target: # of Red Dots left: 5 , # of Orange Dots left: 5\n Time left(sec): 60");
    }

    @Test 
    public void testRemove(){
        board1.setEndCondition(new EndByMoves());
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("06", "07"));
        board1.remove(input);
        assertEquals(board1.getMessage(),"Target: # of Red Dots left: 5 , # of Orange Dots left: 3\n Number of moves left: 9");
    }

    @Test (expected=IllegalArgumentException.class)
    public void testRemoveExpection(){
        input = new ArrayList<String>(Arrays.asList("16", "25", "26", "27"));
        board1.remove(input);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testRemoveExpection1(){
        input = new ArrayList<String>(Arrays.asList("86", "25", "26", "27"));
        board1.remove(input);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author beyourwild
 */
public class OperatorFinderTest {
    OperatorFinder operatorFinder;
    
    public OperatorFinderTest() {
        operatorFinder = new OperatorFinder();
    }

    /**
     * Test of getExpression method, of class OperatorFinder.
     */
    @Test
    public void testCase1() {
        assertEquals("5-75+3-2+65", operatorFinder.getExpression("5,75,3,2,65", -4));
    }
    
    @Test
    public void testCase2() {
        assertEquals("NOT FOUND!!", operatorFinder.getExpression("45,65,23,12,67", -1200));
    }
    
    @Test
    public void testCase3() {
        assertEquals("1+2+3+4*5+6*7+8*9+10", operatorFinder.getExpression("1,2,3,4,5,6,7,8,9,10", 150));
    }
    
    @Test
    public void testCase4() {
        assertEquals("NOT FOUND!!", operatorFinder.getExpression("1,2,3,4", 20));
    }
    
    @Test
    public void testCase5() {
        assertEquals("1+2+3*4+5", operatorFinder.getExpression("1,2,3,4,5", 20));
    }

}

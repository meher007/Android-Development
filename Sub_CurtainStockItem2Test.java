/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author home
 */
public class Sub_CurtainStockItem2Test {

    Sub_CurtainStockItem2 obj;
    private final String curtain_id = "M525";
    private final String material = "Cotton";
    private final String style = "Eyelet";
    private final int price = 1500;
    private final String colour = "Baby Blue";
    private final String brand = "John Lewis";
    private final String size = "Large";
    private final int stockLevel = 4;
    private final String Gender = "Boy";
    private final String NewArrivals = "Yes";

    public Sub_CurtainStockItem2Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("### Testing the class Sub_CurtainStockItem2 ###");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        obj = new Sub_CurtainStockItem2(curtain_id,
                material,
                style,
                price,
                colour,
                brand,
                size,
                stockLevel,
                Gender,
                NewArrivals);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setGender method, of class Sub_CurtainStockItem2.
     */
    @Test
    public void testSetGender() {
        System.out.println("\nTestSetGender");
        String expGender = "Girl";
        obj.setGender(expGender);
        String result = obj.getGender();
        assertEquals(expGender, result);
    }

    /**
     * Test of setNewArrivals method, of class Sub_CurtainStockItem2.
     */
    @Test
    public void testSetNewArrivals() {
        System.out.println("\nTestSetNewArrivals");
        String expNewArrivals = "Yes";
        obj.setNewArrivals(expNewArrivals);
        String result = obj.getNewArrivals();
        assertEquals(expNewArrivals, result);
    }

    /**
     * Test of getGender method, of class Sub_CurtainStockItem2.
     */
    @Test
    public void testGetGender() {
        System.out.println("\nTestGetGender");
        String expResult = "Boy";
        String result = obj.getGender();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNewArrivals method, of class Sub_CurtainStockItem2.
     */
    @Test
    public void testGetNewArrivals() {
        System.out.println("\nTestGetNewArrivals");
        String expResult = "Yes";
        String result = obj.getNewArrivals();
        assertEquals(expResult, result);
    }

// Test of Constructor, of class Sub_CurtainStockItem2.
    @Test
    public void Constructor() {
        System.out.println("\nTestContructor");

        Sub_CurtainStockItem2 newobj;

        newobj = new Sub_CurtainStockItem2("M521", "100% Cotton", "Traditional", 900, "Red", "Zara", "Medium", 3, "Girl","Yes");

        assertEquals("M521", newobj.getCurtain_id());
        assertEquals("100% Cotton", newobj.getMaterial());
        assertEquals(900, newobj.getPrice());
        assertEquals(3, newobj.getStockLevel());
        assertEquals("Girl", newobj.getGender());
        assertEquals("Yes", newobj.getNewArrivals());

    }


}

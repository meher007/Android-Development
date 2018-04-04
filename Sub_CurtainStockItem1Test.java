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
public class Sub_CurtainStockItem1Test {

    Sub_CurtainStockItem1 obj;
    private final String test1_curtain_id = "M525";
    private final String test1_material = "Cotton";
    private final String test1_style = "Eyelet";
    private final int test1_price = 1500;
    private final String test1_colour = "GREEN";
    private final String test1_brand = "John Lewis";
    private final String test1_size = "Large";
    private final int test1_stockLevel = 4;
    private final int test1_DiscountInPercentage = 5;
    private final String test1_Rating = "A++";

    public Sub_CurtainStockItem1Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("### Testing the class Sub_CurtainStockItem1 ###");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        obj = new Sub_CurtainStockItem1(test1_curtain_id,
                test1_material,
                test1_style,
                test1_price,
                test1_colour,
                test1_brand,
                test1_size,
                test1_stockLevel,
                test1_DiscountInPercentage,
                test1_Rating);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setDiscountInPercentage method, of class Sub_CurtainStockItem1.
     */
    @Test
    public void testSetDiscountInPercentage() {
        System.out.println("\nTestsetDiscountInPercentage");
        int expDiscount = 10;
        obj.setDiscountInPercentage(expDiscount);
        int result = obj.getDiscountInPercentage();
        assertEquals(expDiscount, result);
    System.out.println("\nTestsetDiscountInPercentage" + obj);
    }

    /**
     * Test of setRating method, of class Sub_CurtainStockItem1.
     */
    @Test
    public void testSetRating_String() {
        System.out.println("\nTestsetRating");
        String expRating = "B++";
        obj.setRating(expRating);
        String result = obj.getRating();
        assertEquals(expRating, result);
    }

    /**
     * Test of getDiscountInPercentage method, of class Sub_CurtainStockItem1.
     */
    @Test
    public void testGetDiscountInPercentage() {
        System.out.println("\nTestGetDiscountInPercentage");
        int expResult = 5;
        int result = obj.getDiscountInPercentage();
        assertEquals(expResult, result);

    }

    /**
     * Test of GetRating method, of class Sub_CurtainStockItem1.
     */
    @Test
    public void testGetRating() {
        System.out.println("\nTestGetRating");
        String expResult = "A++";
        String result = obj.getRating();
        assertEquals(expResult, result);

    }

    // Test of Constructor, of class Sub_CurtainStockItem1.
    @Test
    public void Constructor() {
        System.out.println("\nTestContructor");

        Sub_CurtainStockItem1 newobj;

        newobj = new Sub_CurtainStockItem1("M529", "Polyester", "Eyelet", 700, "GREEN", "Zara", "Small", 12, 7, "B");

        assertEquals("M529", newobj.getCurtain_id());
        assertEquals("Polyester", newobj.getMaterial());
        assertEquals(700, newobj.getPrice());
        assertEquals(12, newobj.getStockLevel());
        assertEquals(7, newobj.getDiscountInPercentage());
        assertEquals("B", newobj.getRating());

    }
}

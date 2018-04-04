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
 * @author Meher
 */
public class CurtainStockItemTest {

    CurtainStockItem obj;
    private final String test_curtain_id = "M525";
    private final String test_material = "Fabric";
    private final String test_style = "Eyelet";
    private final int test_price = 1500;
    private final String test_colour = "GREEN";
    private final String test_brand = "John Lewis";
    private final String test_size = "Large";
    private final int test_stockLevel = 4;

    public CurtainStockItemTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Testing the class CurtainStockItem");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        obj = new CurtainStockItem(test_curtain_id, 
                                     test_material, 
                                        test_style, 
                                        test_price, 
                                       test_colour, 
                                        test_brand, 
                                         test_size, 
                                    test_stockLevel);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of validCode method, of class CurtainStockItem.
     */
    @Test
    public void testValidCode1() {
        System.out.println("\nTestValidCode");
        String vCurtainId = "M225"; // It is a valid CurtainId that's why expected result is true
        boolean expResult = true;
        boolean result = obj.validCode(vCurtainId);
        assertEquals(expResult, result);

    }

    /**
     * Test of validCode method, of class CurtainStockItem.
     */
    @Test
    public void testValidCode2() {
        System.out.println("\nTestValidCode");
        String vCurtainId = "M22M"; // Last character is not a numeric that's why expected result is fasle 
        boolean expResult = false;
        boolean result = obj.validCode(vCurtainId);
        assertEquals(expResult, result);

    }

    /**
     * Test of validCode method, of class CurtainStockItem.
     */
    @Test
    public void testValidCode3() {
        System.out.println("\nTestvalidCode");
        String vCurtainId = "2225"; // All character is numeric that's why expected result is fasle 
        boolean expResult = false;
        boolean result = CurtainStockItem.validCode(vCurtainId);
        assertEquals(expResult, result);

    }

    /**
     * Test of validCode method, of class CurtainStockItem.
     */
    @Test
    public void testValidCode4() {
        System.out.println("\nTestvalidCode");
        String vCurtainId = "M2255"; // CurtainId is incorrect length that's why expected result is fasle 
        boolean expResult = false;
        boolean result = CurtainStockItem.validCode(vCurtainId);
        assertEquals(expResult, result);

    }

    /**
     * Test of setMaterial method, of class CurtainStockItem.
     */
    @Test
    public void testSetMaterial() { 
        System.out.println("\nTestSetMaterial");
        String material = "Cotton Mix";
        //CurtainStockItem instance = new CurtainStockItem();
        obj.setMaterial(material);
        String result = obj.getMaterial();
        assertEquals(material, result);
    }

    /**
     * Test of setColour method, of class CurtainStockItem.
     */
    @Test
    public void testSetColour() {
        System.out.println("\nTestSetColour");
        String expColour = "Blue"; 
        obj.setColour(expColour);
        String result = obj.getColour();
        assertEquals(expColour, result);
        System.out.println("\nTestSetColour is " + result);
    }

    /**
     * Test of getCurtain_id method, of class CurtainStockItem.
     */
    @Test
    public void testGetCurtain_id() {
        System.out.println("\nTestGetCurtain_id");

        String expResult = "M525";
        String result = obj.getCurtain_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaterial method, of class CurtainStockItem.
     */
    @Test
    public void testGetMaterial() {
        System.out.println("\nTestGetMaterial");

        String expResult = "Fabric";
        String result = obj.getMaterial();
        assertEquals(expResult, result);

    }

    /**
     * Test of getStyle method, of class CurtainStockItem.
     */
    @Test
    public void testGetStyle() {
        System.out.println("\nTestGetStyle");
        String expResult = "Eyelet";
        String result = obj.getStyle();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getPrice method, of class CurtainStockItem.
     */
    @Test
    public void testGetprice() {
        System.out.println("\nTestGetprice");
        int expResult = 1500;
        int result = obj.getPrice();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getColour method, of class CurtainStockItem.
     */
    @Test
    public void testGetColour() {
        System.out.println("\nTestGetColour()");
        String expResult = "GREEN";
        String result = obj.getColour();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of GetBrand method, of class CurtainStockItem.
     */
    @Test
    public void testGetBrand() {
        System.out.println("\nTestGetBrand");
        String expResult = "John Lewis";
        String result = obj.getBrand();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of GetSize method, of class CurtainStockItem.
     */
    @Test
    public void testGetSize() {
        System.out.println("\nTestGetSize");
        String expResult = "Large";
        String result = obj.getSize();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of GetSize method, of class CurtainStockItem.
     */
    @Test
    public void testGetStockLevel() {
        System.out.println("\nTestGetStockLevel");
        int expResult = 4;
        int result = obj.getStockLevel();
        assertEquals(expResult, result);
       
    }
    
    
 // Test of Constructor, of class CurtainStockItem.
    @Test
    public void Constructor(){
        System.out.println("\nTestContructor");
        CurtainStockItem newobj;
            newobj = new CurtainStockItem ("M522", "Polyester Mix", "Eyelet", 1100, "GREEN", "Zara", "Small", 6);
            
            assertEquals("M522", newobj.getCurtain_id());            
            assertEquals("Polyester Mix", newobj.getMaterial());
            assertEquals(1100, newobj.getPrice());
            assertEquals(6, newobj.getStockLevel());
    
    }
}

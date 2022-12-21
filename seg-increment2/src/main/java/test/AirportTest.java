package test;

import model.Airport;
import model.LogicalRunway;
import model.Runway;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

//LOOK UP IDS IN TESTING PLAN DOC (FOUND IN TRELLO) TO UNDERSTAND EACH TEST
public class AirportTest {

    @Test
    //id = 1, needs redoing
    public void testConstructor1(){
        Airport testAirport = new Airport();
        assertEquals(0 ,testAirport.getRunways().size());
    }

    @Test
    //id 2a
    public void testConstructor2a(){
        Runway run1 = new Runway();
        Runway[] runwayArray = new Runway[]{run1};
        Airport testAirport = new Airport(runwayArray);
        assertArrayEquals(runwayArray, testAirport.getRunways().toArray()); // do we need .toArray()? it only works with it for some reason
    }

    @Test
    //id 2b, needs redoing
    public void testConstructor2b(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        Runway[] runwayArray = new Runway[]{run1,run2};
        Airport testAirport = new Airport(runwayArray);
        assertArrayEquals(runwayArray, testAirport.getRunways().toArray()); // do we need .toArray()? it only works with it for some reason
    }

    @Test
    //id 2c
    public void testConstructor2c(){
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);
        assertArrayEquals(runwayArray, testAirport.getRunways().toArray()); // do we need .toArray()? it only works with it for some reason
    }

    /*@Test
    //id 2d, Actually probably not required
    public void testConstructor2d() throws Exception{
        int[] runwayArray = new int[]{1,2,3};
        Airport testAirport = new Airport(runwayArray);
        assertThrows(E);
    }*/

    @Test
    //id 3a
    public void testConstructor3a(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(runwayArrayList, testAirport.getRunways());
    }

    @Test
    //id 3b
    public void testConstructor3b(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(runwayArrayList, testAirport.getRunways());
    }

    @Test
    //id 3c
    public void testConstructor3c(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(runwayArrayList, testAirport.getRunways());
    }

    //@Test
    //id 3d, Probably not required actually
    /*public void testConstructor3d(){
        ArrayList<Integer> runwayArrayList = new ArrayList<Integer>();
        ArrayList.add(1);
        ArrayList.add(2);
        ArrayList.add(3);
        Airport testAirport = new Airport(runwayArray);
        assertEquals(runwayArray, testAirport.getRunways().toArray()); // do we need .toArray()? it only works with it for some reason
    }*/

    @Test
    //id = 4a , needs to be redone
    public void testGetRunways4a(){
        Airport testAirport = new Airport();
        assertSame(0 ,testAirport.getRunways().size());
    }

    @Test
    //id 4b
    public void testGetRunways4b(){
        Runway run1 = new Runway();
        Runway[] runwayArray = new Runway[]{run1};
        Airport testAirport = new Airport(runwayArray);
        assertSame(runwayArray[0], testAirport.getRunways().toArray()[0]); // do we need .toArray()? it only works with it for some reason
    }

    @Test
    //id 4c
    public void testGetRunways4c(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        Runway[] runwayArray = new Runway[]{run1,run2};
        Airport testAirport = new Airport(runwayArray);

        System.out.println(testAirport.getRunways().get(0));
        System.out.println(runwayArray[0]);
        System.out.println(testAirport.getRunways().toArray()[1]);
        System.out.println(runwayArray[1]);

        assertSame(runwayArray[0], testAirport.getRunways().toArray()[0]); // do we need .toArray()? it only works with it for some reason
        assertSame(runwayArray[1], testAirport.getRunways().toArray()[1]);
    }

    @Test
    //id 4d , need to redo
    public void testGetRunways4d(){
        Runway[] runwayArray = new Runway[0];
        Airport testAirport = new Airport(runwayArray);
        assertSame(0, testAirport.getRunways().size());
    }

    @Test
    //id 4e
    public void testGetRunways4e(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        Airport testAirport = new Airport(runwayArrayList);
        assertSame(runwayArrayList.get(0), testAirport.getRunways().get(0));
    }

    @Test
    //id 4f
    public void testGetRunways4f(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        Airport testAirport = new Airport(runwayArrayList);
        assertSame(runwayArrayList.get(0), testAirport.getRunways().get(0));
        assertSame(runwayArrayList.get(1), testAirport.getRunways().get(1));
    }

    @Test
    //id 4g
    public void testGetRunways4g(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        assertSame(runwayArrayList, testAirport.getRunways());
    }

    @Test
    //id 5a
    public void testAddRunway5a(){
        Runway run1 = new Runway();
        Airport testAirport = new Airport();
        testAirport.addRunway(run1);
        assertSame(run1 ,testAirport.getRunways().get(0));
    }

    @Test
    //id 5b, needs working on
    public void testAddRunway5b(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        Airport testAirport = new Airport();
        testAirport.addRunway(run1);
        testAirport.addRunway(run2);

        /* the code here works if u directly change the arraylist
        testAirport.getRunways().add(run1);
        testAirport.getRunways().add(run2);
        */

        assertSame(run1 ,testAirport.getRunways().get(0));
        assertSame(run2 ,testAirport.getRunways().get(1));
    }

    /*@Test
    //id 5c , probably not needed
    public void testAddRunway5c(){
        Integer testInteger = new Integer();
        Airport testAirport = new Airport();
        testAirport.addRunway(testInteger);
        assertSame(run1 ,testAirport.getRunways().get(0));
    } */

    @Test
    //id 5d, needs working on
    public void testAddRunway5d(){
        Runway run1 = new Runway();
        Runway[] runwayArray = new Runway[0];
        Airport testAirport = new Airport(runwayArray);
        testAirport.addRunway(run1);
        assertSame(run1 ,testAirport.getRunways().get(0));
    }

    @Test
    //id 5e, needs working on
    public void testAddRunway5e(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        Runway[] runwayArray = new Runway[0];
        Airport testAirport = new Airport(runwayArray);
        testAirport.addRunway(run1);
        testAirport.addRunway(run2);
        assertSame(run1 ,testAirport.getRunways().get(0));
        assertSame(run2 ,testAirport.getRunways().get(1));
    }

    /*@Test
    //id 5f , probably not needed
    public void testAddRunway5f(){
        Integer testInteger = new Integer();
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);
        testAirport.addRunway(testInteger);
        assertSame(run1 ,testAirport.getRunways().get(0));
    } */

    @Test
    //id 5g
    public void testAddRunway5g(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        testAirport.addRunway(run1);
        assertSame(run1 ,testAirport.getRunways().get(0));
    }

    @Test
    //id 5h, needs working on
    public void testAddRunway5h(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        testAirport.addRunway(run1);
        testAirport.addRunway(run2);
        assertSame(run1 ,testAirport.getRunways().get(0));
        assertSame(run2 ,testAirport.getRunways().get(1));
    }

    /*@Test
    //id 5i , probably not needed
    public void testAddRunway5i(){
        Integer testInteger = new Integer();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        testAirport.addRunway(testInteger);
        assertSame(run1 ,testAirport.getRunways().get(0));
    } */

    @Test
    //id 6a
    public void testAddRunways6a(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport();
        runwayArrayList.add(run1);
        testAirport.addRunways(runwayArrayList);
        assertSame(run1,testAirport.getRunways().get(0));
    }

    @Test
    //id 6b, needs redoing
    public void testAddRunways6b(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport();
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        testAirport.addRunways(runwayArrayList);
        assertSame(run1,testAirport.getRunways().get(0));
        assertSame(run2,testAirport.getRunways().get(1));
    }

    @Test
    //id 6c , needs redoing
    public void testAddRunways6c(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport();
        testAirport.addRunways(runwayArrayList);
        assertSame(0,testAirport.getRunways().size());
    }

    /*@Test
    //id 6d, probably not needed
    public void testAddRunways6d(){
        Integer testInteger = new Integer();
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        Airport testAirport = new Airport();
        integerArrayList.add(testInteger);
        testAirport.addRunways(integerArrayList);
        assertSame(testInteger,testAirport.getRunways().get(0));
    }*/

    @Test
    //id 6e, needs working on
    public void testAddRunways6e(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Runway[] constructorArray = new Runway[0];
        Airport testAirport = new Airport(constructorArray);
        runwayArrayList.add(run1);
        testAirport.addRunways(runwayArrayList);
        assertSame(run1,testAirport.getRunways().get(0));
    }

    @Test
    //id 6f, needs redoing
    public void testAddRunways6f(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Runway[] constructorArray = new Runway[0];
        Airport testAirport = new Airport(constructorArray);
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        testAirport.addRunways(runwayArrayList);
        assertSame(run1,testAirport.getRunways().get(0));
        assertSame(run2,testAirport.getRunways().get(1));
    }

    @Test
    //id 6g
    public void testAddRunways6g(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Runway[] constructorArray = new Runway[1];
        Airport testAirport = new Airport(constructorArray);
        testAirport.addRunways(runwayArrayList);
        assertSame(null,testAirport.getRunways().get(0));
    }

    /*@Test
    //id 6h, probably not needed
    public void testAddRunways6h(){
        Integer testInteger = new Integer();
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        Runway[] constructorArray = new Runway[1];
        Airport testAirport = new Airport(constructorArray);
        integerArrayList.add(testInteger);
        testAirport.addRunways(integerArrayList);
        assertSame(testInteger,testAirport.getRunways().get(0));
    }*/

    @Test
    //id 6i
    public void testAddRunways6i(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        ArrayList<Runway> constructorArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(constructorArrayList);
        runwayArrayList.add(run1);
        testAirport.addRunways(runwayArrayList);
        assertSame(run1,testAirport.getRunways().get(0));
    }

    @Test
    //id 6j, needs redoing
    public void testAddRunways6j(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        ArrayList<Runway> constructorArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(constructorArrayList);
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        testAirport.addRunways(runwayArrayList);
        assertSame(run1,testAirport.getRunways().get(0));
        assertSame(run2,testAirport.getRunways().get(1));
    }

    @Test
    //id 6k , needs redoing
    public void testAddRunways6k(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        ArrayList<Runway> constructorArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(constructorArrayList);
        testAirport.addRunways(runwayArrayList);
        assertSame(0,testAirport.getRunways().size());
    }

    /*@Test
    //id 6l, probably not needed
    public void testAddRunways6l(){
        Integer testInteger = new Integer();
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        ArrayList<Runway> constructorArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(constructorArrayList);
        integerArrayList.add(testInteger);
        testAirport.addRunways(integerArrayList);
        assertSame(testInteger,testAirport.getRunways().get(0));
    }*/

    @Test
    //id 7a, questionable
    public void testRemoveRunway7a(){
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.removeRunway(testRunway);
    }

    @Test
    //id 7b, needs redoing
    public void testRemoveRunway7b(){
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        testAirport.removeRunway(testRunway);
        assertEquals(0,testAirport.getRunways().size());
    }

    @Test
    //id 7c, needs redoing
    public void testRemoveRunway7c(){
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        Runway testRunway2 = new Runway();
        testAirport.addRunway(testRunway);
        testAirport.addRunway(testRunway2);
        testAirport.removeRunway(testRunway);
        assertEquals(1,testAirport.getRunways().size());
    }

    /*@Test
    //id 7d, probs not needed
    public void testRemoveRunway7d(){
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        Integer testInteger = new Integer();
        testAirport.addRunway(testRunway);
        testAirport.removeRunway(testInteger);
        assertEquals(0,testAirport.getRunways().size());
    }*/

    @Test
    //id 7e, questionable
    public void testRemoveRunway7e(){
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);
        Runway testRunway = new Runway();
        testAirport.removeRunway(testRunway);
    }

    @Test
    //id 7f, needs redoing
    public void testRemoveRunway7f(){
        Runway run1 = new Runway();
        Runway[] runwayArray = new Runway[]{run1};
        Airport testAirport = new Airport(runwayArray);
        testAirport.removeRunway(run1);
        assertEquals(0, testAirport.getRunways().size());
    }

    @Test
    //id 7g, needs redoing
    public void testRemoveRunway7g(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        Runway[] runwayArray = new Runway[]{run1,run2};
        Airport testAirport = new Airport(runwayArray);
        testAirport.removeRunway(run1);
        assertEquals(1, testAirport.getRunways().size());
    }

    /*@Test
    //id 7h, probs not needed
    public void testRemoveRunway7h(){
        Runway run1 = new Runway();
        Runway[] runwayArray = new Runway[]{run1};
        Airport testAirport = new Airport(runwayArray);
        Integer testInteger = new Integer();
        testAirport.removeRunway(testInteger);
        assertEquals(0, testAirport.getRunways().size());
    }*/

    @Test
    //id 7i, questionable
    public void testRemoveRunway7i(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        Runway testRunway = new Runway();
        testAirport.removeRunway(testRunway);
    }

    @Test
    //id 7j, needs redoing
    public void testRemoveRunway7j(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        Airport testAirport = new Airport(runwayArrayList);
        testAirport.removeRunway(run1);
        assertEquals(0, testAirport.getRunways().size());

    }

    @Test
    //id 7k, needs redoing
    public void testRemoveRunway7k(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        Airport testAirport = new Airport(runwayArrayList);
        testAirport.removeRunway(run1);
        assertEquals(1, testAirport.getRunways().size());
    }

    /*@Test
    //id 7l, probs not needed
    public void testRemoveRunway7l(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        Airport testAirport = new Airport(runwayArrayList);
        Integer testInteger = new Integer();
        testAirport.removeRunway(testInteger);
        assertEquals(0, testAirport.getRunways().size());
    }*/

    @Test
    //id 8a
    public void testGetRESA8a(){
        Airport testAirport = new Airport();
        assertEquals(240, testAirport.getResa());
    }

    @Test
    //id 8b
    public void testGetRESA8b(){
        Runway run1 = new Runway();
        Runway[] runwayArray = new Runway[]{run1};
        Airport testAirport = new Airport(runwayArray);
        assertEquals(240, testAirport.getResa());
    }

    @Test
    //id 8c, needs redoing
    public void testGetRESA8c(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        Runway[] runwayArray = new Runway[]{run1,run2};
        Airport testAirport = new Airport(runwayArray);
        assertEquals(240, testAirport.getResa());
    }

    @Test
    //id 8d
    public void testGetRESA8d(){
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);
        assertEquals(240, testAirport.getResa());
    }

    @Test
    //id 8e
    public void testGetRESA8e(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(240, testAirport.getResa());
    }

    @Test
    //id 8f
    public void testGetRESA8f(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(240, testAirport.getResa());
    }

    @Test
    //id 8g
    public void testGetRESA8g(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(240, testAirport.getResa());
    }

    @Test
    //id 9a
    public void testGetStripEnd9a(){
        Airport testAirport = new Airport();
        assertEquals(60, testAirport.getStripEnd());
    }

    @Test
    //id 9b
    public void testGetStripEnd9b(){
        Runway run1 = new Runway();
        Runway[] runwayArray = new Runway[]{run1};
        Airport testAirport = new Airport(runwayArray);
        assertEquals(60, testAirport.getStripEnd());
    }

    @Test
    //id 9c, needs redoing
    public void testGetStripEnd9c(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        Runway[] runwayArray = new Runway[]{run1,run2};
        Airport testAirport = new Airport(runwayArray);
        assertEquals(60, testAirport.getStripEnd());
    }

    @Test
    //id 9d
    public void testGetStripEnd9d(){
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);
        assertEquals(60, testAirport.getStripEnd());
    }

    @Test
    //id 9e
    public void testGetStripEnd9e(){
        Runway run1 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(60, testAirport.getStripEnd());
    }

    @Test
    //id 9f
    public void testGetStripEnd9f(){
        Runway run1 = new Runway();
        Runway run2 = new Runway();
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        runwayArrayList.add(run1);
        runwayArrayList.add(run2);
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(60, testAirport.getStripEnd());
    }

    @Test
    //id 9g
    public void testGetStripEnd9g(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        assertEquals(60, testAirport.getStripEnd());
    }

    @Test
    //id 10a , may violate CAA's principles on strip ends
    public void testSetStripEnd10a() throws Exception {
        Airport testAirport = new Airport();
        testAirport.setStripEnd(1);
        assertEquals(1, testAirport.getStripEnd());
    }

    @Test
    //id 10b , may violate CAA's principles on strip ends
    public void testSetStripEnd10b() throws Exception {
        Airport testAirport = new Airport();
        testAirport.setStripEnd(0);
        assertEquals(0, testAirport.getStripEnd());
    }

    @Test
    //id 10c, needs re doing
    public void testSetStripEnd10c() throws Exception {
        Airport testAirport = new Airport();
        assertThrows(Exception.class, () -> { testAirport.setStripEnd(-1); });
    }

    @Test
    //id 10d , may violate CAA's principles on strip ends
    public void testSetStripEnd10d() throws Exception {
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);
        testAirport.setStripEnd(1);
        assertEquals(1,testAirport.getStripEnd());
    }

    @Test
    //id 10e , may violate CAA's principles on strip ends
    public void testSetStripEnd10e() throws Exception {
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);
        testAirport.setStripEnd(0);
        assertEquals(0,testAirport.getStripEnd());
    }

    @Test
    //id 10f, needs redoing
    public void testSetStripEnd10f() throws Exception {
        Runway[] runwayArray = new Runway[1];
        Airport testAirport = new Airport(runwayArray);

        assertThrows(Exception.class, () -> { testAirport.setStripEnd(-1); });
    }

    @Test
    //id 10g , may violate CAA's principles on strip ends
    public void testSetStripEnd10g() throws Exception {
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        testAirport.setStripEnd(1);
        assertEquals(1, testAirport.getStripEnd());
    }

    @Test
    //id 10h , may violate CAA's principles on strip ends
    public void testSetStripEnd10h() throws Exception {
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);
        testAirport.setStripEnd(0);
        assertEquals(0, testAirport.getStripEnd());
    }

    @Test
    //id 10i , needs redoing
    public void testSetStripEnd10i(){
        ArrayList<Runway> runwayArrayList = new ArrayList<Runway>();
        Airport testAirport = new Airport(runwayArrayList);

        assertThrows(Exception.class, () -> { testAirport.setStripEnd(-1); });
    }

    @Test
    //id 11a
    public void testAddLogicalRunway11a() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"0","0","0");
        assertEquals(10, testLogRunway.getTORA());
        assertEquals(10, testLogRunway.getTODA());
        assertEquals(10, testLogRunway.getASDA());
        assertEquals(10, testLogRunway.getLDA());
        assertEquals(9, testLogRunway.getHeading()); //formatted without the 0 for numbers less than 10
        assertSame('C', testLogRunway.getLetter());
        assertEquals(0, testLogRunway.getStopway());
        assertEquals(0, testLogRunway.getClearway());
        assertEquals(0, testLogRunway.getDisplacedThreshold());

    }

    @Test
    //id 11b , questionable
    public void testAddLogicalRunway11b() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","20","20","10","09",'C',"10","10","0");
        assertEquals(10, testLogRunway.getTORA());
        assertEquals(20, testLogRunway.getTODA()); //asks for toda then adds clearway and stopway after.
        assertEquals(20, testLogRunway.getASDA());
        assertEquals(10, testLogRunway.getLDA());
        assertEquals(9, testLogRunway.getHeading()); //formatted without the 0 for numbers less than 10
        assertSame('C', testLogRunway.getLetter());
        assertEquals(10, testLogRunway.getStopway());
        assertEquals(10, testLogRunway.getClearway());
        assertEquals(0, testLogRunway.getDisplacedThreshold());
    }

    @Test
    //id 11c, should throw error
    public void testAddLogicalRunway11c() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway, "11", "10", "10", "10", "09", 'C', "0", "0", "0");
        assertThrows(Exception.class, () -> { LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway, "11", "10", "10", "10", "09", 'C', "0", "0", "0"); });
    }

    @Test
    //id 11d, should throw error, toda can't be larger than tora when there is no clearway available
    public void testAddLogicalRunway11d() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","11","10","10","09",'C',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","11","10","10","09",'C',"0","0","0"); });
    }

    @Test
    //id 11e, should throw error,
    public void testAddLogicalRunway11e() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","11","10","09",'C',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","11","10","09",'C',"0","0","0"); } );
    }

    @Test
    //id 11f, should throw error
    public void testAddLogicalRunway11f() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","11","09",'C',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","11","09",'C',"0","0","0");});
    }

    @Test
    //id 11g, should fail
    public void testAddLogicalRunway11g() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"0","0","11");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"0","0","11"); });
    }

    @Test
    //id 11h
    public void testAddLogicalRunway11h() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'q',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'q',"0","0","0");});

    }

    @Test
    //id 11i, questionable using -1 as default value
    public void testAddLogicalRunway11i() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","999",'C',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","999",'C',"0","0","0");});

    }

    @Test
    //id 11j, shouldn't except neg numbers for heading
    public void testAddLogicalRunway11j() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","-1",'C',"0","0","0");
        assertThrows(Exception.class, () -> { LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","-1",'C',"0","0","0");});
    }

    @Test
    //id 11k, should fail, stop way cannot be bigger than clearway since clearway contains stopway
    public void testAddLogicalRunway11k() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"5","3","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"5","3","0");});
    }

    @Test
    //id 11l, should throw error
    public void testAddLogicalRunway11l() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"-10","10","10","10","09",'C',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"-10","10","10","10","09",'C',"0","0","0");});
    }

    @Test
    //id 11m , should throw error
    public void testAddLogicalRunway11m() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","-10","10","10","09",'C',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","-10","10","10","09",'C',"0","0","0");});
    }

    @Test
    //id 11n, should throw error
    public void testAddLogicalRunway11n() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","-10","10","09",'C',"0","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","-10","10","09",'C',"0","0","0");});
    }

    @Test
    //id 11o, should throw error
    public void testAddLogicalRunway11o() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","-10","09",'C',"0","0","0");
        assertThrows(Exception.class, ()->{LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","-10","09",'C',"0","0","0");});
    }

    @Test
    //id 11p, should throw error
    public void testAddLogicalRunway11p() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"-10","0","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"-10","0","0");});
    }

    @Test
    //id 11q, should throw error
    public void testAddLogicalRunway11q() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"0","-10","0");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"0","-10","0");});
    }

    @Test
    //id 11r, should throw error
    public void testAddLogicalRunway11r() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        //LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"0","0","-10");
        assertThrows(Exception.class, () -> {LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'C',"0","0","-10");});
    }

    @Test
    //id 11s
    public void testAddLogicalRunway11s() throws Exception {
        Airport testAirport = new Airport();
        Runway testRunway = new Runway();
        testAirport.addRunway(testRunway);
        LogicalRunway testLogRunway = testAirport.addLogicalRunway(testRunway,"10","10","10","10","09",'L',"0","0","1");
        assertEquals(10, testLogRunway.getTORA());
        assertEquals(10, testLogRunway.getTODA());
        assertEquals(10, testLogRunway.getASDA());
        assertEquals(10, testLogRunway.getLDA());
        assertEquals(9, testLogRunway.getHeading()); //formatted without the 0 for numbers less than 10
        assertSame('L', testLogRunway.getLetter());
        assertEquals(0, testLogRunway.getStopway());
        assertEquals(0, testLogRunway.getClearway());
        assertEquals(1, testLogRunway.getDisplacedThreshold());
    }
}


package test;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LogicalTest {

    @Test
    void testBlastProtectionPartition() throws Exception {
        //No blast protection i.e. 0
        Model mod1 = new Model();
        mod1.addAirport("240","60","air1");
        Runway run1 = new Runway();
        try {
            LogicalRunway log1 = run1.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
            HashMap<String,Number> map1 = new HashMap<String,Number>();
            map1.put("27R",50);
            //no blast protection
            run1.addObstacle(map1,20,0);

            assertEquals(3534,run1.calculateTakeOffAway(log1, mod1.getAirport()).getTora());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }

        //less blast protection than resa but greater than 0
        Model mod2 = new Model();
        mod2.addAirport("240","60","air1");
        Runway run2 = new Runway();
        try {
            LogicalRunway log2 = run2.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
            HashMap<String,Number> map2 = new HashMap<String,Number>();
            map2.put("27R",50);
            run2.addObstacle(map2,20,230);

            //same value expected as with no blast protection as resa is bigger
            assertEquals(3534,run2.calculateTakeOffAway(log2, mod2.getAirport()).getTora());
        }catch(Exception e){
            e.printStackTrace();
            fail("Constructor Failed due to invalid input");
        }

        //greater blast protection than resa
        Model mod3 = new Model();
        mod3.addAirport("240","60","air1");
        Runway run3 = new Runway();
        try {
            LogicalRunway log3 = run3.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
            HashMap<String,Number> map3 = new HashMap<String,Number>();
            map3.put("27R",50);
            run3.addObstacle(map3,20,301);

            //value is one less than with no blast protection as blast protection one higher than resa
            assertEquals(3533,run3.calculateTakeOffAway(log3, mod3.getAirport()).getTora());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }


    }

    @Test
    void testInvalidInput() {
        //test that exception when input a string and not a number
        assertThrows(Exception.class, () ->{new LogicalRunway("a", "1", "1", "1", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "a", "1", "1", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "a", "1", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "a", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "a", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "1", 'L', "a", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "a", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "0", "a");});

        //ASDA larger than TODA
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "2", "1", "1", 'L', "0", "0", "1");});

        //TORA larger than ASDA
        assertThrows(Exception.class, () ->{new LogicalRunway("2", "1", "1", "1", "1", 'L', "0", "0", "1");});

        //LDA larger than TORA
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "2", "1", 'L', "0", "0", "1");});

        //Displaced Threshold not less than LDA!
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "0", "2");});

        //LDA - Displaced Threshold not less than TORA
        assertThrows(Exception.class, () ->{new LogicalRunway("2", "1", "1", "2", "1", 'L', "0", "0", "1");});

        //"TODA != TORA + Clearway"
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "2", "1", "2", "1", 'L', "0", "1", "0");});

        //"ASDA != TORA + Stopway"
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "2", "1", 'L', "0", "0", "1");});

        //negative tests
        assertThrows(Exception.class, () ->{new LogicalRunway("-1", "1", "1", "1", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "-1", "1", "1", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "-1", "1", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "-1", "1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "-1", 'L', "0", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "1", 'L', "-1", "0", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "-1", "1");});
        assertThrows(Exception.class, () ->{new LogicalRunway("1", "1", "1", "1", "1", 'L', "0", "0", "-1");});


    }




	@Test
	void testTakeOffTowardsS1() throws Exception {
		//Scenario 1
		Model mod1 = new Model();

		mod1.addAirport("240","60","air1");
		Runway run1 = new Runway();
		try {
            LogicalRunway log1 = run1.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
            HashMap<String, Number> map1 = new HashMap<String, Number>();
            map1.put("27R", 3646);
            run1.addObstacle(map1, 12, 0);
            Calculation values1 = run1.calculateTakeOffTowards(log1, mod1.getAirport());
            assertEquals(2986, values1.getTora(), "Test Takeoff Towards TORA, Senario 1");
            assertEquals(2986, values1.getToda(), "Test Takeoff Towards TODA, Senario 1");
            assertEquals(2986, values1.getAsda(), "Test Takeoff Towards ASDA, Senario 1");
        }catch(Exception e){
		    e.printStackTrace();
		    fail("Constructor Failed due to invalid input");
        }
	}
	@Test
	void testTakeOffTowardsS2() throws Exception {
	
		//Scenario 2
		Model mod2 = new Model();
        mod2.addAirport("240","60","air1");
		Runway run2 = new Runway();
		try {
            LogicalRunway log2 = run2.addLogicalRunway("3660", "3660", "3660", "3353", "9", 'R', "0", "0", "307");
            HashMap<String, Number> map2 = new HashMap<String, Number>();
            map2.put("09R", 2853);
            run2.addObstacle(map2, 25, 0);
            Calculation values2 = run2.calculateTakeOffTowards(log2, mod2.getAirport());
            assertEquals(1850, values2.getTora(), "Test Takeoff Towards TORA, Senario 2");
            assertEquals(1850, values2.getToda(), "Test Takeoff Towards TODA, Senario 2");
            assertEquals(1850, values2.getAsda(), "Test Takeoff Towards ASDA, Senario 2");
        }catch(Exception e){
		    e.printStackTrace();
            fail("Constructor Failed due to invalid input");
        }
		
	}
	@Test
	void testTakeOffTowardsS3() throws Exception {
				
		//Scenario 3
		Model mod3 = new Model();
        mod3.addAirport("240","60","air1");
		Runway run3 = new Runway();
		try{
            LogicalRunway log3 = run3.addLogicalRunway("3660", "3660", "3660", "3660", "27", 'L', "0", "0", "0");
            HashMap<String,Number> map3 = new HashMap<String,Number>();
            map3.put("27L",3203);
            run3.addObstacle(map3,15,0);
            Calculation values3 = run3.calculateTakeOffTowards(log3,mod3.getAirport());
            assertEquals(2393,values3.getTora(),"Test Takeoff Towards TORA, Senario 3");
            assertEquals(2393,values3.getToda(),"Test Takeoff Towards TODA, Senario 3");
            assertEquals(2393,values3.getAsda(),"Test Takeoff Towards ASDA, Senario 3");
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
				
	}
	@Test
	void testTakeOffTowardsS4() throws Exception {
		//Scenario 4
		Model mod4 = new Model();
        mod4.addAirport("240","60","air1");
		Runway run4 = new Runway();
		try{
            LogicalRunway log4 = run4.addLogicalRunway("3902", "3902", "3902", "3595", "9", 'L', "0", "0", "306");
            HashMap<String,Number> map4 = new HashMap<String,Number>();
            map4.put("09L",3546);
            run4.addObstacle(map4,20,0);
            Calculation values4 = run4.calculateTakeOffTowards(log4,mod4.getAirport());
            assertEquals(2792,values4.getTora(),"Test Takeoff Towards TORA, Senario 4");
            assertEquals(2792,values4.getAsda(),"Test Takeoff Towards ASDA, Senario 4");
            assertEquals(2792,values4.getToda(),"Test Takeoff Towards TODA, Senario 4");
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
	}

	@Test
	void testLandingTowardsS1() throws Exception {
		//Scenario 1
		Model mod1 = new Model();
        mod1.addAirport("240","60","air1");
		Runway run1 = new Runway();
		try {
            LogicalRunway log1 = run1.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
            HashMap<String, Number> map1 = new HashMap<String, Number>();
            map1.put("27R", 3646);
            run1.addObstacle(map1, 12, 0);
            assertEquals(3346, run1.calculateLandingTowards(log1, mod1.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
	}
	@Test
	void testLandingTowardsS2() throws Exception {
		//Scenario 2
		Model mod2 = new Model();
        mod2.addAirport("240","60","air1");
		Runway run2 = new Runway();
		try{
            LogicalRunway log2 = run2.addLogicalRunway("3660", "3660", "3660", "3353", "9", 'R', "0", "0", "307");
            HashMap<String,Number> map2 = new HashMap<String,Number>();
            map2.put("09R",2853);
            run2.addObstacle(map2,25,0);
            assertEquals(2553,run2.calculateLandingTowards(log2, mod2.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
		
		
		
		
	}
	@Test
	void testLandingTowardsS3() throws Exception {
				
		//Scenario 3
		Model mod3 = new Model();
        mod3.addAirport("240","60","air1");
		Runway run3 = new Runway();
		try {
            LogicalRunway log3 = run3.addLogicalRunway("3660", "3660", "3660", "3660", "27", 'L', "0", "0", "0");
            HashMap<String, Number> map3 = new HashMap<String, Number>();
            map3.put("27L", 3203);
            run3.addObstacle(map3, 15, 0);
            assertEquals(2903, run3.calculateLandingTowards(log3, mod3.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
				
	}
	@Test
	void testLandingTowardsS4() throws Exception {
		//Scenario 4
		Model mod4 = new Model();
        mod4.addAirport("240","60","air1");
		Runway run4 = new Runway();
		try{
            LogicalRunway log4 = run4.addLogicalRunway("3902", "3902", "3902", "3595", "9", 'L', "0", "0", "0");
            HashMap<String,Number> map4 = new HashMap<String,Number>();
            map4.put("09L",3546);
            run4.addObstacle(map4,20,0);
            assertEquals(3246,run4.calculateLandingTowards(log4, mod4.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
	}
	@Test
	void testTakeOffAwayS1() throws Exception {
		//Scenario 1
		Model mod1 = new Model();
        mod1.addAirport("240","60","air1");
		Runway run1 = new Runway();
		try{
            LogicalRunway log4 = run1.addLogicalRunway("3902", "3902", "3902", "3595", "9", 'L', "0", "0", "306");
            HashMap<String,Number> map1 = new HashMap<String,Number>();
            map1.put("09L",-50);
            run1.addObstacle(map1,25,0);
            Calculation values1 = run1.calculateTakeOffAway(log4,mod1.getAirport());
            assertEquals(3346,values1.getTora(),"Test Takeoff Away TORA, Scenario 1");
            assertEquals(3346,values1.getToda(),"Test Takeoff Away TODA, Scenario 1");
            assertEquals(3346,values1.getAsda(),"Test Takeoff Away ASDA, Scenario 1");
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
	}
	@Test
	void testTakeOffAwayS2() throws Exception {
	
		//Scenario 2
		Model mod2 = new Model();
        mod2.addAirport("240","60","air1");
		Runway run2 = new Runway();
		try{
            LogicalRunway log2 = run2.addLogicalRunway("3660", "3660", "3660", "3660", "27", 'L', "0", "0", "0");
            HashMap<String,Number> map2 = new HashMap<String,Number>();
            map2.put("27L",500);
            run2.addObstacle(map2,25,0);
            Calculation values2 = run2.calculateTakeOffAway(log2,mod2.getAirport());
            assertEquals(2860,values2.getTora(),"Test Takeoff Away TORA, Scenario 2");
            assertEquals(2860,values2.getToda(),"Test Takeoff Away TODA, Scenario 2");
            assertEquals(2860,values2.getAsda(),"Test Takeoff Away ASDA, Scenario 2");
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
		
	}
	@Test
	void testTakeOffAwayS3() throws Exception {
				
		//Scenario 3
		Model mod3 = new Model();
        mod3.addAirport("240","60","air1");
		Runway run3 = new Runway();
		try{
            LogicalRunway log3 = run3.addLogicalRunway("3660", "3660", "3660", "3353", "9", 'R', "0", "0", "307");
            HashMap<String,Number> map3 = new HashMap<String,Number>();
            map3.put("09R",150);
            run3.addObstacle(map3,15,0);
            Calculation values3 = run3.calculateTakeOffAway(log3,mod3.getAirport());
            assertEquals(2903,values3.getTora(),"Test Takeoff Away TORA, Scenario 3");
            assertEquals(2903,values3.getToda(),"Test Takeoff Away TODA, Scenario 3");
            assertEquals(2903,values3.getAsda(),"Test Takeoff Away ASDA, Scenario 3");
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
				
	}
	@Test
	void testTakeOffAwayS4() throws Exception {
		//Scenario 4
		Model mod4 = new Model();
        mod4.addAirport("240","60","air1");
		Runway run4 = new Runway();
		try{
            LogicalRunway log4 = run4.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
            HashMap<String,Number> map4 = new HashMap<String,Number>();
            map4.put("27R",50);
            run4.addObstacle(map4,20,0);
            Calculation values4 = run4.calculateTakeOffAway(log4,mod4.getAirport());
            assertEquals(3534,values4.getTora(),"Test Takeoff Away TORA, Scenario 4");
            assertEquals(3534,values4.getAsda(),"Test Takeoff Away ASDA, Scenario 4");
            assertEquals(3612,values4.getToda(),"Test Takeoff Away TODA, Scenario 4");
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
	}
	
	@Test
	void testLandingOverS1() throws Exception {
		//Scenario 1
		Model mod1 = new Model();
        mod1.addAirport("240","60","air1");
		Runway run1 = new Runway();
		try{
            LogicalRunway log1 = run1.addLogicalRunway("3902", "3902", "3902", "3595", "9", 'L', "0", "0", "306");
            HashMap<String,Number> map1 = new HashMap<String,Number>();
            map1.put("09L",-50);
            run1.addObstacle(map1,12,0);
            assertEquals(2985,run1.calculateLandingOver(log1, mod1.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
	}
	@Test
	void testLandingOverS2() throws Exception {
	
		//Scenario 2
		Model mod2 = new Model();
        mod2.addAirport("240","60","air1");
		Runway run2 = new Runway();
		try{
            LogicalRunway log2 = run2.addLogicalRunway("3660", "3660", "3660", "3660", "27", 'L', "0", "0", "0");
            HashMap<String,Number> map2 = new HashMap<String,Number>();
            map2.put("27L",500);
            run2.addObstacle(map2,25,0);
            assertEquals(1850,run2.calculateLandingOver(log2, mod2.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
		
	}
	@Test
	void testLandingOverS3() throws Exception {
				
		//Scenario 3
		Model mod3 = new Model();
        mod3.addAirport("240","60","air1");
		Runway run3 = new Runway();
		try{
            LogicalRunway log3 = run3.addLogicalRunway("3660", "3660", "3660", "3353", "9", 'R', "0", "0", "307");
            HashMap<String,Number> map3 = new HashMap<String,Number>();
            map3.put("09R",150);
            run3.addObstacle(map3,15,0);
            assertEquals(2393,run3.calculateLandingOver(log3, mod3.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
				
	}

    @Test
    void testLandingOverS4() throws Exception {
        //Scenario 4
        Model mod4 = new Model();
        mod4.addAirport("240","60","air1");
        Runway run4 = new Runway();
        try {
            LogicalRunway log4 = run4.addLogicalRunway("3884", "3962", "3884", "3884", "27", 'R', "0", "78", "0");
            HashMap<String,Number> map4 = new HashMap<String,Number>();
            map4.put("27R",50);
            run4.addObstacle(map4,20,0);
            assertEquals(2774,run4.calculateLandingOver(log4, mod4.getAirport()).getLda());
        }catch(Exception e){
            fail("Constructor Failed due to invalid input");
        }
    }

}



/*
LogicalRunway logical = mod.addLogicalRunway(3660,3660,3660,3353, 9,'R',0,0,307);


HashMap<String,Number> map = new HashMap<>();
map.put("9R",2853);
map.put("27R",3646);
runway.addObstacle(map,25,0);

HashMap<String,Number> values = runway.calculateTakeOffTowards(logical,mod.getAirport());
for(String val : values.keySet()){
    System.out.println(val+": "+values.get(val));
}



//System.out.println(runway.calculateLandingOver(logical));


*/

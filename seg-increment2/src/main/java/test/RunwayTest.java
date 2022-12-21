
package test;

//import static org.junit.jupiter.api.Assertions.*;
import model.*;

//import org.junit.jupiter.api.BeforeEach;
import model.LogicalRunway;
import model.Runway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class RunwayTest {


    @Test
	//id 1
	public void testConstructor(){
   		Runway runway = new Runway("name");
   		//Runway example = new Runway();
   		//example.setName("name");

   		assertEquals("name", runway.getName());
	}

	@Test
	//id 2a
	public void testValidConstructor2a() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway(testLogRunHash,testObstacle);
		assertEquals(testLogRunHash,testRunway.getLogicalRunways());
		assertSame(testObstacle,testRunway.getObstacle());
	}

	@Test
	//id 2b
	public void testValidConstructor2b() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway(testLogRunHash,testObstacle);
		assertEquals(testLogRunHash,testRunway.getLogicalRunways());
		assertEquals(testLogRun,testRunway.getLogicalRunways().get("36C"));
		assertEquals(testLogRun2,testRunway.getLogicalRunways().get("18C"));
		assertSame(testObstacle,testRunway.getObstacle());
	}

	@Test
	//id 2c
	public void testInvalidConstructor2c() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		LogicalRunway testLogRun3 = new LogicalRunway("10","10","10","10","01",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		testLogRunHash.put(testLogRun3.getCombinedReference(),testLogRun3);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {Runway testRunway = new Runway(testLogRunHash,testObstacle);});
	}

	@Test
	//id 3a
	public void testValidConstructor3a() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway("name",testLogRunHash,testObstacle);
		assertEquals(testLogRunHash,testRunway.getLogicalRunways());
		assertSame(testObstacle,testRunway.getObstacle());
		assertEquals("name",testRunway.getName());
	}

	@Test
	//id 3b
	public void testValidConstructor3b() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway("name",testLogRunHash,testObstacle);
		assertEquals(testLogRunHash,testRunway.getLogicalRunways());
		assertEquals(testLogRun,testRunway.getLogicalRunways().get("36C"));
		assertEquals(testLogRun2,testRunway.getLogicalRunways().get("18C"));
		assertSame(testObstacle,testRunway.getObstacle());
		assertEquals("name",testRunway.getName());
	}

	@Test
	//id 3c -> hashmap needs to be checked
	public void testInvalidConstructor3c() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","04",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		LogicalRunway testLogRun3 = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		testLogRunHash.put(testLogRun3.getCombinedReference(),testLogRun3);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {Runway testRunway = new Runway("name",testLogRunHash,testObstacle);});
	}

	@Test
	//id 4a
	public void testValidConstructor4a() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		Runway testRunway = new Runway("name",testLogRunHash);
		assertEquals(testLogRunHash,testRunway.getLogicalRunways());
		assertEquals("name",testRunway.getName());
	}

	@Test
	//id 4b
	public void testValidConstructor4b() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		Runway testRunway = new Runway("name",testLogRunHash);
		assertEquals(testLogRunHash,testRunway.getLogicalRunways());
		assertEquals(testLogRun,testRunway.getLogicalRunways().get("36C"));
		assertEquals(testLogRun2,testRunway.getLogicalRunways().get("18C"));
		assertEquals("name",testRunway.getName());
	}

	@Test
	//id 4c
	public void testInvalidConstructor4c() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","01",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		LogicalRunway testLogRun3 = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		testLogRunHash.put(testLogRun3.getCombinedReference(),testLogRun3);
		Assertions.assertThrows(Exception.class, () -> {Runway testRunway = new Runway("name",testLogRunHash);});
	}

	@Test
	//id 5
	public void testGetLargestLogicalRunway() throws Exception {
		LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","36",'L',"0","0","0");

		LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","18",'R',"0","0","0");


		Runway runway = new Runway();
		runway.addLogicalRunway(logic1);
		runway.addLogicalRunway(logic2);


		assertEquals(logic1, runway.getLargestLogicalRunway());
   }

    @Test
	//id 6a , should throw error
	public void testAddLogicalRunway6a() throws Exception {
   		Runway runway = new Runway();
   		LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
	    LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","10",'L',"0","0","0");

	    runway.addLogicalRunway(logic1);
		Assertions.assertThrows(Exception.class, () -> {runway.addLogicalRunway(logic2);});

	    //HashMap<String,LogicalRunway> logicalRunways;


	    //assertEquals(logic1, runway.getLogicalRunways().get("01R"));
	    //assertEquals(logic2, runway.getLogicalRunways().get("10L"));



   }

    @Test
	//id 6b , should throw error perhaps
	public void testAddLogicalRunway6b() throws Exception {
    	LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","09",'L',"0","0","0");
    	Runway runway = new Runway();
    	runway.addLogicalRunway(logic1);
		LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","09",'L',"0","0","0");
		Assertions.assertThrows(Exception.class, () -> {runway.addLogicalRunway(logic2);});
		//LogicalRunway logic3 = new LogicalRunway("3660","3660","3660","3660","09",'L',"0","0","0");
		//runway.addLogicalRunway(logic3);
		//System.out.println(logic1.getCombinedReference());

   }

    @Test
	//id 6c
	public void testAddLogicalRunway6c() throws Exception {
		LogicalRunway logic1 = new LogicalRunway("3660", "3660", "3660", "3660", "09", 'L', "0", "0", "0");

		LogicalRunway logic2 = new LogicalRunway("3660", "3660", "3660", "3660", "27", 'R', "0", "0", "0");

		LogicalRunway logic3 = new LogicalRunway("3660", "3660", "3660", "3660", "18", 'L', "0", "0", "0");
		Runway runway = new Runway();


		Assertions.assertThrows(Exception.class, () -> {
			runway.addLogicalRunway(logic1);
			runway.addLogicalRunway(logic2);
			runway.addLogicalRunway(logic3);
		});
	}

	@Test
	//id 6d
	public void testAddLogicalRunway6d() throws Exception {
		LogicalRunway logic1 = new LogicalRunway("3660", "3660", "3660", "3660", "09", 'L', "0", "0", "0");
		Runway runway = new Runway();
		runway.addLogicalRunway(logic1);
		assertEquals(3660, logic1.getTORA());
		assertEquals(3660, logic1.getTODA());
		assertEquals(3660, logic1.getASDA());
		assertEquals(3660, logic1.getLDA());
		assertEquals(9, logic1.getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('L', logic1.getLetter());
		assertEquals(0, logic1.getStopway());
		assertEquals(0, logic1.getClearway());
		assertEquals(0, logic1.getDisplacedThreshold());
	}

	@Test
	//id 6e
	public void testAddLogicalRunway6e() throws Exception {
		LogicalRunway logic1 = new LogicalRunway("3660", "3660", "3660", "3660", "09", 'L', "0", "0", "0");
		Runway runway = new Runway();
		runway.addLogicalRunway(logic1);
		assertEquals(3660, logic1.getTORA());
		assertEquals(3660, logic1.getTODA());
		assertEquals(3660, logic1.getASDA());
		assertEquals(3660, logic1.getLDA());
		assertEquals(9, logic1.getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('L', logic1.getLetter());
		assertEquals(0, logic1.getStopway());
		assertEquals(0, logic1.getClearway());
		assertEquals(0, logic1.getDisplacedThreshold());

		LogicalRunway logic2 = new LogicalRunway("3660", "3660", "3660", "3660", "27", 'R', "0", "0", "0");
		runway.addLogicalRunway(logic2);
		assertEquals(3660, logic2.getTORA());
		assertEquals(3660, logic2.getTODA());
		assertEquals(3660, logic2.getASDA());
		assertEquals(3660, logic2.getLDA());
		assertEquals(27, logic2.getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('R', logic2.getLetter());
		assertEquals(0, logic2.getStopway());
		assertEquals(0, logic2.getClearway());
		assertEquals(0, logic2.getDisplacedThreshold());
	}


	@Test
	//id 7a
	public void testAddObstacle7a() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway(testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class,() -> {testRunway.addObstacle(testPos,1,300,0);});

	}

	@Test
	//id 7b
	public void testAddObstacle7b() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway(testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		testRunway.addObstacle(testPos,1,300,0);
		assertEquals(testObstacle.getObstaclePosition(),testRunway.getObstacle().getObstaclePosition());
		assertEquals(testObstacle.getHeight(),testRunway.getObstacle().getHeight());
		assertEquals(testObstacle.getBlastProtection(),testRunway.getObstacle().getBlastProtection());
		assertEquals(testObstacle.getDistanceFromCentre(),testRunway.getObstacle().getDistanceFromCentre());
	}

	@Test
	//id 7c
	public void testAddObstacle7c() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {testRunway.addObstacle(testPos,1,300,0);});

	}

	@Test
	//id 7d
	public void testAddObstacle7d() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		testRunway.addObstacle(testPos,1,300,0);
		assertEquals(testObstacle.getObstaclePosition(),testRunway.getObstacle().getObstaclePosition());
		assertEquals(testObstacle.getHeight(),testRunway.getObstacle().getHeight());
		assertEquals(testObstacle.getBlastProtection(),testRunway.getObstacle().getBlastProtection());
		assertEquals(testObstacle.getDistanceFromCentre(),testRunway.getObstacle().getDistanceFromCentre());
	}

	@Test
	//id 7e
	public void testAddObstacle7e() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {testRunway.addObstacle(testPos,1,300,0);});
	}

	@Test
	//id 7f
	public void testAddObstacle7f() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		testRunway.addObstacle(testPos,1,300,0);
		assertEquals(testObstacle.getObstaclePosition(),testRunway.getObstacle().getObstaclePosition());
		assertEquals(testObstacle.getHeight(),testRunway.getObstacle().getHeight());
		assertEquals(testObstacle.getBlastProtection(),testRunway.getObstacle().getBlastProtection());
		assertEquals(testObstacle.getDistanceFromCentre(),testRunway.getObstacle().getDistanceFromCentre());
	}

	@Test
	//id 7g
	public void testAddObstacle7g() throws Exception{
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name");
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {testRunway.addObstacle(testPos,1,300,0);});
	}

	@Test
	//id 8a
	public void testAddObstacle8a() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway(testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {testRunway.addObstacle(testPos,1,300);});

	}

	@Test
	//id 8b
	public void testAddObstacle8b() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway(testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		testRunway.addObstacle(testPos,1,300);
		assertEquals(testObstacle.getObstaclePosition(),testRunway.getObstacle().getObstaclePosition());
		assertEquals(testObstacle.getHeight(),testRunway.getObstacle().getHeight());
		assertEquals(testObstacle.getBlastProtection(),testRunway.getObstacle().getBlastProtection());
	}

	@Test
	//id 8c
	public void testAddObstacle8c() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {testRunway.addObstacle(testPos,1,300);});
	}

	@Test
	//id 8d
	public void testAddObstacle8d() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash,null);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		testRunway.addObstacle(testPos,1,300);
		assertEquals(testObstacle.getObstaclePosition(),testRunway.getObstacle().getObstaclePosition());
		assertEquals(testObstacle.getHeight(),testRunway.getObstacle().getHeight());
		assertEquals(testObstacle.getBlastProtection(),testRunway.getObstacle().getBlastProtection());
	}

	@Test
	//id 8e
	public void testAddObstacle8e() throws Exception {
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {testRunway.addObstacle(testPos,1,300);});
	}

	@Test
	//id 8f
	public void testAddObstacle8f() throws Exception {
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name",testLogRunHash);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		testRunway.addObstacle(testPos,1,300);
		assertEquals(testObstacle.getObstaclePosition(),testRunway.getObstacle().getObstaclePosition());
		assertEquals(testObstacle.getHeight(),testRunway.getObstacle().getHeight());
		assertEquals(testObstacle.getBlastProtection(),testRunway.getObstacle().getBlastProtection());
	}

	@Test
	//id 8g
	public void testAddObstacle8g() throws Exception{
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",10);
		Runway testRunway = new Runway("name");
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Assertions.assertThrows(Exception.class, () -> {testRunway.addObstacle(testPos,1,300);});
	}

	@Test
	//id 9a
	public void testGetLogicalRunways9a() throws Exception{
		Runway runway = new Runway("name");
		assertEquals(0,runway.getLogicalRunways().size());
	}

	@Test
	//id 9b
	public void testGetLogicalRunways9b() throws Exception{
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway(testLogRunHash,testObstacle);
		assertEquals(0,testRunway.getLogicalRunways().size());
	}

	@Test
	//id 9c
	public void testGetLogicalRunways9c() throws Exception{
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway(testLogRunHash,testObstacle);
		assertEquals(2,testRunway.getLogicalRunways().size());
	}

	@Test
	//id 9d
	public void testGetLogicalRunways9d() throws Exception{
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway("name",testLogRunHash,testObstacle);
		assertEquals(0,testRunway.getLogicalRunways().size());
	}

	@Test
	//id 9e
	public void testGetLogicalRunways9e() throws Exception{
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		Obstacle testObstacle = new Obstacle(testPos,1,300,0);
		Runway testRunway = new Runway("name",testLogRunHash,testObstacle);
		assertEquals(2,testRunway.getLogicalRunways().size());
	}

	@Test
	//id 9f
	public void testGetLogicalRunways9f() throws Exception{
		//LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","00",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		//testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		Runway testRunway = new Runway("name",testLogRunHash);
		assertEquals(0,testRunway.getLogicalRunways().size());
	}

	@Test
	//id 9g
	public void testGetLogicalRunways9g() throws Exception{
		LogicalRunway testLogRun = new LogicalRunway("10","10","10","10","36",'C',"0","0","0");
		LogicalRunway testLogRun2 = new LogicalRunway("10","10","10","10","18",'C',"0","0","0");
		HashMap<String,LogicalRunway> testLogRunHash = new HashMap<String, LogicalRunway>();
		testLogRunHash.put(testLogRun.getCombinedReference(),testLogRun);
		testLogRunHash.put(testLogRun2.getCombinedReference(),testLogRun2);
		Runway testRunway = new Runway("name",testLogRunHash);
		assertEquals(2,testRunway.getLogicalRunways().size());
	}

	@Test
	//id 10a
	public void testAddLogicalRunway10a() throws Exception {
		Runway runway = new Runway();
		//LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		//LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","10",'L',"0","0","0");

		runway.addLogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTORA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTODA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getASDA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getLDA());
		assertEquals(1, runway.getLogicalRunways().get("01R").getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('R', runway.getLogicalRunways().get("01R").getLetter());
		assertEquals(0, runway.getLogicalRunways().get("01R").getStopway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getClearway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getDisplacedThreshold());
	}

	@Test
	//id 10b
	public void testAddLogicalRunway10b() throws Exception {
		Runway runway = new Runway();
		//LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		//LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","19",'L',"0","0","0");

		runway.addLogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTORA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTODA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getASDA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getLDA());
		assertEquals(1, runway.getLogicalRunways().get("01R").getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('R', runway.getLogicalRunways().get("01R").getLetter());
		assertEquals(0, runway.getLogicalRunways().get("01R").getStopway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getClearway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getDisplacedThreshold());

		runway.addLogicalRunway("3660","3660","3660","3660","19",'L',"0","0","0");
		assertEquals(3660, runway.getLogicalRunways().get("19L").getTORA());
		assertEquals(3660, runway.getLogicalRunways().get("19L").getTODA());
		assertEquals(3660, runway.getLogicalRunways().get("19L").getASDA());
		assertEquals(3660, runway.getLogicalRunways().get("19L").getLDA());
		assertEquals(19, runway.getLogicalRunways().get("19L").getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('L', runway.getLogicalRunways().get("19L").getLetter());
		assertEquals(0, runway.getLogicalRunways().get("19L").getStopway());
		assertEquals(0, runway.getLogicalRunways().get("19L").getClearway());
		assertEquals(0, runway.getLogicalRunways().get("19L").getDisplacedThreshold());
	}

	@Test
	//id 10c
	public void testAddLogicalRunway10c() throws Exception {
		Runway runway = new Runway();
		//LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		//LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","19",'L',"0","0","0");

		runway.addLogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTORA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTODA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getASDA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getLDA());
		assertEquals(1, runway.getLogicalRunways().get("01R").getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('R', runway.getLogicalRunways().get("01R").getLetter());
		assertEquals(0, runway.getLogicalRunways().get("01R").getStopway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getClearway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getDisplacedThreshold());

		Assertions.assertThrows(Exception.class, () -> {runway.addLogicalRunway("3660","3660","3660","3660","10",'L',"0","0","0");});

	}

	@Test
	//id 10d
	public void testAddLogicalRunway10d() throws Exception {
		Runway runway = new Runway();
		//LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		//LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","19",'L',"0","0","0");

		runway.addLogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTORA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTODA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getASDA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getLDA());
		assertEquals(1, runway.getLogicalRunways().get("01R").getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('R', runway.getLogicalRunways().get("01R").getLetter());
		assertEquals(0, runway.getLogicalRunways().get("01R").getStopway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getClearway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getDisplacedThreshold());
		//runway.addLogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		//Assertions.assertThrows(Exception.class, () -> {runway.addLogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");});

	}

	@Test
	//id 10e
	public void testAddLogicalRunway10e() throws Exception {
		Runway runway = new Runway();
		//LogicalRunway logic1 = new LogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		//LogicalRunway logic2 = new LogicalRunway("3660","3660","3660","3660","19",'L',"0","0","0");

		runway.addLogicalRunway("3660","3660","3660","3660","01",'R',"0","0","0");
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTORA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getTODA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getASDA());
		assertEquals(3660, runway.getLogicalRunways().get("01R").getLDA());
		assertEquals(1, runway.getLogicalRunways().get("01R").getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('R', runway.getLogicalRunways().get("01R").getLetter());
		assertEquals(0, runway.getLogicalRunways().get("01R").getStopway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getClearway());
		assertEquals(0, runway.getLogicalRunways().get("01R").getDisplacedThreshold());

		runway.addLogicalRunway("3660","3660","3660","3660","19",'L',"0","0","0");
		assertEquals(3660, runway.getLogicalRunways().get("19L").getTORA());
		assertEquals(3660, runway.getLogicalRunways().get("19L").getTODA());
		assertEquals(3660, runway.getLogicalRunways().get("19L").getASDA());
		assertEquals(3660, runway.getLogicalRunways().get("19L").getLDA());
		assertEquals(19, runway.getLogicalRunways().get("19L").getHeading()); //formatted without the 0 for numbers less than 10
		assertSame('L', runway.getLogicalRunways().get("19L").getLetter());
		assertEquals(0, runway.getLogicalRunways().get("19L").getStopway());
		assertEquals(0, runway.getLogicalRunways().get("19L").getClearway());
		assertEquals(0, runway.getLogicalRunways().get("19L").getDisplacedThreshold());
		//runway.addLogicalRunway("3660","3660","3660","3660","36",'R',"0","0","0");
		Assertions.assertThrows(Exception.class, () -> {runway.addLogicalRunway("3660","3660","3660","3660","36",'R',"0","0","0");});

	}

	@Test
	//id 11a
	public void testCalculationTakeOffAway11a() throws Exception {
    	Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
    	testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
    	testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
    	Airport testAirport = new Airport(runwayArray);
		Calculation testTakeOffAway = testRunway.calculateTakeOffAway(testRunway.getLargestLogicalRunway(),testAirport);
		assertEquals("1700 (R TORA) = 2000 (TORA) - 0 (Distance from Threshold) - 0 (displaced threshold) - 240 (RESA) - 60 (Strip End)",testTakeOffAway.getToraCalculation());
		assertEquals("1700 (R TODA) = 1700 (R TORA) + 0 (Clearway)",testTakeOffAway.getTodaCalculation());
		assertEquals("1700 (R ASDA) = 1700 (R TORA) + 0 (Stopway)",testTakeOffAway.getAsdaCalculation());
	}

	@Test
	//id 11b
	public void testCalculationTakeOffAway11b() throws Exception {
		Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
		testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		//HashMap<String,Number> testPos = new HashMap<String,Number>();
		//testPos.put("00C",0);
		//testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
		Airport testAirport = new Airport(runwayArray);
		Assertions.assertThrows(Exception.class , () -> {Calculation testTakeOffAway = testRunway.calculateTakeOffAway(testRunway.getLargestLogicalRunway(),testAirport);});
	}

	@Test
	//id 12a
	public void testCalculationTakeOffTowards12a() throws Exception {
		Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
		testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",1900);
		testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
		Airport testAirport = new Airport(runwayArray);
		Calculation testTakeOffAway = testRunway.calculateTakeOffTowards(testRunway.getLargestLogicalRunway(),testAirport);
		assertEquals("1340 (R TORA) = 1900 (Distance From Threshold) + 0 (Displaced Threshold) - 500 (Slope) - 60 (Strip End)",testTakeOffAway.getToraCalculation());
		assertEquals("1340 (TODA) = (R TORA)",testTakeOffAway.getTodaCalculation());
		assertEquals("1340 (ASDA) = (R TORA)",testTakeOffAway.getAsdaCalculation());
	}

	@Test
	//id 12b
	public void testCalculationTakeOffTowards12b() throws Exception {
		Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
		testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		//HashMap<String,Number> testPos = new HashMap<String,Number>();
		//testPos.put("00C",1900);
		//testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
		Airport testAirport = new Airport(runwayArray);
		Assertions.assertThrows(Exception.class , () -> {Calculation testTakeOffAway = testRunway.calculateTakeOffTowards(testRunway.getLargestLogicalRunway(),testAirport);});
	}

	@Test
	//id 13a
	public void testCalculationLandingOver13a() throws Exception {
		Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
		testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",0);
		testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
		Airport testAirport = new Airport(runwayArray);
		Calculation testTakeOffAway = testRunway.calculateLandingOver(testRunway.getLargestLogicalRunway(),testAirport);
		assertEquals("1440 (R LDA) = 2000 (LDA) -0 (Distance From Threshold) - 500 (Slope) - 60 (Strip End)",testTakeOffAway.getLdaCalculation());
	}

	@Test
	//id 13b
	public void testCalculationLandingOver13b() throws Exception {
		Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
		testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		//HashMap<String,Number> testPos = new HashMap<String,Number>();
		//testPos.put("00C",0);
		//testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
		Airport testAirport = new Airport(runwayArray);
		Assertions.assertThrows(Exception.class , () -> {Calculation testTakeOffAway = testRunway.calculateLandingOver(testRunway.getLargestLogicalRunway(),testAirport);});
	}

	@Test
	//id 14a
	public void testCalculationLandingTowards14a() throws Exception {
		Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
		testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		HashMap<String,Number> testPos = new HashMap<String,Number>();
		testPos.put("36C",1900);
		testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
		Airport testAirport = new Airport(runwayArray);
		Calculation testTakeOffAway = testRunway.calculateLandingTowards(testRunway.getLargestLogicalRunway(),testAirport);
		assertEquals("1600 (R LDA) = 1900 (Distance from Threshold) - 240 (RESA) - 60 (Strip End)",testTakeOffAway.getLdaCalculation());
	}

	@Test
	//id 14b
	public void testCalculationLandingTowards14b() throws Exception {
		Runway testRunway = new Runway();
		ArrayList<Runway> runwayArray = new ArrayList<Runway>();
		testRunway.addLogicalRunway("2000","2000","2000","2000","36",'C',"0","0","0");
		//HashMap<String,Number> testPos = new HashMap<String,Number>();
		//testPos.put("00C",1900);
		//testRunway.addObstacle(testPos,10,0,0);
		runwayArray.add(testRunway);
		Airport testAirport = new Airport(runwayArray);
		Assertions.assertThrows(Exception.class , () -> {Calculation testTakeOffAway = testRunway.calculateLandingTowards(testRunway.getLargestLogicalRunway(),testAirport);});
	}

}



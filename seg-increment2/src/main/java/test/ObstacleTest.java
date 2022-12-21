package test;


import model.Obstacle;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ObstacleTest {

	//Unit tetsing


	@Test
	//id 1a
	public void testValidMap1() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01R", 1);
		Obstacle obs1 = new Obstacle(map1, 1, 1);
		assertEquals(true, obs1.getObstaclePosition().containsKey("01R"));
		assertEquals(true, obs1.getObstaclePosition().containsValue(1));

	}
	@Test
	//id 1b
	public void testValidMap2() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1R", 1);
		Obstacle obs1 = new Obstacle(map1, 1, 1);
		assertEquals(true, obs1.getObstaclePosition().containsKey("01R"));
		assertEquals(true, obs1.getObstaclePosition().containsValue(1));

	}

	@Test
	//id 1c
	public void testValidMap3() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01L", 1);
		Obstacle obs1 = new Obstacle(map1, 1, 1);
		assertEquals(true, obs1.getObstaclePosition().containsKey("01L"));
		assertEquals(true, obs1.getObstaclePosition().containsValue(1));

	}
	@Test
	//id 1d
	public void testMapBoundary1() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01R", 0);
		Obstacle obs1 = new Obstacle(map1, 1, 1);
		assertEquals(true, obs1.getObstaclePosition().containsKey("01R"));
		assertEquals(true, obs1.getObstaclePosition().containsValue(0));

	}

	@Test
	//id 1e
	public void testMapBoundary2() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01R", Integer.MAX_VALUE);
		Obstacle obs = new Obstacle(map1, 1, 1);
		assertEquals(true, obs.getObstaclePosition().containsKey("01R"));
		assertEquals(true, obs.getObstaclePosition().containsValue(Integer.MAX_VALUE));


	}

	@Test(expected = Exception.class)
	//id 1f
	public void testInvalidMap1() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01P", 1);
		Obstacle obs = new Obstacle(map1, 1, 1);
	}
	@Test(expected = Exception.class)
	//id 1g
	public void testInvalidMap2() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1LL", 1);
		Obstacle obs = new Obstacle(map1, 1, 1);
	}
	@Test(expected = Exception.class)
	//id 1h
	public void testInvalidMap3() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("-3R", 1);
		Obstacle obs = new Obstacle(map1, 1, 1);
	}

	@Test(expected = Exception.class)
	//id 1i
	public void testInvalidMap4() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("001R", 1);
		Obstacle obs = new Obstacle(map1, 1, 1);
	}
	@Test(expected = Exception.class)
	//id 1j
	public void testInvalidMaps() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("3R", 1);
		map1.put("5P", 1);
		Obstacle obs = new Obstacle(map1, 1, 1);
	}
	@Test
	//id 1k
	public void testValidMap5() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1R", -1);
		Obstacle obs = new Obstacle(map1, 1, 1);
		assertEquals(-1, obs.getObstaclePosition().get("01R"));
	}
	@Test
	//id 1l
	public void testValidMaps() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("3R", 1);
		map1.put("9L", 1);
		Obstacle obs1 = new Obstacle(map1, 1, 1);
		assertEquals(true, obs1.getObstaclePosition().containsKey("03R"));
		assertEquals(true, obs1.getObstaclePosition().containsValue(1));

		assertEquals(true, obs1.getObstaclePosition().containsKey("09L"));
		assertEquals(true, obs1.getObstaclePosition().containsValue(1));

	}
	@Test
	//id 2a
	public void testValidBlastProtection() throws Exception {
	HashMap<String, Number> map1 = new HashMap<String, Number>();
	map1.put("1R", 1);
	Obstacle obs1 = new Obstacle(map1, 1, 1);
	assertEquals(1, obs1.getBlastProtection());

}
	@Test
	//id 2b
	public void testBlastProtectionBoundary1() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1R", 1);
		Obstacle obs1 = new Obstacle(map1, 1, 0);
		assertEquals(0, obs1.getBlastProtection());
	}

	@Test
	//id 2c
	public void testBlastProtectionBoundary2() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1R", 1);
		Obstacle obs1 = new Obstacle(map1, 1, Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, obs1.getBlastProtection());
	}

	@Test(expected = Exception.class)
	//id 2d
	public void testInvalidBlastProtection() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1R", 1);
		Obstacle obs1 = new Obstacle(map1, 1, -1);
	}

	@Test
	//id 2e
	public void testHeightBoundary1() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1R", 1);
		Obstacle obs1 = new Obstacle(map1, 0, 1);
		assertEquals(0, obs1.getHeight());

	}

	@Test
	//id 2f
	public void testHeightBoundary2() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1R", 1);
		Obstacle obs1 = new Obstacle(map1, Integer.MAX_VALUE, 1);
		assertEquals(Integer.MAX_VALUE, obs1.getHeight());

	}
	@Test(expected = Exception.class)
    //id 2g
	public void testInvalidHeight() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("-3R", 1);
		Obstacle obsPos = new Obstacle(map1, -1, 1);
	}







    @Test
	//id 3a
    public void testValidSetBlastProtection() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obs = new Obstacle(map1, 1, 1);
        obs.setBlastProtection(2);
        assertEquals(2, obs.getBlastProtection());
    }
	@Test
	//id 3b
	public void testBoundarySetBlastProtection() throws Exception {
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01R", 1);
		Obstacle obs = new Obstacle(map1, 1, 1);
		obs.setBlastProtection(0);
		assertEquals(0, obs.getBlastProtection());
	}

	@Test(expected = Exception.class)
	//id 3c
	public void testSetInvalidBlast() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		obstacle.setBlastProtection(-1);
	}

	@Test
	//id 4a
    public void testValidSetHeight() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obs = new Obstacle(map1, 1, 1);
        obs.setHeight(2);
        assertEquals(2, obs.getHeight());
    }
    @Test
	//id 4b
    public void testBoundarySetHeight() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obs = new Obstacle(map1, 1, 1);
        obs.setHeight(0);
        assertEquals(0, obs.getHeight());
    }
    @Test(expected = Exception.class)
	//id 4c
    public void testNegativeSetHeight() throws Exception {
        HashMap<String, Number> map1 = new HashMap<String, Number>();
        map1.put("01R", 1);
        Obstacle obs = new Obstacle(map1, 1, 1);
        obs.setHeight(-1);
    }

	@Test(expected = Exception.class)
    //id 5a
	public void testObsPosSetterLetter() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("3P", 1);
		obstacle.setObstaclePosition(map1);
	}
	@Test(expected = Exception.class)
    //id 5b
	public void testObsPosSetterList() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map2 = new HashMap<String, Number>();
		map2.put("3L", 1);
		map2.put("2P", 1);
		obstacle.setObstaclePosition(map2);
	}

	@Test(expected = Exception.class)
    //id 5c
	public void testObsPosSetterMapValue() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map3 = new HashMap<String, Number>();
		map3.put("3L", 1);
		map3.put("222R", 1);
		obstacle.setObstaclePosition(map3);
	}

	@Test(expected = Exception.class)
    //id 5d
	public void testObsPosNumber() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map4 = new HashMap<String, Number>();
		map4.put("RL", 1);
		map4.put("2L", 1);
		obstacle.setObstaclePosition(map4);
	}
	@Test
	//id 5e
	public void testValidObsPosSetter1() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("1L", 1);
		obstacle.setObstaclePosition(map1);
	}
	@Test
	//id 5f
	public void testValidObsPosSetter2() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01R", 1);
		obstacle.setObstaclePosition(map1);
	}
	@Test
	//id 5g
	public void testValidObsPosSetter3() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01C", 1);
		obstacle.setObstaclePosition(map1);
	}
	@Test
	//id 5h
	public void testValidObsPosSetter4() throws Exception {
		Obstacle obstacle = new Obstacle(1, 1, 1, 1, 1);
		HashMap<String, Number> map1 = new HashMap<String, Number>();
		map1.put("01L", 0);
		obstacle.setObstaclePosition(map1);
	}

}

package test;

//import static org.junit.jupiter.api.Assertions.*;

import model.Plane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import model.*;

class PlaneTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	//id 1
	public void testValidPlane() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, 2);
		assertEquals("name", plane.getName());
		assertEquals("model", plane.getModel());
		assertEquals("call", plane.getCallSign());
		assertEquals(1, plane.getLength());
		assertEquals(2, plane.getHeight());
	}

	@Test
	//id 2
	public void testNameBoundary() throws Exception {
		Plane plane = new Plane("1", "model", "call", 1, 2);
		assertEquals("1", plane.getName());
	}
	@Test
	//id 3
	public void testModelBoundary() throws Exception {
		Plane plane = new Plane("name", "1", "call", 1, 2);
		assertEquals("1", plane.getModel());
	}
	@Test
	//id 4
	public void testCallsignBoundary() throws Exception {
		Plane plane = new Plane("name", "model", "1", 1, 2);
		assertEquals("1", plane.getCallSign());
	}
	@Test
	//id 5
	public void testLengthBoundary() throws Exception {
		Plane plane = new Plane("name", "model", "call", Integer.MAX_VALUE, 2);
		assertEquals(Integer.MAX_VALUE, plane.getLength());
	}
	@Test
	//id 6
	public void testHeightBoundary() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, Integer.MAX_VALUE);
	}

	@Test
	//id 7
	public void TestInvalidName(){
		Assertions.assertThrows(Exception.class, () -> { new Plane("", "model", "call", 1, 2); });
	}

	@Test
	//id 8
	public void TestInvalidModel(){
		Assertions.assertThrows(Exception.class, () -> { new Plane("name", "", "call", 1, 2); });
	}

	@Test
	//id 9
	public void TestInvalidCallsign(){
		Assertions.assertThrows(Exception.class, () -> { new Plane("name", "model", "", 1, 2); });
	}

	@Test
	//id 10
	public void TestInvalidLength(){
		Assertions.assertThrows(Exception.class, () -> { new Plane("name", "model", "call", -1, 2); });
	}

	@Test
	//id 11
	public void TestInvalidHeight(){
		Assertions.assertThrows(Exception.class, () -> { new Plane("name", "model", "call", 1, -2); });
	}

	@Test
	//id 12
	public void testValidSetters() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, 2);
		plane.setName("newName");
		plane.setModel("newModel");
		plane.setCallSign("newCall");
		plane.setLength(10);
		plane.setHeight(20);
		assertEquals("newName", plane.getName());
		assertEquals("newModel", plane.getModel());
		assertEquals("newCall", plane.getCallSign());
		assertEquals(10, plane.getLength());
		assertEquals(20, plane.getHeight());
	}

	@Test
	//id 13a
	public void TestInvalidNameSetter() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, 2);
		Assertions.assertThrows(Exception.class, () -> { plane.setName(""); });


	}

	@Test
	//id 13b
	public void TestValidNameSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setName("testPlane");
		assertEquals("testPlane",planeTest.getName());
	}

	@Test
	//id 13c
	public void TestUpdatedValidNameSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setName("testPlane");
		planeTest.setName("Black Bird");
		assertEquals("Black Bird",planeTest.getName());
	}

	@Test
	//id 14a
	public void TestInvalidModelSetter() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, 2);
		Assertions.assertThrows(Exception.class, () -> { plane.setModel(""); });	}

	@Test
	//id 14b
	public void TestValidModelSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setModel("testPlane");
		assertEquals("testPlane",planeTest.getModel());
	}

	@Test
	//id 14c
	public void TestUpdatedModelNameSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setModel("testPlane");
		planeTest.setModel("SR-71");
		assertEquals("SR-71",planeTest.getModel());
	}

	@Test
	//id 15a
	public void TestInvalidCallsignSetter() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, 2);
		Assertions.assertThrows(Exception.class, () -> { plane.setCallSign(""); });
	}

	@Test
	//id 15b
	public void TestValidCallsignSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setCallSign("testPlane");
		assertEquals("testPlane",planeTest.getCallSign());
	}

	@Test
	//id 15c
	public void TestUpdatedValidCallsignSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setCallSign("testPlane");
		planeTest.setCallSign("Big Ben");
		assertEquals("Big Ben",planeTest.getCallSign());
	}

	@Test
	//id 16a
	public void TestInvalidLengthSetter() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, 2);
		Assertions.assertThrows(Exception.class, () -> { plane.setLength(-1); });

	}

	@Test
	//id 16b
	public void TestValidBoundaryLengthSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setLength(0);
		assertEquals(0,planeTest.getLength());
	}

	@Test
	//id 16c
	public void TestUpdatedValidLengthSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setLength(1);
		assertEquals(1,planeTest.getLength());
	}


	@Test
	//id 17a
	public void TestInvalidHeightSetter() throws Exception {
		Plane plane = new Plane("name", "model", "call", 1, 2);
		Assertions.assertThrows(Exception.class, () -> { plane.setHeight(-2); });
	}

	@Test
	//id 17b
	public void TestValidBoundaryHeightSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setHeight(0);
		assertEquals(0,planeTest.getHeight());
	}

	@Test
	//id 17c
	public void TestUpdatedValidHeightSetter() throws Exception {
		Plane planeTest = new Plane("name", "model", "call", 1, 2);
		planeTest.setHeight(1);
		assertEquals(1,planeTest.getHeight());
	}
}

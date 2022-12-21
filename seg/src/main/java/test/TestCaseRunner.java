package test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses( {ModelTest.class,ObstacleTest.class,PlaneTest.class,RunwayTest.class,LogicalTest.class})


public class TestCaseRunner {
}






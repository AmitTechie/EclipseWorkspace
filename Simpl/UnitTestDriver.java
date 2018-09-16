package Simpl;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class UnitTestDriver {
	
	public static void runUnitTests() {
		Result result = JUnitCore.runClasses(RandomNumberGeneratorTest.class);
		System.out.println(result.wasSuccessful());
		
		result = JUnitCore.runClasses(PlayerTest.class);
		System.out.println(result.wasSuccessful());
		
		result = JUnitCore.runClasses(TeamTest.class);
		System.out.println(result.wasSuccessful());

		result = JUnitCore.runClasses(MatchTest.class);
		System.out.println(result.wasSuccessful());

	}

}

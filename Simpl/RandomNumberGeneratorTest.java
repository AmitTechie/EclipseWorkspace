package Simpl;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RandomNumberGeneratorTest {
	
	@Test
	public void equalProbabilityRandomNumberTest() {
		//this will test that numbers in the range [0-99] both 0 and 99 inclusive will be
		//generated uniformly means with equal probability
		
		//test the functionality 10 times
		int TestCount  = 10;
		while(TestCount-- > 0) {
			RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
			Set<Integer> numberSet = new HashSet<>();
			for(int i=0; i<100; i++) {
				int newNumber = randomNumberGenerator.getRandomNumber();
				assertFalse(numberSet.contains(newNumber));
				numberSet.add(newNumber);
			}
		}
	}

}

package Simpl;

import java.util.Random;

//this class will generate random numbers uniformly in the range [0-99]
public class RandomNumberGenerator {
	
	private int []numbers;
	private int N;
	private Random random;
	
	public RandomNumberGenerator() {
		N = 100;
		numbers = new int[N];
		random = new Random();
		for(int i=0; i<N; i++) {
			numbers[i] = i;
		}
	}
	
	private void resetNumbers() {
		N = 100;
		for(int i=0; i<N; i++) {
			numbers[i] = i;
		}
	}
	
	//it will return number in the range of [0-99] with guaranteed uniformity. 
	public int getRandomNumber() {

		if(N == 0) {
			resetNumbers();
		}	

		int randumNumber = random.nextInt(N--);
		int result = numbers[randumNumber];
		numbers[randumNumber] = numbers[N];
		return result;
	}

}

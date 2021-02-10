package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriorityQueueTest {

	private SimplePriorityQueue<Integer> singleInteger;
	private SimplePriorityQueue<Integer> manyIntegers;
	private SimplePriorityQueue<Double> manyDoubles;
	private SimplePriorityQueue<Character> manyCharacters;

	private Random rand;
	private String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@BeforeEach
	void TestSetup() {
		rand = new Random();
		
		singleInteger = new SimplePriorityQueue<>();
		singleInteger.insert(1);

		manyIntegers = new SimplePriorityQueue<>();
		for (int i = 0; i < 1000; i++)
			manyIntegers.insert(rand.nextInt(999) + 1);
		manyIntegers.insert(0);

		manyDoubles = new SimplePriorityQueue<>();
		for (int i = 0; i < 1000; i++)
			manyDoubles.insert(rand.nextDouble() + 1.0);
		manyDoubles.insert(0.0);
		
		manyCharacters = new SimplePriorityQueue<>();
		for (int i = 0; i < 1000; i++)
			manyCharacters.insert(allCharacters.charAt(rand.nextInt(26)));
		manyCharacters.insert('A');
	}

	@Test
	void testOneElement() {
		assertTrue(singleInteger.findMin() == 1);
	}

	@Test
	void testManyIntegers() {
		assertTrue(manyIntegers.findMin() == 0);
	}

	@Test
	void testManyDoubles() {
		assertTrue(manyDoubles.findMin() == 0.0);
	}
	
	@Test
	void testManyCharacters() {
		assertTrue(manyCharacters.findMin() == 'A');
	}
}

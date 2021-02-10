package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriorityQueueTest {

	private SimplePriorityQueue<Integer> singleInteger;
	private SimplePriorityQueue<Integer> manyIntegers;
	private SimplePriorityQueue<String> manyStrings;
	private String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private Random rand;
	
	@BeforeEach
	void TestSetup() {
		rand = new Random();
		
		singleInteger = new SimplePriorityQueue<>();
		singleInteger.insert(1);

		manyIntegers = new SimplePriorityQueue<>();
		for (int i = 100; i > 0; i--)
			manyIntegers.insert(rand.nextInt(100) + 1);
		manyIntegers.insert(0);
	}

	@Test
	void testOneElement() {
		assertTrue(singleInteger.findMin() == 1);
	}

	@Test
	void testManyIntegers() {
		System.out.print(manyIntegers.findMin());
		assertTrue(manyIntegers.findMin() == 0);
	}
	
	@Test
	void testManyStrings() {
		for (int i = 0; i < 1000; i++) {
			String tempString = "";
			for (int j = 0; j < 10; j++) {
				tempString += allCharacters.charAt(rand.nextInt(26));
			}
			manyStrings.insert(tempString);
		}
		manyStrings.insert("a");
		assertTrue(manyStrings.findMin() == "a");
	}
}

package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriorityQueueTest {

	private SimplePriorityQueue<Integer> singleInteger;
	private SimplePriorityQueue<Integer> manyIntegers;
	private SimplePriorityQueue<Double> manyDoubles;
	private SimplePriorityQueue<Character> manyCharacters;
	private SimplePriorityQueue<String> manyStrings;
	
	private SimplePriorityQueue<Integer> insertAllIntegers;
	
	private ArrayList<Integer> arrayListIntegers;

	private Random rand;
	private String allCharacters = "abcdefghijklmnopqrstuvwxyz";

	@BeforeEach
	void TestSetup() {
		rand = new Random();

		singleInteger = new SimplePriorityQueue<>();
		manyIntegers = new SimplePriorityQueue<>();
		manyDoubles = new SimplePriorityQueue<>();
		manyCharacters = new SimplePriorityQueue<>();
		manyStrings = new SimplePriorityQueue<>();
		insertAllIntegers = new SimplePriorityQueue<>();
		arrayListIntegers = new ArrayList<>();

		for (int i = 0; i < 1000; i++) {
			manyIntegers.insert(rand.nextInt(999) + 1);
			manyDoubles.insert(rand.nextDouble() + 1.0);
			manyCharacters.insert(allCharacters.charAt(rand.nextInt(26)));
			arrayListIntegers.add(rand.nextInt(999) + 1);
			
			String tempString = "";
			for (int j = 0; j < 10; j++)
				tempString += allCharacters.charAt(rand.nextInt(26));
			manyStrings.insert(tempString);
		}
	}

	@Test
	void testOneElement() {
		singleInteger.insert(1);
		assertTrue(singleInteger.findMin() == 1);
	}

	@Test
	void testManyIntegers() {
		manyIntegers.insert(0);
		assertTrue(manyIntegers.findMin() == 0);
	}

	@Test
	void testManyDoubles() {
		manyDoubles.insert(0.0);
		assertTrue(manyDoubles.findMin() == 0.0);
	}

	@Test
	void testManyCharacters() {
		manyCharacters.insert('a');
		assertTrue(manyCharacters.findMin() == 'a');
	}

	@Test
	void testManyStrings() {
		manyStrings.insert("aaaaaaaaaa");
		assertTrue(manyStrings.findMin().equals("aaaaaaaaaa"));
	}
	
	@Test
	void testClear() {
		singleInteger.clear();
		manyIntegers.clear();
		manyDoubles.clear();
		manyCharacters.clear();
		manyStrings.clear();
		assertTrue(singleInteger.isEmpty() && manyIntegers.isEmpty() && manyDoubles.isEmpty() && manyCharacters.isEmpty() && manyStrings.isEmpty());
	}
	
	@Test 
	void testInsertAllIntegers() {
		insertAllIntegers.insertAll(arrayListIntegers);
		insertAllIntegers.insert(0);
		assertTrue(insertAllIntegers.findMin() == 0);
	}
}

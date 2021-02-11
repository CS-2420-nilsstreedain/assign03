package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
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
	private SimplePriorityQueue<Integer> inverseInteger;

	private Random rand;
	private String allCharacters = "abcdefghijklmnopqrstuvwxyz";
	private ArrayList<Integer> arrayListIntegers;

	@BeforeEach
	void TestSetup() {
		rand = new Random();

		singleInteger = new SimplePriorityQueue<>();
		manyIntegers = new SimplePriorityQueue<>();
		manyDoubles = new SimplePriorityQueue<>();
		manyCharacters = new SimplePriorityQueue<>();
		manyStrings = new SimplePriorityQueue<>();
		insertAllIntegers = new SimplePriorityQueue<>();
		inverseInteger = new SimplePriorityQueue<>((i1, i2) -> (i2 - i1));

		arrayListIntegers = new ArrayList<>();

		for (int i = 0; i < 1000; i++) {
			manyIntegers.insert(rand.nextInt(999) + 1);
			manyDoubles.insert(rand.nextDouble() + 1.0);
			manyCharacters.insert(allCharacters.charAt(rand.nextInt(26)));
			arrayListIntegers.add(rand.nextInt(999) + 1);
			inverseInteger.insert(rand.nextInt(999) + 1);

			String tempString = "";
			for (int j = 0; j < 10; j++)
				tempString += allCharacters.charAt(rand.nextInt(26));
			manyStrings.insert(tempString);
		}
	}

	@Test
	void testOneElement() {
		singleInteger.insert(1);
		assertEquals(1, singleInteger.findMin());
	}

	@Test
	void testManyIntegers() {
		manyIntegers.insert(0);
		assertEquals(0, manyIntegers.findMin());
	}

	@Test
	void testManyDoubles() {
		manyDoubles.insert(0.0);
		assertEquals(0.0, manyDoubles.findMin());
	}

	@Test
	void testManyCharacters() {
		manyCharacters.insert('a');
		assertEquals('a', manyCharacters.findMin());
	}

	@Test
	void testManyStrings() {
		manyStrings.insert("aaaaaaaaaa");
		assertEquals("aaaaaaaaaa", manyStrings.findMin());
	}

	@Test
	void testClear() {
		singleInteger.clear();
		manyIntegers.clear();
		manyDoubles.clear();
		manyCharacters.clear();
		manyStrings.clear();
		assertTrue(singleInteger.isEmpty() && manyIntegers.isEmpty() && manyDoubles.isEmpty()
				&& manyCharacters.isEmpty() && manyStrings.isEmpty());
	}

	@Test
	void testInsertAllIntegers() {
		insertAllIntegers.insertAll(arrayListIntegers);
		insertAllIntegers.insert(0);
		assertEquals(0, insertAllIntegers.findMin());
	}

	@Test
	void testDeleteException() {
		assertThrows(NoSuchElementException.class, () -> {
			insertAllIntegers.deleteMin();
		});
	}
	
	@Test
	void testFindMinException() {
		assertThrows(NoSuchElementException.class, () -> {
			insertAllIntegers.findMin();
		});
	}

	@Test
	void testComparatorConstructor() {
		inverseInteger.insert(1000);
		assertEquals(1000, inverseInteger.findMin());
	}

	@Test
	void testDeleteMin() {
		manyIntegers.insert(0);
		assertEquals(0, manyIntegers.deleteMin());
	}
}

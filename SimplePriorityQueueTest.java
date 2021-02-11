package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the SimplePriiorityQueue class.
 * 
 * @author Paul Nuffer, Nils Streedain
 * @version February 10, 2021
 */
class SimplePriorityQueueTest {

	private SimplePriorityQueue<Integer> emptyInteger;
	private SimplePriorityQueue<Integer> manyIntegers;
	private SimplePriorityQueue<Double> manyDoubles;
	private SimplePriorityQueue<Character> manyCharacters;
	private SimplePriorityQueue<String> manyStrings;
	private SimplePriorityQueue<Integer> insertAllIntegers;
	private SimplePriorityQueue<Integer> inverseInteger;
	private SimplePriorityQueue<String> sizeSortStrings;
	private SimplePriorityQueue<String> inverseSizeSortStrings;

	private Random rand;
	private ArrayList<Integer> arrayListIntegers;
	private String allCharacters = "abcdefghijklmnopqrstuvwxyz";

	@BeforeEach
	void TestSetup() {

		emptyInteger = new SimplePriorityQueue<>();
		manyIntegers = new SimplePriorityQueue<>();
		manyDoubles = new SimplePriorityQueue<>();
		manyCharacters = new SimplePriorityQueue<>();
		manyStrings = new SimplePriorityQueue<>();
		insertAllIntegers = new SimplePriorityQueue<>();
		inverseInteger = new SimplePriorityQueue<>((i1, i2) -> (i2 - i1));
		sizeSortStrings = new SimplePriorityQueue<>((i1, i2) -> (i1.length() - i2.length()));
		inverseSizeSortStrings = new SimplePriorityQueue<>((i1, i2) -> (i2.length() - i1.length()));

		rand = new Random();
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

			for (int j = 0; j < rand.nextInt(15) + 2; j++)
				tempString += allCharacters.charAt(rand.nextInt(26));
			sizeSortStrings.insert(tempString);
			inverseSizeSortStrings.insert(tempString);
		}
	}

	@Test
	void testOneElement() {
		emptyInteger.insert(1);
		assertEquals(1, emptyInteger.findMin());
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
		assertFalse(manyIntegers.isEmpty());
		emptyInteger.clear();
		manyIntegers.clear();
		manyDoubles.clear();
		manyCharacters.clear();
		manyStrings.clear();
		assertTrue(emptyInteger.isEmpty() && manyIntegers.isEmpty() && manyDoubles.isEmpty() && manyCharacters.isEmpty()
				&& manyStrings.isEmpty()); // all queues must be empty to pass
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
	void testStringComparatorConstructor() {
		sizeSortStrings.insert("a");
		assertEquals("a", sizeSortStrings.findMin());
	}

	@Test
	void testInverseStringComparatorConstructor() {
		inverseSizeSortStrings.insert("123456789012345678901234567890");
		assertEquals("123456789012345678901234567890", inverseSizeSortStrings.findMin());
	}

	@Test
	void testDeleteMin() {
		manyIntegers.insert(0);
		assertEquals(0, manyIntegers.deleteMin());
	}

	@Test
	void testManyRemove() {
		for (int i = 0; i < 100; i++)
			emptyInteger.insert(1);
		for (int i = 0; i < 100; i++)
			emptyInteger.deleteMin();
		assertTrue(emptyInteger.isEmpty());
	}

	@Test
	void testManyRemoveLarge() {
		manyIntegers.insert(0);
		for (int i = 0; i < 100; i++)
			manyIntegers.insert(-1);
		for (int i = 0; i < 100; i++)
			manyIntegers.deleteMin();
		assertEquals(0, manyIntegers.findMin());
	}

	@Test
	void testEmptySize() {
		assertEquals(0, emptyInteger.size());
	}

	@Test
	void testLargeSize() {
		assertEquals(1000, manyIntegers.size());
	}
}

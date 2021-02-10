package assign03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PriorityQueueTest {

	@Test
	void testOneElement() {
		SimplePriorityQueue<Integer> small = new SimplePriorityQueue<Integer>();
		small.insert(1);
		assertTrue(small.findMin() == 1);
		
	}

}

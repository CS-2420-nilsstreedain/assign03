package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A priority queue that supports access of the minimum element only.
 * 
 * @author Paul Nuffer, Nils Streedain
 * @version February 10, 2021
 *
 * @param <E> the type of elements contained in this priority queue
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {

	private int size = 0;
	private Object[] arr;
	private Comparator<? super E> cmp;

	private final int INITIAL_ARRAY_SIZE = 10;

	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		arr = (E[]) new Object[INITIAL_ARRAY_SIZE];
		cmp = null;
	}

	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		arr = (E[]) new Object[INITIAL_ARRAY_SIZE];
		this.cmp = cmp;
	}

	/**
	 * Retrieves, but does not remove, the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E findMin() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		// min is always at the highest index, size - 1
		return (E) arr[size - 1];
	}

	/**
	 * Retrieves and removes the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E deleteMin() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		size--;

		// after decrementing size, the min is now at index size instead of size - 1
		return (E) arr[size];
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert(Object item) {
		E goal = (E) item;

		// calls the binary search helper method to determine the index to place an item
		int index = insertSearch(goal);

		// checks if the backing array is full and a doubling is needed
		if (arr.length == size) {
			// Makes a new array of double length, copies all values over, and sets our
			// array to the new larger one
			Object[] newArr = new Object[arr.length * 2];
			for (int i = 0; i < arr.length; i++)
				newArr[i] = arr[i];
			arr = newArr;
		}

		// Starts at the end of the array, and shifts values up 1 until reaching our
		// desired placement
		for (int i = size; i > index; i--)
			arr[i] = arr[i - 1];

		arr[index] = item;
		size++;
	}

	/**
	 * Searches for the index to place the item in insert using a binary search.
	 * 
	 * @param goal - the goal for the binary search
	 * @return mid - the index for the item to be placed
	 */
	@SuppressWarnings("unchecked")
	public int insertSearch(E goal) {
		int low = 0, high = size - 1, mid = high / 2;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (compare(goal, (E) arr[mid]) == 0)
				return mid;
			else if (compare(goal, (E) arr[mid]) < 0)
				low = mid + 1;
			else
				high = mid - 1;
		}

		// because of our additive shifting behavior, we return low to ensure we get a
		// value 1 greater than the element the new element is less than
		return low;
	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param obj1 - first element to compared
	 * @param obj2 - second element to compared
	 * @return comparison between obj1 and obj2
	 */
	@SuppressWarnings("unchecked")
	private int compare(E obj1, E obj2) {
		if (cmp == null)
			return ((Comparable<? super E>) obj1).compareTo(obj2); // default comparator
		return cmp.compare(obj1, obj2); // passed in comparator
	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (Object object : coll)
			insert(object);
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be empty
	 * when this call returns.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		arr = (E[]) new Object[INITIAL_ARRAY_SIZE];
		size = 0;
	}
}

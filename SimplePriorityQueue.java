package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

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
		int index = insertSearch(goal);

		if (arr.length == size) {
			Object[] newArr = new Object[arr.length * 2];
			for (int i = 0; i < arr.length; i++)
				newArr[i] = arr[i];
			arr = newArr;
		}

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
			return ((Comparable<? super E>) obj1).compareTo(obj2);
		return cmp.compare(obj1, obj2);
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
		if (size == 0)
			return true;
		return false;
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

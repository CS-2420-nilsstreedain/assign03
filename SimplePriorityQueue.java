package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E> {
	
	private int size = 0;
	private Object[] arr;
	private Comparator<? super E> cmp;
	
	private final int INITIAL_ARRAY_SIZE = 1000;

	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		arr =  (E[]) new Object[INITIAL_ARRAY_SIZE];
	}


	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		arr =  (E[]) new Object[INITIAL_ARRAY_SIZE];
		this.cmp = cmp;
	}


	@Override
	public E findMin() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public E deleteMin() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void insert(Object item) {
		E goal = (E) item;
		int index = insertSearch(goal);
		
		//actually add the thing at index
		
	}
	
	@SuppressWarnings("unchecked")
	public int insertSearch(E goal) {
		int low = 0, high = arr.length - 1, mid = high/2;
		while(low <= high) {
			mid = (low + high) / 2;
			if(((Comparable<? super E>)goal).compareTo((E)arr[mid]) == 0) 
				break;
			else if(((Comparable<? super E>)goal).compareTo((E)arr[mid]) == -1) 
				high = mid - 1;
			else 
				low = mid + 1;
		}
		return mid;
	}


	@Override
	public void insertAll(Collection coll) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int size() {
		return size;
	}


	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}

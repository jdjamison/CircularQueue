// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Joe Jamison
// RESOURCES: Only the starter code and javadoc were used on this assignment.

package uno.collections.queues;

/**
 * Provides a standard definition of a circular queue (aka, a ring buffer)
 * with a maximum capacity.  All queue operations are guaranteed to complete
 * in constant time. 
 * 
 * @author jdjamison
 *
 * @param <E> The type of an individual element stored in the queue.
 */
public class BoundedCircularQueue<E> implements Queue<E>
{
	/**
	 * The array where we will store data in this queue.
	 */
	private E[] values;
	
	/**
	 * The index of the current front item in the queue.
	 */
	private int front;
	
	/**
	 * The index of the current rear item in the queue.	
	 */
	private int rear;
	
	/**
	 * The number of items currently stored in the queue.
	 */
	private int numberOfItems;
	
	/**
	 * Constructs a new, initially empty BoundedCircularQueue with 
	 * a default maximum capacity of 10.
	 */
	
	public BoundedCircularQueue()
	{
		// Define the method per the provided docs.
		this(10);
	}
	
	/**
	 * Constructs a new, initially empty BoundedCircularQueue.
	 * 
	 * @param capacity The maximum number of items that can be stored 
	 * in this queue.
	 */
	@SuppressWarnings("unchecked")
	public BoundedCircularQueue(int capacity)
	{
		// Define the method per the provided docs.
		values = (E[]) new Object[capacity];
		front = 0;
		rear = values.length - 1;
		numberOfItems = 0;
	}
	
	/* (non-Javadoc)
	 * @see uno.collections.queues.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		// Define the method per the provided interface docs.
		return numberOfItems == 0;
	}

	/* (non-Javadoc)
	 * @see uno.collections.queues.Queue#size()
	 */
	@Override
	public int size()
	{
		// Define the method per the provided interface docs.
		return numberOfItems;
	}

	/* (non-Javadoc)
	 * @see uno.collections.queues.Queue#peek()
	 */
	@Override
	public E peek() throws IllegalStateException
	{
		// Define the method per the provided interface docs. 
		if (isEmpty())
		{
			throw new IllegalStateException("Cannot peek on an empty queue!");
		}
		return values[front];
	}

	/* (non-Javadoc)
	 * @see uno.collections.queues.Queue#dequeue()
	 */
	@Override
	public E dequeue() throws IllegalStateException
	{
		if (isEmpty())
		{
			throw new IllegalStateException("Cannot dequeue from an empty queue!");
		}
		//T Define the method per the provided interface docs. 
		E returnVal = values[front];
		front = (front + 1) % values.length;
		numberOfItems--;
		return returnVal;
	}

	/**
	 * Add an item to the rear of this queue.
	 * 
	 * @param item The value to be added to the queue.
	 * @throws IllegalStateException when the queue is full.  The message will read 
	 * "Cannot enqueue because queue is already full!"
	 */
	@Override
	public void enqueue(E item)
	{
		// Define enqueue as described in the docs.  Notice that
		//      this version of enqueue should throw an exception when 
		//      no additional item can be added!
		if (isFull())
		{
			throw new IllegalStateException("Cannot enqueue because queue is already full!");
		}
		rear = (rear + 1) % values.length;
		values[rear] = item;
		numberOfItems++;
	}

	/**
	 * A method to test whether this BoundedCircularQueue is currently
	 * full.
	 * 
	 * @return true if this queue is full, false if at least one more item 
	 * can be enqueued.
	 */
	public boolean isFull()
	{
		return numberOfItems == values.length;
		
	}
	
	
	
}

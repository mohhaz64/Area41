
/**
 * 
 * This class implements a queue with methods to check if it's empty, peek,
 * dequeue, enqueue and print the queue.
 * @version 2.2.9
 * @author Ben Hyde
 *
 */

import java.util.NoSuchElementException;

/**
 * Implementation of Queue ADT
 * @param <T> type of queue
 */
public class Queue<T> {

	private QueueElement<T> head;
	private QueueElement<T> tail;
	
	/**
	 * Constructs an empty Queue.
	 */
	public Queue () {
		head = null;
		tail = null;
	}
	
	/**
	 * Returns true if the queue is empty
	 * @return true if empty.
	 */
	public boolean isEmpty () {
		return ((head == null) && (tail == null));
	}
	
	
	/**
	 * Returns the element at the head of the queue
	 * @return first element in queue.
	 */
	public T peek () throws NoSuchElementException {
		if (!isEmpty()) {
			return head.getElement();
		}
		else {
			//If the queue is empty, it throws a NoSuchElementException
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Removes the front element of the queue
	 */
	public void dequeue () throws NoSuchElementException {
		
		if (!isEmpty()) {
			QueueElement<T> front = head.getNext();
			head = front;
		}
		else {
			//If the queue is empty, it throws a NoSuchElementException
			throw new NoSuchElementException();
		}
		//If the last element is dequeue, it will make sure both head and tail are reset
		if (head == null) {
			tail = null;
		}
	}
	
	/**
	 * Puts an element on the back of the queue.
	 * @param element to be added.
	 */
	public void enqueue (T element) {
		
		QueueElement<T> new_QueueElement = new QueueElement<T>(element, null);
		
		if (isEmpty()) {
			head = new_QueueElement;
			tail = new_QueueElement;
		}
		else {
			tail.setNext(new_QueueElement);
			tail = tail.getNext();
		}
	}
	
	/**
	 * Method to print the full contents of the queue in order from head to tail.
	 */
	public void print () {
		
		if (!isEmpty()) {
			QueueElement<T> temp = head;
			
			//Iterates through the queue until the tail
			while (temp.getNext() != null) {
				System.out.print(temp.getElement() + ",\n");
				temp = temp.getNext();
			}
			
			//Prints the tail
			System.out.println(temp.getElement() + ",\n");
		}
		else {
			System.out.println("The queue is empty.");
		}
	}
}

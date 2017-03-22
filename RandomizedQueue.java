import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Node<Item> _head = null;
	private int _count = 0;

	public RandomizedQueue() // construct an empty randomized queue
	{
	}

	private void throwIfNull(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
	}

	public boolean isEmpty() // is the queue empty?
	{
		return _count == 0;
	}

	public int size() // return the number of items on the queue
	{
		return _count;
	}

	public void enqueue(Item item) // add the item
	{
		throwIfNull(item);

		if (_head == null)
			_head = new Node<Item>(item);
		else
			_head = new Node<Item>(item, _head, null);

		_count++;
	}

	public Item dequeue() // remove and return a random item
	{
		int randomNum = StdRandom.uniform(_count);

		Node<Item> current = _head;

		for (int i = 0; i < randomNum; i++) {
			current = current._prev;
		}

		if (current._prev != null)
			current._prev._next = current._next;

		if (current._next != null)
			current._next._prev = current._prev;

		if (current == _head)
			_head = current._prev;

		_count--;

		return current.getItem();
	}

	public Item sample() // return (but do not remove) a random item
	{
		int randomNum = StdRandom.uniform(_count);

		Node<Item> current = _head;

		for (int i = 0; i < randomNum; i++) {
			current = current._prev;
		}

		return current.getItem();
	}

	public Iterator<Item> iterator() // return an independent iterator over
										// items in random order
	{
		return new RandomizedQueueIterator(this);
	}

	@SuppressWarnings("hiding")
	private class Node<Item> {

		public Node(Item item) {
			this._item = item;
		}

		public Node(Item item, Node<Item> prev, Node<Item> next) {
			this._item = item;

			if (prev != null) {
				prev._next = this;
				this._prev = prev;
			}

			if (next != null) {
				this._next = next;
				next._prev = this;
			}
		}

		private Item _item;

		public Item getItem() {
			return _item;
		}

		public Node<Item> _next = null;
		public Node<Item> _prev = null;
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		RandomizedQueue<Item> _rq;

		public RandomizedQueueIterator(RandomizedQueue<Item> rq) {
			_rq = rq;
		}

		@Override
		public boolean hasNext() {
			return !_rq.isEmpty();
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();

			return _rq.dequeue();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunTests();

	}

	private void print() {
		p("");
		Node<Item> node = _head;
		while (node != null) {
			p(node.getItem());
			node = node._prev;
		}
	}

	private static void p(Object o) {
		System.out.println(o);
	}

	private static void RunTests() {
		TestEnqueue();
		TestAddNullItem();
		TestIterator();
	}

	private static void TestEnqueue() {
		RandomizedQueue<String> rq = new RandomizedQueue<>();

		rq.enqueue("a");
		rq.enqueue("b");
		rq.enqueue("c");
		rq.print();

		rq.dequeue();
		rq.print();

		rq.dequeue();
		rq.print();

		rq.dequeue();
		rq.print();

		rq.dequeue();

	}

	private static void TestAddNullItem() {

	}

	private static void TestIterator() {

	}
}

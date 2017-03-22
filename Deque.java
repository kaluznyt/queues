import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> _first = null;
	private Node<Item> _last = null;
	private int _length = 0;

	public static void main(String[] args) {
		RunTests();
	}

	private static void p(Object o) {
		System.out.println(o);
	}

	public boolean isEmpty() // is the deque empty?
	{
		return _length == 0;
	}

	public int size() // return the number of items on the deque
	{
		return _length;
	}

	private void throwIfNull(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
	}

	private boolean addFirstNode(Item item) {
		if (_first == null && _last == null) {
			Node<Item> node = new Node<Item>(item);
			_first = _last = node;
			_length = 1;
			return true;
		}

		return false;
	}

	private Item removeLastNode() {
		Item item = null;

		if (_first != null && _last != null && _first == _last) {
			item = _first._item;
			_first = _last = null;
			_length = 0;
			return item;
		}

		return item;
	}

	public void addFirst(Item item) // add the item to the front
	{
		throwIfNull(item);

		if (addFirstNode(item))
			return;
		else {
			_first = new Node<Item>(item, null, _first);
			_length++;
		}
	}

	public void addLast(Item item) // add the item to the end
	{
		throwIfNull(item);

		if (addFirstNode(item))
			return;
		else {
			_last = new Node<Item>(item, _last, null);
			_length++;
		}
	}

	public Item removeFirst() // remove and return the item from the front
	{
		if (isEmpty())
			throw new NoSuchElementException();

		Item item = null;

		if ((item = removeLastNode()) != null) {
			return item;
		} else if (_first != null && _first._next != null) {
			item = _first.getItem();

			if (_first._next != null) {
				_first._next._prev = null;
				_first = _first._next;
			}

			if (_first == _last) {
				_first = _last;
				_last = _first;
			}

			_length--;
		}

		return item;
	}

	public Item removeLast() // remove and return the item from the end
	{
		if (isEmpty())
			throw new NoSuchElementException();

		Item item = null;

		if ((item = removeLastNode()) != null) {
			return item;
		} else if (_last != null && _last._prev != null) {
			item = _last.getItem();

			if (_last._prev != null) {
				_last._prev._next = null;
				_last = _last._prev;
			}

			if (_last == _first) {
				_first = _last;
				_last = _first;
			}

			_length--;
		}

		return item;
	}

	public Iterator<Item> iterator() // return an iterator over items in order
										// from front to end
	{
		return new DequeIterator(this);
	}

	// private void print() {
	// Node<Item> node = _first;
	//
	// while (node != null) {
	// System.out.println(node.getItem());
	// node = node._next;
	// }
	// }

	private class DequeIterator implements Iterator<Item> {
		private Deque<Item> _deque = null;
		private Node<Item> _currentNode = null;

		public DequeIterator(Deque<Item> deque) {
			_deque = deque;
			_currentNode = deque._first;
		}

		@Override
		public boolean hasNext() {
			return _currentNode != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();

			Item item = _currentNode.getItem();
			_currentNode = _currentNode._next;

			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

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

	private static void RunTests() {
		TestRemoveFromEmpty();
		TestAddNullItem();
		TestIterator();
		Test2();
	}

	private static void TestRemoveFromEmpty() {
		Deque<String> deque = new Deque<String>();
		deque.addFirst("1");
		deque.addLast("2");

		deque.removeFirst();
		deque.removeFirst();

		try {
			p(deque.removeFirst());
		} catch (NoSuchElementException ex) {
			p("Passed TestRemoveFromEmpty");
			return;
		}

		p("Failed TestRemoveFromEmpty");

	}

	private static void TestAddNullItem() {
		try {
			Deque<String> deque = new Deque<String>();
			deque.addFirst(null);
		} catch (NullPointerException ex) {

			try {
				Deque<String> deque = new Deque<String>();
				deque.addLast(null);
			} catch (NullPointerException ex2) {

				p("Passed TestAddNullItem");
			}
		}
	}

	private static void TestIterator() {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		deque.addFirst(4);
		deque.addFirst(5);

		Iterator<Integer> iter = deque.iterator();

		while (iter.hasNext()) {
			p(iter.next());
		}

	}

	private static void Test2() {
		Deque<Integer> deque = new Deque<>();

		deque.addFirst(0);
		deque.addFirst(1);
		deque.removeLast();
		deque.isEmpty();
		deque.isEmpty();
		deque.removeLast();
	}

}

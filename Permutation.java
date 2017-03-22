import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			args = new String[1];
			args[0] = "10";
		}

		RandomizedQueue<String> _rq = new RandomizedQueue<>();
		String line = StdIn.readLine();

		// String[] inputStrings = new String[5];
		// inputStrings[0] = "a";
		// inputStrings[1] = "b";
		// inputStrings[2] = "c";
		// inputStrings[3] = "d";
		// inputStrings[4] = "e";
		String[] inputStrings = line.split("\\s");

		int times = Integer.parseInt(args[0]);

		for (String s : inputStrings) {
			_rq.enqueue(s);
		}

		Iterator<String> i = _rq.iterator();

		while (times > 0 && i.hasNext()) {
			String n = i.next();
			StdOut.println(n);
			times--;
		}
	}
}

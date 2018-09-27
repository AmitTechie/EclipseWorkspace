package RoundRobinIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		
		List<String> names = new ArrayList<>();
		names.add("Amit");
		names.add("Kumar");
		names.add("RAJPUT");
		List<Integer> salary = new ArrayList<>();
		salary.add(100);
		salary.add(200);
		salary.add(300);
		salary.add(400);
		
		GroupIterator groupIterator = new GroupIterator();
		groupIterator.addIterator(names.iterator());
		groupIterator.addIterator(salary.iterator());

		while(groupIterator.hasNext()) {
			System.out.println("Iterator: "+groupIterator.next());
		}
	}

}

package RoundRobinIterator;

import java.util.*;

public class GroupIterator {

	
	private List<Iterator<?>> iteratorList;
	private int iteratorIndex;
	
	public void addIterator(Iterator<?> iterator) {
		if (iteratorList == null) {
			iteratorList = new ArrayList<>();
		}
		
		iteratorList.add(iterator);
	}
	
	public boolean hasNext() {
		if(iteratorIndex == iteratorList.size()) {
			iteratorIndex = 0;
		}
		
		while(!iteratorList.get(iteratorIndex).hasNext()) {
			iteratorList.remove(iteratorIndex);
			
			if(iteratorList.isEmpty()) {
				return false;
			}

			if(iteratorIndex == iteratorList.size()) {
				iteratorIndex = 0;
			}
		}
		
		return true;
	}

	public Object next() {
		if(iteratorList.isEmpty()) {
			return null;
		}
		
		if(iteratorIndex == iteratorList.size()) {
			iteratorIndex = 0;
		}
		
		return iteratorList.get(iteratorIndex++).next();
	}

}

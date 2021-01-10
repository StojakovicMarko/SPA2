package prakticne_vjezbe_6;

import java.util.Comparator;

public class KomparatorPoDuzini implements Comparator<Put> {

	@Override
	public int compare(Put o1, Put o2) {
		// TODO Auto-generated method stub
		return o1.getLength() - o2.getLength();
	}

}

package prakticne_vjezbe_6;

import java.util.Comparator;

public class KomparatorPoVrednosti implements Comparator<Put>{

	@Override
	public int compare(Put o1, Put o2) {
		// TODO Auto-generated method stub
		return o2.getVrednost() - o1.getVrednost();
	}

}

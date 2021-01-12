package lavirinit_minotour;

import java.util.Comparator;

public class KomparatorPoOruziju implements Comparator<Put>{

	@Override
	public int compare(Put o1, Put o2) {
		// TODO Auto-generated method stub
		return o2.putNajviseOruzija() - o1.putNajviseOruzija();
	}

}

package lavirinit_minotour;

import java.util.ArrayList;



public class Put {
	
	private ArrayList<Polje> polja;
	
	
	public Put() {
		polja = new ArrayList<Polje>();
	}
	
	public Put(Put original) {
		this();
		for(Polje p: original.polja) {
			dodaj(p.getX(),p.getY(),p.getV());
		}
	}

	public void dodaj(int x, int y, int v) {
		// TODO Auto-generated method stub
		polja.add(new Polje(x, y, v));
		
	}
	
	public int putNajviseOruzija() {
		ArrayList<Integer> oruzija = new ArrayList<>();
		int br = 0;
		for(Polje p: polja) {
			if(p.getV() > 0 && !oruzija.contains(p.getV())) {
				oruzija.add(p.getV());
				br++;
			}
		}
		return br;
	}
	
	public Put kopija() {
		Put k = new Put();
		for(Polje p : polja) {
			k.dodaj(p.getX(),p.getY(), p.getV());
		}
		return k;
	}
	public int getLength() {
		return polja.size();
	}
	
	public void izbaciKraj() {
		if(getLength() > 0)
			polja.remove(getLength() -1 );
		else
			System.out.println("Put ne sadrzi polja");
	}

	@Override
	 public String toString(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("Resenje: [");
//        if(getLength() > 0){
//            sb.append(polja.get(0));
//            for(int i = 1; i < getLength(); i++){
//                sb.append(", " + polja.get(i));
//            }
//        }
//        sb.append("]");
//        return sb.toString();
		return "Put" + polja;
    }
	
	

}

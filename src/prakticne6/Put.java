package prakticne6;

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
	
	public void dodaj(int x,int y, int v) {
		polja.add(new Polje(x,y,v));
	}
	
	public Put kopija() {
		Put k = new Put();
		for(Polje p:polja) {
			k.dodaj(p.getX(),p.getY(),p.getV());
		}
		return k;
	}
	
	public void izbaciKraj() {
		if(getLength() > 0)
			polja.remove(getLength() -1 );
		else
			System.out.println("Put ne sadrzi polja");
	}
	
	public int getLength() {
		return polja.size();
	}
	
	public int getVrednost() {
		int rez = 0;
		for(Polje p :polja) {
			rez += p.getV();
		}
		return rez;
	}

	@Override
	public String toString() {
		return "Put " + polja ;
	}
	
	
	
	
	
}

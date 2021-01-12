package lavirinit_minotour;

import java.util.Comparator;

import org.svetovid.Svetovid;

public class NalazenjePuta {
	
	public static final int OGRADA = -11;
	public static final int MINOTAUR = 20;
	public static final int PRINCEZA = 30;
	
	private static int sirina, visina;
	
	private static int [][] lavirint;
	private static boolean [][] posecesnost;
	
	private Put optimalanPut;
	
	public NalazenjePuta(String fajl) {
		if(!Svetovid.testIn(fajl)) {
			System.out.println("Ne postoji fail");
			
		}
		sirina = Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();
		lavirint = new int[visina][sirina];
		posecesnost = new boolean[visina][sirina];
		
		for(int i = 0; i < visina;i++) {
			for(int j = 0; j < sirina;j++) {
				lavirint[i][j] = Svetovid.in(fajl).readInt();
			}
		}
		Svetovid.closeIn(fajl);
		stampaj();
	}

	private void stampaj() {
		// TODO Auto-generated method stub
		System.out.println(visina +" "+ sirina);
		System.out.println("Lavirint");
		for(int i = 0; i < visina;i++) {
			for(int j = 0; j < sirina;j++) {
				System.out.printf("%1$5d",lavirint[i][j]);
			}
			System.out.println();
		}
		
	}
	
	
	public Put saNajviseRazlicitogOruzija(int x, int y) {
		Put r = new Put();
		nalazenjePuta(x, y, r, new KomparatorPoOruziju());
		return optimalanPut;
	}
	
	public void nalazenjePuta(int x, int y,Put p , Comparator<Put> komparator) {
		if(x < 0 || x >= visina || y < 0 || y >= sirina) {
			return;
		}
		if(lavirint[x][y] == OGRADA) {
			return;
		}
		if(posecesnost[x][y]) {
			return;
		}
		if( x + 1 < visina) {
			if(lavirint[x + 1][y] == MINOTAUR)
				return;
		}
		if( x -1 >= 0) {
			if(lavirint[x - 1][y] == MINOTAUR)
				return;
		}
		if( y + 1 < sirina) {
			if(lavirint[x][y + 1] == MINOTAUR)
				return;
		}
		if( y - 1 >= 0) {
			if(lavirint[x][y - 1] == MINOTAUR)
				return;
		}
		
		if(lavirint[x][y] == PRINCEZA) {
			p.dodaj(x, y, 0);
			if(optimalanPut == null || komparator.compare(p,optimalanPut) < 0) {
				optimalanPut = p.kopija();
			}
			p.izbaciKraj();
			return;
		}
		posecesnost[x][y] = true;
		p.dodaj(x, y, lavirint[x][y]);
		
		nalazenjePuta(x, y + 1, p,komparator );
		nalazenjePuta(x, y - 1, p,komparator );
		nalazenjePuta(x + 1 , y, p,komparator );
		nalazenjePuta(x - 1, y, p,komparator );
		
		posecesnost[x][y] = false;
		p.izbaciKraj();
		return;
	}
	
	public static void main(String[]args) {
		
		String imeFajla = Svetovid.in.readLine("Unesite naziv fajla");
		String fajl = "res/"+imeFajla+".txt";
		if(!Svetovid.testIn(fajl)) {
			System.out.println("Ne moze ucitati fajl");
		}
		
		NalazenjePuta najPut = new NalazenjePuta(fajl);
		
		Put p = najPut.saNajviseRazlicitogOruzija(0, 0);
		System.out.println("Najbolji put");
		if(p != null) {
			System.out.println(p);
			
		}else {
			System.out.println("Nema rijesenja");
		}
	}
	

}

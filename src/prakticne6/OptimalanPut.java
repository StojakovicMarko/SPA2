package prakticne6;
import java.util.Comparator;

import org.svetovid.Svetovid;

public class OptimalanPut {
	
	public static final int IZLAZ = -99;
	public static final int ZID = -11;
	
	private static int visina, sirina;
	
	private static int [][] lavirint;
	private static boolean [][] posecenost;
	
	private Put optimalnaPut;
	
	
	public OptimalanPut(String fajl) {
		if(!Svetovid.testIn(fajl)) {
			throw new RuntimeException("Nemoguce ucitati fajl");
		}
		
		sirina= Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();
		lavirint = new int[visina][sirina];
		posecenost = new boolean[visina][sirina];
		
		for(int i = 0;i < visina;i++) {
			for(int j = 0;j < sirina;j++) {
				lavirint[i][j] = Svetovid.in(fajl).readInt();
			}
		}
		Svetovid.closeIn(fajl);
		stampja();
		
	}


	private void stampja() {
		// TODO Auto-generated method stub
		System.out.println(visina+ " "+ sirina);
		System.out.println("Lavirint");
		for(int i = 0;i < visina;i++) {
			for(int j = 0; j < sirina;j++) {
				System.out.printf("%1$5d",lavirint[i][j]);
			}
			System.out.println();
		}
		
		
	}
	
	public static void stampajPosecenost() {
		System.out.println(visina+ " "+ sirina);
		System.out.println("Lavirint posecenosti");
		for(int i = 0;i < visina;i++) {
			for(int j = 0; j < sirina;j++) {
				System.out.printf("%1$5d",posecenost[i][j]?1:0);
			}
			System.out.println();
		}
	}
	
	public Put najkraciPut(int x, int y) {
		Put p = new Put();
		optimalniPut(x, y, p, new KomparatorPoDuzini());
		return optimalnaPut;
	}
	
	public Put najvecaVrijednostBlaga(int x, int y) {
		Put p = new Put();
		optimalniPut(x, y, p, new KomparatorPoVrednosti());
		return optimalnaPut;
	}
	
	public void optimalniPut(int x,int y, Put r, Comparator<Put> compa) {
		if(x < 0 || x >= visina || y < 0 || y >= sirina) {
			return;
		} 
		
		if(lavirint[x][y] == ZID) {
			return;
		}
		
		if(posecenost[x][y]) {
			return;
		}
		
		if(lavirint[x][y] == IZLAZ) {
			r.dodaj(x, y, 0);
			if(optimalnaPut == null || compa.compare(r, optimalnaPut) < 0) {
				optimalnaPut = r.kopija();
			}
			r.izbaciKraj();
			return;
		}
		
		posecenost[x][y] = true;
		r.dodaj(x, y, lavirint[x][y]);
		
		optimalniPut(x, y + 1, r, compa);
		optimalniPut(x, y - 1 , r, compa);
		optimalniPut(x + 1, y, r, compa);
		optimalniPut(x - 1, y, r, compa);
		
		posecenost[x][y] = false;
		r.izbaciKraj();
		return;
	}
	
	
	public static void main(String[]args) {
		String imeFajla = Svetovid.in.readLine("Unesite ime fajla");
		String imeF = "res/"+imeFajla+".txt";
		if(!Svetovid.testIn(imeF)) {
			System.out.println("Nije moguce ucitati fajl");
		}
		
		OptimalanPut otp = new OptimalanPut(imeF);
		Put p;
		p = otp.najkraciPut(0, 0);
		System.out.println("Najkraci put ");
		if(p != null) {
			System.out.println(p);
		}else {
			System.out.println("Nema rijesenja");
		}
		System.out.println();
		Put najviseBlaga;
		najviseBlaga = otp.najvecaVrijednostBlaga(0, 0);
		System.out.println("Put sa najvise blaga ");
		if(najviseBlaga != null) {
			System.out.println(najviseBlaga);
		}else {
			System.out.println("Nema rijesenja");
		}
		
				
	}
	

}

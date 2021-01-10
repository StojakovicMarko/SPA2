package prakticne5;


import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;


public class PostojanjePuta {
	
	public static final int IZLAZ = -99;
	public static final int ZID = -11;
	
	private static int visina,sirina;
	
	private static int [][] lavirint;
	private static boolean[][] posecenost;
	
	public static boolean ucitaj(String fajl) {
		if(!Svetovid.testIn(fajl)) {
			return false;
		}
		sirina = Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();
		lavirint = new int[visina][sirina];
		posecenost = new boolean[visina][sirina];
		for(int i = 0;i < visina;i++) {
			for(int j = 0; j < sirina;j++) {
				lavirint[i][j] = Svetovid.in(fajl).readInt();
			}
		}
		Svetovid.closeIn(fajl);
		return true;
	}
	
	public static void stampaj() {
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
	
	
	private static boolean postojiPut(int x, int y, List<String> polja,boolean rupa,int prepreka,int brPrepeka) {
		
		if(x < 0 || x >= visina  || y < 0 || y >= sirina) 
			return false;
		//Omoguciti penjanje po preprekama, ali samo po preprekama koje su za najvise 3 vislje od visine trenutnog polja. 
        //Ukoliko se vec nalazimo na prepreci, dozvoljeno je preci na vislju prepreku, ukoliko ona nije veca za vise od 3 od postojece prepreke. 
		//Silazak sa prepreke je moguc na bilo koje polje osim zida.
		//Problem rijesavamo tako sto, u rekurzivnom pozivu proslijedimo prethonu prepreku, tacnije polje prepreke
		//Od trenutnog polja oduzimamo preprku, ako je rezultat veci od 3 vracamo netacno
		
		if(lavirint[x][y] - prepreka > 3) {
			return false;
		}
		//Omoguciti preskakanje prepreka, ali najvise 4 puta
		//Svaki broj u lavirintu veci od 0 je prepreka osim -11  je zid; -1 je rupa; -99 je izlaz
		//Za svako polje koje je vece od 0 u lavirintu, povecavamo brojac za jedan
		// Ukoliko je broj prepeka veci od 4 vracamo false
       
		if(lavirint[x][y] > 0) {
			brPrepeka++;
		}
		
		if(brPrepeka > 4) {
			return false;
		}
		
		if(lavirint[x][y] == ZID) 
			return false;
		
		if(posecenost[x][y]) 
			return false;
		
		if(lavirint[x][y]== IZLAZ) { 
			polja.add(x+ "-"+y);
			return true;
		}	
		
		
		posecenost[x][y] = true;
		polja.add(x+"-"+y);
		
		if(rupa && lavirint[x][y] == -1) {
			posecenost[x][y] = false;
			polja.remove(x+"-"+y);
			return false;
		}
		
		if(postojiPut(x,y+1,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
			return true;
		
		if(postojiPut(x, y-1,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
			return true;
		
		if(postojiPut(x+1, y,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
			return true;
		
		
		if(postojiPut(x-1, y,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
			return true;
		
		//dijagonalno kretanje po lavirintu 
		//kod dijagonalno kretanja dodamo jos 4 if
//		
//		if(postojiPut(x+1, y+1,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
//			return true;
//		
//		if(postojiPut(x-1, y+1,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
//			return true;
//		
//		if(postojiPut(x+1, y-1,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
//			return true;
//		
//		if(postojiPut(x-1, y-1,polja,lavirint[x][y] == -1,lavirint[x][y],brPrepeka)) 
//			return true;
//		
		posecenost[x][y] = false;
		polja.remove(x+"-"+y);
		return false;
		
	}
	
	public static void main(String[]args) {
		String fajl = Svetovid.in.readLine("Unesite ime fajla: ");
		String imeFajl = "res/"+fajl+".txt";
		if(ucitaj(imeFajl)) {
		   stampaj();
		   System.out.println();
		}
		List<String> polja = new ArrayList<String>();
		System.out.println("Unesite pocetne kordinate: ");
		int x = Svetovid.in.readInt("Unesite visnu: ");
		int y = Svetovid.in.readInt("Unestie sirinu: ");
		//-1 je rupa, i treba da stavimo u rekurziju da li je rupa
		// jer korisnik moze da unese kordinatu koja je rupa
		if(postojiPut(x, y,polja,lavirint[x][y] != -1,lavirint[x][y],0)) {
			System.out.println("Postoji put");
			stampajPosecenost();
			System.out.println(polja);
		}else {
			System.out.println("Ne postoji put");
		}
		
	}

}

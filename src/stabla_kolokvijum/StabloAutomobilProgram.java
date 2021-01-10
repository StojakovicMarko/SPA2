package stabla_kolokvijum;



import java.util.List;
import java.util.Scanner;

import org.svetovid.Svetovid;
import prakticne7.TreeIO;

public class StabloAutomobilProgram {

	public static void main(String[] args) {

		TreeIO<StabloAutomobil> io = new TreeIO<>(StabloAutomobil.class);

		StabloAutomobil stablo = io.read(Svetovid.in("res/Malo-Plava.txt"));
		io.print(Svetovid.out, stablo);
		
		System.out.println("1- Ispis svih registracija iz Novog Sada");
		System.out.println("2- Vraca koliko su ukupno presli svi plavi automobili");
		System.out.println("3-  Vraca stablo iste strukture u kom je automobil sa datom registracijom presao dati broj kilometara");
		System.out.println("4- Vraca sortiranu listu svih automobila plave boje");
		System.out.println("5- BST");
		System.out.print("Unesite vas izbor : ");
		Scanner in = new Scanner(System.in);
		int br = in.nextInt();
		switch (br) {
		case 1:
			
			stablo.ispisiNS();
			break;
		case 2:
		
			long km = stablo.presliPlavi();
			System.out.println("Svi plavi automobili su presli : "+km);
			break;
		case 3:
			Scanner in2 = new Scanner(System.in);
			System.out.print("Unesite registraciju : ");
			String reg = in2.nextLine();
			System.out.print("Unesite kilometre: ");
			long dodajKm = in2.nextLong();
			StabloAutomobil stabloAuto = stablo.kopija(reg,dodajKm);
			System.out.println("Kopija stabla iste strukture");
			io.print(Svetovid.out, stabloAuto);
			in2.close();
			break;
			
		case 4:
			List<Automobil> lista = stablo.sortiraniPlavi();
			if(lista != null) {
				for(Automobil a: lista) {
					System.out.println(a);
				}
			}else {
				System.out.println("Lista je prazna");
			}
			break;
			
		case 5:
			boolean je = stablo.jeBST();
			System.out.println(je? "Moze":"Ne moze");
			break;
			
		default:
			System.out.println("Ne postoji opcija");
			break;
			
		}
		
		in.close();

		
	}
}

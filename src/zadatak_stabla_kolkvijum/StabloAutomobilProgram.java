package zadatak_stabla_kolkvijum;

import java.util.Scanner;

import org.svetovid.Svetovid;
import prakticne7.TreeIO;

public class StabloAutomobilProgram {

	public static void main(String[] args) {

		TreeIO<StabloAutomobil> io = new TreeIO<>(StabloAutomobil.class);

		StabloAutomobil stablo = io.read(Svetovid.in("res/Malo.txt"));
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
			// Ispisuje sve registracije iz Novog Sada
			stablo.ispisiNS();
			break;
		}
		
		in.close();

		
	}
}

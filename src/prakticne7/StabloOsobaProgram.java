package prakticne7;

import java.util.Comparator;
//import java.util.List;

import org.svetovid.Svetovid;


// Konkretno stablo koje sadrzi ocene

// Glavna klasa
public class StabloOsobaProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<StabloOsoba> io = new TreeIO<>(StabloOsoba.class);

		// Procitamo stablo iz fajla
		StabloOsoba stablo = io.read(Svetovid.in("res/Osobe-mini.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);

		System.out.println(stablo.jePrazno()? "Da":"Ne");

		Osoba osobaX = new Osoba("Vuk", "Wolfeschlegelsteinhausenberger", 41966);
		boolean postoji = stablo.postojiElement(stablo.koren, osobaX);
		System.out.println(postoji?"Da":"Ne");
//		
//		stablo.stampajInorder(stablo.koren);
//		System.out.println();
//		stablo.stampajPostorder(stablo.koren);
//		System.out.println();
//		stablo.stampajPreorder(stablo.koren);
//		System.out.println();
//		
		
		stablo.stampajListove(stablo.koren);
		//pronalazi osobu u stablu i vraca njeno podstablo
        StabloOsoba sOsoba = stablo.pronadjiOsobu(osobaX);
        io.print(Svetovid.out, sOsoba);
        //pronalazi osobu u stablu i vraca samo tu osobu
//        StabloOsoba.Cvor cvor = stablo.pronadjiSamoOsobu(osobaX);
//        System.out.println(cvor.osoba);
//        stablo.stampajCvor(cvor);
//        
//		  List<Osoba> listaOsoba = stablo.stampajSveIspod(osobaX);
//		  System.out.println(listaOsoba);
        
        
//		  sOsoba.ubaci(new Osoba("Raja","Rajkovic",31985), new Osoba("Sasa","Peric", 414545), true);
//		  io.print(Svetovid.out, sOsoba);
		  
//		  Osoba optimalnaOsoba = stablo.optimalnaOsoba(new KomparatorPoPlati(), stablo.koren);
//		  System.out.println(optimalnaOsoba);
//		  
		 


	}
	
	
}

class KomparatorPoPlati implements Comparator<Osoba>{

	@Override
	public int compare(Osoba o1, Osoba o2) {
		// TODO Auto-generated method stub
		return o1.getPlata() - o2.getPlata();
	}
	
}
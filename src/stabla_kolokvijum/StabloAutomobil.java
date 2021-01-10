package stabla_kolokvijum;

import java.util.ArrayList;
import java.util.List;

public class StabloAutomobil {

	protected static class Cvor {

		public Automobil info;

		public Cvor levo;
		public Cvor desno;

		public Cvor(Automobil info, Cvor levo, Cvor desno) {
			this.info = info;
			this.levo = levo;
			this.desno = desno;
		}
	}

	protected final Cvor koren;
	

	public StabloAutomobil() {
		this.koren = null;
	}

	public StabloAutomobil(Automobil auto) {
		koren = new Cvor(auto, null, null);
	}

	protected StabloAutomobil(Cvor koren) {
		this.koren = koren;
	}

	public boolean jePrazno() {
		if (koren == null)
			return true;
		return false;
	}

	// 1 - Metod koji ispisuje sve registracije iz Novog Sada
	public void ispisiNS() {
		ispisiNs(koren);
	}

	private void ispisiNs(Cvor cvor) {
		// TODO Auto-generated method stub
		if (cvor == null)
			return;

		if (cvor.info.getRegistracija().substring(0, 2).equalsIgnoreCase("NS")) {
			System.out.println(cvor.info.getRegistracija());
		}
		ispisiNs(cvor.levo);
		ispisiNs(cvor.desno);

	}

	// 2 - Metod koji vraca koliko su ukupno presli svi placvi automobili

	public long presliPlavi() {
		long kilometri = presliPlavi(koren);
		return kilometri;
	}

	private long presliPlavi(Cvor cvor) {
		// TODO Auto-generated method stub
		long kilometri = 0;
		long levo, desno;
		if (cvor == null)
			return 0;
		if (cvor.info.getBoja().equalsIgnoreCase("Plava")) {
			kilometri += cvor.info.getKilometraza();
		}
		if (cvor.levo != null) {
			levo = presliPlavi(cvor.levo);
			kilometri += levo;
		}

		if (cvor.desno != null) {
			desno = presliPlavi(cvor.desno);
			kilometri += desno;
		}
		return kilometri;

	}

	// 3 - Metod koji vraca nezavisnu kopiju stabla iste strukture od cvora u kom
	// je automobil sa prosledjenom registracijom presao jos dati broj kilometara

	public StabloAutomobil presaoJos(String registracija, long km) {
		Cvor novi = null;
		Automobil auto = null;
		Cvor cvor = presaoJos(koren, registracija);
		if (cvor != null) {
			auto = new Automobil(cvor.info.getModel(), cvor.info.getBoja(), cvor.info.getRegistracija(),
					cvor.info.getKilometraza() + km);
			novi = new Cvor(auto, cvor.levo, cvor.desno);
		}

		return cvor != null ? new StabloAutomobil(novi) : null;
	}

	private Cvor presaoJos(Cvor cvor, String registracija) {
		// TODO Auto-generated method stub
		if (cvor == null)
			return null;

		if (cvor.info.getRegistracija().equalsIgnoreCase(registracija)) {
			return cvor;
		}
		Cvor nadjenLevo = presaoJos(cvor.levo, registracija);
		if (nadjenLevo != null)
			return nadjenLevo;
		Cvor nadjenDesno = presaoJos(cvor.desno, registracija);
		if (nadjenDesno != null)
			return nadjenDesno;

		return null;
	}

	// 4 - Vraca sortiranu listu svih automobila koji su plave boje,
	// takve da im je kilometraza veca od roditelja i njegovog
	// drugog deteta (ako postoji).

	public List<Automobil> sortiraniPlavi() {
		List<Automobil> lista = new ArrayList<Automobil>();
		sortiraniPlavi(koren, null, lista);
		lista.sort((Automobil a0, Automobil a1) -> (int) (a0.getKilometraza() - a1.getKilometraza()));
		return lista;

	}

	private void sortiraniPlavi(Cvor tekuci, Cvor roditelj, List<Automobil> lista) {
		// TODO Auto-generated method stub
		if (tekuci == null)
			return;
		if (tekuci.info.getBoja().equalsIgnoreCase("Plava")) {
			if (roditelj != null && tekuci.info.getKilometraza() > roditelj.info.getKilometraza()) {
				if (tekuci.levo != null && tekuci.info.getKilometraza() > tekuci.levo.info.getKilometraza()) {
					lista.add(tekuci.info);
				} else if (tekuci.desno != null && tekuci.info.getKilometraza() > tekuci.desno.info.getKilometraza()) {
					lista.add(tekuci.info);
				}

			}

		}
		sortiraniPlavi(tekuci.levo, tekuci, lista);
		sortiraniPlavi(tekuci.desno, tekuci, lista);

	}

	// 3 - Metod koji vraca nezavisnu kopiju stabla iste strukture u kom
	// je automobil sa prosledjenom registracijom presao jos dati broj kilometara
	public StabloAutomobil kopija(String reg, long km) {

		Cvor noviKoren = kopija(koren, reg, km);
		return new StabloAutomobil(noviKoren);

	}

	private Cvor kopija(Cvor cvor, String reg, long km) {
		// TODO Auto-generated method stub
		Cvor noviCvor;
		if (cvor == null)
			return null;
		if (cvor.info.getRegistracija().equalsIgnoreCase(reg)) {
			Automobil auto = new Automobil(cvor.info.getModel(), cvor.info.getBoja(), cvor.info.getRegistracija(),
					cvor.info.getKilometraza() + km);
			noviCvor = new Cvor(auto, null, null);
		} else {
			noviCvor = new Cvor(cvor.info, null, null);
		}

		noviCvor.levo = kopija(cvor.levo, reg, km);
		noviCvor.desno = kopija(cvor.desno, reg, km);

		return noviCvor;
	}

	// 5 - Vraca da li ovo stablo zadovoljava uslove za binarno
	// stablo pretrazivanja (Binary Search Tree - BST) po
	// kilometrazi automobila, odnosno da li vazi za svaki cvor da
	// su svi u levom podstablu sa manje kilometara, a u desnom
	// podstablu sa vise. Listovi trivijalno zadovoljavaju uslov.
	
	public boolean jeBST() {
		List<Integer> lista = new ArrayList<Integer>();
		if(koren == null)
			return false;
		jeBst(koren,lista);
		return lista.isEmpty()? true:false;
	}

	private void jeBst(Cvor cvor, List<Integer> lista) {
		// TODO Auto-generated method stub
		if(cvor == null)
			return;
		if(cvor.levo != null && cvor.levo.info.getKilometraza() > cvor.info.getKilometraza()) {
			lista.add(1);
		}
        if(cvor.desno != null && cvor.desno.info.getKilometraza() < cvor.info.getKilometraza()) {
        	lista.add(1);
		}
        jeBst(cvor.levo, lista);
        jeBst(cvor.desno, lista);
		
	}

}

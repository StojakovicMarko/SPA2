package prakticne7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StabloOsoba {
	
	protected static class Cvor{
		
		
		//Sadrzaj cvora
		public Osoba osoba;
		
		//Levo i desno podstablo
		public Cvor levo;
		public Cvor desno;
		
		
		//jedini konstruktor
		
		public Cvor(Osoba osoba, Cvor levo, Cvor desno) {
			this.osoba = osoba;
			this.levo = levo;
			this.desno = desno;
		}
		
	}
	
	//Stablo ima referencu na korenski cvor
	
	protected final Cvor koren;
	
	//Kreiramo prazno stablo
	
	public StabloOsoba() {
		this.koren = null;
		
	}
	
	//Kreiramo stablo sa jednom osobom u korenu 
	//I praznim podstablima
	
	public StabloOsoba(Osoba osoba) {
		
		koren = new Cvor(osoba, null,null);
	}
	
	//Specijalan konstruktor koji koriste neki metodi ove klase
	
	protected StabloOsoba(Cvor koren) {
		this.koren = koren;
	}
	
	//Metod koji proverava da li stablo prazno 
	
	public boolean jePrazno() {
		if(koren == null)
			return true;
		return false;
	}
	
	//Provjeravamo da li postoji elemetn u stablu
	
	public boolean postojiElement(Cvor cvor, Osoba osoba) {
		if(cvor == null)
			return false;
		
		if(Objects.equals(cvor.osoba, osoba))
			return true;
		
		boolean nadjenLevo = postojiElement(cvor.levo, osoba);
		if(nadjenLevo)
			return nadjenLevo;
		boolean nadjenDesno = postojiElement(cvor.desno, osoba);
		if(nadjenDesno)
			return nadjenDesno;
		
		return false;
	}
	
	public void stampajPreorder(Cvor cvor) {
		if(cvor == null)
			return;
		
		System.out.println(cvor.osoba);
		stampajPreorder(cvor.levo);
		stampajPreorder(cvor.desno);
	}
	
	public void stampajInorder(Cvor cvor) {
		if(cvor == null)
			return;
		stampajInorder(cvor.levo);
		System.out.println(cvor.osoba);
		stampajInorder(cvor.desno);
	}
	
	public void stampajPostorder(Cvor cvor) {
		if(cvor == null)
			return;
		
		stampajPostorder(cvor.levo);
		stampajPostorder(cvor.desno);
		System.out.println(cvor.osoba);
	}
	
	public void stampajListove(Cvor cvor) {
		if(cvor == null)
			return;
		if(cvor.levo == null && cvor.desno == null)
			System.out.println(cvor.osoba);
		else {
			stampajListove(cvor.levo);
			stampajListove(cvor.desno);
		}
	}
	//Pronalazi osobu i vraca njeno podstablo
	public StabloOsoba pronadjiOsobu(Osoba osoba) {
		Cvor cvor = pronadjiOsobu(koren, osoba);
		if(cvor == null)
			return null;
		return new StabloOsoba(cvor);
	}
	
	private Cvor pronadjiOsobu(Cvor cvor,Osoba osoba) {
		if(cvor == null)
			return null;
		if(Objects.equals(cvor.osoba, osoba))
			return cvor;
		
		Cvor nadjenLevo = pronadjiOsobu(cvor.levo, osoba);
		if(nadjenLevo != null)
			return nadjenLevo;
		
		Cvor nadjenDesno = pronadjiOsobu(cvor.desno, osoba);
		if(nadjenDesno != null)
			return nadjenDesno;
		
		return null;
	}
	//Prolazi kroz stablo i vraca tu osobu ako je pronadje
	public Cvor pronadjiSamoOsobu(Osoba osoba) {
		Cvor cvor = pronadjiOsobu(koren,osoba);
		if(cvor == null)
			return null;
		return cvor;
	}
	

	//stampa samo cvor koji proslijedimo
	public void stampajCvor(Cvor cvor) {
		if(cvor == null)
			return;
		System.out.println(cvor.osoba);
	}
	
	
	public List<Osoba> stampajSveIspod(Osoba sef){
		
		List<Osoba> osobe = new ArrayList<Osoba>();
		Cvor cvor = pronadjiOsobu(koren, sef);
		if(cvor != null)
			sveOsobeIspod(cvor, osobe);
		
		return osobe;
		
	}
	
	private void sveOsobeIspod(Cvor cvor, List<Osoba> lista) {
		if(cvor == null)
			return;
		sveOsobeIspod(cvor.levo, lista);
		lista.add(cvor.osoba);
		sveOsobeIspod(cvor.desno, lista);
		
	}
	
	public boolean ubaci(Osoba roditelj, Osoba potomak, boolean levo) {
		Cvor cvor = pronadjiOsobu(koren, roditelj);
		if(levo && cvor.levo == null) {
		     cvor.levo = new Cvor(potomak, null, null);
		     return true;
		}
		if(!levo && cvor.desno == null) {
			cvor.desno = new Cvor(potomak, null, null);
			return true;
		}
		return false;
	}
	
	public Cvor roditeljOd(Osoba potomak) {
		Cvor cvor = roditeljOd(koren,null,potomak);
		if(cvor == null)
			return null;
		
		return cvor;
	}
	
	private Cvor roditeljOd(Cvor tekuci, Cvor roditelj, Osoba potomak) {
		if(tekuci == null)
			return null;
		
		if(Objects.equals(tekuci.osoba, potomak))
			return roditelj;
		
		Cvor roditeljLevo = roditeljOd(tekuci.levo, tekuci, potomak);
		if(roditeljLevo != null)
			return roditeljLevo;
		
		Cvor roditeljDesno = roditeljOd(tekuci.desno, tekuci, potomak);
		if(roditeljDesno != null)
			return roditeljDesno;
		
		return null;
	}
	
	public Osoba optimalnaOsoba(Comparator<Osoba> komparator, Cvor cvor) {
		
		if(cvor == null)
			return null;
		
		Osoba optimalnaLevo, optimalnaDesno;
		Osoba optimalna = cvor.osoba;
		
		if(cvor.levo != null) {
			optimalnaLevo = optimalnaOsoba(komparator, cvor.levo);
			if(komparator.compare(optimalna, optimalnaLevo) < 0)
				optimalna = optimalnaLevo;
		}
		if(cvor.desno != null) {
			optimalnaDesno = optimalnaOsoba(komparator, cvor.desno);
			if(komparator.compare(optimalna, optimalnaDesno) < 0)
				optimalna = optimalnaDesno;
		}
		
		return optimalna;
	}
	//Metod za kopiranje stabla

	public StabloOsoba kopija() {
		
		Cvor noviKoren = kopija(koren);
		return new StabloOsoba(noviKoren);
		
	}

	private Cvor kopija(Cvor cvor) {
		// TODO Auto-generated method stub
		if(cvor == null)
			return null;
		Cvor noviCvor = new Cvor(cvor.osoba,null,null);
		noviCvor.levo = kopija(cvor.levo);
		noviCvor.desno = kopija(cvor.desno);
		
		return noviCvor;
	}
	
	//Obrtanje stabla
	
	public StabloOsoba obrni() {
		StabloOsoba novo = new StabloOsoba(koren);
		novo.obrni(koren);
		return novo;
	}

	private void obrni(Cvor cvor) {
		// TODO Auto-generated method stub
		if(cvor == null)
			return;
		Cvor tmp = cvor.desno;
		cvor.desno = cvor.levo;
		cvor.levo = tmp;
		
		obrni(cvor.levo);
		obrni(cvor.desno);
		
	}
	
	

}

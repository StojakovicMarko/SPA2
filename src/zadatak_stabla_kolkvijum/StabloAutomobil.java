package zadatak_stabla_kolkvijum;

public class StabloAutomobil {
	
	protected static class Cvor{
		
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
		if(koren == null)
			return true;
		return false;
	}
	
	
	public void ispisiNS() {
		ispisiNs(koren);
	}

	private void ispisiNs(Cvor cvor) {
		// TODO Auto-generated method stub
		if(cvor == null)
			return;
		
		if(cvor.info.getRegistracija().substring(0,2).equalsIgnoreCase("NS")) {
			System.out.println(cvor.info.getRegistracija());
		}
		ispisiNs(cvor.levo);
		ispisiNs(cvor.desno);
		
	}


}

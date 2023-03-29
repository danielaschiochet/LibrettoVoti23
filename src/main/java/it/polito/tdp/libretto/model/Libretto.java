package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.polito.tdp.libretto.db.VotoDAO;

public class Libretto {
	
	private List<Voto> voti;

	public Libretto() {
		
		VotoDAO dao = new VotoDAO();
		this.voti = dao.listVoti();
	}
	
	/**
	 * Aggiungi un nuovo voto al libretto
	 * (per ora non fa nessun controllo)
	 * @param v il Voto da aggiungere
	 * @return true
	 */
	public boolean add(Voto v) {
		if(this.esisteConflitto(v) || this.esisteVotoDuplicato(v)) {
			//non aggiungere voto
			throw new IllegalArgumentException("Voto errato: "+v);
		}
		VotoDAO dao = new VotoDAO();
		dao.createVoto(v);
		return this.voti.add(v);
	}
	
	public void stampa() {
		for(Voto v : this.voti) {
			System.out.println(v);
		}
	}
	
	public List<Voto> getVoti(){
		return this.voti;
	}
	
	public void stampaPuntiUguali(int valore) {
		for(Voto v : this.voti) {
			if(v.getPunti() == valore)
				System.out.println(v);
		}
	}
		
	public Voto cercaVotoPerNome(String corso) {
		for(Voto v : this.voti) {
			if(v.getCorso().equals(corso)) {
				return v;
			}
		}
		return null;
		
		// throw new RuntimeException("Voto non trovato"); usiamo l'eccezione x eventi imprevisti
	}
	
	public boolean esisteVotoDuplicato(Voto nuovo) {
		for(Voto v: this.voti) {
			if(v.isDuplicato(nuovo)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean esisteConflitto(Voto nuovo) {
		for(Voto v: this.voti) {
			if(v.isConflitto(nuovo)) {
					return true;
			}
		}
		return false;
	}
	/**
	 * Metodo 'factory' per creare un nuovo libretto con i voti migliorati.
	 * @return
	 */
	public Libretto librettoMigliorato() {
		
		Libretto migliore = new Libretto();
		migliore.voti = new ArrayList<>();
	
		for(Voto v: this.voti) {
			migliore.voti.add(v.clone());
			//migliore.voti.add(new Voto(v));
		}
		for(Voto v: migliore.voti) {
			v.setPunti(v.getPunti()+2);
		}
	
		return migliore;
	}
	
	public void cancellaVotiInferiori(int punti) {
		
		List<Voto> daCancellare = new ArrayList<Voto>();
		for(Voto v: this.voti) {
			if(v.getPunti()<punti) {
				daCancellare.add(v);
			}
		}
		
		this.voti.removeAll(daCancellare);
		
		//non si modifica la lista su cui si vuole iterare!
	}
	
	public Libretto libOrdinatoAlfabeticamente() {
		
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>(this.voti);
		
		ordinato.voti.sort(new ComparatorByName());
		Collections.sort(ordinato.voti, new ComparatorByName());
	
		return ordinato;
	}
	
	public Libretto libOrdinatoPerVoto() {
		
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>(this.voti);
		
		ordinato.voti.sort(new Comparator<Voto>() {

			@Override
			public int compare(Voto o1, Voto o2) {
				return o2.getPunti()-o1.getPunti();
			}
		});
	
		return ordinato;
	}

	public String toString() {
		String txt = "" ;
		for (Voto v : this.voti) {
			txt = txt + v.toString()+"\n";
		}
		return txt;
	}



}

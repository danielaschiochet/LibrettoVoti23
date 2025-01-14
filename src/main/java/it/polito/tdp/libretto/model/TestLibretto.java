package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Libretto lib = new Libretto();
		
		//1
		lib.add(new Voto ("Analisi I", 29, LocalDate.of(2021, 2, 15)));
		lib.add(new Voto ("Informatica", 25, LocalDate.of(2021, 2, 20)));
		lib.add(new Voto ("Fisica I", 21, LocalDate.of(2022, 6, 10)));
		lib.add(new Voto ("Fisica III", 22, LocalDate.of(2023, 2, 4)));
		
		//2
		lib.stampaPuntiUguali(25);
		
		//3
		Voto votoCercato = lib.cercaVotoPerNome("Analisi I");
		System.out.println(votoCercato);
		
		//4
		Voto a1bis = new Voto ("Analisi I", 29, LocalDate.of(2025, 2, 15));
		Voto a1ter = new Voto ("Analisi I", 30, LocalDate.of(2025, 2, 15));
		
		System.out.println(a1bis+" è duplicato "+lib.esisteVotoDuplicato(a1bis));
		System.out.println(a1ter+" è duplicato "+lib.esisteVotoDuplicato(a1ter));
		
		//5
		Voto f1bis = new Voto ("Fisica I", 20, LocalDate.of(2025, 6, 10));
		Voto f1ter = new Voto ("Fisica I", 21, LocalDate.of(2025, 6, 10));
		
		System.out.println(f1bis+" è in conflitto "+lib.esisteConflitto(f1bis));
		System.out.println(f1ter+" è in conflitto "+lib.esisteConflitto(f1ter));
		
		//6
		try {
			lib.add(new Voto ("Informatica", 25, LocalDate.of(2021, 2, 23)));
		}catch(IllegalArgumentException e) {
			System.out.println("Errore nell'inserimento del voto.");
			System.out.println(e.getMessage());
		}
		
		//7
		System.out.println("LIBRETTO ORIGINARIO");
		lib.stampa();
		
		Libretto migliore = lib.librettoMigliorato();
		
		System.out.println("LIBRETTO MIGLIORATO");
		migliore.stampa();
	
		System.out.println("LIBRETTO ORIGINARIO DI NUOVO");
		lib.stampa();
		
		//9
		//System.out.println("LIBRETTO ORIGINARIO >24");
		//lib.cancellaVotiInferiori(24);
		//lib.stampa();
		
		//8
		System.out.println("LIBRETTO PER ORDINE ALFABETICO");
		lib.libOrdinatoAlfabeticamente().stampa();
		
		System.out.println("LIBRETTO PER ORDINE DI VOTO");
		lib.libOrdinatoPerVoto().stampa();;
		
		
	}

}

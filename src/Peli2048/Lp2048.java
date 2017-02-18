package Peli2048;
import java.util.Random;
import java.util.Scanner;


/*
 * Ai tries to get least points possible
 * (Gets 0 points around once every 10 000 000 tries)
 */

public class Lp2048 {
	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		boolean z = true;
		boolean running = true;
		boolean aiBoolean = false;
		
		while (running){
		System.out.println("Tekoäly? y/n");
		String ai = sc.nextLine();
		
		LpAi tekoaly = new LpAi();
		int suunta = 0;
		
		if (ai.equals("y")){
			aiBoolean = true;
		}
		else{
			aiBoolean = false;
		}
		for(int i = 0; i < 10000000; i++){
			if (i % 50000 == 0){
				System.out.println(i);
			}
		Matriisi m = new Matriisi();
		m.uusiAlkio();
		m.uusiAlkio();
		boolean[] mahdolliset = m.mahdolliset();
		z = true;
		//m.tulosta();
		int moves = 0;
		while (z && aiBoolean){
			suunta = tekoaly.siirto(m);
			if (suunta == 0){
				m.siirtoYlos();
			}
			if (suunta == 1){
				m.siirtoOikea();
			}
			if (suunta == 2){
				m.siirtoAlas();
			}
			if (suunta == 3){
				m.siirtoVasen();
			}
			mahdolliset = m.mahdolliset();
			
			if(mahdolliset[0] == false && mahdolliset[1] == false && 
			mahdolliset[2] == false && mahdolliset[3] == false){
				z = false;
				if (m.getPisteet() < 13){
					System.out.println("");
					m.tulosta();
					System.out.println("Game Over! Moves: " + moves + " Points: " + m.getPisteet());
				}
			}
			moves = moves + 1;
		}

		
		while(z && !aiBoolean){
			String syote = sc.nextLine();
			if (syote.equals("v")){
				m.siirtoVasen();
			}
			if (syote.equals("o")){
				m.siirtoOikea();
			}
			if (syote.equals("y")){
				m.siirtoYlos();
			}
			if (syote.equals("a")){
				m.siirtoAlas();
			}
			if(syote.equals("lopeta")){
			z = false;
			}
			m.tulosta();
			System.out.println("Pisteet: " + m.getPisteet());
		}
		}//for
		}
	}
}

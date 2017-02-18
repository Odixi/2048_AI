package Peli2048;
import java.util.Random;
import java.util.Scanner;

public class Peli2048 {
	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		boolean z = true;
		boolean running = true;
		boolean aiBoolean = false;
		
		while (running){
		System.out.println("Teko�ly? y/n");
		String ai = sc.nextLine();
		
		LpAi tekoaly = new LpAi();
		int suunta = 0;
		
		if (ai.equals("y")){
			aiBoolean = true;
		}
		else{
			aiBoolean = false;
		}
		Matriisi m = new Matriisi();
		m.uusiAlkio();
		m.uusiAlkio();
		boolean[] mahdolliset = m.mahdolliset();
		z = true;
		m.tulosta();
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
			m.tulosta();
			System.out.println("");
			
			if(mahdolliset[0] == false && mahdolliset[1] == false && 
			mahdolliset[2] == false && mahdolliset[3] == false){
				z = false;
				System.out.println("Game Over! Moves: " + moves + " Points: " + m.getPisteet());
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
		}
	}
}
class Matriisi{
	int[][] matriisi;
	int pisteet;

public Matriisi(){
	this.matriisi = new int[4][4];
	for (int i = 0; i < matriisi.length;i++){
		for (int j = 0; j < matriisi[0].length;j++){
		matriisi[i][j] = 0;	
		}	
	}
	this.pisteet = 0;
}

public int[][] getMatriisi(){
	return matriisi;
}
public int getPisteet(){
	return pisteet;
}

public void siirtoYlos(){
	boolean jt = false;
	boolean mer = false;
	for(int i = 0; i < 4;i++){
		mer = false;
		for (int j = 1; j < 4; j++){
			if (matriisi[i][0] == matriisi[i][1] && matriisi[i][2] == matriisi[i][3] && matriisi[i][0] != 0 && matriisi[i][2] != 0){
				matriisi[i][0] = matriisi[i][0] + matriisi[i][0];
				pisteet = pisteet + matriisi[i][0];
				matriisi[i][1] = matriisi[i][2] + matriisi[i][2];
				pisteet = pisteet + matriisi[i][0];
				matriisi[i][2] = 0;
				matriisi[i][3] = 0;
				jt = true;
				mer = true;
				j = 0;
			}
			else if (matriisi[i][j] == matriisi[i][j-1] && matriisi[i][j] != 0 && !mer){
				matriisi[i][j-1] = matriisi[i][j-1] + matriisi[i][j-1];
				pisteet = pisteet + matriisi[i][j-1];
				matriisi[i][j] = 0;
				jt = true;
				mer = true;
				j = 0;
			}
			else if (matriisi[i][j-1] == 0 && matriisi[i][j] != 0){
				matriisi[i][j-1] = matriisi[i][j];
				matriisi[i][j] = 0;
				j = 0;
				jt = true;
				
			}
			
		}
	}
	if (jt){
		uusiAlkio();
	}
}
public void siirtoAlas(){
	boolean jt = false;
	boolean mer = false;
	for(int i = 0; i < 4;i++){
		mer = false;
		for (int j = 2; j >= 0; j--){
			if (matriisi[i][0] == matriisi[i][1] && matriisi[i][2] == matriisi[i][3] && matriisi[i][0] != 0 && matriisi[i][2] != 0){
				matriisi[i][2] = matriisi[i][0] + matriisi[i][0];
				pisteet = pisteet + matriisi[i][2];
				matriisi[i][3] = matriisi[i][3] + matriisi[i][3];
				pisteet = pisteet + matriisi[i][3];
				matriisi[i][0] = 0;
				matriisi[i][1] = 0;
				jt = true;
				mer = true;
				j = 3;
			}
			else if (matriisi[i][j] == matriisi[i][j+1] && matriisi[i][j] != 0 && !mer){
				matriisi[i][j+1] = matriisi[i][j+1] + matriisi[i][j+1];
				pisteet = pisteet + matriisi[i][j+1];
				matriisi[i][j] = 0;
				jt = true;
				mer = true;
				j = 3;
			}
			else if (matriisi[i][j+1] == 0 && matriisi[i][j] != 0){
				matriisi[i][j+1] = matriisi[i][j];
				matriisi[i][j] = 0;
				j = 3;
				jt = true;
				
			}
			
		}
	}
	if (jt){
		uusiAlkio();
	}
}
public void siirtoVasen(){
	boolean jt = false;
	boolean mer = false;
	for(int i = 0; i < 4;i++){
		mer = false;
		for (int j = 1; j < 4; j++){
			if (matriisi[0][i] == matriisi[1][i] && matriisi[2][i] == matriisi[3][i] && matriisi[0][i] != 0 && matriisi[2][i] != 0){
				matriisi[0][i] = matriisi[0][i] + matriisi[0][i];
				pisteet = pisteet + matriisi[0][i];
				matriisi[1][i] = matriisi[2][i] + matriisi[2][i];
				pisteet = pisteet + matriisi[1][i];
				matriisi[2][i] = 0;
				matriisi[3][i] = 0;
				jt = true;
				mer = true;
				j = 0;
			}
			else if (matriisi[j][i] == matriisi[j-1][i] && matriisi[j][i] != 0 && !mer){
				matriisi[j-1][i] = matriisi[j-1][i] + matriisi[j-1][i];
				pisteet = pisteet + matriisi[j-1][i];
				matriisi[j][i] = 0;
				jt = true;
				mer = true;
				j = 0;
			}
			else if (matriisi[j-1][i] == 0 && matriisi[j][i] != 0){
				matriisi[j-1][i] = matriisi[j][i];
				matriisi[j][i] = 0;
				j = 0;
				jt = true;
				
			}
			
		}
	}
	if (jt){
		uusiAlkio();
	}
}
public void siirtoOikea(){
	boolean jt = false;
	boolean mer = false;
	for(int i = 0; i < 4;i++){
		mer = false;
		for (int j = 2; j >= 0; j--){
			if (matriisi[0][i] == matriisi[1][i] && matriisi[2][i] == matriisi[3][i] && matriisi[0][i] != 0 && matriisi[2][i] != 0){
				matriisi[2][i] = matriisi[0][i] + matriisi[0][i];
				pisteet = pisteet + matriisi[2][i];
				matriisi[3][i] = matriisi[3][i] + matriisi[3][i];
				pisteet = pisteet + matriisi[3][i];
				matriisi[1][i] = 0;
				matriisi[0][i] = 0;
				jt = true;
				mer = true;
				j = 3;
			}
			else if (matriisi[j][i] == matriisi[j+1][i] && matriisi[j][i] != 0 && !mer){
				matriisi[j+1][i] = matriisi[j+1][i] + matriisi[j+1][i];
				pisteet = pisteet + matriisi[j+1][i];
				matriisi[j][i] = 0;
				jt = true;
				mer = true;
				j = 3;
			}
			else if (matriisi[j+1][i] == 0 && matriisi[j][i] != 0){
				matriisi[j+1][i] = matriisi[j][i];
				matriisi[j][i] = 0;
				j = 3;
				jt = true;
				
			}
			
		}
	}
	if (jt){
		uusiAlkio();
	}
}
public void uusiAlkio(){
	Random rnd = new Random();
	int lkm = 0;
	for (int i = 0; i < matriisi.length;i++){
		for(int j = 0; j < matriisi.length; j++){
			if (matriisi[i][j] == 0){
				lkm = lkm + 1;
			}
		}
	}
	int a = 0;
	int[][] tyhjat = new int[lkm][2];
	for (int i = 0; i < matriisi.length;i++){
		for(int j = 0; j < matriisi.length; j++){
			if (matriisi[i][j] == 0){
				tyhjat[a][0] = i;
				tyhjat[a][1] = j;
				a++;
			}
		}
	}
	if (rnd.nextInt(10) > 0){
		int r = rnd.nextInt(lkm);
		matriisi[tyhjat[r][0]][tyhjat[r][1]] = 2;
	}
	else {
		int r = rnd.nextInt(lkm);
		matriisi[tyhjat[r][0]][tyhjat[r][1]] = 4;
	}
}

public boolean[] mahdolliset(){
	boolean[] mahd = new boolean[4]; // 0 = yl�s, 1 = oikea, 2 = alas, 3 = vasen
	mahd[0] = false; mahd[1] = false; mahd[2] = false; mahd[3] = false;
	
	// yl�s
	for(int i = 0; i < 4;i++){
		for (int j = 1; j < 4; j++){
			if ((matriisi[i][j] == matriisi[i][j-1] && matriisi[i][j] != 0) ||
			 (matriisi[i][j-1] == 0 && matriisi[i][j] != 0)){
				mahd[0] = true;
			}
			
		}
	}
	
	// oikea
	for(int i = 0; i < 4;i++){
		for (int j = 2; j >= 0; j--){
			if ((matriisi[j][i] == matriisi[j+1][i] && matriisi[j][i] != 0) ||
			 (matriisi[j+1][i] == 0 && matriisi[j][i] != 0)){
				mahd[1] = true;
			}
			
		}
	}
	
	// alas
	for(int i = 0; i < 4;i++){
		for (int j = 2; j >= 0; j--){
			if ((matriisi[i][j] == matriisi[i][j+1] && matriisi[i][j] != 0) ||
			 (matriisi[i][j+1] == 0 && matriisi[i][j] != 0)){
				mahd[2] = true;
			}
			
		}
	}
	
	//vasen
	for(int i = 0; i < 4;i++){
		for (int j = 1; j < 4; j++){
			if ((matriisi[j][i] == matriisi[j-1][i] && matriisi[j][i] != 0) ||
				(matriisi[j-1][i] == 0 && matriisi[j][i] != 0)){
				mahd[3] = true;
			}
			
		}
	}
	
	return mahd;
	
}

public void tulosta(){
	int max = 0;
	for (int i = 0; i < 4;i++){
		for (int j = 0; j < 4; j++){
			if (matriisi[i][j] > max){
				max = matriisi[i][j];
			}
		}
	}
	for (int i = 0; i < 4; i++){
		for (int j = 0; j < 4; j++){
			System.out.print(matriisi[j][i] + " ");
			if(max > 10 && matriisi[j][i] < 10){
				System.out.print(" ");
			}
			if(max > 100 && matriisi[j][i] < 100){
				System.out.print(" ");
			}
			if(max > 1000 && matriisi[j][i] < 1000){
				System.out.print(" ");
			}
		}
		System.out.println("");
	}
}
}

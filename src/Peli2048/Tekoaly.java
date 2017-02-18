package Peli2048;

public class Tekoaly {

	private int prioReuna;  // prioriteetti 1
	private int prioReuna2; // prioriteetti 2
	private Matriisi mat;
	
	public Tekoaly(){
		this.prioReuna = 0;
		this.prioReuna2 = 1;
		this.mat = new Matriisi();
	}

	public int siirto(Matriisi matriisi) {
		
		this.mat = matriisi;
		
		prioLaskeminen();
		
		boolean[] mahd = mat.mahdolliset();
		
		if (mahd[this.prioReuna]){
			return this.prioReuna;
		}
		else if (mahd[this.prioReuna2]){
			return this.prioReuna2;
		}
		else{
			if (this.prioReuna2 > 2 && mahd[prioReuna2-2]){
				return this.prioReuna2 - 2;
			}
			else if(mahd[prioReuna2+2]){
				return this.prioReuna2 + 2;
			}
			for (int i = 0; i < 4; i++){
				if (mahd[i]){
					return i;
				}
			}
			return 0;
		}
		
		// Lasketaan prioriteettiarvot jokaiselle suunnalle
		
		// 1. Jos suurin tiili ei ole kulmassa katsotaan 
		// saako sen sinne
		
		// 2. Reunariveistä suurinta summaa kohti pelataan
		// ja samalla huomion missä kulmassa suurin tiili on
		// Katsotaan samlla ettei pelata vastakkaiseen suuntaan
		
		// 3. Jos prioriteettirivi ei ole täynnä pyritääntäyttämään
		
		// 4. 
		
		
		
	}
	
	private void prioLaskeminen(){
		
		int[] summat = new int[4];
		
		for (int i = 0; i >= 3; i++){
			summat[i] = rivinSumma(i);
		}
		
		int suurin = 0;
		int toinen = 0;
		
		for (int i = 0; i >= 3; i++){
			if (summat[i] > suurin){
				suurin = summat[i];
				this.prioReuna = i;
			}
			else if (summat[i] > toinen){
				toinen = summat[i];
				this.prioReuna2 = i;
			}
		}
	}
	
	private int rivinSumma(int x){
		int summa = 0;
		if (x == 0){
			summa = mat.getMatriisi()[0][0] + mat.getMatriisi()[1][0] + mat.getMatriisi()[2][0] + mat.getMatriisi()[3][0];
		}
		if (x == 2){
			summa = mat.getMatriisi()[3][0] + mat.getMatriisi()[3][1] + mat.getMatriisi()[3][2] + mat.getMatriisi()[3][3];
		}
		if (x == 3){
			summa = mat.getMatriisi()[0][3] + mat.getMatriisi()[1][3] + mat.getMatriisi()[2][3] + mat.getMatriisi()[3][3];
		}
		if (x == 4){
			summa = mat.getMatriisi()[0][0] + mat.getMatriisi()[0][1] + mat.getMatriisi()[0][2] + mat.getMatriisi()[0][3];
		}
		return summa;
	}
	
	
}

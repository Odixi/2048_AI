package Peli2048;

/*
 * Ai that tries to get least amount of points possible
 */

public class LpAi {

	private int eiSummaudu; 
	private int prioReuna2; // prioriteetti 2
	private Matriisi mat;
	
	public LpAi(){
		this.eiSummaudu = 0;
		this.prioReuna2 = 1;
		this.mat = new Matriisi();
	}

	public int siirto(Matriisi matriisi) {
		
		this.mat = matriisi;
		
		prioLaskeminen();
		
		boolean[] mahd = mat.mahdolliset();
		
		if (mahd[this.eiSummaudu]){
			return this.eiSummaudu;
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

		
	}
	
	private void prioLaskeminen(){
		
		boolean[] mahd = new boolean[4]; // 0 = yl�s, 1 = oikea, 2 = alas, 3 = vasen
		mahd[0] = false; mahd[1] = false; mahd[2] = false; mahd[3] = false;
		
		// yl�s
		for(int i = 0; i < 4;i++){
			for (int j = 1; j < 4; j++){
				if ((mat.getMatriisi()[i][j] == mat.getMatriisi()[i][j-1] && mat.getMatriisi()[i][j] != 0)){
					mahd[0] = true;
				}
				
			}
		}
		
		// oikea
		for(int i = 0; i < 4;i++){
			for (int j = 2; j >= 0; j--){
				if ((mat.getMatriisi()[j][i] == mat.getMatriisi()[j+1][i] && mat.getMatriisi()[j][i] != 0)){
					mahd[1] = true;
				}
				
			}
		}
		
		// alas
		for(int i = 0; i < 4;i++){
			for (int j = 2; j >= 0; j--){
				if ((mat.getMatriisi()[i][j] == mat.getMatriisi()[i][j+1] && mat.getMatriisi()[i][j] != 0)){
					mahd[2] = true;
				}
				
			}
		}
		
		//vasen
		for(int i = 0; i < 4;i++){
			for (int j = 1; j < 4; j++){
				if ((mat.getMatriisi()[j][i] == mat.getMatriisi()[j-1][i] && mat.getMatriisi()[j][i] != 0)){
					mahd[3] = true;
				}
				
			}
		}//for
		
		int a = 0;
		
		if (mahd[0] == true && mahd[1] == true && mahd[2] == true && mahd[3] == true){
			this.eiSummaudu = 0;
		}//if
		else{
			while(true){
			a = (int)(Math.random()*4);
				if (mahd[a] == false){
					this.eiSummaudu = a;
					break;
				}//if
			}//while
		}//else

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

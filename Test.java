package projetAI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

class Test{
	static Bloc[][] mat;
	static int n;
	boolean Fleche = true;
	static Direction direction = Direction.EST;
	static Random rand = new Random(); 
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("entrez la taille de la matrice: ");
		n = sc.nextInt();
		mat = new Bloc[n][n];
		for(int i=0; i<n; i++) {
			mat[i] = new Bloc[n];
			for(int j=0; j<n; j++)
				mat[i][j] = new Bloc();
		}
		int pits;
			System.out.print("\n entrez le nombre de puits: ");
			int rand_int1=rand.nextInt(n); 
			pits = rand_int1+1;
		for(int i=0; i<pits; i++) {
			int x=rand.nextInt(n-1)+1;
			int y=rand.nextInt(n-1)+1;
			System.out.print("la position du puit " + (i+1) + ": ");
			System.out.println(x);
			System.out.println(y);
			if(x == 1 && y == 2){
				y=3;
			}else if(x==2 && y ==1){
			x=3;}
				AjouPuit(n-x, y-1);
			
		}
		System.out.print("\n entrez la position du Wampus: ");
		AjouWumpus(n-sc.nextInt(), sc.nextInt()-1);
		System.out.print("\n entrez la position de l or: ");
		AjouOr(n-sc.nextInt(), sc.nextInt()-1);
		System.out.print("\nVotre emplacement actuel est : [1] [1]");
		int r = n - 1;/*n=r+1*/
		int c = 0;
		int rPrev = -1, cPrev = -1;
		int Deplacements = 0;
		System.out.println("\n voulez vous jouer:");
		char t='y';
		if(sc.next().charAt(0)==t) {
			System.out.println("\n etat initial:");
			while(!mat[r][c].AOr) {
				printmat(r, c);
				 if(mat[r][c].APuit) {
					System.out.println("Ahhhhhhhhhhhhhhhhhh je suis tombe dans le puit");
					int score=-Deplacements-1000;
					System.out.println("\n Votre score final est de   " + score+" ");
					System.exit(0);
					
				}
				else if(mat[r][c].AWumpus) {
					System.out.println("Ahhhhhhhhhhhhhhhhhh LE MONSTRE");
					int score=-Deplacements-1000;
					System.out.println("\n Votre score final est de   " + score+" ");
					System.exit(0);
				}else {
					if(mat[r][c].AFroid) {
						System.out.println("vous etes pret du puit");
					}
					else if(mat[r][c].APuanteur) {
						System.out.println("vous etes pret du monstre");
					}else { 
						System.out.println("je ressens rien");
					}
					System.out.println("vous pouvez continuer");
					System.out.println("veuillez choisir ou vous voulez vous deplacer / quitter le jeu");
					String user=sc.next();
					if(user.charAt(0)=='h') {
						switch(direction) {
						case EST:
							direction = Direction.SUD;
							break;
						case OUEST:
							direction = Direction.NORD;
							break;
						case NORD:
							direction = Direction.EST;
							break;
						case SUD:
							direction = Direction.OUEST;
							break;
						}
						System.out.println("vous avez choisi de tourner a haut");
						r--;
						Deplacements++;
					}
					if(user.charAt(0)=='b') {
						switch(direction) {
						case EST:
							direction = Direction.SUD;
							break;
						case OUEST:
							direction = Direction.NORD;
							break;
						case NORD:
							direction = Direction.EST;
							break;
						case SUD:
							direction = Direction.OUEST;
							break;
						}
						System.out.println("vous avez choisi de tourner a bas");
						r++;
						Deplacements++;
					}
					if(user.charAt(0)=='d') {
						switch(direction) {
						case EST:
							direction = Direction.SUD;
							break;
						case OUEST:
							direction = Direction.NORD;
							break;
						case NORD:
							direction = Direction.EST;
							break;
						case SUD:
							direction = Direction.OUEST;
							break;
						}
						System.out.println("vous avez choisi de tourner a droite");
						c++;
						Deplacements++;
					}
					if(user.charAt(0)=='g') {
						switch(direction) {
						case EST:
							direction = Direction.NORD;
							break;
						case OUEST:
							direction = Direction.SUD;
							break;
						case NORD:
							direction = Direction.OUEST;
							break;
						case SUD:
							direction = Direction.EST;
							break;
						}
						System.out.println("vous avez choisi de tourner a gauche");
						c--;
						Deplacements++;
					}
					if(user.equals("quitter")) {
						System.out.println("vous avez quitte");
						System.exit(0);
					}
				}
				if(Deplacements > n*n) {
					System.out.println("\nPas de solution!");
					int score=-Deplacements;
					System.out.println("\n Votre score final est de   " + score+" ");
					System.exit(0);
				}
				
			}
			if(Deplacements <= n*n) {
				System.out.println("\nOr touve en  " + Deplacements + " Deplacements.");
				int score= 1000-Deplacements;
				System.out.println("\n Votre score final est de   " + score+" ");
				sc.close();
				System.exit(0);
			}

		}
		else {
			System.out.println("\netat initial:");
			printmat(r, c);

			while(!mat[r][c].AOr) {
				mat[r][c].Visite = true;
				mat[r][c].StatutPuit = Bloc.NonPresent;
				mat[r][c].StatutWampus = Bloc.NonPresent;

				if(!mat[r][c].AFroid) {
					if(r >= 1 && mat[r-1][c].StatutPuit == Bloc.PasSur)
						mat[r-1][c].StatutPuit = Bloc.NonPresent;
					if(r <= (n-2) && mat[r+1][c].StatutPuit == Bloc.PasSur)
						mat[r+1][c].StatutPuit = Bloc.NonPresent;
					if(c >= 1 && mat[r][c-1].StatutPuit == Bloc.PasSur)
						mat[r][c-1].StatutPuit = Bloc.NonPresent;
					if(c <= (n-2) && mat[r][c+1].StatutPuit == Bloc.PasSur)
						mat[r][c+1].StatutPuit = Bloc.NonPresent;
				}

				if(!mat[r][c].APuanteur) {
					if(r >= 1 && mat[r-1][c].StatutWampus == Bloc.PasSur)
						mat[r-1][c].StatutWampus = Bloc.NonPresent;
					if(r <= (n-2) && mat[r+1][c].StatutWampus == Bloc.PasSur)
						mat[r+1][c].StatutWampus = Bloc.NonPresent;
					if(c >= 1 && mat[r][c-1].StatutWampus == Bloc.PasSur)
						mat[r][c-1].StatutWampus = Bloc.NonPresent;
					if(c <= (n-2) && mat[r][c+1].StatutWampus == Bloc.PasSur)
						mat[r][c+1].StatutWampus = Bloc.NonPresent;
				}

				boolean NouvChemain = false;

				if(r >= 1 && !((r-1) == rPrev && c == cPrev) && mat[r-1][c].Visite == false && mat[r-1][c].StatutPuit == Bloc.NonPresent && mat[r-1][c].StatutWampus == Bloc.NonPresent) {
					rPrev = r;
					cPrev = c;
					r--; //go up
					NouvChemain = true;
				}
				else if(r <= (n-2) && !((r+1) == rPrev && c == cPrev) && mat[r+1][c].Visite == false && mat[r+1][c].StatutPuit == Bloc.NonPresent && mat[r+1][c].StatutWampus == Bloc.NonPresent) {
					rPrev = r;
					cPrev = c;
					r++; //go down
					NouvChemain = true;
				}
				else if(c >= 1 && !(r == rPrev && (c-1) == cPrev) && mat[r][c-1].Visite == false && mat[r][c-1].StatutPuit == Bloc.NonPresent && mat[r][c-1].StatutWampus == Bloc.NonPresent) {
					rPrev = r;
					cPrev = c;
					c--; //go left
					NouvChemain = true;
				}
				else if(c <= (n-2) && !(r == rPrev && (c+1) == cPrev) && mat[r][c+1].Visite == false && mat[r][c+1].StatutPuit == Bloc.NonPresent && mat[r][c+1].StatutWampus == Bloc.NonPresent) {
					rPrev = r;
					cPrev = c;
					c++; //go right
					NouvChemain = true;
				}

				if(!NouvChemain) {
					int temp1 = rPrev;
					int temp2 = cPrev;
					rPrev = r;
					cPrev = c;
					r = temp1;
					c = temp2;
				}

				Deplacements++;

				System.out.println("\n\nDeplacement " + Deplacements + ":");
				int a=n-r;
				int b=c+1;
				System.out.println("Deplacement vers la case " + a + " "+b);
				printmat(r, c);

				if(Deplacements > n*n) {
					System.out.println("\nPas de solution!");
					int score=-Deplacements;
					System.out.println("\n Votre score final est de   " + score+" ");
					break;
				}
			}
			if(Deplacements <= n*n)
				System.out.println("\nOr touve en  " + Deplacements + " Deplacements.");
			int score= 1000-Deplacements;
			System.out.println("\n Votre score final est de   " + score+" ");

			sc.close();
		}
	}

	static void AjouPuit(int r, int c) {
		mat[r][c].APuit = true;

		if(r >= 1)
			mat[r-1][c].AFroid = true;
		if(r <= (n-2))
			mat[r+1][c].AFroid = true;
		if(c >= 1)
			mat[r][c-1].AFroid = true;
		if(c <= (n-2))
			mat[r][c+1].AFroid = true;
	}

	static void AjouWumpus(int r, int c) {
		mat[r][c].AWumpus = true;

		if(r >= 1)
			mat[r-1][c].APuanteur = true;
		if(r <= (n-2))
			mat[r+1][c].APuanteur = true;
		if(c >= 1)
			mat[r][c-1].APuanteur = true;
		if(c <= (n-2))
			mat[r][c+1].APuanteur = true;
	}

	static void AjouOr(int r, int c) {
		mat[r][c].AOr = true;
	}
	static void printmat(int r, int c) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				char charToPrint = '-';
				if(r == i && c == j) {
					charToPrint = '*';
					/*switch(direction) {
					case EST:
						charToPrint = '>';
						break;
					case OUEST:
						charToPrint = '<';
						break;
					case NORD:
						charToPrint = '^';
						break;
					case SUD:
						charToPrint = 'v';
						break;
					}
*/
				}

				else if(mat[i][j].APuit)
					charToPrint = 'O';
				else if(mat[i][j].AWumpus)
					charToPrint = 'X';
				else if(mat[i][j].AOr)
					charToPrint = '$';

				System.out.print(charToPrint + "\t");
			}
			System.out.println();
		}
	}
}
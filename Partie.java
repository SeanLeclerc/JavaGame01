
//============================================================================
// //=====================================
//Programmeur : Sean Leclerc
//Fichier : Partie.java
//Date : Dec 5, 2016
//Description : Devoir 2 
//======================================
//============================================================================

import logdm.utilitaires.*;

//============================================================================
public class Partie
// ============================================================================
{
	// Variables
	private Visionneur v = null;
	private int energie = 20;
	int nbDes = 0;
	private int xp = 10;
	private int yp = 10;
	private int[][] g = new int[21][21]; // g[colonnes][ranges]
	private int cp = 70;
	private int nbDep = 0;
	private int nbObs = 0;
	private int nbGliss = 0;
	private int d1 = 0;
	private int d2 = 0;
	private int d3 = 0;
	private int d4 = 0;
	private int d5 = 0;
	private int d6 = 0;

	// Constructeur
	// ------------------------------------------------------------------------
	public Partie(Visionneur v)
	// ------------------------------------------------------------------------
	{
		this.v = v;
		v.placerImage(xp, yp, cp);
		g[xp][yp] = cp;
		// on dessine le mur
		for (int i = 1; i < 20; i++) {
			v.placerImage(i, 0, 32);
			g[i][0] = 32;
			v.placerImage(i, 20, 32);
			g[i][20] = 32;
			v.placerImage(0, i, 33);
			g[0][i] = 32;
			v.placerImage(20, i, 33);
			g[20][i] = 32;

		}
		// Coin
		v.placerImage(0, 0, 20);
		g[0][0] = 32;
		v.placerImage(20, 0, 21);
		g[20][0] = 32;
		v.placerImage(0, 20, 23);
		g[0][20] = 32;
		v.placerImage(20, 20, 22);
		g[20][20] = 32;

		// on place les des
		int lecode = 0;
		int x, y;
		for (int i = 0; i < 100; i++) {
			do {
				x = (int) (Math.random() * 19) + 1;
				y = (int) (Math.random() * 19) + 1;
			} while (g[x][y] != 0);
			do {
				lecode = (int) (Math.random() * 6) + 73;

			} while (g[x + 1][y] == lecode || g[x - 1][y] == lecode || g[x][y + 1] == lecode || g[x][y - 1] == lecode);
			v.placerImage(x, y, lecode);
			g[x][y] = lecode;
			nbDes++;
		}
		// Cerises
		int code = 19;
		for (int i = 0; i < 4; i++) {
			do {
				x = (int) (Math.random() * 19) + 1;
				y = (int) (Math.random() * 19) + 1;
			} while (g[x][y] != 0);
			v.placerImage(x, y, code);
			g[x][y] = code;
		}

	}

	// M�thodes
	// ------------------------------------------------------------------------
	public void demarrer()
	// ------------------------------------------------------------------------
	{
		int x, y;
		int dir = 0;
		String[] sDir = { "Haut", "Droite", "Bas", "Gauche" };
		v.afficher(this); // Affiche le toString de la partie dans la console

		do {
			dir = v.lireDirection(); // 0=Haut 1=Droite 2=Bas 3=Gauche
			// Obstacles
			nbDep++;
			energie--;
			if (nbDep % 2 == 0) {
				nbObs++;
				do {
					x = (int) (Math.random() * 21);
					y = (int) (Math.random() * 21);
				} while (g[x][y] != 0);
				v.placerImage(x, y, 98);
				g[x][y] = 98;

			}
			// Bouger la tete
			if (dir == 1) { // Droite
				if (g[xp + 1][yp] == 0) {
					if(g[xp+1][yp] == 32) {
						
					}
					// C'est libre
					v.effacer(xp, yp);
					g[xp][yp] = 0;
					xp++;
					v.placerImage(xp, yp, cp, dir);
					g[xp][yp] = cp;
				} else if (g[xp + 1][yp] != 0 && g[xp + 1][yp] != 32 && g[xp + 1][yp] != 98 && g[xp + 2][yp] == 0 ) {
					v.effacer(xp, yp);
					v.effacer(xp + 1, yp);
					g[xp][yp] = 0;
					xp++;
					g[xp + 1][yp] = g[xp][yp];
					g[xp][yp] = cp;
					v.placerImage(xp, yp, cp, dir);
					v.placerImage(xp + 1, yp, g[xp + 1][yp]);
					nbGliss++;

				
				}if (g[xp + 2][yp] == g[xp + 1][yp]) {
					v.effacer(xp + 2, yp);
					v.effacer(xp + 1, yp);
					g[xp + 2][yp] = 0;
					g[xp + 1][yp] = 0;
					if (g[xp + 2][yp] == 73) {
						d1 += 2;
						d1++;
					}
					if (g[xp + 2][yp] == 74) {
						d2 += 4;
						d2++;
					}
					if (g[xp + 2][yp] == 75) {
						d3 += 6;
						d3++;
					}
					if (g[xp + 2][yp] == 76) {
						d4 += 8;
						d4++;
					}
					if (g[xp + 2][yp] == 77) {
						d5 += 10;
						d5++;
					}
					if (g[xp + 2][yp] == 78) {
						d6 += 12;
						d6++;
					}

				}
				if (g[xp + 1][yp - 1] == g[xp + 1][yp]) {
					v.effacer(xp + 1, yp - 1);
					v.effacer(xp + 1, yp);
					g[xp + 1][yp - 1] = 0;
					g[xp + 1][yp] = 0;
					
					if (g[xp + 1][yp-1] == 73) {
						d1 += 2;
						d1++;
					}
					if (g[xp + 1][yp-1] == 74) {
						d2 += 4;
						d2++;
					}
					if (g[xp + 1][yp-1] == 75) {
						d3 += 6;
						d3++;
					}
					if (g[xp + 1][yp-1] == 76) {
						d4 += 8;
						d4++;
					}
					if (g[xp + 1][yp-1] == 77) {
						d5 += 10;
						d5++;
					}
					if (g[xp + 1][yp-1] == 78) {
						d6 += 12;
						d6++;
					}

				}
				if (g[xp + 1][yp + 1] == g[xp + 1][yp]) {
					v.effacer(xp + 1, yp + 1);
					v.effacer(xp + 1, yp);
					g[xp + 1][yp + 1] = 0;
					g[xp + 1][yp] = 0;

					if (g[xp + 1][yp] == 73) {
						d1 += 2;
						d1++;
					}
					if (g[xp + 1][yp] == 74) {
						d2 += 4;
						d2++;
					}
					if (g[xp + 1][yp] == 75) {
						d3 += 6;
						d2++;
					}
					if (g[xp + 1][yp] == 76) {
						d4 += 8;
						d4++;
					}
					if (g[xp + 1][yp] == 77) {
						d5 += 10;
						d5++;
					}
					if (g[xp + 1][yp] == 78) {
						d6 += 12;
						d6++;
					}
				}
				} else if (dir == 0) { // Haut
					//if(g[xp][yp-1] == 32 )
					if (g[xp][yp - 1] == 0) {
						// C'est libre
						v.effacer(xp, yp);
						g[xp][yp] = 0;
						yp--;
						v.placerImage(xp, yp, cp, dir);
						g[xp][yp] = cp;
					} else if (g[xp][yp - 1] != 0 && g[xp][yp - 1] != 19 && g[xp][yp - 1] != 98 && g[xp][yp - 2] == 0) {
						v.effacer(xp, yp);
						v.effacer(xp, yp - 1);
						g[xp][yp] = 0;
						yp--;
						g[xp][yp - 1] = g[xp][yp];
						g[xp][yp] = cp;
						v.placerImage(xp, yp, cp, dir);
						v.placerImage(xp, yp - 1, g[xp][yp - 1]);
						nbGliss++;
						// Elimine
					
					}if (g[xp + 1][yp - 1] == g[xp][yp - 1]) {
							v.effacer(xp, yp - 1);
							v.effacer(xp + 1, yp - 1);
							g[xp + 1][yp - 1] = 0;
							g[xp][yp - 1] = 0;
							
							if (g[xp + 1][yp-1] == 73) {
								d1 += 2;
								d1++;
							}
							if (g[xp + 1][yp-1] == 74) {
								d2 += 4;
								d2++;
							}
							if (g[xp + 1][yp-1] == 75) {
								d3 += 6;
								d3++;
							}
							if (g[xp + 1][yp-1] == 76) {
								d4 += 8;
								d4++;
							}
							if (g[xp + 1][yp-1] == 77) {
								d5 += 10;
								d5++;
							}
							if (g[xp + 1][yp-1] == 78) {
								d6 += 12;
								d6++;
							}

						}
						if (g[xp - 1][yp - 1] == g[xp][yp - 1]) {
							v.effacer(xp, yp - 1);
							v.effacer(xp - 1, yp - 1);
							g[xp - 1][yp - 1] = 0;
							g[xp][yp - 1] = 0;
							
							if (g[xp - 1][yp -1] == 73) {
								d1 += 2;
								d1++;
							}
							if (g[xp - 1][yp-1] == 74) {
								d2 += 4;
								d2++;
							}
							if (g[xp - 1][yp-1] == 75) {
								d3 += 6;
								d3++;
							}
							if (g[xp - 1][yp-1] == 76) {
								d4 += 8;
								d4++;
							}
							if (g[xp - 1][yp-1] == 77) {
								d5 += 10;
								d5++;
							}
							if (g[xp - 1][yp-1] == 78) {
								d6 += 12;
								d6++;
							}

						}
						if (g[xp][yp - 2] == g[xp][yp - 1]) {
							v.effacer(xp, yp - 2);
							v.effacer(xp, yp - 1);
							g[xp + 2][yp] = 0;
							g[xp][yp - 1] = 0;

							if (g[xp][yp - 1] == 73) {
								d1 += 2;
								d1++;
							}
							if (g[xp][yp - 1] == 74) {
								d2 += 4;
								d2++;
							}
							if (g[xp][yp - 1] == 75) {
								d3 += 6;
								d3++;
							}
							if (g[xp][yp - 1] == 76) {
								d4 += 8;
								d4++;
							}
							if (g[xp][yp - 1] == 77) {
								d5 += 10;
								d5++;
							}
							if (g[xp][yp - 1] == 78) {
								d6 += 12;
								d6++;
							}

						
					}

				} else if (dir == 2) { // Bas
					if (g[xp][yp + 1] == 0) {
						// C'est libre
						v.effacer(xp, yp);
						g[xp][yp] = 0;
						yp++;
						v.placerImage(xp, yp, cp, dir);
						g[xp][yp] = cp;
					}
					else if (g[xp][yp + 1] != 0 && g[xp][yp + 1] != 19 && g[xp][yp + 1] != 98 && g[xp][yp + 2] == 0) {
						v.effacer(xp, yp);
						v.effacer(xp, yp + 1);
						g[xp][yp] = 0;
						yp++;
						g[xp][yp + 1] = g[xp][yp];
						g[xp][yp] = cp;
						v.placerImage(xp, yp, cp, dir);
						v.placerImage(xp, yp + 1, g[xp][yp + 1]);
						nbGliss++;
					 // Elimine
					}if (g[xp + 1][yp + 1] == g[xp][yp + 1]) {
						v.effacer(xp + 1, yp + 1);
						v.effacer(xp, yp + 1);
						g[xp + 1][yp + 1] = 0;
						g[xp][yp + 1] = 0;
						
						if (g[xp + 1][yp+1] == 73) {
							d1 += 2;
							d1++;
						}
						if (g[xp + 1][yp+1] == 74) {
							d2 += 4;
							d2++;
						}
						if (g[xp + 1][yp+1] == 75) {
							d3 += 6;
							d3++;
						}
						if (g[xp + 1][yp+1] == 76) {
							d4 += 8;
							d4++;
						}
						if (g[xp + 1][yp+1] == 77) {
							d5 += 10;
							d5++;
						}
						if (g[xp + 1][yp+1] == 78) {
							d6 += 12;
							d6++;
						}

					}
					if (g[xp - 1][yp + 1] == g[xp][yp + 1]) {
						v.effacer(xp - 1, yp + 1);
						v.effacer(xp, yp + 1);
						g[xp - 1][yp + 1] = 0;
						g[xp][yp + 1] = 0;
						
						if (g[xp - 1][yp +1] == 73) {
							d1 += 2;
							d1++;
						}
						if (g[xp - 1][yp+1] == 74) {
							d2 += 4;
							d2++;
						}
						if (g[xp - 1][yp+1] == 75) {
							d3 += 6;
							d3++;
						}
						if (g[xp - 1][yp+1] == 76) {
							d4 += 8;
							d4++;
						}
						if (g[xp - 1][yp+1] == 77) {
							d5 += 10;
							d5++;
						}
						if (g[xp - 1][yp+1] == 78) {
							d6 += 12;
							d6++;
						}

					}
					if (g[xp][yp + 2] == g[xp][yp + 1]) {
						v.effacer(xp, yp + 2);
						v.effacer(xp, yp + 1);
						g[xp][yp + 2] = 0;
						g[xp][yp + 1] = 0;

						if (g[xp][yp + 1] == 73) {
							d1 += 2;
							d1++;
						}
						if (g[xp][yp + 1] == 74) {
							d2 += 4;
							d2++;
						}
						if (g[xp][yp + 1] == 75) {
							d3 += 6;
							d3++;
						}
						if (g[xp][yp + 1] == 76) {
							d4 += 8;
							d4++;
						}
						if (g[xp][yp + 1] == 77) {
							d5 += 10;
							d5++;
						}
						if (g[xp][yp + 1] == 78) {
							d6 += 12;
							d6++;
						}
					}
					

				} else if (dir == 3) { // Gauche
					if (g[xp - 1][yp] == 0) {
						// C'est libre
						v.effacer(xp, yp);
						g[xp][yp] = 0;
						xp--;
						v.placerImage(xp, yp, cp, dir);
						g[xp][yp] = cp;
					}
					else if (g[xp - 1][yp] != 0 && g[xp - 1][yp] != 19 && g[xp - 1][yp] != 98 && g[xp - 2][yp] == 0) {
						v.effacer(xp, yp);
						v.effacer(xp - 1, yp);
						g[xp][yp] = 0;
						xp--;
						g[xp - 1][yp] = g[xp][yp];
						g[xp][yp] = cp;
						v.placerImage(xp, yp, cp, dir);
						v.placerImage(xp - 1, yp, g[xp - 1][yp]);
						nbGliss++;
						// Elimine
					}if (g[xp - 1][yp + 1] == g[xp - 1][yp]) {
							v.effacer(xp - 1, yp + 1);
							v.effacer(xp - 1, yp);
							g[xp - 1][yp + 1] = 0;
							g[xp - 1][yp] = 0;
							
							if (g[xp -1 ][yp+1] == 73) {
								d1 += 2;
								d1++;
							}
							if (g[xp - 1][yp+1] == 74) {
								d2 += 4;
								d2++;
							}
							if (g[xp - 1][yp+1] == 75) {
								d3 += 6;
								d3++;
							}
							if (g[xp - 1][yp+1] == 76) {
								d4 += 8;
								d4++;
							}
							if (g[xp - 1][yp+1] == 77) {
								d5 += 10;
								d5++;
							}
							if (g[xp - 1][yp+1] == 78) {
								d6 += 12;
								d6++;
							}

						}
						if (g[xp - 2][yp] == g[xp - 1][yp]) {
							v.effacer(xp - 2, yp);
							v.effacer(xp - 1, yp);
							g[xp - 2][yp] = 0;
							g[xp - 1][yp] = 0;
							
							if (g[xp - 2][yp] == 73) {
								d1 += 2;
								d1++;
							}
							if (g[xp - 2][yp] == 74) {
								d2 += 4;
								d2++;
							}
							if (g[xp - 2][yp] == 75) {
								d3 += 6;
								d3++;
							}
							if (g[xp - 2][yp] == 76) {
								d4 += 8;
								d4++;
							}
							if (g[xp - 2][yp] == 77) {
								d5 += 10;
								d5++;
							}
							if (g[xp - 2][yp] == 78) {
								d6 += 12;
								d6++;
							}

						}
						if (g[xp - 1][yp - 1] == g[xp - 1][yp]) {
							v.effacer(xp - 1, yp + 1);
							v.effacer(xp - 1, yp);
							g[xp - 1][yp - 1] = 0;
							g[xp - 1][yp] = 0;

							if (g[xp - 1][yp - 1] == 73) {
								d1 += 2;
								d1++;
							}
							if (g[xp - 1][yp - 1] == 74) {
								d2 += 4;
								d2++;
							}
							if (g[xp - 1][yp - 1] == 75) {
								d3 += 6;
								d3++;
							}
							if (g[xp - 1][yp - 1] == 76) {
								d4 += 8;
								d4++;
							}
							if (g[xp - 1][yp - 1] == 77) {
								d5 += 10;
								d5++;
							}
							if (g[xp - 1][yp - 1] == 78) {
								d6 += 12;
								d6++;
							}
						}
					}
				

			

			v.effacerConsole();
			v.afficher(this); // Affiche le toString de la partie dans la
								// console
			v.afficher("\n" + sDir[dir]);
		} while (energie > 0);

	}

	// ------------------------------------------------------------------------
	public String toString()
	// ------------------------------------------------------------------------
	{
		String s = "";
		s += "=======================================\n";
		s += "Sean Leclerc\n";
		s += "=========================================\n";
		s += "Nombre de deplacements : " + nbDep + "\n";
		s += "Nombre de glissements : " + nbGliss + " \n";
		s += "Nombre de Des : " + nbDes + "\n";
		s += "Nombre d'obstacles : " + nbObs + "\n";
		s += "Unite d'energie: " + energie + "\n";
		s += "==========================================\n";
		s += "Des ramasses \n";
		s += "Nombre de 1            : " + "\n";
		s += "Nombre de 2            : " + "\n";
		s += "Nombre de 3            : " + "\n";
		s += "Nombre de 4            : " + "\n";
		s += "Nombre de 5            : " + "\n";
		s += "Nombre de 6            : " + "\n";
		s += "==========================================\n";
		s += "Nombre de points       : " + (d1 + d2 + d3 + d4 + d5 + d6) + "\n";
		s += "==========================================\n";
		return s;
	}

}// Fin de la classe Partie

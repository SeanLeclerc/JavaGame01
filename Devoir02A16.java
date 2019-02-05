//============================================================================
// Programmeur: Daniel Morin
// Date: 23 oct. 2016
// Fichier: Devoir02A16.java
// Description: 
//============================================================================

import java.awt.Font;
import logdm.utilitaires.*;

//============================================================================
public class Devoir02A16
//============================================================================
{

	//>>>>>>>>> IL NE FAUT PAS MODIFIER LE CONTENU DE CETTE CLASSE <<<<<<<<<<<
	//------------------------------------------------------------------------
	public static void main(String[] args) 
	//------------------------------------------------------------------------
	{
		Visionneur v = new Visionneur( 
				false,21,21,666,true,
				Visionneur.GRAPHIQUE,Visionneur.MOYENNE );
		v.setPoliceConsole(new Font( Font.MONOSPACED , Font.PLAIN , 16) );
		do {
			v.effacer();
			v.effacerConsole();
			Partie partie = new Partie( v );
			partie.demarrer();
		}while( v.lireBoolean("Une autre partie?","oui","non") );
		v.effacer();
		v.effacerConsole();
		v.placerMot(4, 10, "Bonne journee", 0);
	}

}

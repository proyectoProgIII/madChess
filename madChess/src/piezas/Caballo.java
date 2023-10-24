package piezas;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;

public class Caballo extends Pieza implements MetodosInterfaz {

	public Caballo(Boolean isWhite) {
		super("n",isWhite);
	}

	

	@Override
	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
		
		int casillaIndex = casillas.indexOf(curCasilla);
		System.out.println(casillaIndex);
		
		ArrayList<Casilla> casillasDisp = new ArrayList<>(); 
		//casillasDisp.add(casillas.get(casillaIndex+6));
		//casillasDisp.add(casillas.get(casillaIndex+10));
		//casillasDisp.add(casillas.get(casillaIndex+15));
		//casillasDisp.add(casillas.get(casillaIndex+17));
		casillasDisp.add(casillas.get(casillaIndex-6));
		casillasDisp.add(casillas.get(casillaIndex-10));
		casillasDisp.add(casillas.get(casillaIndex-15));
		casillasDisp.add(casillas.get(casillaIndex-17));
		
		return casillasDisp; 
		//Da error si alguna de las casillas disponibles no esta dentro del tablero, 
		//por ejemplo cuando el caballo "puede" moverse fuera del tablero, en su posicion inicial por ejemplo
		// pero el metodo como tal funciona funciona bien cuando todos sus movimientos tienen sentido, salvo por ese bug
		// He dejado con barras los tres ultimos para que no explote en su posicion inicial
	}
}

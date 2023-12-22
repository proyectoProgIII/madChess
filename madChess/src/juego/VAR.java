package juego;

import java.util.ArrayList;
import java.util.HashMap;

import objetos.Casilla;
import objetos.Movimiento;
import objetos.Pieza;
import objetos.Tablero;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import utils.Session;
import utils.utils;

public class VAR {
	
	private static DatosPartida datosPartida;
	private static Tablero tablero = Session.getVentana().getPanelJuego().getTablero();
	private static int curMov;
	private static HashMap<Movimiento,Pieza> piezasComidas = new HashMap<Movimiento,Pieza>();
	
	public static void avanzarMov() {
		if (curMov == datosPartida.getMovimientos().size()-1) {
			utils.alert("Fin de la partida", "No hay más movimientos");
			return;
		}
		
		Movimiento newMov = datosPartida.getMovimientos().get(curMov+1);
		Casilla casillaOrigen = tablero.getCasilla(newMov.getCasillaSalida().getFila(),newMov.getCasillaSalida().getColumna());
		Casilla casillaDestino = tablero.getCasilla(newMov.getCasillaLlegada().getFila(),newMov.getCasillaLlegada().getColumna());
		
		Pieza piezaMovida = casillaOrigen.getPieza();
		piezasComidas.put(newMov, casillaDestino.getPieza());
		
		casillaOrigen.setPieza(null);
		casillaDestino.setPieza(piezaMovida);
		
		curMov++;
		
	}

	public static void retrodecerMov() {
		if (curMov == -1) {
			utils.alert("Inicio de la partida", "No hay más movimientos");
			return;
		}
		
		Movimiento newMov = datosPartida.getMovimientos().get(curMov);
		Casilla casillaOrigen = tablero.getCasilla(newMov.getCasillaSalida().getFila(),newMov.getCasillaSalida().getColumna());
		Casilla casillaDestino = tablero.getCasilla(newMov.getCasillaLlegada().getFila(),newMov.getCasillaLlegada().getColumna());
		
		
		Pieza piezaMovida = casillaDestino.getPieza();
		Pieza piezaComida = piezasComidas.get(newMov);// de alguna manera hay que guardarla para luego poder volverla a poner al retroceder
		
		casillaOrigen.setPieza(piezaMovida);
		casillaDestino.setPieza(piezaComida);
		
		curMov--;
		
	}
	
	
	
	
	public static void runInitialConfig() {
		curMov = -1;
		regenerarTablero();
		Session.getVentana().getPanelJuego().modoTempPonerbtns();
		Session.getVentana().getPanelJuego().getPanelVAR().loadMovs(datosPartida.getMovimientos());
	}
	
	
	
	

	
	
	

	public DatosPartida getDatosPartida() {
		return datosPartida;
	}

	public static void setDatosPartida(DatosPartida datos) {
		datosPartida = datos;
		runInitialConfig();
		
		
	}
	


	
	public static void regenerarTablero() { //esto es temp , luego ya se verá como se cambia
		ArrayList<Casilla> casillas = tablero.getCasillas();
		for (Casilla casilla:casillas) {
			casilla.setPieza(null);
			casilla.setDisabled(false);
		}
		Session.getVentana().getPanelJuego().resetTextAreas();
		
		Rey reyBlack = new Rey(false,false);
		Rey reyWhite = new Rey(true,false);
		
		casillas.get(0).setPieza(new Torre(false,false));
		casillas.get(1).setPieza(new Caballo(false,false));
		casillas.get(2).setPieza(new Alfil(false,false));
		casillas.get(3).setPieza(new Reina(false,false));
		casillas.get(4).setPieza(reyBlack);
		casillas.get(5).setPieza(new Alfil(false,false));
		casillas.get(6).setPieza(new Caballo(false,false));
		casillas.get(7).setPieza(new Torre(false,false));

		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(new Peon(false,false));
		}

		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(new Peon(true,false)); 
		}
		
		casillas.get(56).setPieza(new Torre(true,false));
		casillas.get(57).setPieza(new Caballo(true,false));
		casillas.get(58).setPieza(new Alfil(true,false));
		casillas.get(59).setPieza(new Reina(true, false));
		casillas.get(60).setPieza(reyWhite);
		casillas.get(61).setPieza(new Alfil(true,false));
		casillas.get(62).setPieza(new Caballo(true,false));
		casillas.get(63).setPieza(new Torre(true,false));
    }

	
	public static void irAMovimiento(int indx) {
		if (indx > curMov) {
			for (int i = curMov; i < indx; i++) {
				avanzarMov();
			} 
        }
		else if (indx < curMov) {
			for (int i = curMov; i > indx; i--) {
				retrodecerMov();
			}
		}
		
	}
	

}

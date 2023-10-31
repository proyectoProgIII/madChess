package juego;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

import objetos.Boost;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import objetos.Tablero;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import ventanas.OLDVentanaJuego;
import ventanas.Juego;

public class Partida {

	protected ArrayList<Jugador> jugadores; // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	
	protected int gameId;
	protected Date fecha;
	protected ArrayList<Casilla> casillas;
	protected HashMap<String,Pieza> piezas = new HashMap<String,Pieza>();
	
	protected HashMap<Boost, Boolean> boosts;
	protected Tablero tablero;
	protected Juego ventana;


	public Partida(ArrayList<Jugador> jugadores, int gameId, Date fecha,HashMap<Boost, Boolean> boosts) {
		super();
		this.jugadores = jugadores;
		this.gameId = gameId;
		this.fecha = fecha;
		this.boosts = boosts;	
	}
	
	
	
	
	
	public Partida(Juego ventana) {
		this.ventana = ventana;
		this.tablero = ventana.getTablero();
		
		casillas = tablero.getCasillas();
		cargarPiezasTablero();
		
		
		
        tablero.tableroDiv.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		Casilla curCasilla = tablero.getCurCasilla(e);
        		moverPiezaTablero(tablero.prevCasilla,curCasilla,tablero.casillasDisp);
        		tablero.dragging = false;
        		
        }
        
        });
		
        

        
        
		
	}
	




	protected void moverPiezaTablero(Casilla prevCasilla,Casilla curCasilla,ArrayList<Casilla> casillasDisp) {
		
		if(prevCasilla != null && prevCasilla.getPieza()!=null) { //Confirmamos que estamos arrastrando una pieza
			
			
			if (prevCasilla != curCasilla && casillasDisp.contains(curCasilla)){ // Si la casilla esta entre las disponibles y no es la casilla de la que sale
       		Pieza pieza= prevCasilla.getPieza();
       		Pieza newPieza = curCasilla.getPieza();
       		Pieza piezaComida = null;
       		
       		if (checkEnroque(pieza,newPieza)) {
       			newPieza.setPMoved();
       		}
       		else{
       			if (newPieza!=null) {
           			//entonces estamos comiento una pieza
           			piezaComida = curCasilla.getPieza();
           		}
       			newPieza = null;
       			
       			if (checkPromotion(pieza,curCasilla)) {
           			tablero.initPromocion(curCasilla);
           			printMovimiento("Pieza promocionada ");
           			// FIXME : Para la versión final la pieza se tiene que promocionar en Partida no en Tablero
           		}
       		}
       		
       		
       		
       		
       		prevCasilla.setPieza(newPieza);
       		curCasilla.setPieza(pieza);
       		
       		pieza.setPMoved();  
       		guardarMovimiento(prevCasilla,curCasilla,piezaComida);
       		
			}
			 
			prevCasilla.setDragging(false);
			
			tablero.dragImg.setIcon(null); // Borramos la img del panel superior

    		for(Casilla casillaDisp: tablero.casillasDisp) {
    			casillaDisp.setDisponible(false);
    		} 
    		
    		       
		}
		
	}



	private void guardarMovimiento(Casilla prevCasilla, Casilla curCasilla, Pieza piezaComida) {
		//Aquí guardaríamos el movimiento en la base de datos, con su user etc para las analíticas
		String extra = (piezaComida==null) ? " ":" Pieza comida";
		printMovimiento(prevCasilla.getPos()+" --> "+curCasilla.getPos()+extra);
		
		
	}
	
	private void printMovimiento(String movimiento) {
		ventana.setNewMovimiento(movimiento);
	}
	





	protected boolean checkEnroque(Pieza curPieza,Pieza newPieza) {
		return(
				newPieza!=null&&
				(((curPieza instanceof Torre) && (newPieza instanceof Rey)  ) ||
				((curPieza instanceof Rey) && (newPieza instanceof Torre))		)&&
				
				!curPieza.getPMoved()&&
				!newPieza.getPMoved()
				
				);
	}
	
	protected boolean checkPromotion(Pieza curPieza, Casilla curCasilla) {
		return (curPieza instanceof Peon)&&
				(curCasilla.getFila() == 0);	
	}

	
	protected void cargarPiezasTablero() {

		casillas.get(0).setPieza(new Torre(false));
		casillas.get(1).setPieza(new Caballo(false));
		casillas.get(2).setPieza(new Alfil(false));
		casillas.get(3).setPieza(new Reina(false));
		casillas.get(4).setPieza(new Rey(false));
		casillas.get(5).setPieza(new Alfil(false));
		casillas.get(6).setPieza(new Caballo(false));
		casillas.get(7).setPieza(new Torre(false));

		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(new Peon(false));
		}

		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(new Peon(true));
		}
		
		casillas.get(56).setPieza(new Torre(true));
		casillas.get(57).setPieza(new Caballo(true));
		casillas.get(58).setPieza(new Alfil(true));
		casillas.get(59).setPieza(new Reina(true));
		casillas.get(60).setPieza(new Rey(true));
		casillas.get(61).setPieza(new Alfil(true));
		casillas.get(62).setPieza(new Caballo(true));
		casillas.get(63).setPieza(new Torre(true));
        }
	}




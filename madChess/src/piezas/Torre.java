package piezas;

import java.util.ArrayList;
import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;
import piezas.Rey;

public class Torre extends Pieza implements MetodosInterfaz {

    public Torre(Boolean isWhite) {
        super("r", isWhite);
    }

    @Override
    public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        // Posibles movimientos de la torre: arriba, abajo, izquierda y derecha
        int[][] movimientos = {
        			{0, -1}, 
        		{0, 1}, {-1, 0},
        			{1, 0}
        };

        for (int[] movimiento : movimientos) { //Recorre cada movimiento posible desde la posicion de la torre
            int nuevaFila = fila + movimiento[0];
            char nuevaColumna = (char) (columna + movimiento[1]);

            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            while (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                
                if (
                		casillaDisp.getPieza()!=null&&
                		casillaDisp.getPieza().getIsWhite()==this.getIsWhite())
                {break;}
                
                
                
                
                casillasDisp.add(casillaDisp);

                // Avanzo a la dirección del movimiento
                nuevaFila += movimiento[0];
                nuevaColumna = (char) (nuevaColumna + movimiento[1]);
                if (casillaDisp.getPieza()!=null) {break;}
            }
        }

        return casillasDisp;
    }
    
    
    public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        // Posibles movimientos de la torre: arriba, abajo, izquierda y derecha
        int[][] movimientos = {
        			{0, -1}, 
        		{0, 1}, {-1, 0},
        			{1, 0}
        };

        for (int[] movimiento : movimientos) { //Recorre cada movimiento posible desde la posicion de la torre
            int nuevaFila = fila + movimiento[0];
            char nuevaColumna = (char) (columna + movimiento[1]);

            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            while (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                
                if (
                		casillaDisp.getPieza()!=null&& //Hay pieza
                		casillaDisp.getPieza().getIsWhite()==this.getIsWhite()) // Y es enemiga
                {
                	casillasDisp.add(casillaDisp);
                	break;}
                

                
                casillasDisp.add(casillaDisp);

                // Avanzo a la dirección del movimiento
                nuevaFila += movimiento[0];
                nuevaColumna = (char) (nuevaColumna + movimiento[1]);
                if (casillaDisp.getPieza()!=null) {break;}
            }
        }

        return casillasDisp;
    }
}

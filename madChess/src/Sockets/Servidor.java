package Sockets;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import juego.DatosPartida;
import ventanas.ConfPOnline;

public class Servidor {
    private ServerSocket serverSocket;
    private HashMap<String, ArrayList<ClienteHandler>> clientes = new HashMap<String, ArrayList<ClienteHandler>> ();
    private HashMap<String, DatosPartida> partidas = new HashMap<String, DatosPartida>();
    
    private int PUERTO = 8005;

    
    public Servidor() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor runeado en: " + PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void iniciar() {
    	System.out.println("Esperando jugadores");
        while (true) {
            try {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado --> " + clienteSocket);

                
                
                
                
                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket,partidas, clientes);
                

                Thread clienteThread = new Thread(clienteHandler);
                clienteThread.start();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    

	public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }

}

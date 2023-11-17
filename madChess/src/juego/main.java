package juego;


import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.UIManager;

import ventanas.VentanaPrincipal;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setDefaultFont();
		VentanaPrincipal v1 = new VentanaPrincipal();
		
	}
	
	
    private static void setDefaultFont() {
        try {
            String nombreFont = "/srcmedia/unsr.ttf";
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, main.class.getResourceAsStream(nombreFont));
            customFont = customFont.deriveFont(Font.PLAIN, 16);

            // Configurar la fuente por defecto para JLabel
            UIManager.put("Label.font", customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

}

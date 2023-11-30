package objetos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.border.LineBorder;

import juego.Escalador;


public class Casilla extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Color color;
    protected int fila;
	protected char columna;
    protected Pieza pieza;
    protected Boolean dragging = false;
    protected Boolean iluminada = false;
    protected int imgSize;
    protected Boolean isDisponible = false;
    protected Boolean isHielo = false;


	public Casilla(Color color, int fila, int c) {
		this.color = color;
		this.fila = fila;
		this.columna = posicionToAlfabeto(c);

	}

	
	 public int getFila() {
	  		return fila;
	  	}

	  	public char getColumna() {
	  		return columna;
	  	}
	  	
	  public String getPos() {
		  return (this.columna+""+this.getFila());
	  }

	
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
		this.repaint();
		
	}
	
	
	private char posicionToAlfabeto(int columna) {
		return (char) ('A' + columna);
		
	}
	
	public void setDebugClr(Color color) {
		this.color = color;
		repaint();
		
	}


	// "iluminamos" sumando o restando valores al color RGB actual cuando se hoverea una casilla
	protected void iluminarCasilla(Boolean status) {
		if (status&&!iluminada || !status&&iluminada) {
			iluminada = status;
			int n = (status) ? 10 : -10;
			try {
				this.color = new Color( color.getRed()+n,color.getGreen()+n,color.getBlue()+n);
				this.paint(getGraphics());
			} catch (Exception e) {
				//Color muy grande
			}

		}
		
	}
	
	
	

	
	
	
	public void setHover(boolean status) {
		if (this.pieza!=null) {
			this.getParent().getParent().setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else {			
			this.getParent().getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			iluminarCasilla(status);
		}
		
	}
	
	
	public void setDragging(Boolean status) {
		this.dragging = status;
		repaint();
	}
	

	
	
	
	
	
	



	public Pieza getPieza() {
		return pieza;
	}


	@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // para que se vea nice
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        super.paintComponent(g);
        
        
        g.setColor(this.color);
        int height = Math.min(getWidth(), getHeight());
        g.fillRect(0, 0, height, height); // xPos, yPos W , H
        

        imgSize = (int)(height / 2 * 1.70);
        int x = (getWidth() - imgSize) / 2;
        int y = (getHeight() - imgSize) / 2;
        
        
        if (this.isDisponible) {
        	g.setColor(new Color(255, 201, 0 ));
        	if (this.pieza != null) {
            	final int radius1 = 80;//FIXME: Los circulos tendrían que tener relación al tamaño de la casilla
            	final int radius2 = 58;
            	g.fillOval(height/2 - radius1/2, height/2 - radius1/2,radius1,radius1);
            	g.setColor(this.color);
            	g.fillOval(height/2 - radius2/2, height/2 - radius2/2,radius2,radius2);
        	}
        	else {
            	final int radius = Escalador.escalar(25); //FIXME: En circulo tendría que tener relación al tamaño de la casilla
            	g.fillOval(height/2 - radius/2, height/2 - radius/2,radius,radius);	
        	}

        }
        
        
        if (this.pieza != null && !dragging) {	
        	Image img = this.pieza.getImg().getImage();
        	g.drawImage(img, x, y, imgSize, imgSize, null);
        }
        
        
        
    }


	public void setDisponible(boolean status) {
		isDisponible = status;
		repaint();
		
	}


	public void setPiezaOculto(Pieza newPieza) {
		this.pieza= newPieza;
	}


	public Boolean getIsHielo() {
		return isHielo;
	}


	public void setIsHielo(Boolean isHielo) {
		this.isHielo = isHielo;
		if(isHielo) {
        	this.color = new Color( color.getRed(),color.getGreen(),color.getBlue()+30);
        	
        }else {
        	this.color = new Color( color.getRed(),color.getGreen(),color.getBlue()-30);
        }
		repaint();
	}


	

	
	

}

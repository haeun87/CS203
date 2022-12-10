package Lab05;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class ShapeComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private int canvasWidth;
	private int canvasHeight;
	private String name;

	/**
	 * This function sets the width and height for Shapecomponent
	 * @param int width
	 * @param int height
	 * @return
	 */
	public void setCanvasSize(int width, int height) {
		this.canvasWidth = width;
		this.canvasHeight = height;
	}

	/**
	 * This function sets the name to use as Watermark
	 * @param String name
	 * @return
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This function draws a grass component to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	void drawGrass(Graphics2D g, double posX, double posY, double width, double height) {
		g.setColor(new Color(76,153,0));
		Rectangle2D.Double frameFull = new Rectangle2D.Double(posX, posY, width, height);
		g.fill(frameFull);
	}

	/**
	 * This function draws a house body component to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	void drawHouseBody(Graphics2D g, double posX, double posY, double width, double height) {
		g.setColor(new Color(250,235,215));
		Rectangle2D.Double frameFull = new Rectangle2D.Double(posX, posY, width, height);
		g.fill(frameFull);
		g.setColor(Color.DARK_GRAY);
		g.draw(frameFull);
	}

	/**
	 * This function draws a roof component to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	void drawRoof(Graphics2D g, double posX, double posY, double width, double height) {

		int[ ] posXs = {(int)posX,(int)(posX+width/2),(int)(posX+width)};
		int[ ] posYs = {(int)(posY+height), (int)posY ,(int)(posY+height)};
		g.setColor(new Color(178,34,34));
		g.fillPolygon(posXs, posYs, 3);
		g.setColor(Color.DARK_GRAY);
		g.drawPolygon(posXs, posYs, 3);
	}

	/**
	 * This function draws a window component to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	void drawWindow(Graphics2D g, double posX, double posY, double width, double height) {
		// Create a shape
		g.setColor(Color.WHITE);
		Rectangle2D.Double frameFull = new Rectangle2D.Double(posX, posY, width, height);
		g.fill(frameFull);

		Rectangle2D.Double frameHalfLeft = new Rectangle2D.Double(posX, posY, width/2, height);
		Rectangle2D.Double frameHalfRight = new Rectangle2D.Double(posX+width*0.5, posY, width/2, height);

		g.setColor(Color.DARK_GRAY);
		g.draw(frameHalfLeft);
		g.draw(frameHalfRight);

	}

	/**
	 * This function draws a balcony component to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	void drawBalcony(Graphics2D g, double posX, double posY, double width, double height) {
		Rectangle2D.Double balconyFrame;
		double posXBalcony = posX;
		double posYBalcony = posY+height*0.8;
		double widthBalcony = width/10;
		double heightBalcony = height*0.2;

		for(int i=0; i < 10; i++) {
			posXBalcony = posX + width/10*i;
			balconyFrame = new Rectangle2D.Double(posXBalcony, posYBalcony, widthBalcony, heightBalcony);
			g.draw(balconyFrame);
		}

		Rectangle2D.Double balconyDeck = new Rectangle2D.Double(posX, posY+height, width, height*0.1);
		g.fill(balconyDeck);
	}

	/**
	 * This function draws a door component to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	void drawDoor(Graphics2D g, double posX, double posY, double width, double height) {
		// Create a shape
		g.setColor(new Color(205,92,92));
		Rectangle2D.Double frameFull = new Rectangle2D.Double(posX, posY, width, height);
		g.fill(frameFull);


		g.setColor(Color.DARK_GRAY);
		g.draw(frameFull);

		g.fillOval((int)(posX+width*0.75), (int)(posY+height*0.5), (int)(width*0.20), (int)(height*0.20));

		Rectangle2D.Double step;
		double posYStep = posY + height;
		double stepHeight = posYStep*0.02;
		for(int i=0; i < 3; i++) {
			step = new Rectangle2D.Double(posX, posYStep + stepHeight*i, width, stepHeight);
			g.draw(step);
		}

	}

	/**
	 * This function draws a tree component to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	void drawTree(Graphics2D g, double posX, double posY, double width, double height) {
		// Draw Tree head
		g.setColor(new Color(203,204,0));
		Arc2D.Double treeTop = new Arc2D.Double(posX, posY, width, height, 0, 180, Arc2D.PIE);
		Arc2D.Double treeMiddle = new Arc2D.Double(posX-width*0.25, posY+height*0.3, width*1.5, height*1.5, 0, 360, Arc2D.PIE);
		Arc2D.Double treeBottom = new Arc2D.Double(posX-width*0.5, posY+height*0.6, width*2, height*2, 0, 360, Arc2D.PIE);

		g.fill(treeTop);
		g.fill(treeMiddle);
		g.fill(treeBottom);

		int[ ] posXs = {(int)(posX+width*0.5*0.60),(int)(posX+width*0.5),(int)(posX+width*0.5*1.40)};
		int[ ] posYs = {(int)(posY+height*3.5), (int)(posY+height) ,(int)(posY+height*3.5)};
		g.setColor(new Color(204,102,0));
		g.fillPolygon(posXs, posYs, 3);

	}

	/**
	 * This function writes desired string to the canvas
	 * @param Graphics2D g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @return
	 */
	void writeName(Graphics2D g, double posX, double posY) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 20));
		g.drawString(this.name, (int)posX, (int)posY);

	}

	/**
	 * This function includes and executes the drawing of component to canvas comprehensively.
	 * @param Graphics g
	 * @param double posX - starting x position of the component
	 * @param double posY - starting y position of the component
	 * @param double width - the horizontal length of the component
	 * @Param double height - the vertical length of the component
	 * @return
	 */
	@Override
	public void paintComponent(Graphics g) {
		// Casts g as a Graphics2D object, allows us to draw/fill with shape objects
		Graphics2D g2 = (Graphics2D) g;

		double posX = this.canvasWidth*0.25;
		double posY = this.canvasHeight*0.25;
		double width = this.canvasWidth*0.40;
		double height = this.canvasHeight*0.40;


		drawGrass(g2, 0, posY+height*0.9, this.canvasWidth, canvasHeight-posY+height*0.9);

		drawHouseBody(g2, posX, posY, width, height);

		drawRoof(g2, posX-width*0.2, posY-height*0.2, width*1.4, height*0.2);

		drawWindow(g2, posX*1.1, posY*1.1, width*0.4, height*0.4); // left-top window
		drawBalcony(g2, posX*1.1, posY*1.1, width*0.4, height*0.4); // left-top balcony

		drawWindow(g2, posX*1.1+width*0.5, posY*1.1, width*0.4, height*0.4); // right-top window
		drawBalcony(g2, posX*1.1+width*0.5, posY*1.1, width*0.4, height*0.4); // right-top balcony

		drawWindow(g2, posX*1.1+width*0.5, posY*1.1 + height*0.5, width*0.4, height*0.4); // right-bottom window
		drawBalcony(g2, posX*1.1+width*0.5, posY*1.1 + height*0.5, width*0.4, height*0.4); // right-bottom balcony

		drawDoor(g2, posX*1.1+width*0.05, posY*1.1 + height*0.5, width*0.3, height*0.35);
		drawTree(g2, this.canvasWidth*0.75, posY+height*0.5, width*0.3, height*0.3);

		writeName(g2, this.canvasWidth*0.825, this.canvasHeight*0.925);
	}



}

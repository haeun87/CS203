package hsuh_HW2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

public class TetrisComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private int canvasWidth;
	private int canvasHeight;
	private double sideLength;
	private Graphics2D g2;
	private static Random ran = new Random();

	/**
	 * Enum class that defines types of tetromino shape
	 */
	public enum Shape {
		SHAPE_I, SHAPE_J, SHAPE_O, SHAPE_L,
		SHAPE_T, SHAPE_S, SHAPE_Z;

		/**
		 * This function returns randomly chosen shape as an Enum property
		 * @param
		 * @return Shape shape - randomly selected shape
		 */
	    public static Shape randomShape()  {
	    	Shape[] shapes = values();
	        return shapes[ran.nextInt(shapes.length)];
	    }

	}

	/**
	 * Enum class that defines directions of tetromino shape
	 */
	public enum Direction {
		NORTH, SOUTH, EAST, WEST;

		/**
		 * This function returns randomly chosen direction as an Enum property
		 * @param
		 * @return Direction direction - randomly selected direction
		 */
	    public static Direction randomDirection()  {
	        Direction[] directions = values();
	        return directions[ran.nextInt(directions.length)];
	    }
	}

	/**
	 * This function sets the information of a canvas frame size that will be used to calculate the position boundary
	 * @param int width
	 * @param int height
	 * @return
	 */
	public void setCanvasSize(int width, int height) {
		this.canvasWidth = width;
		this.canvasHeight = height;
	}

	/**
	 * This function sets the side length of square that makes up a tetromino.
	 * @param int sideLength
	 * @return
	 */
	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}


	/**
	 * This function returns a Color class instance that is formed by randomly chosen RGB color.
	 * @param
	 * @return Color color - a Color instance of randomly chosen RGB.
	 */
	public Color getRandomColor() {
		Random ran = new Random();
		int red = ran.nextInt(0, 255);
		int green = ran.nextInt(0, 255);
		int blue = ran.nextInt(0, 255);
		return new Color(red,green, blue);
	}

	/**
	 * This function draws a tetromino by the given information.
	 * @param Shape tShape
	 * @param Color tColor
	 * @return
	 */
	public void setTetromino(Shape tShape, Color tColor) {

		// Get Random direction
		Direction tDirection = Direction.randomDirection();

		// Determine the position
		List<double[]> tPositions = getRandomPositions(tShape, tDirection);

		// Drow a tetromino
		Rectangle2D.Double t;
		for(double[] position: tPositions) {
			this.g2.setColor(tColor);
			t = new Rectangle2D.Double(position[0], position[1], this.sideLength, this.sideLength);
			this.g2.fill(t);
			this.g2.setColor(Color.DARK_GRAY);
			this.g2.draw(t);
		}

	}


	/**
	 * This function returns list of coordinates by the given shape and direction.
	 * @param Shape s
	 * @param Direction d
	 * @return List<double[]> positions - a list of x-y coordinates that will compose of a tetromino.
	 */
	public List<double[]> getRandomPositions(Shape s, Direction d) {
		List<double[]> positions = new ArrayList<>();
		double margin = this.sideLength*5; // In order to avoid being drawn out size of the frame
		double startX = ran.nextDouble(0,this.canvasWidth-margin);
		double startY = ran.nextDouble(0, this.canvasHeight-margin);
		switch(s) {
			case SHAPE_I:
				switch(d) {
					case NORTH:
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*3, startY+this.sideLength});
						break;
					case EAST:
						positions.add(new double[]{startX+this.sideLength*2, startY});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength*3});
						break;
					case SOUTH:
						positions.add(new double[]{startX, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength*3, startY+this.sideLength*2});
						break;

					case WEST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*3});
						break;
				}
				break;
			case SHAPE_J:
				switch(d) {
					case NORTH:
						positions.add(new double[]{startX, startY});
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						break;
					case EAST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength*2, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						break;
					case SOUTH:
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength*2});
						break;
					case WEST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						positions.add(new double[]{startX, startY+this.sideLength*2});
						break;
				}
				break;
			case SHAPE_O: // Regardless of its direction, Shape O remains the same! Therefore, skips the conditional statements.
				positions.add(new double[]{startX, startY});
				positions.add(new double[]{startX+this.sideLength, startY});
				positions.add(new double[]{startX, startY+this.sideLength});
				positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
				break;
			case SHAPE_L:
				switch(d) {
					case NORTH:
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY});
						break;
					case EAST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength*2});
						break;
					case SOUTH:
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX, startY+this.sideLength*2});
						break;
					case WEST:
						positions.add(new double[]{startX, startY});
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						break;
				}
				break;
			case SHAPE_T:
				switch(d) {
					case NORTH:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						break;
					case EAST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						break;
					case SOUTH:
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						break;
					case WEST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						break;
				}
				break;
			case SHAPE_S:
				switch(d) {
					case NORTH:
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength*2, startY});
						break;
					case EAST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength*2});
						break;
					case SOUTH:
						positions.add(new double[]{startX, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						break;
					case WEST:
						positions.add(new double[]{startX, startY});
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						break;
				}

				break;
			case SHAPE_Z:
				switch(d) {
					case NORTH:
						positions.add(new double[]{startX, startY});
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						break;
					case EAST:
						positions.add(new double[]{startX+this.sideLength*2, startY});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						break;
					case SOUTH:
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength*2});
						positions.add(new double[]{startX+this.sideLength*2, startY+this.sideLength*2});
						break;
					case WEST:
						positions.add(new double[]{startX+this.sideLength, startY});
						positions.add(new double[]{startX+this.sideLength, startY+this.sideLength});
						positions.add(new double[]{startX, startY+this.sideLength});
						positions.add(new double[]{startX, startY+this.sideLength*2});
						break;
				}
				break;
		}
		return positions;
	}

	/**
	 * This function includes and executes the drawing of a tetromino to canvas comprehensively.
	 * @param Graphics g
	 * @return
	 */
	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D) g;

		// Set shape
		Shape tShape = Shape.randomShape();

		// Set color
		Color tColor = this.getRandomColor();

		// Draw the shape
		setTetromino(tShape, tColor);
	}

}

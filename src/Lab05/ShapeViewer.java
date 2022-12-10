package Lab05;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ShapeViewer {

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		int canvasWidth = 800;
		int canvasHeight = 800;
		String name = "Haeun Suh";

		frame.setSize(canvasWidth, canvasHeight);

		frame.setTitle("CS 203 Lab 5");
		frame.getContentPane().setBackground(new Color(135,206,235)); // Set sky color

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ShapeComponent s = new ShapeComponent();
		s.setCanvasSize(canvasWidth, canvasHeight);
		s.setName(name);

		frame.add(s);

		frame.setVisible(true);
	}
}
package hsuh_HW2;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TetrisViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		int canvasWidth = 800;
		int canvasHeight = 800;
		int sideLength = 50;

		frame.setSize(canvasWidth, canvasHeight);
		frame.setTitle("CS 203 Homework 2");
		frame.getContentPane().setBackground(Color.WHITE);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		TetrisComponent s = new TetrisComponent();
		s.setCanvasSize(canvasWidth, canvasHeight);
		s.setSideLength(sideLength);
		frame.add(s);

		frame.setVisible(true);
	}
}

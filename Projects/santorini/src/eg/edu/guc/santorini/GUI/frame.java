package eg.edu.guc.santorini.GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.santorini.tiles.Cube;

public class frame extends JFrame {
	Button play;
	JLabel L;
	public frame(){
		this.setSize(800, 600);
		this.setBackground(Color.RED);
		play =new Button();
	 L =new JLabel(new ImageIcon("Grace-Santorini.jpg"));
	 L.setLayout(null);
	 

play =new Button ();
Cube a =new Cube(1,5);
L.setIcon((Icon) a);
add(L);
//play.setSize(19, 10);
add(play);
add(L);
		//L.setBounds()
//JPanel j =new JPanel();
//	this.setIconImage(new javax.swing.ImageIcon("small rubix.jpg"));

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new frame();
	}

}

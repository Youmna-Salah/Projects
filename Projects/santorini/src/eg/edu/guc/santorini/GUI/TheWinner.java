package eg.edu.guc.santorini.GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eg.edu.guc.santorini.Board;

public class TheWinner extends JFrame{
	
	public TheWinner(Board board) {
		super();
		setSize(400, 300);
		setTitle("This Santorini Game is Over");
		JPanel panel = new JPanel();
		JPanel jpanel = new JPanel();
		JLabel label = new JLabel();
		JButton button = new javax.swing.JButton();
		String text = board.getWinner().getName()+ " WINs in "+ board.getWinner().getMoves()+" actions";
		label.setText(text);
		button.setText("Play Again !!");
		button.setSize(400, 150);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new OpenFrame().setVisible(true);

			}
		});
		panel.add(label);
		jpanel.add(button);
		add(panel);
		add(button,BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(700,300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TheWinner(null);
	}

}

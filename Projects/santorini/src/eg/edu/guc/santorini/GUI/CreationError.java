package eg.edu.guc.santorini.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreationError extends JFrame{
	
	public CreationError() {
		JLabel label = new JLabel("Every Player should choose one piece");
		JPanel panel = new JPanel();
		panel.add(label);
		panel.setSize(300,100);
		add(panel);
		setSize(300, 100);
		setVisible(true);
	}

}

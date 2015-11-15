import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class Window extends JFrame {

	JButton Refresh;
	JLabel L;
	JTextField text;
	Container contentPane;
ArrayList<Label> labels;

	public Window(final Hashtable<String, String> refresh) {
		this.setResizable(false);
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labels = new ArrayList<Label>();
		L = new JLabel(new ImageIcon("dreams.jpg"));
		this.setContentPane(L);
		contentPane = this.getContentPane();

		FlowLayout b = new FlowLayout(FlowLayout.CENTER, 500, 30);
		contentPane.setLayout(b);
		text = new javax.swing.JTextField("File path", 70);
		text.setAutoscrolls(true);
		text.addActionListener(new ActionListener() {
			String Path = "";
			
			public void actionPerformed(ActionEvent arg0) {
				Path += text.getText();
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	


		Refresh = new JButton();
		Refresh.setIcon(new ImageIcon("repeat.png"));
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				//calling display that should return a hash table
				//containing values of the current state
			//	Refresh(display());
				
			} } 
				);


		Border emptyBorder = BorderFactory.createEmptyBorder();
		Refresh.setBorder(emptyBorder);
		Refresh.setContentAreaFilled(false);
		Refresh.setVisible(true);
		contentPane.add(Refresh);
		this.setSize(1000, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public void Refresh(final Hashtable<String, String> refresh){
	Enumeration<String> keys =refresh.keys();
	String currentKey;
	JLabel l;
		 while(keys.hasMoreElements()){
		     currentKey = keys.nextElement(); 
			l =new JLabel(currentKey+"  "+refresh.get(currentKey));
			l.setVisible(true);
			l.setOpaque(false);

			l.setLocation(100, 100);;
			contentPane.add(l);
			l.setFont(new Font("Courier New", Font.CENTER_BASELINE, 40));
			l.setForeground(new Color(0x00FFFF));
	}

}
	public static void main(String[]args) {
		
		//test
		Hashtable<String, String> l = new Hashtable<String, String>();
		l.put("t1", "1");
		l.put("t2", "2");
		new Window(l);
	}
}

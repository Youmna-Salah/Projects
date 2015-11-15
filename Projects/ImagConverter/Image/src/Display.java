import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.AbstractDocument.Content;


public class Display extends JFrame {
	Container contentPane;
	JLabel L,M,N,O;
	public Display(ArrayList<String> o){
     L=new JLabel(new ImageIcon(o.get(0)));
     M=new JLabel(new ImageIcon(o.get(1)));	
     N=new JLabel(new ImageIcon(o.get(2)));
     O =new JLabel(new ImageIcon(o.get(3)));	
     
	//this.setResizable(false);
		this.add(L);
		this.add(M);
		this.add(O);
		this.add(N);

	FlowLayout b = new FlowLayout();
		this.setLayout(b);
this.setTitle("Image Processing :)");
		this.setSize(812, 633);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

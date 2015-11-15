import java.lang.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
 
public class SNOWMAN extends JFrame {
 
	  
    public void paint (Graphics frame)
    {   frame.setColor (Color.cyan);
    	//frame.fillRect (0, 2000,1000, 100) ; 
	    frame.fillRect (0, 0,1000,2000) ; 
	   
	    frame.setColor(Color.cyan);
	    frame.setColor (Color.white);
	    frame.fillOval (480, 200, 160, 160);
	    frame.fillOval (420,350 , 280, 200);
	    frame.fillOval (360,500, 400, 240);
	     
	    frame.setColor(Color.YELLOW);
	    frame.fillOval(-50, -50, 200, 200);
	
	    frame.setColor (Color.black);    
	    frame.drawLine (680, 440,780,  548);
	    frame.drawLine (440, 440, 350, 548);
	    
	    frame.drawLine (455,220,648,220);
	    frame.fillRect (500,140, 113, 80);
	    frame.drawArc (550,310, 40, 20, 200, 160);
	    frame.fillOval (520, 250, 10, 10);
	    frame.fillOval (600, 250, 10, 10);
	    
	    frame.setColor (Color.blue);
	    frame.fillRect (0, 700,1000, 100) ; 
    }
               
  public static void main(String args[]) 
  {
      SNOWMAN frame = new SNOWMAN();
     // frame.getContentPane().setBackground (Color.cyan);
//      frame.addWindowListener(
//      new WindowAdapter()
//      {
//         public void windowClosing(WindowEvent we)
//         {
//            System.exit(0);
//         }
//      }
//      )
     // frame.setBackground(Color.CYAN);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setIconImage(new ImageIcon("icon.png.jpg").getImage());
    frame.setTitle("Snowman at the GUC!");
     frame.setResizable(false);
      frame.setSize(1000, 2000);
      frame.setVisible(true);
      
   

   }
}

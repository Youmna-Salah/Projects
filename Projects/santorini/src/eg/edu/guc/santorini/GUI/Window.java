package eg.edu.guc.santorini.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.BoardInterface;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Window  extends JFrame{
	private static final long serialVersionUID = 1L;
	protected boolean dragged;
	protected Piece piece;
	protected JLabel prevlabel;


	public Window() throws IOException {
		super();
		setSize(800, 600);
		setLocation(100, 100);
		getContentPane().setBackground(Color.BLUE);
		setLayout(new BorderLayout());
		final JPanel jPanel = new JPanel(new GridLayout(5,5));
		final JPanel display = new JPanel(new FlowLayout());
		final JLabel text =new JLabel();
		display.add(text);
		add(display,BorderLayout.SOUTH);
	
		text.setSize(800,100);
		jPanel.setSize(800, 600);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JFrame.setDefaultLookAndFeelDecorated(true);
		final JLabel[][] jLabel = new JLabel[5][5];
		final Border border = LineBorder.createGrayLineBorder();
		final Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
		for (int i = 0; i < jLabel.length; i++) {
			final int y=i;
			for (int j = 0; j < jLabel[i].length; j++) {
				final int x=j;
				jLabel[i][j]=new JLabel();
				final JLabel current=jLabel[i][j];
				jLabel[i][j].addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseDragged(MouseEvent e) {
						//						if ( getTileType(y,x).length() > 1) {
						//							current.setLocation((int)getMousePosition().getX()-75,(int)getMousePosition().getY()-75);
						//							dragged = true;
						//						}
					}
				});
				jLabel[i][j].addMouseListener(new MouseListener() {

					private Point localpoint;

					@Override
					public void mouseReleased(MouseEvent e) {
						text.setText(Adaptor.getTurn());
						if(dragged) {
							current.setLocation(localpoint);
							jPanel.revalidate();
							jPanel.repaint();
							((JLabel)e.getSource()).setIcon(getImage(Adaptor.getTileType(y,x),jPanel));
							ImageIcon icon = getImage(Adaptor.getTileType(y,x),jPanel);
							Point newp =new Point((int)e.getLocationOnScreen().getX()-100,(int)e.getLocationOnScreen().getY()-100);
							JLabel temp = (JLabel) jPanel.getComponentAt(newp);
							temp.setIcon(icon);
							jPanel.revalidate();
							jPanel.repaint();
							dragged = false;
						}
					}



					@Override
					public void mousePressed(MouseEvent e) {
						text.setText(Adaptor.getTurn());
//						localpoint = e.getLocationOnScreen();
//						if (Adaptor.getTileType(y,x).length() > 1) {
//							ArrayList<Location> temp = Adaptor.getPos(y, x);
//							for (int k = 0; k < temp.size(); k++) {
//								int y1 = temp.get(k).getY();
//								int x1 = temp.get(k).getX();
//								jLabel[y1][x1].setBorder(null);
//
//							}
//						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						if (Adaptor.getTileType(y,x).length() > 1) {
							ArrayList<Location> temp = Adaptor.getPos(y, x);
							text.setText(Adaptor.getTurn());
							for (int k = 0; k < temp.size(); k++) {
								int y1 = temp.get(k).getY();
								int x1 = temp.get(k).getX();
								jLabel[y1][x1].setBorder(null);
								text.setText(Adaptor.getTurn());

							}
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						
						if (Adaptor.getTileType(y,x).length() > 1) {
							ArrayList<Location> temp = Adaptor.getPos(y, x);
							for (int k = 0; k < temp.size(); k++) {
								int y1 = temp.get(k).getY();
								int x1 = temp.get(k).getX();
								if(Adaptor.can(y, x)) {
									jLabel[y1][x1].setBorder(redBorder);
								} else {
									jLabel[y1][x1].setBorder(border);
								}
							}
						}
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						//						if(e.getClickCount()==2) {
							//							if(board.canMove(piece, new Location(y,x))) {
						try {
							//text.setText(Adaptor.getTurn());
							ArrayList<Location> l =Adaptor.Clicked(y,x);
							if(l==null){
								
									JOptionPane.showMessageDialog(jPanel, "not ur turn","Invalid Move!",JOptionPane.WARNING_MESSAGE,new ImageIcon("i.jpg"));
								
							}
							else{
							if(l.size()==2){
								text.setText(Adaptor.getTurn());
								jLabel[l.get(0).getY()][l.get(0).getX()].setIcon(getImage(Adaptor.getTileType(l.get(0).getY(), l.get(0).getX()),jPanel));
								jLabel[l.get(1).getY()][l.get(1).getX()].setIcon(getImage(Adaptor.getTileType(y,x),jPanel));
							}
							if(l.size()==1) {
								text.setText(Adaptor.getTurn());
								jLabel[l.get(0).getY()][l.get(0).getX()].setIcon(getImage(Adaptor.getTileType(y, x),jPanel));
							}
							}
							
					
						} 
						catch (InvalidMoveException e1) {
							//			
							// TODO Auto-generated catch block
							
							JOptionPane.showMessageDialog(jPanel, "You Cannot Move","Invalid Move!",JOptionPane.WARNING_MESSAGE,new ImageIcon("i.jpg"));
							
							//						
							//				
						}
						catch(InvalidPlacementException e2) {
							
							JOptionPane.showMessageDialog(jPanel, "You Cannot Place","Invalid Move!",JOptionPane.WARNING_MESSAGE,null);	
						}
						//}
						//							if(board.canPlace(piece, new Location(y,x))) {
						//								try {
						//									System.out.println("tried to place");
						//									board.place(piece, new Location(y,x));
						//									piece=null;
						//								} catch (InvalidPlacementException e1) {
						//									// TODO Auto-generated catch block
						//									e1.printStackTrace();
						//								}
						//							}
						//current.setIcon(getImage(Adaptor.getTileType(y,x)));
						//	}
						//						else {
						//							if(e.getClickCount()==1 && piece==null) {
						//								prevlabel = current;
						//								piece = board.getPiece(y, x);
						//								current.setIcon(getImage(Adaptor.getTileType(y,x)));
						//							}
						//						}
//						if (Adaptor.getTileType(y,x).length() > 1) {
//							ArrayList<Location> temp = Adaptor.getPos(y, x);
//							for (int k = 0; k < temp.size(); k++) {
//								int y1 = temp.get(k).getY();
//								int x1 = temp.get(k).getX();
//								jLabel[y1][x1].setBorder(null);
//
//							}
//						}
					}


				});
				
				jLabel[i][j].setIcon(getImage(Adaptor.getTileType(y,x),jPanel));
				jLabel[i][j].setVisible(true);
				
				
				jPanel.add(jLabel[i][j]);
				jLabel[i][j].setLocation(i, j);
			}
		}
		text.setText(Adaptor.getTurn());
		add(jPanel);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static ImageIcon getImage(String s, Component jPanel) {

		if(s.equals("0")) {
			return new javax.swing.ImageIcon("TriTile1.jpg");
		} else {
			if(s.equals("1")) {
				return new javax.swing.ImageIcon("Layer1.png");
			} else {
				if(s.equals("2")) {
					return new javax.swing.ImageIcon("Layer2.png");
				} else {
					if(s.equals("3")) {
						return new javax.swing.ImageIcon("Layer3.png");
					} else {
						if(s.equals("4")) {
							return new javax.swing.ImageIcon("not.gif");
						}
					}
				}
			}
		}
		if(s.equals("0C1") || s.equals("0C2")) {
			return new javax.swing.ImageIcon("cube.jpg");
		} else {
			if(s.equals("1C1") || s.equals("1C2")) {
				return new javax.swing.ImageIcon("cube1.jpg");
			} else {
				if(s.equals("2C1") || s.equals("2C2")) {
					return new javax.swing.ImageIcon("cube2.jpg");
				} else {
					if(s.equals("3C1") || s.equals("3C2")) {
						//JOptionPane.showMessageDialog(jPanel, Adaptor.getWin()+" Won!!","Winner is!",JOptionPane.WARNING_MESSAGE,null);
						return new javax.swing.ImageIcon("cube3.jpg");
					}
				}
			}
		}
		if(s.equals("0P1") || s.equals("0P2")) {
			return new javax.swing.ImageIcon("crystal pyramid.png");
		} else {
			if(s.equals("1P1") || s.equals("1P2")) {
				return new javax.swing.ImageIcon("pyramid1.jpg");
			} else {
				if(s.equals("2P1") || s.equals("2P2")) {
					return new javax.swing.ImageIcon("pyramid2.jpg");
				} else {
					if(s.equals("3P1") || s.equals("3P2")) {
						//JOptionPane.showMessageDialog(jPanel, Adaptor.getWin()+" Won!!","Winner is!",JOptionPane.WARNING_MESSAGE,null);	
						return new javax.swing.ImageIcon("pyramid3.jpg");
					}
				}
			}
		}
		return new javax.swing.ImageIcon("noentry.jpg");
	}
	//	private String getTileType(int y, int x) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	public static void main(String[] args) throws IOException {
		//		Player play1 = new Player("Salman",1);
		//		Player play2 = new Player("EKS",2);
		//		Board board = new Board(play1, play2);
		new Adaptor("salman",1,"ahmed",2);
	}
}

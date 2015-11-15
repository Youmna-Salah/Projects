import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.io.*;
import javax.sound.sampled.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Music extends JFrame {

	JButton Play, pause, mute, stop, repeat, browse;
	JLabel L;
	JTextField text;
	Container contentPane;
	JFileChooser browseFC;
	Choose frame;
	Clip Clip ;  
	AudioInputStream audio;
	Boolean mutted,stopped,replay;


	public Music() {
 replay=false;
		this.setResizable(false);
		this.setSize(1000, 2000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		L = new JLabel(new ImageIcon("blue 2.jpg"));
		this.setContentPane(L);
		contentPane = this.getContentPane();

		FlowLayout b = new FlowLayout(FlowLayout.CENTER, 10, 150);
		contentPane.setLayout(b);

		text = new javax.swing.JTextField("File path", 70);
		text.setAutoscrolls(true);
		text.addActionListener(new ActionListener() {
			String Path = "";
			public void actionPerformed(ActionEvent arg0) {
				Path += text.getText();
			}
		});

		browseFC = new JFileChooser();
		browseFC.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		browse = new JButton();
		browse.setIcon(new ImageIcon("open.png"));
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				frame = new Choose();
				frame.setSize(400, 300);
				frame.setVisible(false);
				frame.setLocation(200, 100);
				text.setText(Choose.fileName);

			} } 
				);



		repeat = new JButton();
		repeat.setIcon(new ImageIcon("repeat.png"));
		repeat.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				replay = true;
					try {
						if (Clip!=null&&Clip.isRunning() == false) {
						Clip = AudioSystem.getClip();
						audio = AudioSystem.getAudioInputStream(new File(Choose.fileName));
						Clip.open(audio);
						Clip.start();
						stopped = false;
						
						}
						else {
							while(Clip.isRunning()){
							
							}
							Clip = AudioSystem.getClip();
							audio = AudioSystem.getAudioInputStream(new File(Choose.fileName));
							Clip.open(audio);
							Clip.start();
						}
					
					}

					catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(frame,
								"please select the file that "
										+ "you want to play again !!",
										"replay error",
										JOptionPane.INFORMATION_MESSAGE,
										new  ImageIcon("alert.png"));
					}

				
			}
		});

		stop = new JButton();
		stop.setIcon(new ImageIcon("stop.png"));
		stop.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Play.setEnabled(true);
				if (!(Clip == null)) {
					Clip.stop();	
					stopped =true;
				}
				else {
					JOptionPane.showMessageDialog(frame,
							"Please select an audio file "
									+ "that you want to Stop!!",
									"Stop error",
									JOptionPane.INFORMATION_MESSAGE,
									new  ImageIcon("open.png"));
				}

			} }
				);


		Play = new JButton();
		Play.setIcon(new ImageIcon("Play.png"));
		Play.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				
				try {
					stopped = false;
					Clip = AudioSystem.getClip();
					audio = AudioSystem.getAudioInputStream(
							new File(Choose.fileName));
	
					Clip.open(audio);
					Clip.start();
				
					}
				catch (Exception e) {

					Play.setEnabled(true);
					JOptionPane.showMessageDialog(frame,
							"Please select an audio file!!",
							"Play error",
							JOptionPane.INFORMATION_MESSAGE,
							new  ImageIcon("open.png"));

				}
	     
			

			}}
			);
		

		pause = new JButton(new ImageIcon("Pause.png"));
		pause.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
			if(Clip!=null && !stopped){
					
					if (evt.getClickCount() == 1) {
						
						pause.setEnabled(false);
						Clip.stop();
					}
					else {
					
						pause.setEnabled(true);
						Clip.start();
					}
			}

			else{
					JOptionPane.showMessageDialog(frame,
							"Please select an audio file!!",
							"Play error",
							JOptionPane.INFORMATION_MESSAGE,
							new  ImageIcon("open.png"));
				}
			}

		});

		mute = new JButton(new ImageIcon("mute.png"));
		mute.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				if (Clip == null){
					JOptionPane.showMessageDialog(frame,
							"Please select an audio file!!",
							"Play error",
							JOptionPane.INFORMATION_MESSAGE,
							new  ImageIcon("open.png"));

				}
				else {
					BooleanControl muteControl = (BooleanControl) Clip
							.getControl(BooleanControl.Type.MUTE);

					if (evt.getClickCount() == 1) {
						mutted = true;
						mute.setEnabled(false);
					}
					else {
						mutted = false;
						mute.setEnabled(true);
					}
					if (muteControl != null) {
						if (mutted) {
							muteControl.setValue(true);
						}
						else {
							muteControl.setValue(false);
						}
					}
				}
			}
		});


		Border emptyBorder = BorderFactory.createEmptyBorder();
		stop.setBorder(emptyBorder);
		pause.setBorder(emptyBorder);
		Play.setBorder(emptyBorder);
		repeat.setBorder(emptyBorder);
		mute.setBorder(emptyBorder);
		browse.setBorder(emptyBorder);

		stop.setContentAreaFilled(false);
		pause.setContentAreaFilled(false);
		Play.setContentAreaFilled(false);
		repeat.setContentAreaFilled(false);
		mute.setContentAreaFilled(false);
		browse.setContentAreaFilled(false);

		contentPane.add(text);
		contentPane.add(browse);
		contentPane.add(stop);
		contentPane.add(pause);
		contentPane.add(Play);
		contentPane.add(repeat);
		contentPane.add(mute);






		this.setSize(1000, 2000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[]args) {
		new Music();
	}
}

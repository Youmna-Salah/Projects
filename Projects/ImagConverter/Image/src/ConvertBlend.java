import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import  java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class ConvertBlend extends JFrame {



	JButton browse1,browse2,convert;
	JLabel L;
	JTextField text1,text2;
	Container contentPane;
	JFileChooser browseFC;
	Choose frame1 ,frame2;
	
	ArrayList <String> Images=null;



	public  ConvertBlend(){

		//this.setResizable(false);
		this.setSize(1500, 2500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		L = new JLabel(new ImageIcon("From-the-dreams.jpg"));
		this.setContentPane(L);
		contentPane = this.getContentPane();

		FlowLayout b = new FlowLayout(FlowLayout.CENTER, 20, 100);
		contentPane.setLayout(b);

		Images = new ArrayList<String>();
		text1 = new javax.swing.JTextField("File path", 70);
		text1.setAutoscrolls(true);
		text1.addActionListener(new ActionListener() {
			String Path = "";
			public void actionPerformed(ActionEvent arg0) {
				Path += text1.getText();
			}
		});

		text2 = new javax.swing.JTextField("File path", 70);	
		text2.setAutoscrolls(true);
		text2.addActionListener(new ActionListener() {
			String Path = "";
			public void actionPerformed(ActionEvent arg0) {
				Path += text2.getText();
			}
		});

		browseFC = new JFileChooser();
		browseFC.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		browse1 = new JButton();
		browse1.setIcon(new ImageIcon("browse2.png"));
		browse1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				frame1 = new Choose();
				frame1.setSize(400, 300);
				frame1.setVisible(false);
				frame1.setLocation(200, 100);
				text1.setText(frame1.getFileName());

			} } 
				);

		browse2 = new JButton();
		//browse2.setVisible(false);
		browse2.setIcon(new ImageIcon("browse2.png"));
		browse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				frame2 = new Choose();
				frame2.setSize(400, 300);
				frame2.setVisible(false);
				frame2.setLocation(200, 100);
				text2.setText(frame2.getFileName());
	        	

			} } 
				);
		convert = new JButton();
		convert.setIcon(new ImageIcon("edit.png"));
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


				BufferedImage inputFile,inputFile2 = null;
				try {
					inputFile = ImageIO.read(new File(frame1.getFileName()));
					Images.add(frame1.getFileName());
					BufferedImage negate = new BufferedImage (inputFile.getWidth(), inputFile.getHeight(),
							BufferedImage.TYPE_INT_RGB);
				

					inputFile2 = ImageIO.read(new File(frame2.getFileName()));

					Images.add(frame2.getFileName());

					if (inputFile == null)
						throw new NullPointerException ("bi1 is null");

					if (inputFile2 == null)
						throw new NullPointerException ("bi2 is null");

					int width = inputFile.getWidth ();
					if (width != inputFile2.getWidth ())
						throw new IllegalArgumentException ("widths not equal");

					int height = inputFile.getHeight ();
					if (height != inputFile2.getHeight ())
						throw new IllegalArgumentException ("heights not equal");

					BufferedImage blend = new BufferedImage (width, height,
							BufferedImage.TYPE_INT_RGB);
					int [] rgbim1 = new int [width];
					int [] rgbim2 = new int [width];
					int [] rgbim3 = new int [width];

					for (int row = 0; row < height; row++)
					{
						inputFile.getRGB (0, row, width, 1, rgbim1, 0, width);
						inputFile2.getRGB (0, row, width, 1, rgbim2, 0, width);

						for (int col = 0; col < width; col++)
						{
							int rgb1 = rgbim1 [col];
							int r1 = (rgb1 >> 16) & 255;
							int g1 = (rgb1 >> 8) & 255;
							int b1 = rgb1 & 255;

							int rgb2 = rgbim2 [col];
							int r2 = (rgb2 >> 16) & 255;
							int g2 = (rgb2 >> 8) & 255;
							int b2 = rgb2 & 255;

							double weight = 0.8;
							int r3 = (int) (r1*weight+r2*(1.0-weight));
							int g3 = (int) (g1*weight+g2*(1.0-weight));
							int b3 = (int) (b1*weight+b2*(1.0-weight));
							rgbim3 [col] = (r3 << 16) | (g3 << 8) | b3;
						}

						blend.setRGB (0, row, width, 1, rgbim3, 0, width);
					}
					File outputFile2 = new File(frame2.getImageName()+"blend"+frame2.getType());
					ImageIO.write(blend, "png", outputFile2);

					Images.add(outputFile2.getPath());
					for (int x = 0; x < inputFile.getWidth(); x++) {
						for (int y = 0; y < inputFile.getHeight(); y++) {
							int currentColor = inputFile.getRGB(x, y);
							Color newColour = new Color(currentColor, true);
							newColour = new Color(255 - newColour.getRed(),
									255 - newColour.getGreen(),
									255 - newColour.getBlue());
							negate.setRGB(x, y, newColour.getRGB());
						}
					}


					File outputFile = new File(frame1.getImageName()+"invert"+frame1.getType());
					ImageIO.write(negate, "png", outputFile);

					Images.add(outputFile.getPath());
					new Display(Images);
				}

				catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane,
							"please select the images that "
									+ "you want to blend and negate again !!",
									"conversion error",
									JOptionPane.INFORMATION_MESSAGE,
									new  ImageIcon("alert.png"));
				}  
			}}	);




		Border emptyBorder = BorderFactory.createEmptyBorder();
		browse1.setBorder(emptyBorder);
		browse2.setBorder(emptyBorder);
		convert.setBorder(emptyBorder);


		browse1.setContentAreaFilled(false);
		browse2.setContentAreaFilled(false);
		convert.setContentAreaFilled(false);
		contentPane.add(text1);
		contentPane.add(browse1);
		contentPane.add(text2);
		contentPane.add(browse2);
		contentPane.add(convert);

        this.setTitle("Image Processing :)");
		this.setSize(1000, 2000);
		this.setVisible(true);
		setIconImage(new ImageIcon("icon.png.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[]args) {
		new ConvertBlend();
	}


}


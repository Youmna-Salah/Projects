import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class Choose extends JFrame {
    public JFileChooser choose;
	public static String fileName;
	
	public Choose() {
		choose = new JFileChooser();
		int r = choose.showOpenDialog(new JFrame());
		if (r == JFileChooser.APPROVE_OPTION) {
			fileName = choose.getSelectedFile().getPath();
		}
	} 

}

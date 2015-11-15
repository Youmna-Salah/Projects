import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class Choose extends JFrame {
	public JFileChooser choose=null;
	private String fileName;
	private String ImageName;
	private String type;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getImageName() {
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
	}
	public String Type() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Choose() {
		choose = new JFileChooser();
		int r = choose.showOpenDialog(new JFrame());
		if (r == JFileChooser.APPROVE_OPTION) {
			fileName = choose.getSelectedFile().getPath();
			if(fileName.contains(".png")){
				ImageName = fileName.replaceFirst(".png","" );
				type =".png";
			}
			else 
				if (fileName.contains(".jpeg")){
					ImageName = fileName.replaceFirst(".jpeg","" );
					type =".jpeg";}
				else 
					if (fileName.contains(".jpg")){
						ImageName = fileName.replaceFirst(".jpg","" );
						type =".jpg";
					}
		}
	} 

}

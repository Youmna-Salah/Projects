import java.util.Scanner;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;


public class Converter {
	Scanner sc = new Scanner(System.in);
	double Min ,Max,Red,Green,Blue,Hue,Saturation,Value;
	public Converter(){
		Min = 0.0;
		Max = 0.0;
		Red = 0.0;
		Green =0.0;
		Blue = 0.0;
		Hue = 0.0;
		Saturation = 0.0;
		Value = 0.0;
	}

	public String RGBTHSV(double r,double g,double b){
		r /= 255;
		g /= 255;
		b  /=255;
		String HSV ="[ ";
		Max = Math.max(Math.max(r, g),b);
		Min = Math.min(Math.min(r, g),b);
		Value = Max ;
		if (Max == Min) {
			HSV += "UNDEFINED ,";
		}
		else {

			if (Max == r){
				if (g < b)
				{
					Hue = 60*((g-b)/(Max-Min));
					Hue += 360;
				}
				else {
					Hue = 60*((g-b)/(Max-Min));
				}
			}
			else
			{
				if(Max == g)
				{
					Hue = 60*((b-r)/(Max-Min));
					Hue+= 120;
				}
				else
				{
					Hue = 60*((r-g)/(Max-Min));
					Hue += 240;
				}
			}
			HSV+= Hue*100+" , ";
		}
		if (Max == 0) {
			Saturation = 0;
		}
		else {
			Saturation =(Max-Min)/Max;
		}
		HSV += Saturation*100+" , "+Value*100+" ]";
		return HSV;
	}
	public String HSVTORGB(double h,double s,double v){
		h /=100;
		s /=100;
		v /=100;
		int hInt =(int)(h/60.0);
		String HSV ="";
		double f,p,q,t;
		f = (h/60) - hInt;
		p = v*(1-s)*255;
		q = v*(1-(f*s))*255;
		t = v*(1-(1-f)*s)*255;
		v *=255;
		switch(hInt){
		case(0):HSV ="[ "+ v+" , "+t+", " +p+" ]";return HSV;
		case(1):HSV ="[ "+ q+" , "+v+", " +p+" ]";return HSV;
		case(2):HSV ="[ "+ q+" , "+v+", " +t+" ]";return HSV;
		case(3):HSV ="[ "+ p+" , "+q+", " +v+" ]";return HSV;
		case(4):HSV ="[ "+ t+" , "+p+", " +v+" ]";return HSV;
		case(5):HSV ="[ "+ v+" , "+p+", " +q+" ]";return HSV;
		default:return"UNDEFINED";
		}
	}

	public static void main(String args[]){
		Converter color =new Converter();
		Scanner sc = new Scanner(System.in);
		while(true){
		System.out.println("Please enter 1 if you want to convert from RGB to HSV "+"\n"
				+ "OR 2 if You want to convert from HSV to RGB"+"\n");
		int read = sc.nextInt();
		if (read == 1) {
			double r,g,b ;
			System.out.println("Please enter the Red component");
			r = sc.nextDouble();
			System.out.println("Please enter the Green component");
			g = sc.nextDouble();
			System.out.println("Please enter the Blue component");
			b = sc.nextDouble();
			System.out.println("This colour [ "+r+" , "+
					g+" , "+b+" ]"+"in RGB is equivilant to"
					+ color.RGBTHSV(r,g,b)+"in HSV space.");
		}
		else{
			if (read == 2){
				double h,s,v ;
				System.out.println("Please enter the Hue component");
				h = sc.nextDouble();
				System.out.println("Please enter the Saturation component");
				s = sc.nextDouble();
				System.out.println("Please enter the Value component");
				v = sc.nextDouble();
				System.out.println("This colour [ "+h+" , "+
						s+" , "+v+" ]"+"in HSV is equivilant to"
						+ color.HSVTORGB(h,s,v)+"in RGB space."+"\n"+"\n");
			}
			else {
				System.out.println("Please enter a valid number");
			}
		}
	}
	}





}

package eg.edu.guc.santorini.utilities;

public class Location /*implements Comparable<Location>*/ {
	
	private int y;
	private int x;
	
	public Location(int y , int x) {
		this.setY(y);
		this.setX(x);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Location temp) {
		return (this.y==temp.y && this.x==temp.x);
	}
	
//	public int compareTo(Location temp) {
//		if(this.y==temp.y) {
//			if(this.x==temp.x) {
//				return 0;
//			}
//			if(this.x>temp.x) {
//				return 1;
//			}
//			else {
//				return -1;
//			}
//		}
//		if(this.y>temp.y) {
//			return 1;
//		}
//		else {
//			return -1;
//		}
//	}


}

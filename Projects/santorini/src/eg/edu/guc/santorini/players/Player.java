package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;

public class Player {
	
	@SuppressWarnings("unused")
	private String name;
	private int type;
	private Piece p1;
	private Piece p2;
	
	public Player(){
		name="";
		type=0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player(String s , int n) {
		name=s;
		type=n;
		if(type==1) {
			p1=new Cube();
			p2=new Cube();
		}
		else {
			p1=new Pyramid();
			p2=new Pyramid();
		}
//		if (type==1) {
//			if (name=="P1") {
//				p1 = new Cube(0,0);
//				p2 = new Cube(4,1);
//			}
//			else {
//				p1 = new Cube(0,3);
//				p2 = new Cube(4,4);
//			}
//		}
//		if (type==2) {
//			if (name=="P1") {
//				p1 = new Pyramid(0,0);
//				p2 = new Pyramid(4,1);
//			}
//			else {
//				p1 = new Pyramid(0,3);
//				p2 = new Pyramid(4,4);
//			}
//		}
		
	}

	public void setP1(Piece p1) {
		this.p1 = p1;
	}

	public void setP2(Piece p2) {
		this.p2 = p2;
	}

	public int getMoves() {
		return p1.getMoves()+p2.getMoves();
	}


	public Piece getT1() {
		return p1;
	}

	public Piece getT2() {
		return p2;
	}

}

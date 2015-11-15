///Need a switch to interpret the register number to register name  

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterFile {
	final boolean[] zero = new boolean[] { false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false };
	boolean[] at;
	boolean[] v0;
	boolean[] v1;
	boolean[] a0;
	boolean[] a1;
	boolean[] a2;
	boolean[] a3;
	boolean[] t0;
	boolean[] t1;
	boolean[] t2;
	boolean[] t3;
	boolean[] t4;
	boolean[] t5;
	boolean[] t6;
	boolean[] t7;
	boolean[] t8;
	boolean[] t9;
	boolean[] s0;
	boolean[] s1;
	boolean[] s2;
	boolean[] s3;
	boolean[] s4;
	boolean[] s5;
	boolean[] s6;
	boolean[] s7;
	boolean[] k0;
	boolean[] k1;
	boolean[] gp;
	boolean[] sp;
	boolean[] fp;
	boolean[] ra;
      
	public RegisterFile() {
		at = new boolean[32];
		v0 = new boolean[32];
		v1 = new boolean[32];
		a0 = new boolean[32];
		a1 = new boolean[32];
		a2 = new boolean[32];
		a3 = new boolean[32];
		t0 = new boolean[32];
		t1 = new boolean[32];
		t2 = new boolean[32];
		t3 = new boolean[32];
		t4 = new boolean[32];
		t5 = new boolean[32];
		t6 = new boolean[32];
		t7 = new boolean[32];
		s0 = new boolean[32];
		s1 = new boolean[32];
		s2 = new boolean[32];
		s3 = new boolean[32];
		s4 = new boolean[32];
		s5 = new boolean[32];
		s6 = new boolean[32];
		s7 = new boolean[32];
		t8 = new boolean[32];
		t9 = new boolean[32];
		k0 = new boolean[32];
		k1 = new boolean[32];
		gp = new boolean[32];
		sp = new boolean[32];
		fp = new boolean[32];
		ra = new boolean[32];

		Arrays.fill(at, false);
		Arrays.fill(v0, false);
		Arrays.fill(v1, false);
		Arrays.fill(a0, false);
		Arrays.fill(a1, false);
		Arrays.fill(a2, false);
		Arrays.fill(a3, false);
		Arrays.fill(t0, false);
		Arrays.fill(t1, false);
		Arrays.fill(t2, false);
		Arrays.fill(t3, false);
		Arrays.fill(t4, false);
		Arrays.fill(t5, false);
		Arrays.fill(t6, false);
		Arrays.fill(t7, false);
		Arrays.fill(s0, false);
		Arrays.fill(s1, false);
		Arrays.fill(s2, false);
		Arrays.fill(s3, false);
		Arrays.fill(s4, false);
		Arrays.fill(s5, false);
		Arrays.fill(s6, false);
		Arrays.fill(s7, false);
		Arrays.fill(t8, false);
		Arrays.fill(t9, false);
		Arrays.fill(k0, false);
		Arrays.fill(k1, false);
		Arrays.fill(gp, false);
		Arrays.fill(sp, false);
		Arrays.fill(fp, false);
		Arrays.fill(ra, false);
	}
	
	void copy(RegisterFile source) {
		for(int i = 0; i < 32; at[i] = source.at[i], i++);
		for(int i = 0; i < 32; v0[i] = source.v0[i], i++);
		for(int i = 0; i < 32; v1[i] = source.v1[i], i++);
		for(int i = 0; i < 32; a0[i] = source.a0[i], i++);
		for(int i = 0; i < 32; a1[i] = source.a1[i], i++);
		for(int i = 0; i < 32; a2[i] = source.a2[i], i++);
		for(int i = 0; i < 32; a3[i] = source.a3[i], i++);
		for(int i = 0; i < 32; t0[i] = source.t0[i], i++);
		for(int i = 0; i < 32; t1[i] = source.t1[i], i++);
		for(int i = 0; i < 32; t2[i] = source.t2[i], i++);
		for(int i = 0; i < 32; t3[i] = source.t3[i], i++);
		for(int i = 0; i < 32; t4[i] = source.t4[i], i++);
		for(int i = 0; i < 32; t5[i] = source.t5[i], i++);
		for(int i = 0; i < 32; t6[i] = source.t6[i], i++);
		for(int i = 0; i < 32; t7[i] = source.t7[i], i++);
		for(int i = 0; i < 32; s0[i] = source.s0[i], i++);
		for(int i = 0; i < 32; s1[i] = source.s1[i], i++);
		for(int i = 0; i < 32; s2[i] = source.s2[i], i++);
		for(int i = 0; i < 32; s3[i] = source.s3[i], i++);
		for(int i = 0; i < 32; s4[i] = source.s4[i], i++);
		for(int i = 0; i < 32; s5[i] = source.s5[i], i++);
		for(int i = 0; i < 32; s6[i] = source.s6[i], i++);
		for(int i = 0; i < 32; s7[i] = source.s7[i], i++);
		for(int i = 0; i < 32; t8[i] = source.t8[i], i++);
		for(int i = 0; i < 32; t9[i] = source.t9[i], i++);
		for(int i = 0; i < 32; k0[i] = source.k0[i], i++);
		for(int i = 0; i < 32; k1[i] = source.k1[i], i++);
		for(int i = 0; i < 32; gp[i] = source.gp[i], i++);
		for(int i = 0; i < 32; sp[i] = source.sp[i], i++);
		for(int i = 0; i < 32; fp[i] = source.fp[i], i++);
		for(int i = 0; i < 32; ra[i] = source.ra[i], i++);
	}
	public static void copy(boolean[] a,boolean[] b){
		for(int i = 0; i < 32; b[i] =a[i], i++);

	}
	public void CopyWithRegNumber(int i , boolean[] b){
		switch(i){
		case 0:copy(zero,b);
		case 1:copy(at,b);
		case 2:copy(v0,b);
		case 3:copy(v1,b);
		case 4:copy(a0,b);
		case 5:copy(a1,b);
		case 6:copy(a2,b);
		case 7:copy(a3,b);
		case 8:copy(t0,b);
		case 9:copy(t1,b);
		case 10:copy(t2,b);
		case 11:copy(t3,b);
		case 12:copy(t4,b);
		case 13:copy(t5,b);
		case 14:copy(t6,b);
		case 15:copy(t7,b);
		case 16:copy(t8,b);
		case 17:copy(t9,b);
		case 18:copy(s0,b);
		case 19:copy(s1,b);
		case 20:copy(s2,b);
		case 21:copy(s3,b);
		case 22:copy(s4,b);
		case 23:copy(s5,b);
		case 24:copy(s6,b);
		case 25:copy(s7,b);
		case 26:copy(k0,b);
		case 27:copy(k1,b);
		case 28:copy(gp,b);
		case 29:copy(sp,b);
		case 30:copy(fp,b);
		case 31:copy(ra,b);

		}
		}
		public boolean[] RegVlaue(int i){
			switch(i){
			case 0:return zero;
			case 1:return at;
			case 2:return v0;
			case 3:return v1;
			case 4:return a0;
			case 5:return a1;
			case 6:return a2;
			case 7:return a3;
			case 8:return t0;
			case 9:return t1;
			case 10:return t2;
			case 11:return t3;
			case 12:return t4;
			case 13:return t5;
			case 14:return t6;
			case 15:return t7;
			case 16:return t8;
			case 17:return t9;
			case 18:return s0;
			case 19:return s1;
			case 20:return s2;
			case 21:return s3;
			case 22:return s4;
			case 23:return s5;
			case 24:return s6;
			case 25:return s7;
			case 26:return k0;
			case 27:return k1;
			case 28:return gp;
			case 29:return sp;
			case 30:return fp;
			case 31:return ra;
			default:return zero;

			}    
	}
	
	ArrayList<boolean[]> getAll() {
		ArrayList<boolean[]> all = new ArrayList<boolean[]>();
		all.add(zero);
		all.add(at);
		all.add(v0);
		all.add(v1);
		all.add(a0);
		all.add(a1);
		all.add(a2);
		all.add(a3);
		all.add(t0);
		all.add(t1);
		all.add(t2);
		all.add(t3);
		all.add(t4);
		all.add(t5);
		all.add(t6);
		all.add(t7);
		all.add(s0);
		all.add(s1);
		all.add(s2);
		all.add(s3);
		all.add(s4);
		all.add(s5);
		all.add(s6);
		all.add(s7);
		all.add(t8);
		all.add(t9);
		all.add(k0);
		all.add(k1);
		all.add(gp);
		all.add(sp);
		all.add(fp);
		all.add(ra);

		return all;
	}
	
}

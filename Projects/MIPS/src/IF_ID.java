
public class IF_ID {
	boolean[] reg1;
	boolean[] reg2;
	boolean[] sign_extend;
	boolean[] ID_EX1;
	boolean[] ID_EX2;
	//I added these variables might be needed in further process
	boolean[] OpCode;
	
	public IF_ID(boolean[] instruction) {
		if(instruction.length != 32) {
			System.out.println("Instruction is not correct, NOTHING IS ISTANTIATED");
			return;
		}
		this.reg1 = subset(21,25,instruction);
		this.reg2 = subset(16,20,instruction);
		this.sign_extend = subset(0,15,instruction);
		this.ID_EX1 = subset(16,20,instruction);
		this.ID_EX2 = subset(11,15,instruction);
		this.OpCode = subset(31,26,instruction);
	}
	public IF_ID(){}
	public static boolean[] subset(int start, int end, boolean [] array) {
		boolean[] result = new boolean[end-start+1];
		for(int i = 0; i< end-start+1 ;i++) {
			result[i] = array[start+i];
		}
		return result;
	}
	
	public String toString(boolean[] array) {
		String result = "";
		for(int i = 0; i < array.length;i++) {
			result+=array[i]+", ";
		}
		return result;
	}

}

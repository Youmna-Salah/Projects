import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/* Class name is up for debate
 * Variable names are up for debate
 * Variable types are up for debate
 * Whole structure is up for debate
 */

public class Circuit {

	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	// Variables
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

	// Initialized once
	HashMap<String, boolean[]> instructionmemory;

	// Input
	// Phase 1 input
	boolean[] pc_i; // Program Counter
	// Phase 2 input
	boolean[] ifd_i; // Instruction Fetch/Decode Register
	// Phase 3 input
	boolean[] idex_i; // Instruction Decode/Execute Register
	RegisterFile registerfile_i; // Register File (32 registers)
	// Phase 4 input
	boolean[] exmem_i; // Execute/Memory Register
	// Phase 5 input
	boolean[] memwb_i; // Memory/Writeback Register
	HashMap<String, boolean[]> datamemory_i; // Wad7a

	// Output
	// Phase 1 output
	boolean[] ifd_o;
	// Phase 2 output
	boolean[] idex_o;
	// Phase 3 output
	boolean[] exmem_o;
	boolean[] pc_o;
	// Phase 4 output
	boolean[] memwb_o;
	HashMap<String, boolean[]> datamemory_o;
	// Phase 5 output
	RegisterFile registerfile_o;
	boolean halted;
	HashSet<String> updatedKeys;

	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	// Constructor
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

	Circuit() { // Extra 1
		// Create external registers // Implement
		// Create register files
		registerfile_o = new RegisterFile();
		registerfile_i = new RegisterFile();
		// Create memory
		instructionmemory = new HashMap<String, boolean[]>();
		datamemory_o = new HashMap<String, boolean[]>();
		datamemory_i = new HashMap<String, boolean[]>();
		// Create other
		updatedKeys = new HashSet<String>();
	}
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	// Internal methods
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	
	// Initialization methods
	void assembleInstructions(String filename) { // Extra 2
		// Implement
	}
	
	void assembleData(String filename) { // Extra 3
		// Implement
	}
	
	// Iterated methods
	void fetch() { // Phase 1
		// Implement
	}
	int fromTo(boolean[] fetchedInst,int from ,int to){
		String result = "";
		for(int i = from ;i<fetchedInst.length || i < to;i++){
			if(fetchedInst[i])
			result+="1";
			else
				result+="0";
		}
		return Integer.parseInt(result);
	}

	static int fromBinaryToDecimal(int n){
		int i = n;
		int j = 0;
		int result =0;
		while(i != 0){
			j++;
			i = i/10;
			if(i == 1){
				result +=Math.pow(2, j);
			}
		}
		return result;
	}
/*
 * Arithmetic: add, addi, sub,00,08,00
 * Load/Store: lw, lb, lbu, sw, sb, lui ,23,,24,2B,28,0F
 * Logic: sll, srl, and, nor ,00,00,00,00
 * Control flow: beq, bne, j, jal, jr ,04,05,02,03,00
 * Comparison: slt, sltu 00,00
 * control signals aluSrc ,aluOpcode,registerDestination
 * branch , registerWrite,memoryRead,memoryWrite
 */
	void decode(Object o) { // Phase 2
		// Implement
		/* 1- know the instruction from last 6 bits 31-26
		 * 2- determine the value of the rs ,rt ,rd ,shamt case r format
		 * if store/load/branch determine the value of rs ,rt , jump address
		 * i format rs ,rt,constant
		 */
		ID_IE decode = new ID_IE(o);
		int rs,rt;
		//know the instruction from last 6 bits 31-26
		char type = getFormat(fromTo(decode.OpCode,0,decode.OpCode.length));
		//does include jump reg
		if(type == 'r')
		{
			 rs = fromBinaryToDecimal(fromTo(decode.reg1,0,decode.reg1.length));
			 rt = fromBinaryToDecimal(fromTo(decode.reg2,0,decode.reg2.length));
			decode.ReadData1 = registerfile_i.RegVlaue(rs);
			decode.readData2 = registerfile_i.RegVlaue(rt);
			decode.RegDst[0] = true;
			decode.RegDst[1] = false;
			decode.RegDst[2] = false;
			decode.RegDst[3] = false;
			decode.RegWrite = true;
			decode.AluOp[1] = true;
			decode.AluOp[0] = false;
			
		}
		else if(type == 'l') 
		{
			rt = fromBinaryToDecimal(fromTo(decode.reg2,0,decode.reg2.length));
			decode.readData2 =registerfile_i.RegVlaue(rt);
			decode.AluSrc = true;
			decode.RegWrite = true;
			decode.MemRead = true;
			
		}
		else if (type == 's'){
			rt = fromBinaryToDecimal(fromTo(decode.reg2,0,decode.reg2.length));
			decode.readData2 =registerfile_i.RegVlaue(rt);
			decode.AluSrc = true;
			decode.MemWrite = true;
		}
		//jump and branch in progress 
		else if (type == 'b'){
			 rs = fromBinaryToDecimal(fromTo(decode.reg1,0,decode.reg1.length));
			 rt = fromBinaryToDecimal(fromTo(decode.reg2,0,decode.reg2.length));
			decode.readData2 = registerfile_i.RegVlaue(rt);
			decode.ReadData1 = registerfile_i.RegVlaue(rs);
			decode.Branch = true;
			decode.AluOp[0] = true;
			decode.AluOp[1] = false;
		}
		else if (type == 'j') {
			decode.jump = true;
		//	decode.AluSrc =true; // not sure
		}
		//jump and link
		else if(type == 'l'){
			decode.RegDst[0] = false;
			decode.RegDst[0] = true;
			decode.RegDst[0] = false;
			decode.RegDst[0] = false;
			decode.RegWrite = true;
			decode.jump =true;
			//	decode.AluSrc =true; // not sure
			
		}
		else if (type == 'i'){
			rs = fromBinaryToDecimal(fromTo(decode.reg1,0,decode.reg1.length));
			decode.readData2 = registerfile_i.RegVlaue(rs);
			//decode.immediateValue = signExtend(decode.immediateValue);
			decode.AluSrc = true;
			decode.RegWrite = true;
		}
		boolean[] ra = new boolean[4];
		for(int i = 0 ;i<4;ra[i]=true,i++);
		decode.writeReg = MUX3_1(decode.ID_EX1,decode.ID_EX2,ra, decode.RegDst);
	}
	public boolean[] signExtend(boolean[] a){
		boolean[] b = new boolean[32];
		for(int i = 0; i < 16; b[i+16] =a[i], i++);
            return b;
	}
	public boolean[] MUX3_1(boolean[] a, boolean[] b ,boolean[] c, boolean[] slct){
	if(!slct[0]){
		if(!slct[1])
			return a;
		else
			return b;
	}
	else{
		if(!slct[1])
			return c;
		else {
			return null;
		}
	}
	}
	char getFormat(int Opcode){
		if(Opcode == 000000){
			return 'r';
		}
		else 
			if (Opcode == 100101 || Opcode == 100011 || Opcode ==100100) return 'l';
		else  
			if(Opcode == 001000 || Opcode == 001111) return 'i';
		else
			if(Opcode == 000010 ) return 'j';
			else if ( Opcode == 000011) return 'l';

		else return 'm';
	}
	void execute() { // Phase 3
		// Implement
	}
	
	void memory() { // Phase 4
		// Implement
	}
	
	void writeback() { // Phase 5
		// Implement
	}
	
	void updateRegisters() { // Extra 4
		// Implement
	}
	

	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	// Interface methods
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	
	// @Done
	void init(String instructionfilename, String datafilename) {
		assembleInstructions(instructionfilename);
		assembleData(datafilename);
	}
	
	// @Done
	void tick() {
		fetch();
		//decode();
		execute();
		memory();
		writeback();
		updateRegisters();
	}
	
	String display() { // Extra 5
		return null; // Represent state
	}
	
	// @Done
	boolean halted() {
		return halted;
	}
	
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	// Mock main
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	 
	public static void main(String[] args) { // How to use interface functions
//		Circuit circuit = new Circuit();
//		circuit.init("res/ins.txt", "res/data.txt");
//		System.out.println(circuit.display());
//		
//		for(;!circuit.halted();) {
//			 circuit.tick();
//			 System.out.println(circuit.display());
//		}
//		
//		System.out.println("MIPS SHOKALATA");
	}
}
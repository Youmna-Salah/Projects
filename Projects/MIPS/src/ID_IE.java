
public class ID_IE {
	/*control signals aluSrc ,aluOpcode,registerDestination
	 * branch , registerWrite,memoryRead,memoryWrite
	 */
	boolean[] reg1;
	boolean[] reg2;
	boolean AluSrc;
	boolean[] AluOp;
	boolean[] RegDst;
    boolean RegWrite;
    boolean MemRead;
    boolean MemWrite;
    boolean Branch;
    boolean jump;
    IF_ID fetched;
    boolean[] ReadData1;
    boolean[] readData2;
    boolean[] signExtened;
    boolean[] writeReg;
	boolean[] OpCode;
	boolean[] shamt;
	boolean[] ID_EX1;
	boolean[] ID_EX2;
	boolean[] rd;
	boolean[] immediateValue;


  
    public ID_IE(Object o) {
    	super();
		// TODO Auto-generated constructor stub
    	  this.fetched = (IF_ID)o;
    	  reg1 = fetched.reg1;
    	  reg2 = fetched.reg2;
    	  OpCode = fetched.OpCode;
    	  ID_EX1 = fetched.ID_EX1;
    	  ID_EX2 = fetched.ID_EX2;
    	  shamt = subset(10,6,fetched.sign_extend);
    	  rd = subset(11,5,fetched.sign_extend);
  		immediateValue = fetched.sign_extend;
    	    //Initialize the PC 
    	    //call memory to get the value of WriteReg ,writeData
	}
 
    public static boolean[] subset(int start, int end, boolean [] array) {
		boolean[] result = new boolean[end-start+1];
		for(int i = 0; i< end-start+1 ;i++) {
			result[i] = array[start+i];
		}
		return result;
	}
	
	}

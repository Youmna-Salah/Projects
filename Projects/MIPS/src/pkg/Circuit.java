package pkg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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

	// State
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
	void assembleInstructions(String filename) throws FileNotFoundException { // Extra 2
		Assembler.assembleInstructions(instructionmemory, new Scanner(new FileInputStream("res/ins.txt")));
	}

	void assembleData(String filename) { // Extra 3
		// Implement
	}

	// Iterated methods
	void fetch() { // Phase 1
		// Implement
	}

	void decode() { // Phase 2
		// Implement
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

	// @Done
	void updateRegisters() { // Extra 4
		// Update external registers
		for(int i = 0; i < pc_i.length;pc_i[i] = pc_o[i], i++);
		for(int i = 0; i < ifd_i.length; ifd_i[i] = ifd_o[i], i++);
		for(int i = 0; i < idex_i.length; idex_i[i] = idex_o[i], i++);
		for(int i = 0; i < exmem_i.length; exmem_i[i] = exmem_o[i], i++);
		for(int i = 0; i < memwb_i.length; memwb_i[i] = memwb_o[i], i++);
		
		// Update register file
		registerfile_i.copy(registerfile_o);
		
		// Update memory
		for(String address: updatedKeys) {
			boolean[] source = datamemory_o.get(address);
			if (datamemory_i.containsKey(address)) {
				Toolbox.replaceInMemory(datamemory_i, source, address);
			} else {
				Toolbox.insertIntoMemory(datamemory_i, source, address);
			}
		}
	}

	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
	// Interface methods
	// //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

	// @Done
	void init(String instructionfilename, String datafilename) throws FileNotFoundException {
		assembleInstructions(instructionfilename);
		assembleData(datafilename);
	}

	// @Done
	void tick() {
		fetch();
		decode();
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

	public static void mockMain(String[] args) { // How to use interface functions
		Circuit circuit = new Circuit();
		try {
			circuit.init("res/ins.txt", "res/data.txt");
			System.out.println(circuit.display());

			for (; !circuit.halted();) {
				circuit.tick();
				System.out.println(circuit.display());
			}

		} catch (FileNotFoundException e) {
			System.out.println("One or both files were not found.");
		}

		System.out.println("MIPS SHOKALATA");
	}
}
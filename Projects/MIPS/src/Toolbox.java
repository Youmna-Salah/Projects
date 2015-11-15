

import java.util.ArrayList;
import java.util.HashMap;


public abstract class Toolbox {
	
	static final long MAX_REGISTER_VALUE = 4294967295l;
	
	// For regular registers
	static long getRegisterValue(boolean[] register) {
		long value = 0;
		long multiplier = 1;
		for (int i = 0; i < 32;multiplier *= 2, i++) {
			value += (register[i]) ? multiplier : 0;
		}
		assert (value <= MAX_REGISTER_VALUE); // Check for bugs
		return value;
	}
	
	// For regular registers
	static void setRegisterValue(boolean[] register, long value) { // Void, since arrays are passed by reference
		assert (value <= MAX_REGISTER_VALUE); // Check for bugs
		for (int i = 0; i < 32;value /= 2, i++) {
			register[i] = (value % 2 == 1);
		}
	}
	
	// For regular registers
	static String getRegisterBinary(boolean[] register) {
		String binary = "";
		for (int i = 0; i < 32;) {
			binary = ((register[i]) ? 1 : 0) + binary;
		}
		return binary;
	}
	
	static void insertIntoMemory(HashMap<String, boolean[]> memory, boolean[] register, String address) {
		assert(!memory.containsKey(address));
		boolean[] word = new boolean[32];
		for(int i = 0; i < 32; word[i] = register[i], i++);
		memory.put(address, word);
	}
	
	static void replaceInMemory(HashMap<String, boolean[]> memory, boolean[] register, String address) {
		assert(memory.containsKey(address));
		boolean[] word = memory.get(address);
		for(int i = 0; i < 32; word[i] = register[i], i++);
	}
	
	static ArrayList<String> retrieveMemory(HashMap<String, boolean[]> memory, boolean[] register, String address) {
		// Implement
		// Use getRegisterBinary or getRegisterValue
		return null;
	}
	
	static ArrayList<String> mergeAndRetreiveAll(HashMap<String, boolean[]> instructionmemory, HashMap<String, boolean[]> datamemory) {
		// Implement
		// Use getRegisterBinary or getRegisterValue
		return null;
	}
}

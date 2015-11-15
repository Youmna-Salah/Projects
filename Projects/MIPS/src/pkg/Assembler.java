package pkg;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Assembler { // Incomplete, tragic

	static void assembleInstructions(HashMap<String, boolean[]> memory, Scanner reader) {
		String line;
		String oprx = "j|jal|jr|lw|lb|lbu|sw|sb|lui|add|addi|sub|sll|and|nor|beq|bne|slt|sltu";
		String insrx = "^(?:" + oprx + ")\\s*(?:$[A-Za-z0-9]+|\\-?[0-9]+)(?:\\s*,\\s*(?:(?:$[A-Za-z0-9]+)|(?:\\-?[0-9]+)))*\\s*$";
		Pattern inspattern = Pattern.compile(insrx);
		String regrx = "$(?:zero|at|v[01]|a[0-3]|t[0-9]|s[0-7]|k[01]|gp|sp|fp|ra)";
		String roprx = "";
		String rinsrx = "";
		String instruction;
		String[] args;
		
		for (int lineCount = 1; reader.hasNextLine(); lineCount++) {
			line = reader.nextLine();
			assert(inspattern.matcher(line).matches());
			//instruction = (line.split("\\s*,\\s*", 2))[0];
			//args = (line.split("\\s*,\\s*",2))[1].split("\\s*,\\s*");
			// switch(instruction) {
			// case "j":
			// case "jal":
			// case "jr": argCount = 1; break;
			// case "lw":
			// case "lb":
			// case "lbu":
			// case "sw":
			// case "sb":
			// case "lui": argCount = 2; break;
			// case "add":
			// case "addi":
			// case "sub":
			// case "sll":
			// case "and":
			// case "nor":
			// case "beq":
			// case "bne":
			// case "slt":
			// case "sltu": argCount = 2; break;
			// }
		}
	}
	
	enum InstructionFormat {
		I,
		J,
		Memory,
		R
	}
}

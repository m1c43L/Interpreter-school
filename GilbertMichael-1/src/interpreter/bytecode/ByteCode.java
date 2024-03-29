package interpreter.bytecode;

import interpreter.Program;
import interpreter.VirtualMachine;


/**
 * Abstract class for bytcode
 * @author Michael
 */
public abstract class ByteCode {
  
    
   public abstract void execute(VirtualMachine VM);
   
   public abstract void init(String [] args);
   
   public abstract boolean dumpCode();
}


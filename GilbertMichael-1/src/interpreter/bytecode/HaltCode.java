
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 * Terminate program
 * @author Michael
 */
public class HaltCode extends ByteCode{

    @Override
    public void execute(VirtualMachine VM) {
        VM.haltExecution();
    }

    @Override
    public void init(String[] args) {
        
    }

    @Override
    public boolean dumpCode() {
        System.out.println("HALT");
        return false;
    }
    
}

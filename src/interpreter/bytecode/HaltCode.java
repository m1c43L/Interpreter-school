
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 *
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
    public void dumpCode() {
        System.out.println("HALT");
    }
    
}

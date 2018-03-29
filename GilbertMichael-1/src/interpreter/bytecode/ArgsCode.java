package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 * Set up new Frames
 * @author Michael
 */
public class ArgsCode extends ByteCode {

    private int numArgs;
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.newFrameAtRunStack(numArgs);     
    }

    @Override
    public void init(String[] args) {
        numArgs = Integer.parseInt(args[1]);
    }

    @Override
    public boolean dumpCode() {
        System.out.println("ARGS " + numArgs);
        return true;
    }
    
}

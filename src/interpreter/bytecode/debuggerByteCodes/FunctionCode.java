package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.FunctionEnvironmentRecord;

/**
 *
 * @author Michael
 */
public class FunctionCode extends ByteCode{
    private FunctionEnvironmentRecord newFunction;
    

    @Override
    public void execute(VirtualMachine VM) {
       ((DebuggerVirtualMachine)VM).pushFunction(newFunction);
    }

    @Override
    public void init(String[] args) {
        newFunction = new FunctionEnvironmentRecord();
        newFunction.setFunctionInfo(args[1], Integer.parseInt(args[2]), 
                Integer.parseInt(args[3]));
   
    }

    @Override
    public boolean dumpCode() {
        return false;
    }
    
}

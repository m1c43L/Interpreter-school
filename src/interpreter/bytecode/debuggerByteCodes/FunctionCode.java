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
    
    private String id;
    private int start, end;

    
    public void execute(DebuggerVirtualMachine DVM) {
        DVM.setCurrentFunctionInfo(id, start, end);
    }

    @Override
    public void init(String[] args) {
        id =  args[1];
        start = Integer.parseInt(args[2]);
        end = Integer.parseInt(args[3]);
    }

    @Override
    public boolean dumpCode() {
        return false;
    }

    @Override
    public void execute(VirtualMachine VM) {
        
    }
    
}

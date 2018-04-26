package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Michael
 */
public class FunctionCode extends ByteCode{
    
    private String id;
    private int start, end;

    
    @Override
    public void execute(VirtualMachine VM) {
       // if(start < 1) return;
        DebuggerVirtualMachine DVM = (DebuggerVirtualMachine)VM;
        DVM.setCurrentFunctionInfo(id, start, end);
        DVM.setCall(false);
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

    
}

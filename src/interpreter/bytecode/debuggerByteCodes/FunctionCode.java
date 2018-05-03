package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Michael
 */
public class FunctionCode extends ByteCode{
    
    private String name;
    private int start, end;

    
    @Override
    public void execute(VirtualMachine VM) {
        DebuggerVirtualMachine DVM = (DebuggerVirtualMachine)VM;       
        formatName();
        DVM.setCurrentFunctionInfo(name, start, end);
        DVM.buildCallTrace();
        DVM.incremTraceIndent();
    }

    @Override
    public void init(String[] args) {
        name =  args[1];
        start = Integer.parseInt(args[2]);
        end = Integer.parseInt(args[3]);
    }

    @Override
    public boolean dumpCode() {
        return false;
    }

    private void formatName(){
        int index = name.indexOf('<');
        name = name.substring(0, (index >  -1)? index : name.length() );
    }
    
}

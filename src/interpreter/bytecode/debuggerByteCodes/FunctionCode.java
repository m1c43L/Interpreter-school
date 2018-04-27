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
        
        int indx = (name.contains("<"))?
                name.indexOf('<') : name.length();
        name = name.substring(0, indx);
        DVM.setCurrentFunctionInfo(name, start, end);
        DVM.incremSpace();
        if(DVM.isTraceOn()){
        String args = (DVM.getNumArgs() >  0)? "" + DVM.peekRunStack() : "";
        String trace = DVM.getSpace() + name + "("+ args + ")";
        System.out.println(trace);
        }
      
      
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

    
}

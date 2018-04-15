
package interpreter.debugger;

import interpreter.ByteCodeLoader;
import interpreter.CodeTable;
import interpreter.Program;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Michael
 */
public class Debugger {
    
    private ByteCodeLoader byteCodeLoader;
    
    public Debugger(String baseFile){
        try {
            CodeTable.init();
            byteCodeLoader = new ByteCodeLoader(baseFile.concat(".x.cod"));
        } catch (IOException ex) {
            Logger.getLogger(Debugger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void debug(){
        Program program = byteCodeLoader.loadCodes();
        DebuggerVirtualMachine debuggerVM = new DebuggerVirtualMachine(program);
        debuggerVM.executeProgram();
    }
}

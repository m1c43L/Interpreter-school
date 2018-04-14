package interpreter.debugger;
import interpreter.*;
import java.util.ArrayList;
import java.util.Stack;


/**
 * VM for debugger.
 * extends interpreters VM.
 * @author Michael
 */
public class DebuggerVirtualMachine extends VirtualMachine {
    
    private ArrayList <SourceEntryMarker> makerSourceRecord;
    private Stack <FunctionEnvironmentRecord> funcEnvironmentStack;
    
    
    public DebuggerVirtualMachine(Program newProgram) {
        super(newProgram);  
        makerSourceRecord = new ArrayList();
        funcEnvironmentStack = new Stack();
    }   
    
    
    public void push(FunctionEnvironmentRecord function){
        funcEnvironmentStack.push(function);
    }
    
    public FunctionEnvironmentRecord pop(){
        return funcEnvironmentStack.pop();
    }
    
       
    
    
}



/**
 * Source lines with markers
 * @author Michael
 */
class SourceEntryMarker{
    
    private String sourceLine;
    private boolean isBreakptSet;
    
    public SourceEntryMarker(String sourceLine, boolean isBreakptSet){
        this.sourceLine = sourceLine;
        this.isBreakptSet = isBreakptSet;
    }
    
    public String getSourceLine(){
        return this.sourceLine;
    }
    
    public boolean isBreakptSet(){
        return this.isBreakptSet;
    }
    
}

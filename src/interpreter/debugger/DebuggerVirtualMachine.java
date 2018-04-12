package interpreter.debugger;
import interpreter.*;
import java.util.ArrayList;
import java.util.Stack;
/**
 *
 * @author Michael
 */
public class DebuggerVirtualMachine extends VirtualMachine {
    
    private ArrayList <MarkerSourceEntry> makerSourceRecord;
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




class MarkerSourceEntry{
    
    private String sourceLine;
    private boolean isBreakptSet;
    
    public MarkerSourceEntry(String sourceLine, boolean isBreakptSet){
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

package interpreter.debugger;
import interpreter.*;
import interpreter.ui.UI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


/**
 * VM for debugger.
 * extends interpreters VM.
 * @author Michael
 */
public class DebuggerVirtualMachine extends VirtualMachine {
    
    private ArrayList <SourceEntryMarker> markerSourceRecord;
    private Stack <FunctionEnvironmentRecord> funcEnvironmentStack;
    private UI user;
    

    
    public DebuggerVirtualMachine(Program newProgram) {
        super(newProgram);  
        markerSourceRecord = new ArrayList();
        funcEnvironmentStack = new Stack();
        user = new UI();
    }   
     
    public void push(FunctionEnvironmentRecord function){
        funcEnvironmentStack.push(function);
    }
    
    public FunctionEnvironmentRecord pop(){
        return funcEnvironmentStack.pop();
    }
    
    public void loadSourceCode(String sourceFile) throws FileNotFoundException, IOException{
        markerSourceRecord = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        while(reader.ready()){
            markerSourceRecord.add(new SourceEntryMarker(reader.readLine()));
        }
    }
     
    public void setBreakPoint(int line){
        
    }
    
    public void clearBreakPoint(int line){
        
    }
    
    public void displayCurrentFunction(){
        int counter = 1;
        for(SourceEntryMarker source: markerSourceRecord){
            System.out.println(counter + ".) " + source.getSourceLine());
        }
    }
    
    public void continueExecution(){
        
    }
    
    
    
}



/**
 * Source lines with markers
 * @author Michael
 */
class SourceEntryMarker{
    
    private String sourceLine;
    private boolean isBreakptSet, isPossibleBreakPt;
    
    public SourceEntryMarker(String sourceLine){
        this.sourceLine = sourceLine;
    }
    
    public String getSourceLine(){
        return this.sourceLine;
    }
    
    public boolean isBreakptSet(){
        return this.isBreakptSet;
    }
    
    public void setBreakPt(){
        isBreakptSet = true;
    }
    
    public void clearBreakPt(){
        isBreakptSet = false;
    }
    
}

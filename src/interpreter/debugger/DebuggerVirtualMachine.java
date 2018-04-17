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
    
    
    private ArrayList <SourceLineMarker> sourceRecord;
    private Stack <FunctionEnvironmentRecord> funcEnvironmentStack;
    private UI user;
    private int currentLine;
    private boolean iRunning;
    

    
    public DebuggerVirtualMachine(Program newProgram) {
        super(newProgram);  
        sourceRecord = new ArrayList();
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
        sourceRecord = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        while(reader.ready()){
            sourceRecord.add(new SourceLineMarker(reader.readLine()));
        }
    }
     
    public void setBreakPoint(int line){
        sourceRecord.get(line - 1).setBreakPt();
    }
    
    public void clearBreakPoint(int line){
        sourceRecord.get(line - 1).clearBreakPt();
    }
    
    public void displaySourceFunction(){
        int counter = 1;
        for(SourceLineMarker source: sourceRecord){
            System.out.println(counter + ".) " + source);
        }
    }
    
    public void displayCurrentFunction(){
        System.out.println(sourceRecord.get(currentLine - 1));
    }
    
    public void continueExecution(){
        
    }
    
    public void setCurrentLine(int newCurrentLine){
        currentLine = newCurrentLine;
    }
    
    
    
}



/**
 * Source lines with markers
 * @author Michael
 */
class SourceLineMarker{
    
    private String sourceLine;
    private boolean isBreakptSet, isPossibleBreakPt;
    
    public SourceLineMarker(String sourceLine){
        this.sourceLine = sourceLine;
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
    
    public String toString(){
        return sourceLine;
    }
}

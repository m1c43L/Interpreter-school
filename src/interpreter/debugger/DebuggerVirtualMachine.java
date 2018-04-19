package interpreter.debugger;
import interpreter.*;
import interpreter.bytecode.ByteCode;
import interpreter.ui.UI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


/**
 * VM for debugger.
 * extends interpreters VM.
 * @author Michael
 */
public class DebuggerVirtualMachine extends VirtualMachine {
    
    
    private ArrayList <SourceLineMarker> sourceRecord;
    private Stack <FunctionEnvironmentRecord> funcEnvironmentStack;
  
    private int currentLine;

   
    @Override
    public void executeProgram(){     
        pc = 0;
        currentLine = 1;
        runStack = new RunTimeStack();
        isRunning = true;
        displaySourceCode();
       
        while(isRunning){
            
        }
    }    
    
    public void runCycle(){
        ByteCode code = program.getCode(pc);
               code.execute(this);  
               pc++;
    }
    
    
    
    public DebuggerVirtualMachine(Program newProgram) {
        super(newProgram);  
        sourceRecord = new ArrayList();
        funcEnvironmentStack = new Stack();
        funcEnvironmentStack.push(new FunctionEnvironmentRecord());
    }   
     
    public void pushFunction(FunctionEnvironmentRecord function){
        funcEnvironmentStack.push(function);
    }
    
    public FunctionEnvironmentRecord popFunction(){
        return funcEnvironmentStack.pop();
    }
    
    public void pushFormal(String id, int value){
        funcEnvironmentStack.peek().put(id, value, false);
    }
    
    public void loadSourceCode(String sourceFile) throws FileNotFoundException, IOException{
        sourceRecord = new ArrayList();
        ArrayList possibleBreakPt = program.possibleBreakPts();
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        int line = 1;

        while(reader.ready()){
            StringBuffer sourceLine = new StringBuffer();
            sourceLine.append(line).append(line < 10? ".  ":". ")
                    .append(reader.readLine());
           sourceRecord.add(new SourceLineMarker(sourceLine.toString()
                   , possibleBreakPt.contains(line)));
           line ++;
        }
    }
     
    public boolean canSetBreakPoint(int line){
        return sourceRecord.get(line - 1 ).isPossibleBreakPt();
    }
    
    public void setBreakPoint(int line){
        sourceRecord.get(line - 1).setBreakPt();
    }
    
    public void clearBreakPoint(int line){
        sourceRecord.get(line - 1).clearBreakPt();
    }
    
    private boolean isBreakPointSetTo(int line){
        return sourceRecord.get(line - 1).isBreakptSet();
    }
    
    public void displaySourceCode(){
        int line = 1;
        StringBuffer sourceCode = new StringBuffer();
        
        for(SourceLineMarker sourceLine: sourceRecord){
            sourceCode.append((isBreakPointSetTo(line)? "*":" "))
                    .append(sourceLine)
                    .append((line == currentLine)? "\t <-------------------------------":"")
                    .append("\n");
            line++;
        }
        
        System.out.println(sourceCode);
    }
    
    public String getCurrentLine(){ 
        return (this.isBreakPointSetTo(currentLine)? "*":"") 
                + sourceRecord.get(currentLine - 1);
    }
    
    public void continueExecution(){
        int prevLine = currentLine;
        while(prevLine == currentLine 
                || !sourceRecord.get(currentLine - 1).isBreakptSet()){
            runCycle();     
        }
    }
    
    public void quitExecution(){
        this.isRunning = false;
    }
    
    public void setCurrentLine(int newCurrentLine){
        currentLine = newCurrentLine;
        funcEnvironmentStack.peek().setCurrentLineNumber(currentLine);
    }
    
    public void displayVariables(){
        funcEnvironmentStack.peek().dump();
    }
    
    public Object getCurrentValueOf(String id){
       return funcEnvironmentStack.peek().getValueOf(id);
    }
    
    public void setCurrentFunctionInfo(String id, int start, int end){
        funcEnvironmentStack.peek().setFunctionInfo(id, start, end);
    }
    
}

/**
 * Source lines with markers
 * @author Michael
 */
class SourceLineMarker{
    
    private String sourceLine;
    private boolean isBreakptSet, isPossibleBreakPt;
    
    public SourceLineMarker(String sourceLine, boolean isPossibleBreakPt){
        this.sourceLine = sourceLine;
        this.isPossibleBreakPt = isPossibleBreakPt;
        this.isBreakptSet = false;
    }
    
    public boolean isPossibleBreakPt(){
        return isPossibleBreakPt;
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

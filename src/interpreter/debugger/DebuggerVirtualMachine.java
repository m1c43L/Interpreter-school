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
    private UI user;
    private int currentLine;

    
   
    @Override
    public void executeProgram(){     
        pc = 0;
        currentLine = 1;
        runStack = new RunTimeStack();
        isRunning = true;
        displaySourceCode();
        user.suggestHelp();
        while(isRunning){
            user.setUpCommand();
            user.executeCommandTo(this);
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
        user = new UI();
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
        int line = 1;
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        while(reader.ready()){
           sourceRecord.add(new SourceLineMarker(reader.readLine(), possibleBreakPt.contains(line)));
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
    
    public void displaySourceCode(){
        int counter = 1;
        String breakPtMarker = "*";
        StringBuffer sourceCode = new StringBuffer();
        for(SourceLineMarker sourceLine: sourceRecord){
            sourceCode.append(sourceLine.isBreakptSet()? breakPtMarker:" ")
                    .append(counter)
                    .append(counter < 10? ".  ":". ")
                    .append(sourceLine);
            
            if(counter == currentLine){
                sourceCode.append("\t <-------------------------------");
            }
            sourceCode.append("\n");
            counter++;
        }
        System.out.println(sourceCode);
    }
    
    public void displayCurrentFunction(){
        System.out.println(sourceRecord.get(currentLine - 1));
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
    }
    
    public void displayVariables(){
        this.funcEnvironmentStack.peek().dump();
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

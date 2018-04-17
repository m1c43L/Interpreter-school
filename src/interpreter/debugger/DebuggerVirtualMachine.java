package interpreter.debugger;
import interpreter.*;
import interpreter.bytecode.ByteCode;
import interpreter.ui.UI;
import java.io.BufferedReader;
import java.io.File;
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
    private boolean isContinue;
    
   
    @Override
    public void executeProgram(){     
        pc = 0;
        currentLine = 1;
        runStack = new RunTimeStack();
        isRunning = true;
        this.displaySourceFunction();
        while(isRunning){
            if(isContinue){
               while(!sourceRecord.get(currentLine).isBreakptSet()) 
                   runCycle();           
               isContinue = false;
            }
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
        HashMap possibleBreakPt = program.possibleBreakLines();
        int counter = 1;
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        while(reader.ready()){
           sourceRecord.add(new SourceLineMarker(reader.readLine(), possibleBreakPt.containsKey(counter)));
           counter ++;
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
        String breakPtMarker = "*";
        StringBuffer indent = new StringBuffer();
        for(int i = 0; i < sourceRecord.size(); i++){
            
        }
        for(SourceLineMarker source: sourceRecord){
            System.out.println(source.isBreakptSet()? breakPtMarker:"  " 
                    + counter + ". " + source);
            if(counter == currentLine){
                System.out.print("\t\t <------------------------");
            }
        }
    }
    
    public void displayCurrentFunction(){
        System.out.println(sourceRecord.get(currentLine - 1));
    }
    
    public void continueExecution(){
        
    }
    
    public void quitExecution(){
        this.isRunning = false;
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
    
    public SourceLineMarker(String sourceLine, boolean isPossibleBreakPt){
        this.sourceLine = sourceLine;
        this.isPossibleBreakPt = isPossibleBreakPt;
        this.isBreakptSet = false;
    }
    
    public boolean isPossibleBreakPt(){
        return isPossibleBreakPt;
    }
    
    public void makePossileBreakPt(){
        isPossibleBreakPt = true;
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

package interpreter.debugger;
import interpreter.*;
import interpreter.bytecode.ByteCode;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;


/**
 * VM for debugger.
 * extends interpreters VM.
 * @author Michael
 */
public class DebuggerVirtualMachine extends VirtualMachine {
    
    
    private ArrayList <SourceLineMarker> sourceRecord;
    private Stack <FunctionEnvironmentRecord> funcEnvironmentStack;
    private int currentLineNo;
    private boolean isContinuing;
  
    
    public DebuggerVirtualMachine(Program newProgram) {
        super(newProgram);  
        sourceRecord = new ArrayList();
        funcEnvironmentStack = new Stack();
        funcEnvironmentStack.push(new FunctionEnvironmentRecord());
        pc = 0;
        currentLineNo = 1;
        runStack = new RunTimeStack();
        isRunning = true;
    }   
   
    @Override
    public void executeProgram(){     
        while(isContinuing){ runCycle(); }
        resumeExecution();
    }    
    
    public void pauseExecution(){
        isContinuing = false;
    }
    
    public void resumeExecution(){
        isContinuing = true;
    }
    
     public boolean isRunning(){
        return isRunning;
    }
    
    public void runCycle(){
        ByteCode code = program.getCode(pc);
               code.execute(this);  
               pc++;
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
        int lineNo = 1;

        // create new instance of sourcelinemarker ask if it can contain
        // breakpoint add number indent to the line
        while(reader.ready()){
            StringBuilder sourceLine = new StringBuilder();
            sourceLine.append(lineNo).
                    append(lineNo < 10? ".  ":". ").
                    append(reader.readLine());
           sourceRecord.add(new SourceLineMarker(sourceLine.toString()
                   , possibleBreakPt.contains(lineNo)));
           lineNo ++;
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
    
    public boolean isBreakPointSetTo(int line){
        return sourceRecord.get(line - 1).isBreakptSet();
    }
    
    public String getMarkedSourceCode(){
        int line = 1;
        StringBuilder sourceCode = new StringBuilder();
        
        for(SourceLineMarker sourceLine: sourceRecord){
            sourceCode.append((isBreakPointSetTo(line)? "*":" "))
                    .append(sourceLine)
                    .append((line == currentLineNo)? "\t <-------------------------------":"")
                    .append("\n");
            line++;
        }
        
        return sourceCode.toString();
    }
    
    private String getMarkedSourceLine(int lineNo){
        return (this.isBreakPointSetTo(lineNo)? "*":" ") 
                + sourceRecord.get(lineNo - 1);
    }
    
    public String getCurrentStringFunc(){ 
         StringBuilder sourceCode = new StringBuilder();
        try{
            int start = this.funcEnvironmentStack.peek().getFuncStart(), 
                end = this.funcEnvironmentStack.peek().getFuncEnd();

            for(int i = start; i <= end; i++ ){
                sourceCode.append(getMarkedSourceLine(i))
                        .append((i == currentLineNo)? "\t <-------------------------------":"")
                        .append("\n");
            }   
        }catch(UnInitializedIdException e){
            sourceCode = new StringBuilder(getMarkedSourceCode());
        }
        
            return sourceCode.toString();
    }
    
    public void setCurrentLine(int newCurrentLineNo){
        currentLineNo = newCurrentLineNo;
        funcEnvironmentStack.peek().setCurrentLineNumber(newCurrentLineNo);
    }
    
    public void displayVariables(){
        funcEnvironmentStack.peek().dump();
    }
    
    public Object getCurrentValueOf(String id){ 
      return runStack.get((Integer)funcEnvironmentStack.peek().getValueOf(id));
    }
    
    public Set<String> getAllVariables(){
        return funcEnvironmentStack.peek().getIdKeys();
    }
    
    public void setCurrentFunctionInfo(String id, int start, int end){
        funcEnvironmentStack.peek().setFunctionInfo(id, start, end);
    }
    
    public void dumpFrames(){
        funcEnvironmentStack.peek().dump();
    }
    
    public int getCurrentOffset(){
        return this.runStack.getCurrentFrameOffset();
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

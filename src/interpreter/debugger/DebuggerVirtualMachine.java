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
 * @author Michael
 */
public class DebuggerVirtualMachine extends VirtualMachine {
    
    
    private ArrayList <SourceLineMarker> sourceRecord;
    private Stack <FunctionEnvironmentRecord> funcEnvironmentStack;
    private int currentLineNo;
    private boolean isContinuing, isTraceOn;
    private StringBuilder trace;
    
    public DebuggerVirtualMachine(Program newProgram) {
        super(newProgram);
        funcEnvironmentStack = new Stack();
        funcEnvironmentStack.push(new FunctionEnvironmentRecord());
        pc = 0;
        currentLineNo = -1;
        runStack = new RunTimeStack();
        isRunning = true;      
        trace = new StringBuilder("");
        isTraceOn = false;
    }   
      
    
    @Override
    public void executeProgram(){    
        resumeExecution();
        
        while(isContinuing){
            executeByteCode();
        }
    }   
    
    public void executeCurrentLine(){
        int prevLine = currentLineNo + 1;
        while(prevLine != currentLineNo){
            executeByteCode();
        }
    }
    
    public void executeByteCode(){
        if(!isRunning) return;
        ByteCode code = program.getCode(pc);
               code.execute(this);  
               pc++;
    }
    
    public void stepIn(){
        int prevLine = currentLineNo;
        int currentSize = funcEnvironmentStack.size() ;
        while(prevLine == currentLineNo 
                && currentSize ==  funcEnvironmentStack.size()){
            executeByteCode();
        }
        if(currentSize + 1 == funcEnvironmentStack.size()){
            int n = 2 + this.getNumArgs();
            while(n > 0){
                executeByteCode();
                n--;
            }
        } 
    }
    
    public void stepOut(){  
        if(funcEnvironmentStack.size() > 1){
            int prevSize  = this.funcEnvironmentStack.size() ;
            while(super.isRunning){              
                if((prevSize != funcEnvironmentStack.size() 
                        && isBreakPointSetTo(currentLineNo)) 
                        || prevSize > funcEnvironmentStack.size()){
                    break;
                }
                executeByteCode();
            }
        }
        else{
            this.executeProgram();
        }
        
    }
    
    public void stepOver(){
        int lastLine = currentLineNo, 
             lastESize = funcEnvironmentStack.size();   
        
        while(true){
               executeByteCode();
            if(lastLine != currentLineNo 
                    && lastESize == funcEnvironmentStack.size()) return;
        }
    }
    
    public void setTrace(boolean isOn){
        isTraceOn = isOn;
    }
    
    public String getSpace(){
        return this.trace.toString();
    }
    
    public void incremSpace(){
        this.trace.append(" ");
    }
    
    public void decremSpace(){
        this.trace.deleteCharAt(trace.length() - 1);
    }
    
    public boolean isTraceOn(){
        return isTraceOn;
    }
    
    public void setCurrentLineNo(int newLine){
        currentLineNo = newLine;
    }
     
    public String getCurrentFuncName(){
        return funcEnvironmentStack.peek().getFuncName();
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
    
    public int getNumArgs(){
        return super.runStack.getTopFrameLength();
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
    
    public void setCurrentERecordLine(int newLine){
         funcEnvironmentStack.peek().setCurrentLineNumber(newLine);
     }
    
    
    public void loadSourceCode(String sourceFile) throws FileNotFoundException, IOException{
        sourceRecord = new ArrayList();
        ArrayList possibleBreakPt = program.possibleBreakPts();
        BufferedReader source = new BufferedReader(new FileReader(sourceFile));
        int lineNo = 1;

        // creates new instance of SourceLineMarker and defines if canbe line num
        while(source.ready()){
            StringBuilder sourceLine = new StringBuilder();
                sourceLine.append(lineNo)
                        .append(".")
                        .append(source.readLine());
            sourceRecord.add(new SourceLineMarker(sourceLine.toString()
                   , possibleBreakPt.contains(lineNo)));
            lineNo ++;
        }
            indentSourceCode();
    }
    
    private void indentSourceCode(){
        long marker = 10;
        StringBuilder indent = new StringBuilder();
        int sizeOfSource =  sourceRecord.size(), count = 1;
        
            while(sizeOfSource > 9){
                sizeOfSource /= 10;
                indent.append(" ");
            }
            
        for(SourceLineMarker line: sourceRecord){
            if(count == marker){
                indent.deleteCharAt(indent.length() - 1);
                marker *= 10;
            }
            StringBuilder builder = new StringBuilder();
            builder.append(indent).append(line);
            line.setLine(builder.toString());
            count ++;
        }
    }
    
    public ArrayList<Integer> getBreakPoints(){
        ArrayList breakPoints = new ArrayList();
        for(int i = 1; i <= this.sourceRecord.size(); i++){
            if(sourceRecord.get(i - 1).isBreakptSet()){
                breakPoints.add(i);
            }
        }
        return breakPoints;
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
    
    public String getCurrentSourceFunc(){ 
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
        }catch(IntrinsictException ex){
            sourceCode = new StringBuilder("");
        }
        
            return sourceCode.toString();
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
    
    public void dumpCurrentFrames(){
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
   
    public void setLine(String newLine){
        sourceLine = newLine;
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

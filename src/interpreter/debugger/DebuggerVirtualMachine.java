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
    
    
    private ArrayList <SourceLine> sourceRecord;
    private Stack <FunctionEnvironmentRecord> funcEnvironmentStack;
    private Stack <Integer> callStackLineNo;
    private int currentLineNo;
    
    private boolean isContinuing, 
                    isTraceOn;
    
    private StringBuilder indents,
                          trace; 
    
    
    
    public DebuggerVirtualMachine(Program newProgram) {
        super(newProgram);
        funcEnvironmentStack = new Stack();
        funcEnvironmentStack.push(new FunctionEnvironmentRecord());
        pc = 0;
        currentLineNo = -1;
        runStack = new RunTimeStack();
        isRunning = true;      
        indents = new StringBuilder();
        isTraceOn = false;
        trace = new StringBuilder();
        callStackLineNo = new Stack();
    }   
      
    
    @Override
    public void executeProgram(){    
        resumeExecution();
        while(isContinuing){
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
            int n = 2 + getNumArgs();
            while(n-- > 0)
                executeByteCode();
        } 
    }
    
    public void stepOut(){  
        if(funcEnvironmentStack.size() > 1){
            int prevSize  = this.funcEnvironmentStack.size();
            this.resumeExecution();
            while(isContinuing){              
                if((funcEnvironmentStack.size() < prevSize)){
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
        resumeExecution();
        while(isContinuing){
               executeByteCode();
            if((lastLine != currentLineNo 
                    && lastESize == funcEnvironmentStack.size())) 
                return;
        }
    }
    
    public void setTrace(boolean isOn){
        isTraceOn = isOn;
    }
   
    public int getFuncEnvSize(){
        return this.funcEnvironmentStack.size();
    }
    
    public void incremTraceIndent(){
        indents.append(" ");
    }
    
    public void decremTraceIndent(){
        indents.deleteCharAt(indents.length() - 1);
    }
    
    public boolean isTraceOn(){
        return isTraceOn;
    }
    
    public void pushCallStack(){
        callStackLineNo.push(currentLineNo);
    }
    
    public void popCallStack(){
        callStackLineNo.pop();
    }
    
    public String getCallStack(){
        StringBuilder builder =  new StringBuilder();
        builder.append(makeCallLine(funcEnvironmentStack.peek().getFuncName()
                , currentLineNo));
        
        for(int i = callStackLineNo.size() - 1; i >= 0; i--){
            builder.append(makeCallLine(funcEnvironmentStack.get(i).getFuncName()
                , callStackLineNo.get(i)));
        }
        return builder.toString();
    }
    
    private String makeCallLine(String funcName, int lineNo){
        StringBuilder builder = new StringBuilder();
        builder.append(funcName)
                   .append(" on line: ")
                   .append(lineNo)
                   .append("\n");
        return builder.toString();
    }
    
    private String getCurrentFuncName(){
        return this.funcEnvironmentStack.peek().getFuncName();
    }
    
   public void buildCallTrace(){
       if(!isTraceOn) return;
       trace.append(
               new StringBuilder(indents)
                .append(getCurrentFuncName())
                .append("(")
                .append((getNumArgs() > 0)? parameter() : "")
                .append(")") 
                .append("\n") 
       );
   }
   
   private String parameter(){
       if(this.getNumArgs() == 0) return "";
       StringBuilder parameters = new StringBuilder();
       Object [] items = super.runStack.getParameters();
       for(int i = 0; i < getNumArgs(); i++){
          parameters.append(items[i]);
          if(i+1 < getNumArgs()) parameters.append(",");
       }
       return parameters.toString();
   }
   
   public void buildReturnTrace(){
       if(!isTraceOn) return;
       trace.append(
               new StringBuilder(indents)
                .append("exit: ")
                .append(getCurrentFuncName())
                .append((getNumArgs() > 0)? ": " + super.peekRunStack() : "")
                .append("\n") 
       );
   }
    
    public String getCurrentTrace(){
        StringBuilder returnTrace = trace;
        trace = new StringBuilder();
        return returnTrace.toString();
    }
    
    public void setCurrentLineNo(int newLine){
        currentLineNo = newLine;
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
    
    public int getEnvironmentCurLine(){
       return funcEnvironmentStack.peek().getCurrentLine();
    }
    
    public void pushFormal(String id, int value){
        funcEnvironmentStack.peek().put(id, value, false);
    }
    
    public void setCurrentERecordLine(int newLine){
         funcEnvironmentStack.peek().setCurrentLineNumber(newLine);
     }
    
    
    public void initSourceRecord(String sourceFile) throws FileNotFoundException, IOException{
        sourceRecord = new ArrayList();
        ArrayList possibleBreakPt = program.getPossibleBreakPts();      
        BufferedReader sourceReader = new BufferedReader(new FileReader(sourceFile));
        int lineNo = 1;

        //load, format and add to sourceRecord the source code line by line 
        while(sourceReader.ready()){
            boolean canBeBreakPoint = possibleBreakPt.contains(lineNo);
            String sourceLine =  makeSourceLine(lineNo,sourceReader.readLine());
            sourceRecord.add(new SourceLine(sourceLine, canBeBreakPoint));   
            lineNo ++;
        }
        
        indentSourceCode();
    }
    
    private String makeSourceLine(int lineNum, String sourceLine){
        return (new StringBuilder()
                .append(lineNum )
                .append(".")
                .append(sourceLine))
                .toString();
    }

    private void indentSourceCode(){
        long marker = 10;
        StringBuilder indent = new StringBuilder();
        int sizeOfSource =  sourceRecord.size(), 
                count = 1;
        
            while(sizeOfSource > 9){
                sizeOfSource /= 10;
                indent.append(" ");
            }
            
        for(SourceLine line: sourceRecord){
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
    
    
    public String getSourceCode(){
        return buildSourceFromTo(1, sourceRecord.size() - 1).toString();
    }
    
    private String getSourceLine(int lineNo){
        return (this.isBreakPointSetTo(lineNo)? "*":" ") 
                + sourceRecord.get(lineNo - 1);
    }
    
    private StringBuilder buildSourceFromTo(int start, int end){
        StringBuilder sourceBuilder = new StringBuilder();
        for(int i = start; i <= end; i++ ){
                sourceBuilder.append(getSourceLine(i))
                        .append((i == currentLineNo)? 
                                "\t <-------------------------------":"")
                        .append("\n");
            }
        return sourceBuilder;
    }
    
    private StringBuilder getFuncBuilder() throws UnInitializedIdException, IntrinsictException{
        return buildSourceFromTo( funcEnvironmentStack.peek().getFuncStart(), 
                                  funcEnvironmentStack.peek().getFuncEnd() );
    }
    
    public String getCurrentSourceFunc(){ 
        StringBuilder sourceCode;
        try{
           sourceCode = getFuncBuilder();
        }catch(UnInitializedIdException e){
            sourceCode = new StringBuilder(getSourceCode());
        }catch(IntrinsictException ex){
            sourceCode = new StringBuilder();
            sourceCode.append("**********").
                       append(funcEnvironmentStack.peek().getFuncName()).
                       append("**********");
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
class SourceLine{
    
    private String sourceLine;
    private boolean isBreakptSet, isPossibleBreakPt;
    
    
    public SourceLine(String sourceLine, boolean isPossibleBreakPt){
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
    
    @Override
    public String toString(){
        return sourceLine;
    }
}

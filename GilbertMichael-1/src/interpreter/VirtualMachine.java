package interpreter;

import interpreter.bytecode.*;
import java.util.*;

/**
 * Executes each byte code that is loaded into the program.
 * Keeps track of the current position in the program.
 * Holds a reference to the runtime stack. 
 * 
 * @author Michael
 */
public class VirtualMachine {
    
    private int pc;
    private boolean isRunning, isDumping;
    private Stack returnAddrs;
    private RunTimeStack runStack;
    private Program program;
    
    
    
    public VirtualMachine(Program newProgram){
        returnAddrs = new Stack();
        program = newProgram;
        isDumping = false;
    }
    
    public void executeProgram(){
        pc = 0;
        runStack = new RunTimeStack();
        isRunning = true;
        while(isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if(isDumping){
                if(code.dumpCode())  runStack.dump();
            } // check that operation is correct
            pc++;
        }
    }
    
    public int peekRunStack(){
        return runStack.peek(); 
    }
    
    public int popRunStack(){
        return runStack.pop();
    }
    
    public int pushRunStack(int i){
        return runStack.push(i);
    }
    
    public void newFrameAtRunStack(int offset){
        runStack.newFrameAt(offset);
    }
    
    public void popFrameRunStack(){
        runStack.popFrame();
    }
    
    public int storeRunStack(int offset){
        return runStack.store(offset);
    }
    
    public int loadRunStack(int offset){
        return runStack.load(offset);
    }
    
    public Integer pushRunStack(Integer i){
        return runStack.push(i);
    }
    
    public void setDumpState(boolean state){
        isDumping = state;
    }
    
    public void haltExecution(){
        isRunning = false;
    }
    
    public void pushPC(){
        returnAddrs.push(pc);
    }
    
    public void returnPC(){
        pc = (int) returnAddrs.pop();
    }
    
    public void jumpPC(int addrsIndex){
        pc = addrsIndex;
    }
    
    public boolean isDumpingOn(){
        return isDumping;
    }
    
    public void doDump(){
       runStack.dump();
    }
    
}

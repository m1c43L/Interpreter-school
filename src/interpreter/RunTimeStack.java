package interpreter;

import java.util.*;

/**
 *
 * @author Michael
 */
public class RunTimeStack {
    
    private Vector runStack;
    private Stack <Integer> framePointers;
    
    
    public RunTimeStack(){
        runStack = new Vector();
        framePointers = new Stack();
        framePointers.push(0);
    }
    
    public void dump(){
       int counter = 0;
        for(Object i: runStack){
            
            System.out.print((int)i + " ");
            
        }
        System.out.println();
    }
    
    public int peek(){
        return (int) runStack.lastElement();
    }
    
    public int pop(){
        if(framePointers.peek() == runStack.size()-1) framePointers.pop();
        return (int) runStack.remove(runStack.size() - 1);
    }
    
    public int push(int i){
        runStack.add(i);
        return i;
    }
    
    public void newFrameAt(int offset){
        framePointers.push(runStack.size() - offset);
    }
    
    public void popFrame(){
        int lastFramePos =  framePointers.pop();
        runStack.setElementAt(runStack.get(runStack.size() - 1), 
                lastFramePos);
        //remove exces values in the vector
        for(int i = runStack.size()-1; i > lastFramePos; i--){
            runStack.remove(i);
        }
    }
    
    public int store(int offset){
        runStack.set(offset, runStack.remove(runStack.size() - 1));
        return (int) runStack.get(offset);
    }
    
    public int load(int offset){
        runStack.add(runStack.get(offset + framePointers.peek()));
        return this.peek();
    }
    
    public Integer push(Integer i){
        runStack.add(i.intValue());
        return i;
    }
}

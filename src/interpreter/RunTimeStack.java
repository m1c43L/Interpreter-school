package interpreter;

import java.util.*;

/**
 *
 * @author Michael
 */
public class RunTimeStack {
    
    private Vector runStack;
    private Stack framePointers;
    
    
    public RunTimeStack(){
        runStack = new Vector();
        framePointers = new Stack();
        framePointers.push(0);
    }
    
    public void dump(){
        for(Object i: runStack){
            System.out.print((int)i);
        }
        System.out.println();
    }
    
    public int peek(){
        return (int) runStack.lastElement();
    }
    
    public int pop(){
        return (int) runStack.remove(runStack.size()-1);
    }
    
    public int push(int i){
        runStack.add(i);
        return i;
    }
    
    public void newFrameAt(int offset){
        framePointers.push(offset);
    }
    
    public void popFrame(){
        runStack.add(framePointers.pop());
    }
    
    public int store(int offset){
        runStack.set(offset, this.pop());
        return (int) runStack.get(offset);
    }
    
    public int load(int offset){
        runStack.add(runStack.get(offset));
        return this.peek();
    }
    
    public Integer push(Integer i){
        runStack.add(i.intValue());
        return i;
    }
}

package interpreter;

import java.util.*;

/**
 * Holds the runTimeStacks and frames 
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
    
    /**
     *  Arrange the data for debugging.
     *  Prints runStack Frames.
     */
    public void dump(){   
      //make copy of framePointers, we dont want to mess the framePointers.
      //Cast the object instance to Vector in order to freely access framePointers
      
      Vector <Integer> frameMarker =  new Vector(framePointers);
      //System.out.println(framePointers + "<------");
      StringBuffer frmLog = new StringBuffer("[");
      int indexFM = 1;
        for(int i = 0; i < runStack.size(); i++){
            if(indexFM < frameMarker.size()  // when set up frame []
                    && frameMarker.get(indexFM) == i
                    && i > 0){
                frmLog.deleteCharAt(frmLog.length() - 1);
                frmLog.append("] [");
                indexFM++;
            } 
            frmLog.append(runStack.get(i)).append(",");          
        }
        frmLog.setCharAt(frmLog.length() - 1, ']');
        
        System.out.println( (runStack.isEmpty()) ? "[]": frmLog ); 
    }
    
    
    public int peek(){
        return (int) runStack.lastElement();
    }
    
    public int pop(){     
        // pop past frame boudary special case. may break program anyways.
        //if(runStack.size() - 2 == framePointers.peek() 
        //      && framePointer.size() > 1) { framePointers.pop(); }
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
       int lastFramePos = framePointers.pop();
       //append last item of this frame to the prev frame
        runStack.setElementAt(runStack.get(runStack.size() - 1), 
                lastFramePos);
        //remove excess values from the deleted frame
        for(int i = runStack.size() - 1; i > lastFramePos; i--){
            runStack.remove(i);
        }
    }
    
    public int store(int offset){
        runStack.set(offset+framePointers.peek(), 
                runStack.remove(runStack.size() - 1));
        
        return (int) runStack.get(offset);
    }
    
    public int load(int offset){
        runStack.add(runStack.get(offset + framePointers.peek())); 
        return this.peek();
    }
    
    public Integer push(Integer i){
        runStack.add(i);
        return i;
    }
    
    public Object get(int offset){
        return runStack.get(offset+framePointers.peek());
    }
    
    public int getCurrentFrameOffset(){
        return framePointers.peek() + runStack.size() - 1;
    }
    
    public int getTopFrameLength(){
        return runStack.size() - framePointers.peek();
    }
    
    public Object [] getParameters(){
        int lastIndex = runStack.size();
        Object [] items = new Object[getTopFrameLength()];
        for(int i = lastIndex - getTopFrameLength(); i  < lastIndex; i++){
            items[(i + getTopFrameLength()) - lastIndex] = runStack.get(i);
        }
        return items;
    }
}

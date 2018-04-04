
package interpreter;

/**
 * MileStone 2.
 * Test the symbol table mechanism.
 * @author Michael
 */
public class FunctionEnvironmentRecord {
    
    private java.util.HashMap symbols;
    private String funcName;
    private int startingLineNumber, endingLineNumber, currentLineNumber;
    private Binder mark;
    private Symbol top;
    
    public FunctionEnvironmentRecord(){
        
    }
    
    public void beginScope(){
        symbols = new java.util.HashMap();
    }
    
    public void  setFunctionInfo(String funName, int start, int end){
        
    }
    
    public void setCurrentLineNumber(int newLineNo){
        startingLineNumber = newLineNo;
    }
    
    public void set(String var, int val){
        
    }
    
    public void doPop(int lines){
        
    }
    
    public void dump(){
        
    }
    
    
    public static void main(String args[]){
        
    }
}


class Binder{
    
    private Object value;
    //private Symbol prevtop;   // prior symbol in same scope
    private Binder tail;      // prior binder for same symbol
                            // restore this when closing scope
    Binder(Object v, Symbol p, Binder t) {
	value=v; prevtop=p; tail=t;
    }

    Object getValue() { return value; }
    //Symbol getPrevtop() { return prevtop; }
    Binder getTail() { return tail; }
    }

class Symbol{
    
    private String id;
    private int offset;
    private Symbol prev;
    
    Symbol(String newId, int newOffset){
        id = newId;
        offset = newOffset;
    }
    
    
    public void popLast(){
        if(last != null){
            
        }
    }
}

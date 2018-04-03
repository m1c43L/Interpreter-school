
package interpreter;

/**
 * MileStone 2.
 * Test the symbol table mechanism.
 * @author Michael
 */
public class FunctionEnvironmentRecord {
    
    private java.util.HashMap symbol;
    private String funcName;
    private int startingLineNum, endingLineNum, currentLine;

    
    public FunctionEnvironmentRecord(FunctionEnvironmentRecord fctEnvRecord){
        
    }
    
    public void beginScope(){
        symbol = new java.util.HashMap();
    }
    
    public void  setFunctionInfo(String funName, int start, int end){
        
    }
    
    public void setCurrentLineNumber(){
    
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

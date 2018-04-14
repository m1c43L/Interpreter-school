
package interpreter.debugger;

/**
 * MileStone 2.
 * Test the symbol table mechanism.
 * @author Michael
 */
public class FunctionEnvironmentRecord {
    
    private Table symbols;
    private String funcName;
    private Integer startingLineNo, endingLineNo, currentLineNo;
    
    public FunctionEnvironmentRecord(){
        symbols = new Table();
        symbols.beginScope();
    }
    
    public void beginScope(){
        symbols.beginScope();
    }
    
    public void  setFunctionInfo(String newFuncName, int start, int end){
        funcName = newFuncName;
        startingLineNo = start;
        endingLineNo = end;
    }
    
    public void setCurrentLineNumber(int newLineNo){
        currentLineNo = newLineNo;
    }
    
    public void put(String var, int val, boolean isBeingWatched){  
        symbols.put(var, val, isBeingWatched);
    }
    
    public void pop(int numToPop){
        symbols.endScope(numToPop);
    }
    
    /**
     * prints debug
     */
    public void dump(){
        StringBuilder builder = new StringBuilder();
        builder.append("(<");
        if(!symbols.keys().isEmpty()){
        for(String s: symbols.keys()){
            builder.append(s).append("/").append(symbols.get(s));
            builder.append(",");
        }
        builder.setCharAt( builder.length() - 1,'>');
        }else builder.append(">");
        
        builder.append(",").append((funcName == null)? "-": funcName).append(",")
                .append((startingLineNo == null)? "-": startingLineNo)
                .append(",")
                .append((endingLineNo == null)? "-": endingLineNo)
                .append(",")
                .append((currentLineNo == null)? "-" : currentLineNo)
                .append(")");
        
        System.out.println(builder);
    }
    
    
    /** 
    *   test the class
    */
    /*
    public static void main(String args[]){
        FunctionEnvironmentRecord t = new FunctionEnvironmentRecord();
        t.beginScope();
        t.dump();
        t.setFunctionInfo("g", 1, 20);
        t.dump();
        t.setCurrentLineNumber(5);
        t.dump();
        t.put("a",4);
        t.dump();
        t.put("b", 2);
        t.dump();
        t.put("c", 7);
        t.dump();
        t.put("a", 1);
        t.dump();
        t.pop(2);
        t.dump();
        t.pop(1);
        t.dump();
        
    }
    */
}


class Binder{
    
    private Object value;
    private String prevTop;   // prior symbol in same scope
    private Binder tail;      // prior binder for same symbol
                            // restore this when closing scope
    Binder(Object val, String prevTop, Binder tail, boolean isWatching) {
	this.value=val; this.prevTop=prevTop; this.tail=tail;
        if(isWatching) System.out.println(prevTop + ": " + value);
    }

    Object getValue() { return value; }
    String getPrevtop() { return prevTop; }
    Binder getTail() { return tail; }
    }


 class Table {

  private java.util.HashMap<String,Binder> symbols = new java.util.HashMap<String,Binder>();
  private String top;    // reference to last symbol added to
                         // current scope; this essentially is the
                         // start of a linked list of symbols in scope
  private Binder marks;  // scope mark; essentially we have a stack of
                         // marks - push for new scope; pop when closing
                         // scope

  public Table(){}


 /**
  * Gets the object associated with the specified symbol in the Table.
  */
  public Object get(String key) {
	Binder e = symbols.get(key);
	return e.getValue();
  }

 /**
  * Puts the specified value into the Table, bound to the specified Symbol.<br>
  * Maintain the list of symbols in the current scope (top);<br>
  * Add to list of symbols in prior scope with the same string identifier
  */
  public void put(String key, Object value, boolean isWatching) {
	symbols.put(key, new Binder(value, top, symbols.get(key), isWatching));
	top = key;
  }

 /**
  * Remembers the current state of the Table; push new mark on mark stack
  */
  public void beginScope() {
   // marks = new Binder(null,top,marks);
    top=null;
  }

 /**
  * Restores the table to what it was at the most recent beginScope
  *	that has not already been ended.
  */
  public void endScope(int numToPop) {
      
	while (numToPop > 0) {
	   Binder e = symbols.get(top);
	   if (e.getTail()!=null) symbols.put(top,e.getTail());
	   else symbols.remove(top);
	   top = e.getPrevtop();
           numToPop--;
	}
	//top=marks.getPrevtop();
	//marks=marks.getTail();
  }

  /**
   * @return a set of the Table's symbols.
   */
  public java.util.Set<String> keys() {return symbols.keySet();}
}
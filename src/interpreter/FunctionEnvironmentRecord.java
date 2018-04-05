
package interpreter;

/**
 * MileStone 2.
 * Test the symbol table mechanism.
 * @author Michael
 */
public class FunctionEnvironmentRecord {
    
    private Table symbols;
    private String funcName;
    private int startingLineNo, endingLineNo, currentLineNo;
    private Binder marks;
    private Symbol top;
    
    public FunctionEnvironmentRecord(){
        
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
        startingLineNo = newLineNo;
    }
    
    public void setVarVal(String var, int val){  
        symbols.put(new Symbol(var), val);
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
    private Symbol prevtop;   // prior symbol in same scope
    private Binder tail;      // prior binder for same symbol
                            // restore this when closing scope
    Binder(Object v, Symbol p, Binder t) {
	value=v; prevtop=p; tail=t;
    }

    Object getValue() { return value; }
    Symbol getPrevtop() { return prevtop; }
    Binder getTail() { return tail; }
    }

class Symbol{
    
    private String id;
    
    Symbol(String newId){
        id = newId;
    }
    
}

 class Table {

  private java.util.HashMap<Symbol,Binder> symbols = new java.util.HashMap<Symbol,Binder>();
  private Symbol top;    // reference to last symbol added to
                         // current scope; this essentially is the
                         // start of a linked list of symbols in scope
  private Binder marks;  // scope mark; essentially we have a stack of
                         // marks - push for new scope; pop when closing
                         // scope

  public Table(){}


 /**
  * Gets the object associated with the specified symbol in the Table.
  */
  public Object get(Symbol key) {
	Binder e = symbols.get(key);
	return e.getValue();
  }

 /**
  * Puts the specified value into the Table, bound to the specified Symbol.<br>
  * Maintain the list of symbols in the current scope (top);<br>
  * Add to list of symbols in prior scope with the same string identifier
  */
  public void put(Symbol key, Object value) {
	symbols.put(key, new Binder(value, top, symbols.get(key)));
	top = key;
  }

 /**
  * Remembers the current state of the Table; push new mark on mark stack
  */
  public void beginScope() {
    marks = new Binder(null,top,marks);
    top=null;
  }

 /**
  * Restores the table to what it was at the most recent beginScope
  *	that has not already been ended.
  */
  public void endScope() {
	while (top!=null) {
	   Binder e = symbols.get(top);
	   if (e.getTail()!=null) symbols.put(top,e.getTail());
	   else symbols.remove(top);
	   top = e.getPrevtop();
	}
	top=marks.getPrevtop();
	marks=marks.getTail();
  }

  /**
   * @return a set of the Table's symbols.
   */
  public java.util.Set<Symbol> keys() {return symbols.keySet();}
}
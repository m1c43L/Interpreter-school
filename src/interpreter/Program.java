package interpreter;
import interpreter.bytecode.ByteCode;
import java.util.ArrayList;
/**
 * Holds the bytecode program loaded from the file
 * 
 * @author Michael
 */
public class Program {
    
    private ArrayList <ByteCode> byteCodesList;
    
    public Program(){
        byteCodesList = new ArrayList();
    }
    
    public void add(ByteCode byteCode){
        byteCodesList.add(byteCode);
    }
    
    public ByteCode getCOde(int pgCounter){
        return byteCodesList.get(pgCounter);
    }
    
}

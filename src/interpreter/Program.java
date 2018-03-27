package interpreter;

import interpreter.bytecode.*;
import java.util.*;
/**
 * Holds the bytecode program loaded from the file
 * 
 * @author Michael
 */
public class Program {
    
    private Vector <ByteCode> byteCodesList;

    
    public Program(){
        byteCodesList = new<ByteCode> Vector();
    }
    
    public void add(ByteCode byteCode){
        byteCodesList.add(byteCode);
    }
    
    public ByteCode getCode(int pc){
        return byteCodesList.get(pc);
    }
    
    public void resolveAddress(){
        
        for(ByteCode byteCode: byteCodesList){
            
            if(byteCode.getClass().equals(FalseBranchCode.class)){
                rsFalseBranchAdrs((FalseBranchCode)byteCode);
            }else if(byteCode.getClass().equals(GotoCode.class)){
                rsGotoAdrs((GotoCode) byteCode);
            }else if(byteCode.getClass().equals(CallCode.class)){
                rsCallAdrs((CallCode) byteCode);
            }
        }
    }
    
    private void rsFalseBranchAdrs(FalseBranchCode code){
        
        for(int addrsPos = 0; addrsPos < byteCodesList.size(); addrsPos++){
            
            if(byteCodesList.get(addrsPos).getClass().
                    equals(LabelCode.class)){

                if(((LabelCode) byteCodesList.get(addrsPos)).
                        isEqual(code.getLabel())){
                    code.setAddress(addrsPos);
                    return;
                }         
            }   
            
        }
        
    }
    
    private void rsGotoAdrs(GotoCode code){
        
        for(int addrsPos = 0; addrsPos < byteCodesList.size(); addrsPos++){
            
            if(byteCodesList.get(addrsPos).getClass().
                    equals(LabelCode.class)){
                
                if(((LabelCode) byteCodesList.get(addrsPos)).
                        isEqual(code.getLabel())){
                    code.setAddress(addrsPos);
                    return;
                }              
            }   
        }
    }
    
    private void rsCallAdrs(CallCode code){
        
        for(int addrsPos = 0; addrsPos < byteCodesList.size(); addrsPos++){
            
            if(byteCodesList.get(addrsPos).getClass().
                    equals(LabelCode.class)){
                
                if(((LabelCode) byteCodesList.get(addrsPos)).
                        isEqual(code.getLabel())){
                    code.setAddress(addrsPos);
                    return;
                }              
            }   
        }
    }
    
}

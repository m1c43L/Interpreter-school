package interpreter;

import interpreter.bytecode.*;
import java.util.*;
/**
 * Holds the bytecode program loaded from the file
 * Resolve Addresses
 * 
 * @author Michael
 */
public class Program {
    
    private Vector <ByteCode> byteCodesList;
    private HashMap <String,Integer> address;

    
    public Program(){
        byteCodesList = new Vector();
        address = new HashMap();
    }
    
    public void add(ByteCode byteCode){
        byteCodesList.add(byteCode);
    }
    
    public ByteCode getCode(int pc){
        return byteCodesList.get(pc);
    }
    
    public void resolveAddress(){
        initAddress();
        byteCodesList.forEach((code) -> {
            if(code.getClass().equals(FalseBranchCode.class)){
                ((FalseBranchCode)code).setAddress(this);
            }else if(code.getClass().equals(GotoCode.class)){
                ((GotoCode)code).setAddress(this);
            }else if(code.getClass().equals(CallCode.class)){
                ((CallCode)code).setAddress(this);
            }
        });   
    }
    
    public int getAddress(String label){
        return (int)address.get(label);
    }
    
    private void initAddress(){
        for(int addressIndx = 0; addressIndx < byteCodesList.size();
                addressIndx++){
            
            if(getCode(addressIndx).getClass().equals(LabelCode.class)){
                address.put(((LabelCode) getCode(addressIndx)).getLabel(),
                        addressIndx);
            }
        } 
    }
    
    
    
    
    
    
    /*  initial approacg. tedious algorithm  code above is improved version.
    
    
    public void resolveAddress(){
        
        for(ByteCode byteCode: byteCodesList){
            
            if(byteCode.getClass().equals(FalseBranchCode.class)){
                resFalseBranchAdrs((FalseBranchCode)byteCode);
            }else if(byteCode.getClass().equals(GotoCode.class)){
                resGotoAdrs((GotoCode) byteCode);
            }else if(byteCode.getClass().equals(CallCode.class)){
                resCallAdrs((CallCode) byteCode);
            }
        }
    }
    
    private void resFalseBranchAdrs(FalseBranchCode code){
        
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
    
    private void resGotoAdrs(GotoCode code){
        
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
    
    private void resCallAdrs(CallCode code){
        
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
  
*/
}

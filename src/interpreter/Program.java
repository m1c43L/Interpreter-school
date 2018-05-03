package interpreter;

import interpreter.bytecode.*;
import interpreter.bytecode.debuggerByteCodes.LineCode;
import java.util.*;
/**
 * Holds the bytecode program loaded from the file
 * Resolve Addresses
 * 
 * @author Michael
 */
public class Program {
    
    private ArrayList <ByteCode> byteCodesList;
    private HashMap <String,Integer> address;
   
    
    public Program(){
        byteCodesList = new ArrayList();
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
     
        for(ByteCode code: byteCodesList){
            if(code instanceof FalseBranchCode){
                ((FalseBranchCode)code).setAddress(this);
            }else if(code instanceof GotoCode){
                ((GotoCode)code).setAddress(this);
            }else if(code instanceof CallCode){
                ((CallCode)code).setAddress(this);
            }
        }
    }
    
    public int getAddress(String label){
        return (int)address.get(label);
    }
    
    public ArrayList<Integer> getPossibleBreakPts(){
        ArrayList list = new ArrayList();
            
            for(ByteCode code: byteCodesList){
                if(code instanceof LineCode){
                    list.add(((LineCode)code).getLineNumber());
                }
            }
        list.trimToSize();
        return list;
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
    
   
    
   
}

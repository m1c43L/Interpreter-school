/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.Program;
import interpreter.VirtualMachine;

/**
 *
 * @author Michael
 */
public class CallCode extends ByteCode{
    
    
    private int address;
    private String label;
    
    @Override
    public void init(String [] args){
        
    }

    @Override
    public void execute(VirtualMachine VM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getLabel(){
        return label;
    }
    
    public void setAddress(int newAddress){
        address = newAddress;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 *
 * @author Michael
 */
public class LabelCode extends ByteCode {
    
    private String label;
    
    @Override
    public void execute(VirtualMachine VM) {
        
    }

    @Override
    public void init(String[] args) {
        label = args[1];
    }
    
    public String getLabel(){
        return label;
    }
}

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
public class DumpCode extends ByteCode{

    private boolean isOn;
    
    @Override
    public void execute(VirtualMachine VM) {
        if(isOn) VM.dumpStatToggle();
    }

    @Override
    public void init(String[] args) {
       isOn = (args[1].equals("ON"));
    }
    
}

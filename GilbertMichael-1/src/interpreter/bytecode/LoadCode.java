/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 * Push value of value loaded from offset
 * @author Michael
 */
public class LoadCode extends ByteCode{

    private int offset;
    private String id;   
    
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.loadRunStack(offset);
    }

    @Override
    public void init(String[] args) {
        offset = Integer.parseInt(args[1]);
        id = args[2];
    }

    @Override
    public boolean dumpCode() {
        System.out.println("LOAD " + offset + " " + id + "\t load " +  id);
        return true;
    }
    
}

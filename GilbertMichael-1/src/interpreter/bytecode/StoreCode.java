/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 * Store value to offset poped from the top.
 * @author Michael
 */
public class StoreCode extends ByteCode{

    private String id;
    private int value;
            
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.storeRunStack(value);
    }

    @Override
    public void init(String[] args) {
        value = Integer.parseInt(args[1]);
        id = args[2];
    }
    
    @Override
    public boolean dumpCode() {
        System.out.println("STORE " + value + " " + id + "\tstore " + id );
        return true;
    }
}

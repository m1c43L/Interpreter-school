/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;

/**
 *
 * @author Michael
 */
public class DebugWriteCode extends interpreter.bytecode.WriteCode{
    
    @Override
    public void execute(VirtualMachine VM){
        System.out.println("*************Write************");
        super.execute(VM);   
    }
}

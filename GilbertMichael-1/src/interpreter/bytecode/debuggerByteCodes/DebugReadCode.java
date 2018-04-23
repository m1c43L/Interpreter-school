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
public class DebugReadCode extends interpreter.bytecode.ReadCode{
    
    
    public void execute(VirtualMachine VM){
        System.out.println("***********Read***********");
        super.execute(VM);
    }
}

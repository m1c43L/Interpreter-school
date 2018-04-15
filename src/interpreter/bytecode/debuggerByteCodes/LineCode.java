/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;

/**
 *
 * @author Michael
 */
public class LineCode extends ByteCode {

    @Override
    public void execute(VirtualMachine VM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(String[] args) {
        
    }

    @Override
    public boolean dumpCode() {
        return false;
    }
    
}

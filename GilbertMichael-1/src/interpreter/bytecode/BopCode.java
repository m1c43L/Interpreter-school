/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;

/**
 * Performs Arithmetic and logical operations
 * @author Michael
 */
public class BopCode extends ByteCode {

    private String operator;
    
    private int doOperation(int topLevel, int secondLevel){
        switch(operator){
            case "+"  : return secondLevel + topLevel;
            case "-"  : return secondLevel - topLevel;
            case "*"  : return secondLevel * topLevel;
            case "/"  : return secondLevel / topLevel;
            case "==" : return (secondLevel == topLevel)? 1 : 0;       
            case "!=" : return (secondLevel != topLevel)? 1 : 0;
            case "<=" : return (secondLevel <= topLevel)? 1 : 0;
            case "<"  : return (secondLevel <  topLevel)? 1 : 0;
            case ">=" : return (secondLevel >= topLevel)? 1 : 0;
            case ">"  : return (secondLevel >  topLevel)? 1 : 0;
            case "&"  : return (secondLevel == 1& topLevel == 1)? 1 : 0; 
            case "|"  : return (secondLevel+topLevel > 0 &&
                    secondLevel + topLevel<= 2)? 1 : 0;
            
            default: return 0;
        }
    }
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.pushRunStack(doOperation(VM.popRunStack(),VM.popRunStack()));      
    }

    @Override
    public void init(String[] args) {
        operator = args[1];
    }

    @Override
    public boolean dumpCode() {
        System.out.println("BOP " + operator);
        return true;
    }
    
}

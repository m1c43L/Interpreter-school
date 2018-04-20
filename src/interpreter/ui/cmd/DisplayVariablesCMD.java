/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Michael
 */
public class DisplayVariablesCMD extends CMD{

    private String ids [];

    
    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        dvm.dumpFrames();
        if(ids.length == 0){
           ids = dvm.getAllVariables().toArray(new String[0]);
        }
        StringBuffer out = new StringBuffer();
        for(String s: ids){
            out.append(s)
                    .append(" : ")
                    .append(dvm.getCurrentValueOf(s))
                    .append(" ");
        }
        System.out.println(out);
    }

    @Override
    public void setParameters(String[] inputs) {
        ids = new String[inputs.length - 1];
        for(int indx = 1; indx < inputs.length; indx++){
            ids[indx - 1] = inputs[indx];
        }
        
    }

    @Override
    public String getStringDefinition() {
        return "dispv \t\t- (Display Variable) display specified variables in the current scope"
                + "\n\t\t  (eg. dispv n k). without parameter will print all variables in the current scope";
    }

    
    
}

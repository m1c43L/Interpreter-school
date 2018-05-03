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
    private StringBuilder output;
    
    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        if(ids.length == 0){
           ids = dvm.getAllVariables().toArray(new String[0]);
        }
        output = new StringBuilder();
        for(String id: ids){
            output.append(id)
                  .append(" : ")
                  .append(dvm.getCurrentValueOf(id))
                  .append(" ");
        }
  
    }

    @Override
    public void setParameters(String[] inputs) {
        ids = new String[inputs.length - 1];
        for(int indx = 1; indx < inputs.length; indx++){
            ids[indx - 1] = inputs[indx];
        }
        
    }

    @Override
    public String definition() {
        return "'v' \t- (Display Variable) display specified variables in the current scope"
                + "\n\t  (eg. dispv n k). without parameter will print all variables in the current scope";
    }

    @Override
    public StringBuilder output() {
        return output;
    }

    
    
}

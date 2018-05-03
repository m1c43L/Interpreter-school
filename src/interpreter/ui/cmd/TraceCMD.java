/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import interpreter.debugger.DebuggerVirtualMachine;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class TraceCMD extends CMD{

    private String param;
    private StringBuilder output;
    
    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        output = new StringBuilder();
        if(param.equals("")){
            output.append(dvm.isTraceOn()? "Trace is ON":"Trace is OFF");
        }else{
            if(param.equalsIgnoreCase("y")){
                dvm.setTrace(true);
                output.append("Trace set to ON.");
            }else if(param.equalsIgnoreCase("n")){
                dvm.setTrace(false);
                output.append("Trace set to OFF");
            }else
               System.out.println("Invalid parameter: " + param);
        }
    }

    @Override
    public void setParameters(String[] inputs) {
        param = "";
        if(inputs.length > 1){
            param = inputs[1];
        }
    }

    @Override
    public String definition() {
        return "'t' \t- (Set trace) t <option>; 'y' to turn on; 'n' to turn off; blank to print the state. "
                + "\n\t  (eg. 't y'  - will turn on trace mode; 't' will print 'Trace is ON')";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
    
}

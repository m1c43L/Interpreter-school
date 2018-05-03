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
public class SetBreakPointCMD extends CMD{
    private int [] breakPoints;
    private StringBuilder output;

    private void appendBreakPoints(java.util.ArrayList <Integer> listOfBreakPoints){
        output = new StringBuilder((listOfBreakPoints.size() > 0)? 
                "Break Point on lines: " : "No break point set.");
        for(int lineNo: listOfBreakPoints){
            output.append(lineNo).append(" ");
        }
    }
    
    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        
        if( breakPoints.length == 0){
            appendBreakPoints(dvm.getBreakPoints());
            return;
        }
        output = new StringBuilder();
        for(int i = 0; i < breakPoints.length; i++){
            if(dvm.canSetBreakPoint(breakPoints[i])){
                dvm.setBreakPoint(breakPoints[i]); 
                output.append("Break point set on line: ")
                      .append(breakPoints[i])
                      .append("\n");           
            }else
                output.append("******Cannot be set break point on line: ")
                      .append(breakPoints[i])
                      .append("\n");
        }
    }

    @Override
    public void setParameters(String[] inputs) {
        breakPoints = new int[inputs.length - 1];
        for(int i = 1; i < inputs.length; i++){
            breakPoints[i - 1] = Integer.parseInt(inputs[i]);
        }
        
    }
    

    @Override
    public String definition() {
        return "'+' \t- (Set Break Point) Sets break point in the source code. "
                + "\n\t   Each break points in the source code will be marked by '*'";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
}

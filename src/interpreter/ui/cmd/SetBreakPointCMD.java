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

    private void printBreakPoints(java.util.ArrayList <Integer> listOfBreakPoints){
        StringBuilder build = new StringBuilder("Break Point on lines: ");
        for(int lineNo: listOfBreakPoints){
            build.append(lineNo).append(" ");
        }
        System.out.println(build);
    }
    
    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        
        if( breakPoints.length == 0){
            printBreakPoints(dvm.getBreakPoints());
            return;
        }
        
        for(int i = 0; i < breakPoints.length; i++){
            if(dvm.canSetBreakPoint(breakPoints[i])){
                dvm.setBreakPoint(breakPoints[i]); 
                System.out.println("Break point set on line : " + breakPoints[i]);
            }else
                System.out.println("******Cannot set break point on line: " + breakPoints[i]);
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
    public String getStringDefinition() {
        return "'+' \t- (Set Break Point) Sets break point in the source code. "
                + "\n\t   Each break points in the source code will be marked by '*'";
    }
}

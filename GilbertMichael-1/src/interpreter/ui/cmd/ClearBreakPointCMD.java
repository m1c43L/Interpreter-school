/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import interpreter.debugger.DebuggerVirtualMachine;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class ClearBreakPointCMD extends CMD{
    private ArrayList<Integer> indexList;

    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        for(int i = 0; i < indexList.size(); i++){
            dvm.clearBreakPoint(indexList.get(i));
            System.out.println("Cleared break point on line: " + indexList.get(i));
        }
    }

    @Override
    public void setParameters(String[] inputs) {
        indexList = new ArrayList();
        for(int i = 1; i < inputs.length; i++){
            indexList.add(Integer.parseInt(inputs[i]));
        }
    }
    
    @Override
    public String getStringDefinition() {
        return "'-' \t- (Clear Break Point) Remove all the break points set.";
    }
}

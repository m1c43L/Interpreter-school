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
    private StringBuilder output;

    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        output = new StringBuilder("Cleared break point on line(s):");
        for(int i = 0; i < indexList.size(); i++){
            dvm.clearBreakPoint(indexList.get(i));
            output.append(indexList.get(i));
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
    public String definition() {
        return "'-' \t- (Clear Break Point) Remove all the break points set.";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
}

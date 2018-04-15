/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui;

import interpreter.ui.cmd.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


/**
 *
 * @author Michael
 */
public class Commands{
    
   private static HashMap <String,CMD> commands;
   
   static {
       commands = new HashMap();
       commands.put("sbp", new SetBreakPointCMD());
       commands.put("cbp", new ClearBreakPointCMD());
       commands.put("ce", new ContinueExecutionCMD());
       commands.put("dcf", new DisplayCurrentFunctionCMD());
       commands.put("cv", new DisplayVariablesCMD());
       commands.put("?", new HelpCMD());
       commands.put("qe", new QuitExecutionCMD());    
   }
   
   public static CMD get(String commandName){
       return commands.get(commandName);
   }
   
   public static Collection <CMD> getCommands(){
       return commands.values();
   }
   
  
}

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
       commands.put("set", new SetBreakPointCMD());
       commands.put("clear", new ClearBreakPointCMD());
       commands.put("continue", new ContinueExecutionCMD());
       commands.put("disp-c", new DisplayCurrentFunctionCMD());
       commands.put("disp-v", new DisplayVariablesCMD());
       commands.put("?", new HelpCMD());
       commands.put("quit", new QuitExecutionCMD());    
       commands.put("source", new PrintSourceCodeCMD());
   }
   
   public static CMD get(String commandName){
       return commands.get(commandName);
   }
   
   public static Collection <CMD> getCommands(){
       return commands.values();
   }
   
   public static boolean contains(String commandName){
       return commands.containsKey(commandName);
   }
   
  
}

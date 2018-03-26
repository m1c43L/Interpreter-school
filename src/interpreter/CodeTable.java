/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;
import java.util.HashMap;

/**
 *
 * @author Michael
 */
public class CodeTable {
    
    private static HashMap <String,String> byteCodeTable;
    
    public static void init()
    {
        byteCodeTable = new HashMap();
        byteCodeTable.put("ARGS", "ArgsCode");
        byteCodeTable.put("BOP", "BopCode");
        byteCodeTable.put("CALL", "CallCode");
        byteCodeTable.put("FALSEBRANCH", "FalseBranchCode");
        byteCodeTable.put("GOTO", "GotoCode");
        byteCodeTable.put("HALT", "HaltCode");
        byteCodeTable.put("LIT", "LitCode");
        byteCodeTable.put("LOAD", "LoadCode");
        byteCodeTable.put("POP", "PopCode");
        byteCodeTable.put("READ", "ReadCode");
        byteCodeTable.put("RETURN", "ReturnCode");
        byteCodeTable.put("STORE", "StoreCode");
        byteCodeTable.put("WRITE", "WriteCode");    
    }
    
    public static String get(String code)
    {
        return byteCodeTable.get(code);
    }
}

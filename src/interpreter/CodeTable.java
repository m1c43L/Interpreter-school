package interpreter;
import java.util.HashMap;

/**
 * Records table of ByteCodes to be used in ByteCodeLoader
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
        byteCodeTable.put("LABEL", "LabelCode");
        byteCodeTable.put("POP", "PopCode");
        byteCodeTable.put("READ", "ReadCode");
        byteCodeTable.put("RETURN", "ReturnCode");
        byteCodeTable.put("STORE", "StoreCode");
        byteCodeTable.put("WRITE", "WriteCode");    
        byteCodeTable.put("DUMP", "DumpCode");
    }
    
    public static void initDebuggerByteCodes(){
        byteCodeTable.put("LINE", "debuggerByteCodes.LineCode");
        byteCodeTable.put("FORMAL", "debuggerByteCodes.FormalCode");
        byteCodeTable.put("FUNCTION", "debuggerByteCodes.FunctionCode");
        byteCodeTable.replace("CALL", "debuggerByteCodes.DebugCallCode");
        byteCodeTable.replace("READ",  "debuggerByteCodes.DebugReadCode");
        byteCodeTable.replace("WRITE",  "debuggerByteCodes.DebugWriteCode");
        byteCodeTable.replace("HALT", "debuggerByteCodes.DebugHaltCode");
        byteCodeTable.replace("RETURN", "debuggerByteCodes.DebugReturnCode");
        byteCodeTable.replace("LIT", "debuggerByteCodes.DebugLitCode");
        
    }
    
    public static String get(String code)
    {
        return byteCodeTable.get(code);
    }
}

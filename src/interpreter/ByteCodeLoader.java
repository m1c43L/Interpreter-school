package interpreter;
import java.io.*;


/**
 * Loads the bytecodes from the file into the Virtual Machine.
 * Once loaded, get string from the CodeTable to construct an instance of
 * bytecode.
 * 
 * @author Michael
 */
public class ByteCodeLoader {
    
    private BufferedReader codeFile; 
            
    public ByteCodeLoader(String programFile) throws IOException{
        codeFile = new BufferedReader(new FileReader(programFile));
    }
    
    public Program loadCodes(){
      
        return new Program();
    }
}

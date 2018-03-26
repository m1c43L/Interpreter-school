/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 *
 * @author Michael
 */
public class ByteCodeLoader {
    private BufferedReader programFile;
    
    
    public ByteCodeLoader(String inputProgramFile) throws FileNotFoundException 
    {
        programFile = new BufferedReader(new FileReader(inputProgramFile));
    }
    
    
}

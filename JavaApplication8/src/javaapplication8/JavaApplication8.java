/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Admin
 */


public class JavaApplication8 {
public void listFilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            System.out.println(fileEntry.getName());
        }
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
       final File folder = new File("/home/you/Desktop");
listFilesForFolder(folder); 
        
        try (Stream<Path> paths = Files.walk(Paths.get("/home/you/Desktop"))) {
    paths
        .filter(Files::isRegularFile)
        .forEach(System.out::println);
} 
    }
    
}

package model.fileManagement;

import java.io.*;
import java.util.Scanner;

public class LineRemover {

    PrintWriter printer ;
    Scanner scanner ;

    File gameFile ;
    File tempFile ;

    public LineRemover(File gameFile){
        this.gameFile = gameFile ;
        initialize() ;
    }

    void initialize(){
        try {

            tempFile = new File("src/model/fileManagement/tempFile") ;

            printer = new PrintWriter (new FileWriter(tempFile)) ;
            scanner = new Scanner (new FileReader("src/model/fileManagement/game.data")) ;


        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void remove(String lineToRemove){
        while(scanner.hasNextLine()){
            String currentLine = scanner.nextLine() ;
            currentLine = currentLine.trim() ;
            lineToRemove = lineToRemove.trim() ;
            if(! currentLine.equals(lineToRemove)){
                printer.println(currentLine);
            }
        }
        gameFile.delete() ;
        tempFile.renameTo(new File("src/model/fileManagement/game.data")) ;
    }

}

package backend.fileManagement;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.Scanner;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import engine.ship.SpaceShip;

public class FileManager {

    File file = new File("src/backend/fileManagement/game.data");
    File file2 = new File("src/backend/fileManagement/savedGame");

    FileOutputStream fos ;
    BufferedOutputStream bos ;
    FileInputStream fis ;

    static PrintStream p ;
    static Scanner sc ;


    public FileManager() {
        initialize();
    }


    public void initialize() {
        try {
//			fos = new FileOutputStream(file);
//			fis = new FileInputStream(file);
            p = new PrintStream(file);
            sc = new Scanner(file2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//	   bos = new BufferedOutputStream(fos);
//       p = new PrintStream(bos);
//       sc = new Scanner(fis);
    }

    public void close() {
        p.close();
        sc.close();
    }

//	public void save(SpaceShip spaceShip) {
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.serializeNulls();
//		Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
//		p.println(gson.toJson(spaceShip));
////		System.out.println(gson.toJson(spaceShip));
//	}

//	public static SpaceShip load() {
//		Gson gson = new Gson();
//		return gson.fromJson(sc.nextLine(),SpaceShip.class);
//	}


}

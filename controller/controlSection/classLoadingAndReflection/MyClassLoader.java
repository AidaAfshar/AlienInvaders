package controller.controlSection.classLoadingAndReflection;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;

public class MyClassLoader extends ClassLoader{

    public MyClassLoader(ClassLoader parent){
        super(parent) ;
    }

    public Class<?> loadClass(String name , File file){

        byte[] classData = new byte[0] ;
        try {
            classData = Files.readAllBytes (file.toPath ()) ;

        } catch (IOException e) {
            e.printStackTrace ();
        }

        return defineClass (name,classData,0,classData.length) ;
    }


//    URL url ;
//    URL[] urlArray ;
//    URLClassLoader classLoader;
//
//    String filePath ;
//    String fileName ;
//    String directoryPath ;
//
//    public MyClassLoader(String filePath , String fileName){
//        this.filePath = filePath ;
//        this.fileName = fileName ;
//        this.directoryPath = "file:///" + getDirectoryPath (filePath) ;
//        //initialize() ;
//    }
//
//    public void initialize(){
//        try {
//
//            prepareClassLoader ();
//            loadClass ();
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace ();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace ();
//        }
//
//    }
//
//    void prepareClassLoader() throws MalformedURLException {
//        //System.out.println ("directory path: " + directoryPath);
//        //url = new URL (directoryPath) ;
//        url = new URL ( "file:///C:/Users/User/Desktop/.class files/") ;
//        urlArray = new URL[]{url};
//        classLoader = new URLClassLoader (urlArray) ;
//    }
//
//    void loadClass() throws ClassNotFoundException {
//        Class addedClass = classLoader.loadClass(fileName) ;
//        System.out.println (addedClass.getSimpleName());
//    }
//
//
//    static String getDirectoryPath(String filePath){
//        String directoryPath = new String () ;
//        int length = filePath.length () ;
//        for(int i=length-1 ; i>=0 ; i--){
//            if(filePath.charAt (i) == '\\') {
//                directoryPath = filePath.substring (0, i+1);
//                break;
//            }
//        }
//
//        return directoryPath ;
//    }




}

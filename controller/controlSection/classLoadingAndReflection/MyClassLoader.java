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


}

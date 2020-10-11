package com.company.lusnikov.nsu.lab3;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

public class Lab3 {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException, ClassNotFoundException {
        File file = new File(Lab3.class.getResource("/A").toURI());
        System.out.println(file);
        //for (File f : file.listFiles()) {
           // System.out.println(f);
            URL url = file.toURI().toURL();          // file:/c:/myclasses/
            URL[] urls = new URL[]{url};
            ClassLoader cl = new URLClassLoader(urls);
            System.out.println(cl);
            //Class cls = cl.loadClass(f.getName().split("\\.")[0]);

            Class cls = cl.loadClass("App");
            System.out.println("cls = " + cls);
          //  break;
       // }

    }
}

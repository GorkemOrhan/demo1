package com.example.demo1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyFileReader {
        public InputStream read(String dir){
            try {
                InputStream is = new FileInputStream(dir);
                return is;
            }
            catch (FileNotFoundException ex) {
                 //ex.getMessage();
                return null;
            }
        }

}

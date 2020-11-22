package com.freelancer.ui.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileOperations {
    private Properties prop;
    private String filePath;

    public FileOperations(String filePath) {
        this.filePath = filePath;
        this.readFromPropertyFile();
    }

    private void readFromPropertyFile() {
        InputStream inputStream = null;

        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(this.filePath);
            this.prop = new Properties();
            this.prop.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPropertyValue(String key) { return this.prop.getProperty(key); }


}

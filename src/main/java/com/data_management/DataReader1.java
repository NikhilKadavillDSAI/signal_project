package com.data_management;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class DataReader1 implements DataReader{

    File aFile;

    public DataReader1(String pathStr){
        aFile = new File(pathStr);

        System.out.println("DataReader created!!");      
    }

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        // TODO Auto-generated method stub

        try{
            Scanner fleReader = new Scanner(aFile);
            String[] lineIter;

            while(fleReader.hasNextLine()){
                lineIter = fleReader.nextLine().split(",");
                int patientId = Integer.parseInt(getString(0, lineIter));
                long timestamp = Long.parseLong(getString(1, lineIter));
                String label = getString(2, lineIter);
                double measurementValue = Double.parseDouble(getString(3, lineIter).split("%")[0]);

                // // String for debugging reason
                // System.out.println(patientId+"\t"+measurementValue+"\t"+label+"\t"+timestamp);

                dataStorage.addPatientData(patientId, measurementValue, label, timestamp);

            }
            fleReader.close();

        }catch(Exception e){
            System.out.println(e);
            throw new UnsupportedOperationException("File Read error");
        }
        
    }
    
    // To extract the data from given index in the given array
    private String getString(int pos, String[] toExtractfrm){
        return toExtractfrm[pos].split(": ")[1];
    }

}

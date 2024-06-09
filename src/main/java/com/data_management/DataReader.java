package com.data_management;

import java.io.IOException;

public interface DataReader {

    /**
     * Reads data from a specified source and stores it in the data storage.
     * 
     * @param dataStorage the storage where data will be stored
     * @throws IOException if there is an error reading the data
     */
    void startreadData(DataStorage dataStorage) throws IOException;

    /**
     * Stop the data reading process
     */
    void stopreadData();

    /**
     * Connect to the given WebSocket
     * @param sourse source of the websocket
     * @throws IOException
     */
    void connectToSource(String sourse) throws IOException;


}

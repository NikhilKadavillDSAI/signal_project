package com.data_management;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DataReaderWeb implements DataReader{

    AWebSocketClient client;

    public DataReaderWeb(String uri) throws URISyntaxException{
        client = new AWebSocketClient(new URI(uri));

    }

    @Override
    public void startreadData(DataStorage dataStorage) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startreadData'");
    }

    @Override
    public void stopreadData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopreadData'");
    }

    @Override
    public void connectToSource(String sourse) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connectToSource'");
    }

}

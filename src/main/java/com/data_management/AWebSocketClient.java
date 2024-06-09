package com.data_management;

import java.io.IOException;
import java.net.URI;
import org.java_websocket.client.WebSocketClient;

import org.java_websocket.handshake.ServerHandshake;

public class AWebSocketClient extends WebSocketClient {

    DataStorage storage;

    public AWebSocketClient(URI uri) {
        super(uri);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClose'");
    }

    @Override
    public void onError(Exception arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }

    @Override
    public void onMessage(String arg0) {
        // TODO Auto-generated method stub
        String[] data = arg0.split(",");
        int patientId = Integer.parseInt(data[0]);
        long time = Long.parseLong(data[1]);
        String label = data[2];
        if(data[3].endsWith("%"))data[3] = data[3].substring(0,data[3].length()-1);
        double val = Double.parseDouble(data[3]);

        storage.addPatientData(patientId, val, label, time);
        // throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onOpen'");
    }

    
}

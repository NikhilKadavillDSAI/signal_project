package com.alerts.AlertFactories;

import com.alerts.Alert;

public class AlertFactory {

    public Alert createAlert(String patientID, String condition, long timestamp){
        return new Alert(patientID, condition, timestamp);
    }
    
}

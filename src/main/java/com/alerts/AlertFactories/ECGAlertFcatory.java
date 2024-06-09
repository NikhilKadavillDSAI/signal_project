package com.alerts.AlertFactories;

import com.alerts.Alert;

public class ECGAlertFcatory extends AlertFactory{

    public Alert createAlert(String patientID, long timestamp){
        return createAlert(patientID, "ECG Alert", timestamp);
    }
}
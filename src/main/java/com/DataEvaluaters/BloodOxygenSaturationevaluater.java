package com.DataEvaluaters;

import java.util.ArrayList;
import java.util.List;

import com.alerts.Alert;
import com.data_management.PatientRecord;

public class BloodOxygenSaturationevaluater {

    // Evaluate data to find trends in Blood Oxygen Saturation
    public static Alert evaluateBO(List<PatientRecord> records){
        List<PatientRecord> beforeVals = new ArrayList<>();

        // Low Saturation alert
        for(PatientRecord aRecord: records){
            if(aRecord.getRecordType().equals("BloodSaturation")){

                beforeVals.add(aRecord);
                double curVal = aRecord.getMeasurementValue();
                if(curVal<92){
                    //trigger an alert return boolean value bro
                    System.out.println("Alert!! "+"BloodSaturation"+" rapid decrease");
                    return new Alert(aRecord.getPatientId()+"", "Blood Oxygen Saturation Rapid decrease",aRecord.getTimestamp());
                }
            }
        }
        
        // Decreasing Trend alert
        for(int i=0; i<beforeVals.size(); i++){
            PatientRecord aRecord = beforeVals.get(i);
            double curr = aRecord.getMeasurementValue();
            for(int j=i+1; j<beforeVals.size(); j++){
                if((Math.abs(beforeVals.get(j).getMeasurementValue()-curr))>=5){
                    // trigger an alert return boolean value bro
                    System.out.println("Alert!! BloodSaturation trend alert");
                    return new Alert(aRecord.getPatientId()+"", "Blood Oxygen Saturation trend alert",aRecord.getTimestamp());
                }
            }
        }
        return null;
    }
    
}

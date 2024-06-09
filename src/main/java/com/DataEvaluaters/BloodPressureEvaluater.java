package com.DataEvaluaters;

import java.util.ArrayList;
import java.util.List;

import com.alerts.Alert;
import com.data_management.PatientRecord;

public class BloodPressureEvaluater {
    

    // Evaluate data to find trends in Blood Pressure ( Diastolic and Systolic )
    public static Alert evaluateBP(List<PatientRecord> records, String BPType){

        List<PatientRecord> bpRecords = new ArrayList<>();

        double[] contriants = new double[2];

        if (BPType.equals("SystolicPressure")) contriants=new double[]{180.0,90.0};
        if (BPType.equals("DiastolicPressure")) contriants=new double[]{120.0,60.0};
        
        // High and low BP alert 
        for(PatientRecord aRecord: records){
            if(aRecord.getRecordType().equals(BPType)){
                
                bpRecords.add(aRecord);
                if (bpRecords.size()>3) bpRecords.remove(0);

                double currBP = aRecord.getMeasurementValue();

                if (currBP>=contriants[0]){
                    // Trigger an alert for the High BP
                    System.out.println("Alert!! "+BPType+" High");
                    return new Alert(aRecord.getPatientId()+"", BPType+" high", aRecord.getTimestamp());
                }else if (currBP<=contriants[1]){
                    // Trigger an alert for low BP
                    System.out.println("Alert!! "+BPType+" Low");
                    return new Alert(aRecord.getPatientId()+"", BPType+" low", aRecord.getTimestamp());
                }

                if(bpRecords.size()<3)continue;

                double chk = bpRecords.get(0).getMeasurementValue()-bpRecords.get(1).getMeasurementValue();

                if(Math.abs(chk)>=10){
                    double chk2 = bpRecords.get(1).getMeasurementValue()-bpRecords.get(2).getMeasurementValue();
                    if (Math.abs(chk2)>=10)return new Alert(aRecord.getPatientId()+"", BPType+" trend", aRecord.getTimestamp());
                }
            }
        }
        return null;
        
    }
}

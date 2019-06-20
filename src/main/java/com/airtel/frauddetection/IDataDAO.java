package com.airtel.frauddetection;

import java.util.List;
import com.airtel.frauddetection.DataModel;

public interface IDataDAO {

    List<DataModel> getAllData();
   // DataModel getDataByTxnId(int txnId);
    void addData(DataModel dataModel);
    //void updateDataModel (DataModel dataModel);
    //void deleteDataModel(int dataId);
    //boolean dataExists(int txnId, long customer_no);
}
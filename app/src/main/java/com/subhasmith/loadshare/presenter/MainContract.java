package com.subhasmith.loadshare.presenter;
import com.subhasmith.loadshare.model.ResponseIFSCDataAPI;

import java.util.ArrayList;

/**
 * Created by bpn on 12/6/17.
 */

public interface MainContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer(String ifscCode);

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToView(ResponseIFSCDataAPI data);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetIFSCDataIntractor {

        //void getIFSCData(MainPresenterImpl );

        interface OnFinishedListener {
            void onFinished(ResponseIFSCDataAPI responseIFSCDataAPI);
            void onFailure(Throwable t);
        }

        void getIFSCData(OnFinishedListener onFinishedListener, String id);
    }
}

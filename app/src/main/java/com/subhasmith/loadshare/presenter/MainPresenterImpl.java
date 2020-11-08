package com.subhasmith.loadshare.presenter;


import com.subhasmith.loadshare.model.ResponseIFSCDataAPI;

/**
 * Created by bpn on 12/7/17.
 */

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetIFSCDataIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetIFSCDataIntractor getIFSCDataIntractor;
    private String id;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetIFSCDataIntractor getIFSCDataIntractor, String ifsc) {
        this.mainView = mainView;
        this.getIFSCDataIntractor = getIFSCDataIntractor;
        this.id = ifsc;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getIFSCDataIntractor.getIFSCData(this, id);

    }

    @Override
    public void requestDataFromServer(String id) {
        getIFSCDataIntractor.getIFSCData(this, id);
    }


    @Override
    public void onFinished(ResponseIFSCDataAPI responseIFSCDataAPI) {
        if(mainView != null){
            mainView.setDataToView(responseIFSCDataAPI);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}

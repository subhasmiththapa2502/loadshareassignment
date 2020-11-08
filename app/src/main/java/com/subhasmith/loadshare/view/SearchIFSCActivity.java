package com.subhasmith.loadshare.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.subhasmith.loadshare.R;
import com.subhasmith.loadshare.model.IFSCDataRealm;
import com.subhasmith.loadshare.model.RealmUser;
import com.subhasmith.loadshare.model.ResponseIFSCDataAPI;
import com.subhasmith.loadshare.presenter.GetIFSCDataImpl;
import com.subhasmith.loadshare.presenter.MainContract;
import com.subhasmith.loadshare.presenter.MainPresenterImpl;
import com.subhasmith.loadshare.utils.CircularTransform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;

public class SearchIFSCActivity extends AppCompatActivity implements MainContract.MainView {

    Realm realm;
    ImageView ivSelectedUserImage;
    TextView name;


    EditText etIFSC;
    private MainContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_i_f_s_c);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        initUI();
        updateUI();


    }

    private void updateUI() {
        RealmUser realmUser = realm.where(RealmUser.class).findFirst();

        if (realmUser.getFirstName() != null && realmUser.getLastName() != null) {
            name.setText(realmUser.getFirstName()+" "+realmUser.getLastName());
        }

        if (realmUser.getUserPic() != null) {

            Glide.with(SearchIFSCActivity.this)
                    .load(BitmapFactory.decodeFile(realmUser.getUserPic()))
                    .fitCenter()
                    .centerCrop()
                    .transform(new CircularTransform(SearchIFSCActivity.this))
                    .into(ivSelectedUserImage);


        }
    }

    public void initUI() {
        ivSelectedUserImage = findViewById(R.id.ivSelectedUserImage);
        name = findViewById(R.id.name);
        etIFSC = findViewById(R.id.etIFSC);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public void showDataFromResponseinDialog(ResponseIFSCDataAPI data) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.dialog_layout,
                        null);
        dialog.setView(customLayout);

        TextView branch = customLayout.findViewById(R.id.branch);
        TextView center = customLayout.findViewById(R.id.center);
        TextView district = customLayout.findViewById(R.id.district);
        TextView state = customLayout.findViewById(R.id.state);
        TextView address = customLayout.findViewById(R.id.address);
        TextView contact = customLayout.findViewById(R.id.contact);
        TextView micr = customLayout.findViewById(R.id.micr);
        TextView upi = customLayout.findViewById(R.id.upi);
        TextView rtgs = customLayout.findViewById(R.id.rtgs);
        TextView city = customLayout.findViewById(R.id.city);
        TextView neft = customLayout.findViewById(R.id.neft);
        TextView imps = customLayout.findViewById(R.id.imps);
        TextView bank = customLayout.findViewById(R.id.bank);
        TextView bankCode = customLayout.findViewById(R.id.bankCode);
        TextView ifsc = customLayout.findViewById(R.id.ifsc);

        ImageView close = customLayout.findViewById(R.id.close);
        Button done = customLayout.findViewById(R.id.done);
        if (data.getBranch() != null){
            branch.setText(data.getBranch());
        }
        if (data.getCentre() != null){
            center.setText(data.getCentre());
        }
        if (data.getDistrict() != null){
            district.setText(data.getDistrict());
        }
        if (data.getState() != null){
            state.setText(data.getState());
        }
        if (data.getAddress() != null){
            address.setText(data.getAddress());
        }
        if (data.getContact() != null){
            contact.setText(data.getContact());
        }
        if (data.getMicr() != null){
            micr.setText(data.getMicr());
        }
        if (data.getUpi() != null){
            upi.setText(data.getUpi());
        }
        if (data.getRtgs() != null){
            rtgs.setText(data.getRtgs());
        }
        if (data.getCity() != null){
            city.setText(data.getCity());
        }
        if (data.getNeft() != null){
            neft.setText(data.getNeft());
        }
        if (data.getImps() != null){
            imps.setText(data.getImps());
        }
        if (data.getBank() != null){
            bank.setText(data.getBank());
        }
        if (data.getBankCode() != null){
            bankCode.setText(data.getBankCode());
        }
        if (data.getIfsc() != null){
            ifsc.setText(data.getIfsc());
        }

        dialog.show();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                IFSCDataRealm ifscDataRealm = new IFSCDataRealm();
                ifscDataRealm.setBranch(data.getBranch());
                ifscDataRealm.setCentre(data.getCentre());
                ifscDataRealm.setDistrict(data.getDistrict());
                ifscDataRealm.setState(data.getState());
                ifscDataRealm.setAddress(data.getAddress());
                ifscDataRealm.setContact(data.getContact());
                ifscDataRealm.setMicr(data.getMicr());
                ifscDataRealm.setUpi(data.getUpi());
                ifscDataRealm.setRtgs(data.getRtgs());
                ifscDataRealm.setCity(data.getCity());
                ifscDataRealm.setNeft(data.getNeft());
                ifscDataRealm.setImps(data.getImps());
                ifscDataRealm.setBank(data.getBank());
                ifscDataRealm.setBankCode(data.getBankCode());
                ifscDataRealm.setIfsc(data.getIfsc());

                realm.insertOrUpdate(ifscDataRealm);

            }
        });
    }

    public boolean isPresentInRealm(String ifscCode) {

        IFSCDataRealm ifscDataRealm = realm.where(IFSCDataRealm.class)
                                        .contains("ifsc", ifscCode)
                                        .findFirst();
        return ifscDataRealm != null;

    }

    // Function to validate
    // IFSC (Indian Financial System) Code
    // using regular expression.
    public static boolean isValidIFSCode(String str)
    {
        // Regex to check valid IFSC Code.
        String regex
                = "^[A-Z]{4}0[A-Z0-9]{6}$";

        // Compile the ReGex
        Pattern p
                = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Pattern class contains matcher()
        // method to find matching between
        // the given string and
        // the regular expression.
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    @Override
    public void setDataToView(ResponseIFSCDataAPI data) {
        showDataFromResponseinDialog(data);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    public void search(View view) {
        String IFSCtoSearch = String.valueOf(etIFSC.getText()).trim();
        if (IFSCtoSearch != null && IFSCtoSearch.length() == 11 && isValidIFSCode(IFSCtoSearch)) {
            if (isPresentInRealm(IFSCtoSearch)){

                IFSCDataRealm ifscDataRealm = realm.where(IFSCDataRealm.class)
                        .contains("ifsc", IFSCtoSearch)
                        .findFirst();

                if (ifscDataRealm != null){
                    ResponseIFSCDataAPI responseIFSCDataAPI = new ResponseIFSCDataAPI();

                    responseIFSCDataAPI.setBranch(ifscDataRealm.getBranch());
                    responseIFSCDataAPI.setCentre(ifscDataRealm.getCentre());
                    responseIFSCDataAPI.setDistrict(ifscDataRealm.getDistrict());
                    responseIFSCDataAPI.setState(ifscDataRealm.getState());
                    responseIFSCDataAPI.setAddress(ifscDataRealm.getAddress());
                    responseIFSCDataAPI.setContact(ifscDataRealm.getContact());
                    responseIFSCDataAPI.setMicr(ifscDataRealm.getMicr());
                    responseIFSCDataAPI.setUpi(ifscDataRealm.getUpi());
                    responseIFSCDataAPI.setRtgs(ifscDataRealm.getRtgs());
                    responseIFSCDataAPI.setCity(ifscDataRealm.getCity());
                    responseIFSCDataAPI.setNeft(ifscDataRealm.getNeft());
                    responseIFSCDataAPI.setImps(ifscDataRealm.getImps());
                    responseIFSCDataAPI.setBank(ifscDataRealm.getBank());
                    responseIFSCDataAPI.setBankCode(ifscDataRealm.getBankCode());
                    responseIFSCDataAPI.setIfsc(ifscDataRealm.getIfsc());

                    showDataFromResponseinDialog(responseIFSCDataAPI);
                }


            }else {
                presenter = new MainPresenterImpl(SearchIFSCActivity.this, new GetIFSCDataImpl(), IFSCtoSearch);
                presenter.requestDataFromServer(IFSCtoSearch);

            }
        } else {
            Toast.makeText(this, "Please Enter a Valid IFSC Code", Toast.LENGTH_SHORT).show();
        }
    }
}
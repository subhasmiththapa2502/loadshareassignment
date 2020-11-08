package com.subhasmith.loadshare.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.subhasmith.loadshare.R;
import com.subhasmith.loadshare.model.RealmUser;
import com.subhasmith.loadshare.utils.CircularTransform;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    ImageView ivIcon;
    ImageView ivUserImage;
    Button proceed;
    private static int RESULT_LOAD_IMAGE = 1;
    Realm realm;

    String imageSelected;

    TextInputEditText etFirstName;
    TextInputEditText etLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        initUI();

    }

    private void initUI(){
        ivIcon = findViewById(R.id.ivIcon);
        ivUserImage = findViewById(R.id.ivUserImage);
        proceed = findViewById(R.id.proceed);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
    }

    PermissionListener galleryPermission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            pickImages();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }
    };

    private void pickImages() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void selectImage(View view) {
        TedPermission.with(MainActivity.this)
                .setPermissionListener(galleryPermission)
                .setDeniedMessage(R.string.storage_denied_message)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    public void saveUserData(String firstName, String lastName, String imagePic) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmUser realmUser = new RealmUser();
                realmUser.setFirstName(firstName);
                realmUser.setLastName(lastName);
                realmUser.setUserPic(imagePic);

                realm.insertOrUpdate(realmUser);
            }
        }, () -> {
            startActivity(new Intent(MainActivity.this, SearchIFSCActivity.class));
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            ivIcon.setVisibility(View.GONE);
            Glide.with(MainActivity.this)
                    .load(BitmapFactory.decodeFile(picturePath))
                    .fitCenter()
                    .centerCrop()
                    .transform(new CircularTransform(MainActivity.this))
                    .into(ivUserImage);
            imageSelected = picturePath;

        }


    }

    public void proceed(View view) {

        String firstName = String.valueOf(etFirstName.getText());
        String lastName = String.valueOf(etLastName.getText());
        if ((firstName != null && !firstName.isEmpty()) &&
                lastName != null && !lastName.isEmpty() &&
                imageSelected != null && !imageSelected.isEmpty()){
            saveUserData(firstName,lastName,imageSelected);
        }else {

            if (lastName == null || lastName.isEmpty()){
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            }
            if (firstName == null || firstName.isEmpty()){
                Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
            }
            if (imageSelected == null || imageSelected.isEmpty()){
                Toast.makeText(this, "Please Choose Image", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
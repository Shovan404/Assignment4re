package com.e.hamrobazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.e.hamrobazar.api.HamrobazarApi;
import com.e.hamrobazar.model.User;
import com.e.hamrobazar.serverresponse.ImageResponse;
import com.e.hamrobazar.serverresponse.UserResponse;
import com.e.hamrobazar.strictmode.StrictModeClass;
import com.e.hamrobazar.url.URL;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private CheckBox cb1, cb2, cb3;
    private Button btnRregister;
    private ImageView imgProfile;
    private EditText etREmail, etName, etRPassword, etRCPassword, etPhone, etMphone, etAddress, etAddress1, etAddress2;
    String imagePath;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //binding
        etREmail=findViewById(R.id.etREmail);
        etName=findViewById(R.id.etName);
        etRPassword=findViewById(R.id.etRPassword);
        etRCPassword=findViewById(R.id.etRCPassword);
        etPhone=findViewById(R.id.etPhone);
        etAddress=findViewById(R.id.etAddress);
        etAddress1=findViewById(R.id.etAddress1);
        etAddress2=findViewById(R.id.etAddress2);
        cb1=findViewById(R.id.cb1);
        cb2=findViewById(R.id.cb2);
        cb3=findViewById(R.id.cb3);
        imgProfile = findViewById(R.id.imgProfile);
        btnRregister=findViewById(R.id.btnRregister);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnRregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    try {
                        saveImageOnly();
                    } catch (Exception e) {
                        imageName = "";
                    }
                    signUp();
                } else {
                    Toast.makeText(SignUpActivity.this, "Invalid entries", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private boolean validate() {
        if (TextUtils.isEmpty(etREmail.getText())) {
            etREmail.setError("Enter email");
            etREmail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etName.getText())) {
            etName.setError("Enter full name");
            etName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etRPassword.getText())) {
            etRPassword.setError("Enter password");
            etRPassword.requestFocus();
            return false;
        } else if (etRPassword.getText().toString().length() < 6) {
            etRPassword.setError("Minimum 6 characters");
            return false;
        } else if (etRPassword.getText().toString().length() > 20) {
            etRPassword.setError("Maximum 20 characters");
            return false;
        } else if (TextUtils.isEmpty(etRCPassword.getText())) {
            etRCPassword.setError("Enter confirm password");
            etRCPassword.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etMphone.getText())) {
            etMphone.setError("Enter mobile phone number");
            etMphone.requestFocus();
            return false;
        } else if (!TextUtils.isEmpty(etMphone.getText())) {
            String first = etMphone.getText().toString().charAt(0) + "";
            if (!first.equals("9")) {
                etMphone.setError("invalid mobile number");
                return false;
            }
        } else if (etMphone.getText().toString().length() != 10) {
            etMphone.setError("Mobile number should be 10 digit");
            return false;
        } else if (TextUtils.isEmpty(etAddress.getText())) {
            etAddress.setError("Enter area location");
            etAddress.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etAddress1.getText())) {
            etAddress1.setError("Enter city name");
            etAddress1.requestFocus();
            return false;
        } else if (!cb3.isChecked()) {
            cb3.setError("You must agree to terms of use");
            cb3.requestFocus();
            return false;
        } else if (!etRCPassword.getText().toString().equals(etRCPassword.getText().toString())) {
            etRCPassword.setError("Incorrect confirm password");
            etRCPassword.requestFocus();
            return false;
        }
        return true;
    }




    private void signUp() {
        String email = etREmail.getText().toString();
        String fullname = etName.getText().toString();
        String password = etRPassword.getText().toString();
        String phone = etPhone.getText().toString();
        String mobile = etMphone.getText().toString();
        String address1 = etAddress.getText().toString();
        String address2 = etAddress1.getText().toString();
        String address3 = etAddress2.getText().toString();

        User users = new User(email, fullname, password, phone, mobile, address1, address2, address3, imageName);

        HamrobazarApi hamrobazarAPI = URL.getInstance().create(HamrobazarApi.class);
        Call<UserResponse> signUpCall = hamrobazarAPI.registerUser(users);

        signUpCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignUpActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void BrowseImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            Uri uri = data.getData();
            imgProfile.setImageURI(uri);
            imagePath = getRealPathFromUri(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        HamrobazarApi hamrobazarAPI = URL.getInstance().create(HamrobazarApi.class);
        Call<ImageResponse> responseBodyCall = hamrobazarAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            //Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}

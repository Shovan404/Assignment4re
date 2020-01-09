package com.e.hamrobazar.bll;

import com.e.hamrobazar.api.HamrobazarApi;
import com.e.hamrobazar.serverresponse.UserResponse;
import com.e.hamrobazar.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {
    boolean loginFlag = false;

    public boolean Authentication(String email, String password) {

        HamrobazarApi hamrobazarAPI = URL.getInstance().create(HamrobazarApi.class);
        Call<UserResponse> usersCall = hamrobazarAPI.checkUser(email, password);

        try {
            Response<UserResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                URL.token += loginResponse.body().getToken();
                loginFlag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginFlag;
    }
}

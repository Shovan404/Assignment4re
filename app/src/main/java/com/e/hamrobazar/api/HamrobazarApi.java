package com.e.hamrobazar.api;

import com.e.hamrobazar.model.Products;
import com.e.hamrobazar.model.User;
import com.e.hamrobazar.serverresponse.ImageResponse;
import com.e.hamrobazar.serverresponse.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HamrobazarApi {

    @POST("signup")
    Call<UserResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("products")
    Call<List<Products>>getProduct();
}

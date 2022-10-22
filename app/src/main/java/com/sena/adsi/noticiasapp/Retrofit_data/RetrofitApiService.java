package com.sena.adsi.noticiasapp.Retrofit_data;



import com.sena.adsi.noticiasapp.DetalleActivity;
import com.sena.adsi.noticiasapp.Modelo.Noticia;
import com.sena.adsi.noticiasapp.Modelo.SignUpResponse;
import com.sena.adsi.noticiasapp.Modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApiService {
    @GET("noticias.php")
    Call<List<Noticia>> getNoticias();

    @GET("detalle_noticia.php")
    Call<List<DetalleActivity>> getDetalle(@Query("idnoticia")
                                           int idnoticia);
    @GET("/getuser.php")
    Call<List<Usuario>> getUsersList();

    @FormUrlEncoded // annotation used in POST type requests
    @POST("register.php")
        // API's endpoints
    Call<SignUpResponse> registration(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("logintype") String logintype);
}

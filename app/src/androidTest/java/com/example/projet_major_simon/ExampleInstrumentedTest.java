package com.example.projet_major_simon;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.projet_major_simon.http.RetrofitUtil;
import com.example.projet_major_simon.http.Service;
import com.example.projet_major_simon.transfer.SigninRequest;
import com.example.projet_major_simon.transfer.SigninResponse;
import com.example.projet_major_simon.transfer.SignupRequest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void textsignup() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.post();
        SignupRequest signup = new SignupRequest();
        signup.username = "test2";
        signup.password= "123";
        Call<SignupRequest> call = service.Signuprequest(signup);
        Response<SignupRequest> response = call.execute();
        SignupRequest resultat = response.body();
        String username = new String();
        username = response.body().username;
        Log.i("RETROFIT", resultat.toString());
    }

    @Test
    public void textsignin() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.post();
        SigninRequest signin = new SigninRequest();
        signin.username = "test1";
        signin.password= "123";
        Call<SigninRequest> call = service.Signinrequest(signin);
        Response<SigninRequest> response = call.execute();
        SigninRequest resultat = response.body();
        Log.i("RETROFIT", resultat.toString());
    }

    @Test
    public void testsignout() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.post();

        Call<String> call = service.Signoutrequest();
        Response<String> response = call.execute();
        Log.i("RETROFIT", response.body());
    }
}
package cl.aguzman.prueba4.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import cl.aguzman.prueba4.models.Countries;
import retrofit2.Call;
import retrofit2.Response;

public class GetCountry extends AsyncTask<Object, Object, List<Countries>> {

    @Override
    protected List<Countries> doInBackground(Object... params) {
        Country country = new Interceptor().getCountry();
        Call<List<Countries>> listCall = country.countryPath(params[0]);
        try {
            Response<List<Countries>> response = listCall.execute();
            if(response.code() == 200 && response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

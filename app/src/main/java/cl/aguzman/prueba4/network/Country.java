package cl.aguzman.prueba4.network;

import java.util.List;

import cl.aguzman.prueba4.models.Countries;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Country {
    @GET("{name}")
    Call<List<Countries>> countryPath(@Path("name")Object name);
}

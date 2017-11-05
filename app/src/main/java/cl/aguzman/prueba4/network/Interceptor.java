package cl.aguzman.prueba4.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Interceptor {
   private final static String URL_BASE = "https://restcountries.eu/rest/v2/name/";
   public Country getCountry(){
       Retrofit interceptor = new Retrofit.Builder()
               .baseUrl(URL_BASE)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       Country requestCountry = interceptor.create(Country.class);
       return requestCountry;
   }
}

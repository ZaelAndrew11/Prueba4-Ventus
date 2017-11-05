package cl.aguzman.prueba4.adapters;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import cl.aguzman.prueba4.R;

public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private LayoutInflater inflater;
    private String title;
    private String capital;
    private String region;
    private String subRegion;
    private int population;

    public MapInfoWindowAdapter(LayoutInflater inflater, String title, String capital, String region, String subRegion, int population) {
        this.inflater = inflater;
        this.title = title;
        this.capital = capital;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getInfoContents(Marker marker) {
        View v = inflater.inflate(R.layout.info_window, null);
        TextView titleCountry = (TextView) v.findViewById(R.id.titleCountryTv);
        TextView capitalCountry = (TextView) v.findViewById(R.id.capitalTv);
        TextView regionCountry = (TextView) v.findViewById(R.id.regionTv);
        TextView subRegionCountry = (TextView) v.findViewById(R.id.subRegionTv);
        TextView populationCountry = (TextView) v.findViewById(R.id.populationTv);

        titleCountry.setText(title);
        capitalCountry.setText(capital);
        regionCountry.setText(region);
        subRegionCountry.setText(subRegion);
        String populationFormat = NumberFormat.getNumberInstance().format(population);
        populationCountry.setText("Población: " + populationFormat + " habitantes");

        return v;
    }


}

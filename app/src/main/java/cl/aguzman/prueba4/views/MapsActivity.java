package cl.aguzman.prueba4.views;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cl.aguzman.prueba4.R;
import cl.aguzman.prueba4.adapters.MapInfoWindowAdapter;
import cl.aguzman.prueba4.models.Countries;
import cl.aguzman.prueba4.network.GetCountry;

import static cl.aguzman.prueba4.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public GoogleMap mMap;
    private final static int MY_CODE_PERMISSION = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        final EditText editText = (EditText) findViewById(R.id.userSearchEt);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int textSize = editText.getText().toString().trim().length();
                if (textSize > 0){
                    mMap.clear();
                    String countryUser = editText.getText().toString().trim();
                    new BackgroundCountries().execute(countryUser);
                }else{
                    editText.setError("Campo Vacio");
                }
                return false;
            };
        });

        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, MY_CODE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int checkPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (requestCode == MY_CODE_PERMISSION) {
            if (permissions.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_map));
    }


    private class BackgroundCountries extends GetCountry {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MapsActivity.this);
            progressDialog.setTitle("Buscando País");
            progressDialog.setMessage("Espere un momento...");
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Countries> countries) {
            progressDialog.dismiss();
            if (countries != null && countries.get(0).getLatlng().length != 0) {
                Countries get = countries.get(0);
                float lat = get.getLatlng()[0];
                float lng = get.getLatlng()[1];
                String name = get.getName();
                String capital = "Capital: " + get.getCapital();
                String region = "Región: " + get.getRegion();
                String subregion = "Sub Regiión: " +  get.getSubregion();
                int popopulation = get.getPopulation();
                LatLng location = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(location)).showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                mMap.setInfoWindowAdapter(new MapInfoWindowAdapter(LayoutInflater.from(MapsActivity.this), name, capital, region, subregion, popopulation));
            } else {
                Toast.makeText(MapsActivity.this, "País no encontrado", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

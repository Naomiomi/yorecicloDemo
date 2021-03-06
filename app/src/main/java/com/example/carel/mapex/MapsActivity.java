package com.example.carel.mapex;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private RequestQueue queue;
    private JSONArray puntosTotales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        queue = Volley.newRequestQueue(this);
        obtenerDatos();

        Button botonPlastico = (Button) findViewById(R.id.plastico);
        Button botonPapel = (Button) findViewById(R.id.papel);
        Button botonElec = (Button) findViewById(R.id.elect);
        Button botonAlum = (Button) findViewById(R.id.alu);

// FUNCION DEL BOTON PARA MARCAR LOS PUNTOS DE ACOPIO DE PLASTICO
        botonPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //limpiamos el mapa por si hay marcadores
                mMap.clear();


                Log.d(TAG, "onClick: BOTON PLASTICO!");
                for (int i = 0; i < puntosTotales.length(); i++) {

                    try {
                        JSONObject punto = puntosTotales.getJSONObject(i);
                        if (punto.getJSONArray("materials").length() == 1) {
                            if (punto.getJSONArray("materials").getJSONObject(0).getString("name").compareTo("Plastico") == 0) {
                                Log.d(TAG, "Encontré un punto de plasttico");
                                LatLng punto2 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                mMap.addMarker(new MarkerOptions().position(punto2).title(punto.getString("name")));
                            }
                        } else{
                            for(int x=0;x<punto.getJSONArray("materials").length();x++){
                                if (punto.getJSONArray("materials").getJSONObject(x).getString("name").compareTo("Plastico") == 0) {
                                    Log.d(TAG, "Encontré un punto de plasttico");
                                    LatLng punto3 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                    mMap.addMarker(new MarkerOptions().position(punto3).title(punto.getString("name")));
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        //FUNCION BOTON PAPEL

        botonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //limpiamos el mapa por si hay marcadores
                mMap.clear();


                Log.d(TAG, "onClick: BOTON PAPEL!");
                for (int i = 0; i < puntosTotales.length(); i++) {

                    try {
                        JSONObject punto = puntosTotales.getJSONObject(i);
                        if (punto.getJSONArray("materials").length() == 1) {
                            if (punto.getJSONArray("materials").getJSONObject(0).getString("name").compareTo("Papel") == 0) {
                                Log.d(TAG, "Encontré un punto de papel");
                                LatLng punto2 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                mMap.addMarker(new MarkerOptions().position(punto2).title(punto.getString("name")));
                            }
                        } else{
                            for(int x=0;x<punto.getJSONArray("materials").length();x++){
                                if (punto.getJSONArray("materials").getJSONObject(x).getString("name").compareTo("Papel") == 0) {
                                    Log.d(TAG, "Encontré un punto de papel");
                                    LatLng punto3 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                    mMap.addMarker(new MarkerOptions().position(punto3).title(punto.getString("name")));
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //FUNCION DE BOTON ALUMNIO
        botonAlum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //limpiamos el mapa por si hay marcadores
                mMap.clear();


                Log.d(TAG, "onClick: BOTON ALUMINIO!");
                for (int i = 0; i < puntosTotales.length(); i++) {

                    try {
                        JSONObject punto = puntosTotales.getJSONObject(i);
                        if (punto.getJSONArray("materials").length() == 1) {
                            if (punto.getJSONArray("materials").getJSONObject(0).getString("name").compareTo("Alumnio") == 0) {
                                Log.d(TAG, "Encontré un punto de alumnio");
                                LatLng punto2 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                mMap.addMarker(new MarkerOptions().position(punto2).title(punto.getString("name")));
                            }
                        } else{
                            for(int x=0;x<punto.getJSONArray("materials").length();x++){
                                if (punto.getJSONArray("materials").getJSONObject(x).getString("name").compareTo("Aluminio") == 0) {
                                    Log.d(TAG, "Encontré un punto de aluminio");
                                    LatLng punto3 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                    mMap.addMarker(new MarkerOptions().position(punto3).title(punto.getString("name")));
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        botonElec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //limpiamos el mapa por si hay marcadores
                mMap.clear();


                Log.d(TAG, "onClick: BOTON ELECTRONICOS!");
                for (int i = 0; i < puntosTotales.length(); i++) {

                    try {
                        JSONObject punto = puntosTotales.getJSONObject(i);
                        if (punto.getJSONArray("materials").length() == 1) {
                            if (punto.getJSONArray("materials").getJSONObject(0).getString("name").compareTo("Electronicos") == 0) {
                                Log.d(TAG, "Encontré un punto de Electronicos");
                                LatLng punto2 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                mMap.addMarker(new MarkerOptions().position(punto2).title(punto.getString("name")));
                            }
                        } else{
                            for(int x=0;x<punto.getJSONArray("materials").length();x++){
                                if (punto.getJSONArray("materials").getJSONObject(x).getString("name").compareTo("Electronicos") == 0) {
                                    Log.d(TAG, "Encontré un punto de Electronicos");
                                    LatLng punto3 = new LatLng(punto.getDouble("lat"), punto.getDouble("lng"));
                                    mMap.addMarker(new MarkerOptions().position(punto3).title(punto.getString("name")));
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //Menú Lateral

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void obtenerDatos(){

    String url = "https://api.myjson.com/bins/11auqe";

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
                    Log.d(TAG,"Entre al response");
            try {
                puntosTotales = response.getJSONArray("puntos");
                    Log.d(TAG,String.valueOf(puntosTotales.length()));
                for (int i=0 ; i< puntosTotales.length();i++){
                    JSONObject punto = puntosTotales.getJSONObject(i);
                    Double longitud = punto.getDouble("lng");
                    Double latitud = punto.getDouble("lat");
                    Log.d(TAG,String.valueOf( latitud));
                    Log.d(TAG,String.valueOf( longitud));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d(TAG,"Ocurrio un error");
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });

     queue.add(request);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /// Add a marker in Sydney and move the camera
        LatLng punto = new LatLng(32.6469, -115.446);
      //  mMap.addMarker(new MarkerOptions().position(punto).title("Fundación Hélice A.C"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto,11));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(punto));

    }

    public void AcercaDeNosotrosLaunch(View view){
        Intent acerca_de_nosotros = new Intent(this, AcercaDeHelice.class);
        startActivity(acerca_de_nosotros);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_eco_tips) {
            Intent ecotips = new Intent(this, Ecotips.class);
            startActivity(ecotips);
        } else if (id == R.id.nav_como_reciclar) {
            Intent como_reciclar = new Intent(this, ComoReciclar.class);
            startActivity(como_reciclar);
        } else if (id == R.id.nav_acerca_helice) {
            Intent acerca_de_helice = new Intent(this, AcercaDeHelice.class);
            startActivity(acerca_de_helice);
        } else if (id == R.id.nav_acerca_yoreciclo) {
            Intent acerca_de_yo_reciclo = new Intent(this, AcercaDeYoReciclo.class);
            startActivity(acerca_de_yo_reciclo);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void CargarFragmento(Fragment fragment){
        FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction().replace(R.id.drawer_layout, fragment).commit();
    }
}




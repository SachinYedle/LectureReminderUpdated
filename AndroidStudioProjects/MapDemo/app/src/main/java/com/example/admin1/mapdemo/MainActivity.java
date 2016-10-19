package com.example.admin1.mapdemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener,GoogleMap.OnMarkerClickListener {

    private GoogleApiClient googleApiClient;
    private Location location;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private LocationRequest locationRequest;

    private static final LatLng  HYDERABAD = new LatLng(17.385044, 78.486671);
    private static final LatLng PUNE = new LatLng(18.520430, 73.856744);
    private static final LatLng MUMBAI = new LatLng(19.075984, 72.877656);
    private static final LatLng DELHI = new LatLng(28.704059, 77.102490);
    private static final LatLng VIZAG = new LatLng(17.686816, 83.218482);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

    }

    public void getMyLocation(View view){

        checkPermission();
        if(!checkGpsStatus()){
            Toast.makeText(this,"please enable gps location..",Toast.LENGTH_SHORT).show();
            return;
        }

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mgoogleMap) {

                Log.i("Map Activity","onreadyMap");
                googleMap = mgoogleMap;
                if(googleMap == null)
                    Log.i("Map Activity","googleMap is null");
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient();
                    googleMap.setMyLocationEnabled(true);
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    public final int REQUSTED_CODE = 99;
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

    }

    public boolean checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUSTED_CODE);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUSTED_CODE);
            }
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUSTED_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (googleApiClient == null) {

                            if(checkGpsStatus()){
                                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                                    buildGoogleApiClient();
                                }
                            }
                        }
                        googleMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

    public boolean checkGpsStatus(){

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return true;
        }else{
            showGPSDisabledAlertToUser();
        }
        return false;
    }

    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    public void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i("Map Activity","onConnection suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("Map Activity","onConnection failed");
    }

    @Override
    public void onLocationChanged(Location mlocation) {
        location = mlocation;
        Log.i("Map Activity","OnLocation Changed"+location);


        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(150, 150, conf);
        Canvas canvas1 = new Canvas(bmp);

        Paint color = new Paint();
        color.setTextSize(35);
        color.setColor(Color.BLACK);

        canvas1.drawBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_web_site), 0,0, color);

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        /*MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.image1));
        marker = googleMap.addMarker(markerOptions);
        */
        addMarkers(googleMap,latLng,bmp);


        ArrayList<LatLng> points = new ArrayList<LatLng>();
        points.add(latLng);
        points.add(HYDERABAD);
        points.add(PUNE);
        points.add(MUMBAI);
        points.add(DELHI);
        points.add(VIZAG);
        PolylineOptions polyLineOptions = new PolylineOptions();
        polyLineOptions.addAll(points);
        polyLineOptions.width(35);
        polyLineOptions.color(Color.RED);
        googleMap.addPolyline(polyLineOptions);


        //String url = getMapsApiDirectionsUrl();
        //ReadTask downloadTask = new ReadTask(this);
        //downloadTask.execute(url);

        googleMap.setOnMarkerClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }
    public void addMarkers(GoogleMap mgoogleMap,LatLng latLng,Bitmap bmp){
        googleMap.addMarker(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .title("Riktam")
                .draggable(true));

        googleMap.addMarker(new MarkerOptions().position(HYDERABAD)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .title("hyderabad"));
        googleMap.addMarker(new MarkerOptions().position(PUNE)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .title("Pune"));
        googleMap.addMarker(new MarkerOptions().position(MUMBAI)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .title("Mumbai"));
        googleMap.addMarker(new MarkerOptions().position(DELHI)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .title("Delhi"));

        googleMap.addMarker(new MarkerOptions().position(VIZAG)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .title("Vizag"));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i("Map Activity","onMarkerClick");
        return false;
    }

    private String getMapsApiDirectionsUrl() {
        String src = "origin=" +"17.430288,78.444989";

        String dest = "destination=" + VIZAG.latitude + "," + VIZAG.longitude;

        String waypoints = "&waypoints=" +
                HYDERABAD.latitude + "," + HYDERABAD.longitude+
                "|" + PUNE.latitude + "," + PUNE.longitude +
                "|" + MUMBAI.latitude + "," + MUMBAI.longitude +
                "|" + DELHI.latitude + "," + DELHI.longitude;

        String srcDestinationWaypoints = src +"&"+ dest + waypoints;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + srcDestinationWaypoints;

        Log.i("url",""+url);
        return url;
    }


    private class ReadTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        Context context;
        ReadTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle("Please wait....");
            dialog.setMessage("Loading data");
            dialog.setIndeterminate(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    cancel(true);
                }
            });
        }

        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            super.onPostExecute(result);
            new ParserTask(MainActivity.this).execute(result);

        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        private ProgressDialog dialog;
        private Context context;
        ParserTask(Context context){
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle("Please wait....");
            dialog.setMessage("Loading data");
            dialog.setIndeterminate(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    cancel(true);
                }
            });
        }

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(5);
                polyLineOptions.color(Color.RED);
            }

            googleMap.addPolyline(polyLineOptions);
            dialog.dismiss();
        }
    }

}

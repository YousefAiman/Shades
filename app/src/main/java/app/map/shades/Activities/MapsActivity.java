package app.map.shades.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.map.shades.Adapters.NearByMapAdapter;
import app.map.shades.Fragments.EventsFragment;
import app.map.shades.Fragments.NearbyFragment;
import app.map.shades.Fragments.PlaceFragment;
import app.map.shades.Objects.NearByPlace;
import app.map.shades.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,NearByMapAdapter.RecyclerViewClickListener {

  private GoogleMap mMap;
  private RecyclerView nearByRv;
  private FusedLocationProviderClient fusedLocationProviderClient;
  private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
  private boolean locationPermissionGranted;
  private Location lastKnownLocation;
  private static final int DEFAULT_ZOOM = 12;
  private SearchView searchView;
  private String countryCode;
  private AutocompleteSessionToken sessionToken;
  private PlacesClient placesClient;
  private ArrayList<NearByPlace> places;
  private FrameLayout mapFrameLayout;
//  FrameLayout mapFrameLayout;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);

    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//    mapFrameLayout = findViewById(R.id.mapFrameLayout);
    nearByRv = findViewById(R.id.nearByRv);
    searchView = findViewById(R.id.searchView);
    mapFrameLayout = findViewById(R.id.mapFrameLayout);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

    countryCode = Locale.getDefault().getCountry();
    Places.initialize(MapsActivity.this,"AIzaSyBBINIBhu_2LBm-eVrpMvidiWAZEk7OY_M");
    placesClient = Places.createClient(this);
    sessionToken = AutocompleteSessionToken.newInstance();

  }


  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    updateLocationUI();

    getDeviceLocation();


//    // Add a marker in Sydney and move the camera
//    LatLng sydney = new LatLng(-34, 151);
//    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        getQueryPredictions(newText);
        return true;
      }
    });

   places = new ArrayList<>();
  NearByPlace place1 = new NearByPlace();
  place1.setDistance(2);
  place1.setName("Sushi Place");
  place1.setRating(3.5);
  place1.setImageUrl("https://previews.123rf.com/images/ferli/ferli1611/ferli161100084/65258436-close-up-portrait-of-japanese-food-mini-maki-sushi-platter-on-white-wooden-table-served-with-wasabi-.jpg");

    NearByPlace place2 = new NearByPlace();
    place2.setName("NomNom");
    place2.setDistance(2);
    place2.setRating(5);
    place2.setImageUrl("https://previews.123rf.com/images/ferli/ferli1611/ferli161100084/65258436-close-up-portrait-of-japanese-food-mini-maki-sushi-platter-on-white-wooden-table-served-with-wasabi-.jpg");


    NearByPlace place3 = new NearByPlace();
    place3.setName("Sys Electronic");
    place3.setDistance(2);
    place3.setRating(4);
    place3.setImageUrl("https://ak.jogurucdn.com/media/image/p25/place-2014-10-21-10-4206e70a338b241a5f6194edca333da5.jpg");


    NearByPlace place4 = new NearByPlace();
    place4.setName("tech infotech");
    place4.setDistance(2);
    place4.setRating(5);
    place4.setImageUrl("https://ak.jogurucdn.com/media/image/p25/place-2014-10-21-10-4206e70a338b241a5f6194edca333da5.jpg");


    NearByPlace place5 = new NearByPlace();
    place5.setName("Mall");
    place5.setRating(2);
    place5.setDistance(3.4);
    place5.setImageUrl("https://ak.jogurucdn.com/media/image/p25/place-2014-10-21-10-4206e70a338b241a5f6194edca333da5.jpg");

    places.add(place1);
    places.add(place2);
    places.add(place3);
    places.add(place4);
    places.add(place5);

  NearByMapAdapter nearByMapAdapter = new NearByMapAdapter(places,MapsActivity.this,R.layout.nearby_map_item_layout,this::recyclerViewListClicked);
  nearByRv.setAdapter(nearByMapAdapter);


    ((BottomNavigationView)findViewById(R.id.bottomNavigationView)).setOnNavigationItemSelectedListener(item -> {
//      mapFrameLayout.setVisibility(View.VISIBLE);
      switch (item.getItemId()){

        case R.id.nearMeNavItem:

//          new NearbyFragment().show(getSupportFragmentManager(),"nearby");
          getSupportFragmentManager().beginTransaction().replace(mapFrameLayout.getId(),new NearbyFragment()).addToBackStack(null).commit();
//          startActivity(new Intent(MapsActivity.this,NearByActivity.class));
          break;
        case R.id.eventsNavItem:
//          new EventsFragment().show(getSupportFragmentManager(),"event");
          getSupportFragmentManager().beginTransaction().replace(mapFrameLayout.getId(),new EventsFragment()).addToBackStack(null).commit();
//          startActivity(new Intent(MapsActivity.this,EventsActivity.class));
          break;

        case R.id.userNavItem:

          break;

        case R.id.moreNavItem:

          break;
      }
      return true;
    });
  }
  private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
    if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
      locationPermissionGranted = true;
    } else {
      ActivityCompat.requestPermissions(this,
              new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
              PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    locationPermissionGranted = false;

    switch (requestCode) {
      case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          locationPermissionGranted = true;
        }
      }
    }
    updateLocationUI();
  }

  private void updateLocationUI() {
    if (mMap == null) {
      return;
    }
    try {
      if (locationPermissionGranted) {
        mMap.setMyLocationEnabled(true);
//        mMap.getUiSettings().setMyLocationButtonEnabled(true);
      } else {
        mMap.setMyLocationEnabled(false);
//        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        lastKnownLocation = null;
        getLocationPermission();
      }
    } catch (SecurityException e)  {
      Log.d("locationError", e.getMessage());
    }
  }

  private void getDeviceLocation() {
    try {
      if (locationPermissionGranted) {
        Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
        locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
          @Override
          public void onComplete(@NonNull Task<Location> task) {
            if (task.isSuccessful()) {
              // Set the map's camera position to the current location of the device.
              lastKnownLocation = task.getResult();
              if (lastKnownLocation != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(lastKnownLocation.getLatitude(),
                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
              }
            } else {

              Log.d("locationError", "locationError"+task.getException());
//              mMap.moveCamera(CameraUpdateFactory
//                      .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
//              mMap.getUiSettings().setMyLocationButtonEnabled(false);
            }
          }
        }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            Log.d("locationError",e.getMessage());
          }
        });
      }
    } catch (SecurityException e)  {
      Log.e("Exception: %s", e.getMessage(), e);
    }
  }


  void getQueryPredictions(String search){
    FindAutocompletePredictionsRequest autoCompleteRequest = FindAutocompletePredictionsRequest.builder()
            .setCountry(countryCode)
            .setTypeFilter(TypeFilter.ESTABLISHMENT)
            .setSessionToken(sessionToken)
            .setQuery(search)
            .build();
    placesClient.findAutocompletePredictions(autoCompleteRequest).addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
      @Override
      public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
        if(task.isSuccessful()){
          Toast.makeText(MapsActivity.this, "susfa", Toast.LENGTH_SHORT).show();
            FindAutocompletePredictionsResponse autoCompleteResponse = task.getResult();
            List<AutocompletePrediction> predictions = autoCompleteResponse.getAutocompletePredictions();
            String[] predictionNames = new String[predictions.size()];
            for(int i=0;i<predictions.size();i++){
              predictionNames[i] = predictions.get(i).getFullText(null).toString();
            }
            if(searchView.getSuggestionsAdapter() == null){
              searchView.setSuggestionsAdapter(new SimpleCursorAdapter(MapsActivity.this,
                      R.layout.search_suggestion_item,
                      null,
                      predictionNames,
                      new int[]{R.id.tv_deal}));
            }
        }else{
          Log.d("autoCompleteError","couldn't fetch prediction for: "+search);
        }
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Log.d("autoCompleteError",e.getMessage());
      }
    });

  }


  @Override
  public void onBackPressed() {
    super.onBackPressed();
    getSupportFragmentManager().popBackStack();
  }

  @Override
  public void recyclerViewListClicked(View v, int position) {
    DialogFragment fragment = new PlaceFragment();
    Bundle b = new Bundle();
    b.putSerializable("place",places.get(position));
    fragment.setArguments(b);
    fragment.show(getSupportFragmentManager(),"place");
  }
}
package app.map.shades.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.squareup.picasso.Picasso;

import java.util.Map;

import app.map.shades.Objects.NearByPlace;
import app.map.shades.R;

public class PlaceFragment extends DialogFragment implements OnMapReadyCallback {

  Toolbar toolbar;
  ImageView placeIv;
  public PlaceFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);

  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_place, container, false);
    toolbar = view.findViewById(R.id.toolbar);
    placeIv = view.findViewById(R.id.placeIv);
    view.findViewById(R.id.toolbar_layout).getLayoutParams().height = (int) (getResources().getDisplayMetrics().heightPixels*0.415);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    FragmentManager fm = getChildFragmentManager();


    SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentByTag("mapFragment");
    if (mapFragment == null) {
      mapFragment = new SupportMapFragment();
      FragmentTransaction ft = fm.beginTransaction();
      ft.add(R.id.mapFragmentContainer, mapFragment, "mapFragment");
      ft.commit();
      fm.executePendingTransactions();
    }
    mapFragment.getMapAsync(this);

    assert getArguments() != null;
    NearByPlace place = (NearByPlace) getArguments().getSerializable("place");
    toolbar.setTitle(place.getName());
    Picasso.get().load(place.getImageUrl()).fit().centerCrop().into(placeIv);
    toolbar.setNavigationOnClickListener(v->dismiss());
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    inflater.inflate(R.menu.share, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {

  }
}
package app.map.shades.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.map.shades.Adapters.NearByMapAdapter;
import app.map.shades.Objects.NearByPlace;
import app.map.shades.R;

public class NearbyFragment extends Fragment implements NearByMapAdapter.RecyclerViewClickListener {
  ArrayList<NearByPlace> places;
  public NearbyFragment() {
  }

//  static NearbyFragment newInstance() {
//    return new NearbyFragment();
//  }
//
//  @Override
//  public void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
//    setHasOptionsMenu(true);
//  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_nearby, container, false);
    Toolbar toolbar =view.findViewById(R.id.toolbar);

//    ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(v-> getActivity().onBackPressed());
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setRecyclerAdapter((RecyclerView)view.findViewById(R.id.foodRv));
    setRecyclerAdapter((RecyclerView)view.findViewById(R.id.shopRv));
    setRecyclerAdapter((RecyclerView)view.findViewById(R.id.mallRv));

  }


  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    inflater.inflate(R.menu.filter, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }
  void setRecyclerAdapter(RecyclerView recyclerView){
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

    NearByMapAdapter nearByMapAdapter = new NearByMapAdapter(places, getContext(),R.layout.nearby_item_layout,this);
    recyclerView.setAdapter(nearByMapAdapter);
  }


  @Override
  public void recyclerViewListClicked(View v, int position) {
    DialogFragment fragment = new PlaceFragment();
    Bundle b = new Bundle();
    b.putSerializable("place",places.get(position));
    fragment.setArguments(b);
    fragment.show(getChildFragmentManager(),"place");
  }
}
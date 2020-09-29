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

import app.map.shades.Adapters.EventsAdapter;
import app.map.shades.Adapters.OrganizerAdapter;
import app.map.shades.Objects.Event;
import app.map.shades.Objects.Organizer;
import app.map.shades.R;


public class EventsFragment extends Fragment {

  public EventsFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_events, container, false);

    Toolbar toolbar =view.findViewById(R.id.toolbar);
//    ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(v->getActivity().onBackPressed());
    return view;
  }
//
//  static EventsFragment newInstance() {
//    return new EventsFragment();
//  }
//
//  @Override
//  public void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
//    setHasOptionsMenu(true);
//  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    inflater.inflate(R.menu.filter, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setEventRecyclerAdapter((RecyclerView)view.findViewById(R.id.event1Rv));
    setEventRecyclerAdapter((RecyclerView)view.findViewById(R.id.event2Rv));
    setOrganizerRecyclerAdapter((RecyclerView)view.findViewById(R.id.organizerRv));

  }

  void setEventRecyclerAdapter(RecyclerView recyclerView){
    ArrayList<Event> events = new ArrayList<>();
    Event event = new Event();
    event.setName("Icecream Making Offline");
    event.setImageUrl("https://cleobuttera.com/wp-content/uploads/2019/06/kk-ice-cream-full-mood-720x405.jpg");
    event.setMaker("swad Cooking Instistue , Surfsdsfs");
    event.setTime("07\nSep");

    for(int i=0;i<7;i++){
      events.add(event);
    }

    EventsAdapter eventsAdapter = new EventsAdapter(events, getContext(),R.layout.event_item);
    recyclerView.setAdapter(eventsAdapter);
  }

  void setOrganizerRecyclerAdapter(RecyclerView recyclerView){
    ArrayList<Organizer> organizers = new ArrayList<>();
    Organizer organizer = new Organizer();
    organizer.setImageUrl("https://cleobuttera.com/wp-content/uploads/2019/06/kk-ice-cream-full-mood-720x405.jpg");
    organizer.setName("Swad Cooking");
    organizer.setProfession("Institute, surat");

    for(int i=0;i<7;i++){
      organizers.add(organizer);
    }

    OrganizerAdapter organizerAdapter = new OrganizerAdapter(organizers, getContext(),R.layout.orgnizer_item_layout);
    recyclerView.setAdapter(organizerAdapter);
  }

}
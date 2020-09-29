package app.map.shades.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SearchView;

import app.map.shades.R;

public class SearchSuggestionsAdapter extends CursorAdapter {

  private  LayoutInflater layoutInflater;
  private Context context;
  private SearchView searchView;
  public SearchSuggestionsAdapter(Context context, Cursor c, boolean autoRequery) {
    super(context, c, autoRequery);
    this.context = context;
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
    return layoutInflater.inflate(R.layout.search_suggestion_item,viewGroup,false);
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {


  }
}

package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.AutoComplete;

/**
 * Created by MonkeyFather on 15/03/2018.
 */


public class AutoCompleteAdapter extends ArrayAdapter implements Filterable {
    private final LayoutInflater inflater;
    private List<AutoComplete> results;

    public AutoCompleteAdapter(Context context, int resource) {
        super(context, resource);
        results = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public AutoComplete getItem(int position) {
        return results.get(position);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    try {
                        String term = constraint.toString();
                        results = new AutoSearch().execute(term).get();
                    } catch (Exception e) {
                        Log.d("HUS", "EXCEPTION " + e);
                    }
                    filterResults.values = results;
                    filterResults.count = results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };

        return myFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.autocomplete_item, parent, false);
        TextView resultView = view.findViewById(R.id.autoCompleteItem);
        final AutoComplete result = getItem(position);
        resultView.setText(result.getName());
        return view;
    }

    public AutoComplete getItemById(int id) {
        return results.get(id);
    }

    private class AutoSearch extends AsyncTask<String, String, List<AutoComplete>> {

        @Override
        protected List<AutoComplete> doInBackground(String... params) {
            try {
                final List<AutoComplete> resultsArr = new ArrayList<AutoComplete>();
                final CharacterStyle STYLE_BOLD = new StyleSpan(Typeface.BOLD);
                String input = params[0];
                GeoDataClient mGeoDataClient = Places.getGeoDataClient(getContext(), null);
                AutocompleteFilter cityFilter = new AutocompleteFilter.Builder()
                        .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                        .build();
                final Task<AutocompletePredictionBufferResponse> results = mGeoDataClient.getAutocompletePredictions(input, null, cityFilter);
                Log.d("wowGeo", "result: " + results);
                results.addOnCompleteListener(new OnCompleteListener<AutocompletePredictionBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<AutocompletePredictionBufferResponse> task) {
                        AutocompletePredictionBufferResponse finalResults = task.getResult();
                        for (AutocompletePrediction autoComplete : finalResults) {
                            CharSequence address = autoComplete.getFullText(STYLE_BOLD);
                            String placeId = autoComplete.getPlaceId();
                            Log.d("wowAuto", "address: " + address);
                            AutoComplete autoComplete1 = new AutoComplete();
                            autoComplete1.setName(address);
                            autoComplete1.setPlaceId(placeId);
                            resultsArr.add(autoComplete1);
                        }
                        notifyDataSetChanged();
                        finalResults.release();

                    }
                });

                return resultsArr;

            } catch (Exception e) {
                Log.d("HUS", "EXCEPTION " + e);
                return null;
            }
        }
    }
}




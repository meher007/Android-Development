package adapters;

/**
 * Created by home on 28/07/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.meher.Inventory.R;

import java.util.List;

import entities.ViewCad;



/**
 * Created by Meher Khan on 27/07/2017.
 */


public class ViewListAdapter extends ArrayAdapter<ViewCad> {


    private Context context;
    private List <ViewCad> graphicalview;

    public ViewListAdapter(Context context, List<ViewCad> graphicalview)
    {
        super(context, R.layout.view_list_layout,graphicalview);

        this.context=context;
        this.graphicalview=graphicalview;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_list_layout,parent,false);
        ViewCad image= graphicalview.get(position);

        ImageView imageViewPage = (ImageView) view.findViewById(R.id.imageViewPhoto);
        imageViewPage.setImageResource(image.getpageid());
        //TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        //textViewName.setText(image.getName());

        return view;


    }
}

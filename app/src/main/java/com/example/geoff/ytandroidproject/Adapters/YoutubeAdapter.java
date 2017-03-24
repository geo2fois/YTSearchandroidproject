package com.example.geoff.ytandroidproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geoff.ytandroidproject.GetImg;
import com.example.geoff.ytandroidproject.R;
import com.example.geoff.ytandroidproject.models.YoutubeResult;

import java.util.List;

/**
 * Created by geoff on 22/03/2017.
 */

public class YoutubeAdapter extends ArrayAdapter<YoutubeResult> {
    public YoutubeAdapter(Context context, List<YoutubeResult> YoutubeResults) {
        super(context, 0, YoutubeResults);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_view,parent, false);
        }

        YoutubeResultViewHolder viewHolder = (YoutubeResultViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new YoutubeResultViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.author = (TextView) convertView.findViewById(R.id.author);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.thumbnails = (ImageView) convertView.findViewById(R.id.thumbnails);
            convertView.setTag(viewHolder);
        }

        YoutubeResult result = getItem(position);


        viewHolder.title.setText(result.getTitle());
        viewHolder.author.setText(result.getAuthor());
        viewHolder.description.setText(result.getDescription());
        new GetImg(viewHolder.thumbnails).execute(result.getThumbnails());

        return convertView;
    }


    private class YoutubeResultViewHolder{
        public TextView title;
        public TextView author;
        public TextView date;
        public TextView description;
        public ImageView thumbnails;
    }
}

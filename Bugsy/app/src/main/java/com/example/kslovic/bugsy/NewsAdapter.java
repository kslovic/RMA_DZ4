package com.example.kslovic.bugsy;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
        ArrayList<News> mNews;
        public NewsAdapter(ArrayList<News> news) {
            mNews = news;
        }
        @Override
        public int getCount() {
            return this.mNews.size();
        }
        @Override
        public Object getItem(int position) {
            return this.mNews.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NewsViewHolder newsViewHolder = null;
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_news, parent, false);
                newsViewHolder = new NewsViewHolder(convertView);
                convertView.setTag(newsViewHolder);
            }else{
                newsViewHolder = (NewsViewHolder) convertView.getTag();
            }
            News news = this.mNews.get(position);
            newsViewHolder.tvNewsTitle.setText(news.getTitle());
            newsViewHolder.tvNewsPubDate.setText(news.getPubDate());
            newsViewHolder.tvNewsCategory.setText(news.getCategory());
            newsViewHolder.tvNewsDescription.setText(news.getDescription());
            Picasso.with(parent.getContext())
                    .load(news.getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(newsViewHolder.ivNewsPoster);
            return convertView;
        }
        static private class NewsViewHolder{
            TextView tvNewsTitle, tvNewsPubDate, tvNewsCategory, tvNewsDescription;
            ImageView ivNewsPoster;
            public NewsViewHolder(View newsView) {
                this.tvNewsTitle = (TextView) newsView.findViewById(R.id.tvNewsTitle);
                this.tvNewsPubDate = (TextView) newsView.findViewById(R.id.tvNewsPubDate);
                this.tvNewsCategory = (TextView) newsView.findViewById(R.id.tvNewsCategory);
                this.tvNewsDescription = (TextView) newsView.findViewById(R.id.tvNewsDescription);
                this.ivNewsPoster = (ImageView) newsView.findViewById(R.id.ivNewsPoster);
            }
        }
    }

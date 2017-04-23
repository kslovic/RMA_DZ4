package com.example.kslovic.bugsy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {
    EditText etSearchTerm;
    ListView lvNews;
    NewsAdapter adapter;
    Context context;
    Button bRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context=getApplicationContext();
        this.setUpUi();
        GetNewsInfo getNews = new GetNewsInfo(this);
        getNews.execute();
    }
    private void setUpUi() {
        this.etSearchTerm = (EditText) this.findViewById(R.id.etSearchTerm);
        this.lvNews = (ListView) this.findViewById(R.id.lvNews);
        this.bRefresh = (Button) this.findViewById(R.id.bRefresh);
        etSearchTerm.addTextChangedListener(watcher);
        bRefresh.setOnClickListener(this);
    }
    private final TextWatcher watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        public void afterTextChanged(Editable s) {
            if (s.length() != 0) {
                String searchTerm = etSearchTerm.getText().toString();
                ArrayList<News> selectedNews = NewsDBHelper.getInstance(context).getNews(searchTerm);
                adapter = new NewsAdapter(selectedNews);
                lvNews.setAdapter(adapter);
            }
        }
    };
    public void displayNews(ArrayList<News> news){
        adapter = new NewsAdapter(news);
        this.lvNews.setAdapter(adapter);
        this.lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News newsItem = adapter.getItem(position);
                String link = newsItem.getLink();
                Intent implicitIntent;
                implicitIntent = new Intent();
                Uri uri = Uri.parse(link);
                implicitIntent.setData(uri);
                implicitIntent.setAction(Intent.ACTION_VIEW);
                startActivity(implicitIntent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        ArrayList<News> news= NewsDBHelper.getInstance(this).getAllNews();
        this.displayNews(news);
    }
}

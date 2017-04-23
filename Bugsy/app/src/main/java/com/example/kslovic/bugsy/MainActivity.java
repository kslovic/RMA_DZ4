package com.example.kslovic.bugsy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener
{
    EditText etSearchTerm;
    ImageButton bSearch;
    ListView lvNews;
    NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setUpUi();
        this.hookUpListeners();
        GetNewsInfo getNews = new GetNewsInfo(this);
        getNews.execute();
    }
    private void setUpUi() {
        this.bSearch = (ImageButton) this.findViewById(R.id.ibSearch);
        this.etSearchTerm = (EditText) this.findViewById(R.id.etSearchTerm);
        this.lvNews = (ListView) this.findViewById(R.id.lvNews);
    }
    private void hookUpListeners() {
        this.bSearch.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String searchTerm = this.etSearchTerm.getText().toString();
        this.findNews(searchTerm);
    }
    private void findNews(String searchTerm) {

    }
    public void displayNews(ArrayList<News> news){
        this.bSearch.setEnabled(true);
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

}

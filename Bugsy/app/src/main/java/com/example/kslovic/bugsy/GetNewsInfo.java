package com.example.kslovic.bugsy;

import android.os.AsyncTask;
import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class GetNewsInfo extends AsyncTask<Void,Void,ArrayList<News>> {
    private static final String TAG = "Kristina";
    private MainActivity mActivity;
    URL url;
    private static final String BASE_URL = "http://www.bug.hr/rss/vijesti/";
    public GetNewsInfo(MainActivity activity) { mActivity = activity; }
    public void attach(MainActivity activity){ this.mActivity = activity; }
    public void detach(){ this.mActivity = null; }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected ArrayList<News> doInBackground(Void... params) {
        Document document = retrieveNewsInfo();
        if(document!=null) {
            ArrayList<News> news= new ArrayList<>();
            String title="", pubDate="", category="", description="", imageUrl="", link="";
            Element root = document.getDocumentElement();
            Node channel = root.getFirstChild();
            Log.d("TAG",channel.getTextContent());
            NodeList items = channel.getChildNodes();
            for(int i=0; i<items.getLength();i++) {
                Node current = items.item(i);
                if (current.getNodeName().equalsIgnoreCase("item")) {
                    NodeList itemChilds = current.getChildNodes();
                    for (int j = 0; j < itemChilds.getLength(); j++) {
                        Node curNode = itemChilds.item(j);
                        if (curNode.getNodeName().equalsIgnoreCase("title")) {
                            title = curNode.getTextContent();
                        }
                        if (curNode.getNodeName().equalsIgnoreCase("pubDate")) {
                            pubDate = curNode.getTextContent();
                        }
                        if (curNode.getNodeName().equalsIgnoreCase("category")) {
                            category = curNode.getTextContent();
                        }
                        if (curNode.getNodeName().equalsIgnoreCase("description")) {
                            description = curNode.getTextContent();
                        }
                        if (curNode.getNodeName().equalsIgnoreCase("enclosure")) {
                            imageUrl = curNode.getAttributes().getNamedItem("url").getNodeValue();
                        }
                        if (curNode.getNodeName().equalsIgnoreCase("link")) {
                            link = curNode.getTextContent();
                        }
                    }
                    News newsItem = new News(title, pubDate, category, description, imageUrl, link);
                    news.add(newsItem);
                }
            }
            return news;
        }
        else return null;
    }

    private Document retrieveNewsInfo(){
        try {
        url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream input = connection.getInputStream();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xml = builder.parse(input);
            return xml;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    private void loadNews(Document data){
        Log.d(TAG,data.getDocumentElement().getNodeName());
    }
    @Override
    protected void onPostExecute(ArrayList<News> news) {
        super.onPostExecute(news);
        if(this.mActivity != null){
            this.mActivity.displayNews(news);
        }
    }
    }

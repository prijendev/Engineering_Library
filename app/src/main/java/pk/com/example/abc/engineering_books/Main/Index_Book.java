package pk.com.example.abc.engineering_books.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import pk.com.example.abc.engineering_books.Model.DatabaseHelper;
import pk.com.example.abc.engineering_books.R;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class Index_Book extends Fragment
{



    private StartAppAd startAppAd;
private  Toolbar toolbar;
    private RecyclerView recyclerview;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutmanager;
    private ArrayList<String> img = new ArrayList<String>();
    private ArrayList<String> author = new ArrayList<String>();
    private ArrayList<String> book = new ArrayList<String>();
    private ArrayList<String> images = new ArrayList<String>();
    private ArrayList<String> urls = new ArrayList<String>();

    MyAdapter adapt;
    Intent intent;

    private AdView mAdView;
    Context con;
    public Index_Book()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        con=getActivity();

        startAppAd=new StartAppAd(con);
        layoutmanager=new LinearLayoutManager(con);



        return inflater.inflate(R.layout.recyclerview, container, false);




    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);



        if(MainActivity.id%5==2 || MainActivity.id%5==4)
        {

            MainActivity.id++;
            StartAppSDK.init(con, "205722659", true);
            startAppAd.loadAd(StartAppAd.AdMode.VIDEO);

            startAppAd.showAd(new AdDisplayListener() {
                @Override
                public void adHidden(Ad ad)
                {


                }

                @Override
                public void adDisplayed(Ad ad)
                {

                }

                @Override
                public void adClicked(Ad ad)
                {

                }

                @Override
                public void adNotDisplayed(Ad ad)
                {

                }
            });
        }
        else if(MainActivity.id%5==3)
        {
            MainActivity.id++;
            MobileAds.initialize(con,
                    "ca-app-pub-9328301403988868~9790902642");
            mAdView =getView().findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
        else
            MainActivity.id++;




        DatabaseHelper db = new DatabaseHelper(con);
        db.createDataBase();
        db.openDataBase();

        String table=getArguments().getString("data");
        book=db.getBook(table);
        images=db.images;
        author=db.author;
        urls=db.urls;
        adapt=new MyAdapter(author,book,images,urls);
        recyclerview=(RecyclerView)getView().findViewById(R.id.recycler_view);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setHasFixedSize(true);

        recyclerview.setAdapter(adapt);
        }

    }










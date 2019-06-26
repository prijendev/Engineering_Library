package pk.com.example.abc.engineering_books.Main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.*;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import pk.com.example.abc.engineering_books.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{


    //public ArrayList<String> img=null;
    public ArrayList<String> author;
    public ArrayList<String> book;
    public ArrayList<String> images;
    public ArrayList<String> urls;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private StartAppAd startAppAd;

    public  MyAdapter(ArrayList<String> author,ArrayList<String> book,ArrayList<String> images,ArrayList<String> urls)
    {
        this.book=book;
        this.author=author;
        this.images=images;
        this.urls=urls;
        Log.d("MyAdapter",author.toString());


        //this.img=img;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int i)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.index_book_view, parent, false);

        Log.d("onCreateViewHolder",itemView.toString());



        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int i)
    {
        //String im=img.get(i);
        final String bk=book.get(i);
        String au=author.get(i);

        String img=images.get(i);
        final String url=urls.get(i);

        final Context context=myViewHolder.img.getContext();
        Log.d("Image",img);
        int id = context.getResources().getIdentifier(img, "drawable", context.getPackageName());

        myViewHolder.img.setImageResource(id);
        startAppAd= new StartAppAd(context);
        myViewHolder.txt.setText(bk);
        myViewHolder.author.setText("- "+au);
        myViewHolder.urls.setText(url);
        MobileAds.initialize(context,
                "###################################");
        myViewHolder.rlt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                //Intent intent=new Intent(context,Pdfview.class);
                //intent.putExtra("urls",url);
               // intent.putExtra("name",bk);
                //context.startActivity(intent);

                if(Index.id1%3==1)
                {




                    Index.id1++;



                    mInterstitialAd = new InterstitialAd(context);
                    mInterstitialAd.setAdUnitId("\n" +
                            "ca-app-pub-9328301403988868/5132692080");



                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    mInterstitialAd.setAdListener(new AdListener()
                    {
                        public void onAdLoaded()
                        {
                            // Call displayInterstitial() function when the Ad loads
                            if (mInterstitialAd.isLoaded())
                            {
                                mInterstitialAd.show();
                            }
                        }
                    });
                }
                else if(Index.id1%3==0)
                {
                    Index.id1++;
                    StartAppSDK.init(context, "205722659", true);

                    startAppAd.loadAd(StartAppAd.AdMode.AUTOMATIC);

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
                else
                    Index.id1++;

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

                builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorAccent));


                builder.addDefaultShareMenuItem();

                CustomTabsIntent customTabsIntent = builder.build();

                customTabsIntent.launchUrl(context, Uri.parse(url));








            }
        });





    }

    @Override
    public int getItemCount()
    {
        return author.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView txt;
        TextView author;
        TextView urls;

        RelativeLayout rlt;
        public MyViewHolder(View itemView)
        {
            super(itemView);

            img=(ImageView)itemView.findViewById(R.id.image);
            txt=(TextView)itemView.findViewById(R.id.txt);
            author=(TextView)itemView.findViewById(R.id.author);
            urls=(TextView)itemView.findViewById(R.id.urls);
            rlt=(RelativeLayout)itemView.findViewById(R.id.rlt);
            Log.d("MyViewHolder",txt.toString());

        }
    }


}

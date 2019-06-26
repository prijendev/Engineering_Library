package pk.com.example.abc.engineering_books.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import pk.com.example.abc.engineering_books.R;

public class Index extends Fragment implements View.OnClickListener
{

    private RelativeLayout computer, it, ec, mechanical, civil,rate;
    Context con;
    private InterstitialAd mInterstitialAd;

    int x=0;
    private StartAppAd startAppAd;

    public static int id1=0;
    public Index()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        con=getActivity();
        startAppAd=new StartAppAd(con);
        return inflater.inflate(R.layout.index_main, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);





        computer = getView().findViewById(R.id.com);
        civil = getView().findViewById(R.id.civ);
        mechanical = getView().findViewById(R.id.mech);
        it = getView().findViewById(R.id.imt);

        ec = getView().findViewById(R.id.emc);
        rate=getView().findViewById(R.id.rate);

        computer.setOnClickListener(this);
        civil.setOnClickListener(this);
        mechanical.setOnClickListener(this);

        it.setOnClickListener(this);
        ec.setOnClickListener(this);
        rate.setOnClickListener(this);

        MobileAds.initialize(con, "##################################");
        mInterstitialAd = new InterstitialAd(con);
        mInterstitialAd.setAdUnitId("#####################################");
    }





    @Override
    public void onClick(View v) {

        if (MainActivity.id%5==0)
        {

            MainActivity.id++;


            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener()
            {
                public void onAdLoaded()
                {
                    // Call displayInterstitial() function when the Ad loads
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                }
            });
        }

        Fragment newFragment = new Index_Book();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle data = new Bundle();
        if (v.getId() == R.id.com)
            data.putString("data", "computer");
         if (v.getId() == R.id.mech)
            data.putString("data", "mechanical");
        if (v.getId() == R.id.civ)
            data.putString("data", "civil");

        if (v.getId() == R.id.imt)
            data.putString("data", "it");
         if (v.getId() == R.id.emc)
            data.putString("data", "ec");
        if(v.getId()==R.id.rate)
        {
            Uri marketUri = Uri.parse("market://details?id=" + con.getPackageName());
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }

        newFragment.setArguments(data);
        transaction.replace(R.id.output, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();


    }


}

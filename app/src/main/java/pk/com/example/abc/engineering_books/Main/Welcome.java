package pk.com.example.abc.engineering_books.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import pk.com.example.abc.engineering_books.R;

public class Welcome extends AppCompatActivity
{

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    SharedPreferences sp;
    FragmentTransaction mFragmentTransaction;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Toolbar toolbar = findViewById(R.id.toolbar);


        //MenuItem shareItem = menu.findItem(R.id.share);
        //ShareActionProvider myShareActionProvider =
                //(ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);


        setSupportActionBar(toolbar);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;


        mFragmentManager = getSupportFragmentManager();

        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.replace(R.id.containerView,new Index()).commit();
        View header = mNavigationView.getHeaderView(0);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if(menuItem.getItemId()==R.id.nav_item_home)
                {

                    mFragmentTransaction = mFragmentManager.beginTransaction();

                    mFragmentTransaction.replace(R.id.containerView,new Index()).commit();
                }

                if(menuItem.getItemId()==R.id.nav_item_buy)
                {

                    Uri marketUri = Uri.parse("http://books.malgadi.co.in/");
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                    startActivity(marketIntent);

                }
                if(menuItem.getItemId()==R.id.nav_item_feedback)
                {
                    Intent Email = new Intent(Intent.ACTION_SEND);
                    Email.setType("text/email");
                    Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "17ceuog010@ddu.ac.in" });
                    Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                    Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
                    startActivity(Intent.createChooser(Email, "Send Feedback:"));
                    return true;
                }
                if(menuItem.getItemId()==R.id.nav_item_rate)
                {

                    Uri marketUri = Uri.parse("market://details?id=" + getPackageName());
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                    startActivity(marketIntent);

                }



                return false;
            }

        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);


        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }
}

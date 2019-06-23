package pk.com.example.abc.engineering_books.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.adsCommon.StartAppAd;
import pk.com.example.abc.engineering_books.Model.DatabaseHelper;
import pk.com.example.abc.engineering_books.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    ImageView image;
    Animation animation;
    Timer timer;
    private DatabaseHelper db;
    public static int id=0;
    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        image=(ImageView)findViewById(R.id.welocome_page);
        animation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        image.setAnimation(animation);

        timer=new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, Welcome.class);

                startActivity(intent);
                finish();
            }
        },4000);


    }



}

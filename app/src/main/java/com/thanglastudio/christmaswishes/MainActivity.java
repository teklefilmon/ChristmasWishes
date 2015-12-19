package com.thanglastudio.christmaswishes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int FLAG_SELECTED_POS = 0;
    private static String LOG_TAG = "MainViewActivity";

    ArrayList<MyPojo> pojos = new ArrayList<MyPojo>();
    int[] icons = {R.drawable.bear, R.drawable.christmas_tree, R.drawable.penquin,
            R.drawable.cat, R.drawable.christmas_tree_two, R.drawable.gift,
            R.drawable.snowman_icon, R.drawable.christmas_tree_two, R.drawable.santa_one,
            R.drawable.snoman_two};

    String[] wishes = {"•A little smile, A word of cheer, A bit of love from someone near A little gift from one held dear, Best wishes for the coming year, These make a Merry Christmas!",
            "• I want to wish you and your family all the happiness and joy of Christmas. I hope we can get together this holiday season and spend quality time together.",
            "• A Christmas candle is a lovely thing; It makes no noise at all, But softly gives itself away; While quite unselfish, it grows small.",
            "• Christmas brings family and friends together; it helps us appreciate the love in our lives we can often take for granted. May the true meaning of the holiday season fill your heart and home with many blessings.",
            "•Wishing you peace, joy, and all the best this wonderful holiday has to offer. May this incredible time of giving and spending time with family bring you joy that lasts throughout the year.",
            "•During this season of giving, let us take time to slow down and enjoy the simple things. May this wonderful time of the year touch your heart in a special way. Wishing you much happiness today and throughout the New Year.",
            "•As you celebrate the miracle of this special season, may your heart be filled with joy and peace. May these holiday blessings linger in your home and stay with you throughout the year.",
            "•I am hoping for snow this Christmas so we can spend the day cuddled inside with mugs of hot cocoa and love in our hearts. Don’t forget to bring the marshmallows.",
            "•Christmas is a special time of the year, and my wish is that you feel all of the love and joy of the holiday season deep inside your heart. I know you make me feel the same way.",
            "• I love this time of year because I can use it as an excuse to tell you, and show you, how much I really care about you. Merry Christmas, and thank you for being you; you mean the world to me."

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        selectedCheckBox = (CheckBox) findViewById(R.id.chkwish);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest.Builder adRequest = new AdRequest.Builder().addTestDevice("C6C832DED13CA3AC6F9F6C3A924F11D4");
        AdRequest aadRequest = adRequest.build();
        mAdView.loadAd(aadRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.santa);
        toolbar.setTitle("CHRISTMAS WISHES");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //populating the pojos in arraylist
        for (int i = 0; i < 10; i++) {
            pojos.add(new MyPojo(wishes[i], icons[i]));

        }

        // specify an adapter (see also next example)
        mAdapter = new MyRecyclerViewAdapter(pojos);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                //Toast.makeText(MainActivity.this,"Clicked on Item " + position,Toast.LENGTH_SHORT).show();

                RadioButton radioButton = (RadioButton) v.findViewById(R.id.radwish);
                radioButton.setChecked(true);

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        /*Snackbar.make(view, "SELECTED POS" + FLAG_SELECTED_POS, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/


                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.SEND");
                        intent.putExtra("android.intent.extra.TEXT", wishes[position]);
                        intent.setType("text/plain");
                        MainActivity.this.startActivity(Intent.createChooser(intent, "Warm Wishes"));

                    }


                });
            }
        });
    }
}

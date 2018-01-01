package com.bigmanglobal.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    int myActivePlayer = 0; //0 is cross and 1 is circle
    int dp [] = {2,2,2,2,2,2,2,2,2};
    int win[][] = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,4,8},
            {2,4,6},
            {0,3,6},
            {1,4,7},
            {2,5,8}
    };
    String s[] = {"Crosses" , "Circles"};
    int ww=3;

    //winning logic


    public void playAgain(View view)
    {
        ww=3;

        for(int i=0;i< dp.length ; i++)
            dp[i] = 2;

        myActivePlayer=0;

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line1);
        for(int i=0 ; i<linearLayout.getChildCount(); i++)
        {
            ImageView ll = (ImageView) linearLayout.getChildAt(i);
            ll.setImageResource(R.drawable.qmark);
        }
         linearLayout = (LinearLayout) findViewById(R.id.line2);
        for(int i=0 ; i<linearLayout.getChildCount(); i++)
        {
            ImageView ll = (ImageView) linearLayout.getChildAt(i);
            ll.setImageResource(R.drawable.qmark);
        }
         linearLayout = (LinearLayout) findViewById(R.id.line3);
        for(int i=0 ; i<linearLayout.getChildCount(); i++)
        {
            ImageView ll = (ImageView) linearLayout.getChildAt(i);
            ll.setImageResource(R.drawable.qmark);
        }

    }
    public boolean check(Vector<Integer> a , Vector<Integer> s)
    {
        int count=0;
        for(int j=0;j<3;j++)
        {
            int val = a.elementAt(j);
            for(int i=0;i<s.size();i++)
            {
                if(s.elementAt(i) == val)
                    count++;
            }
        }
        if(count==3)
            return true;
        else
            return false;

    }
    public void imageTapped(View view)
    {
        ImageView myTapped = (ImageView) view ;
        Log.i("TAG IS " , "Image Number Is " + myTapped.getTag().toString());
        int tapValue = Integer.parseInt(myTapped.getTag().toString());
        if(dp[tapValue]==2)
        {
            dp[tapValue] = myActivePlayer;
            if(myActivePlayer==0)
            {
                myTapped.setImageResource(R.drawable.cross);
                myTapped.animate().rotation(360).setDuration(1000);
                myActivePlayer=1;
            }
            else if(myActivePlayer==1)
            {
                Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext() , R.layout.anim);//slide
                myTapped.setImageResource(R.drawable.circle);
                myActivePlayer=0;
            }

            Vector<Integer> set0 = new Vector<Integer>();
            Vector<Integer> set1 = new Vector<Integer>();

            for(int i=0;i<dp.length ; i++)
            {
                if(dp[i] == 0)
                    set0.add(i);
                else if(dp[i]==1)
                    set1.add(i);
            }
            for(int i=0;i<8;i++)
            {
                Vector<Integer> aa = new Vector<Integer>();
                for(int j=0;j<3;j++) {

                    aa.add(win[i][j]);
                }
                if(check(aa , set0))
                {
                    Toast.makeText(MainActivity.this , "Crosses Won The Game" , Toast.LENGTH_SHORT).show();
                    Log.i("set 0 is " , set0.elementAt(0) + " "+ set0.elementAt(1)+ " "+set0.elementAt(2) );
                    myActivePlayer=3;
                    ww=0;
                }

                if(check(aa , set1))
                {
                    Toast.makeText(MainActivity.this , "Circles Won The Game" , Toast.LENGTH_SHORT).show();
                    Log.i("set 0 is " , set1.elementAt(0) + " "+ set1.elementAt(1)+ " "+ set1.elementAt(2) );
                    myActivePlayer=3;
                    ww=1;
                }

            }



        }
        else if(ww==3){
            //getCallingActivity or MainActivity.this
            Toast.makeText(MainActivity.this , "This Possition Is Already Filled" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this , s[ww] + " WON THE GAME" , Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

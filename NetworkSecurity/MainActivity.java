package com.example.csie.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    int xRaindom,yRaindom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyView myView = new MyView(this);
        LinearLayout lnearLayout = (LinearLayout) findViewById(R.id.canvasLayout);
        lnearLayout.addView(myView);

        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                xRaindom = (int)(Math.random()*12);
                yRaindom = (int)(Math.random()*3);
                return false;
            }
        });

    }

    class MyView extends View {
        Bitmap bitmap;
        int width,height;
        Rect src,dst;
        int xlength,ylength;
        int cx,cy;

        public MyView(Context context) {
            super(context);
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.poker);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
            xlength = width / 13;
            ylength = height / 5;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.BLACK);
            cx = canvas.getWidth();
            cy = canvas.getHeight();

           // for (int i=0;i<3;i++)
                //for(int j=0;j<3;j++){
            src = new Rect(xRaindom*xlength, yRaindom*ylength, (xRaindom+1)*xlength, (yRaindom+1)*ylength);
            dst = new Rect(1*cx/3, 1*cy/3, (1+1)*cx/3, (1+1)*cy/3);
            canvas.drawBitmap(bitmap,src,dst,null);
                //}
            invalidate();


        }
    }
}

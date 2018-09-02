package com.root.atul.loginpage4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity{
    public ViewPager vp;
    public LinearLayout ll;
    public SliderAdapter sliderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp=findViewById(R.id.vp);
        ll=findViewById(R.id.ll);
        sliderAdapter=new SliderAdapter(this);
        vp.setAdapter(sliderAdapter);
        vp.setOffscreenPageLimit(3);


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if(i==2)
                {
                    Button button=findViewById(R.id.exit);
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "hello chandu and chaya", Toast.LENGTH_SHORT).show();
                            //add content here
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    });

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

}

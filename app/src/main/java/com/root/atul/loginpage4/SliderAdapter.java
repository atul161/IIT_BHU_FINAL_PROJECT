package com.root.atul.loginpage4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter
{
    public Context context;
    public LayoutInflater inflater;
    public int slideimage[]={R.drawable.logo,R.drawable.donate1,R.drawable.disaster};
    public String slideheading[]={"Help","Help","Great!!"};
    //public String// slidecontent[]={"Appathon is an cross-platform application"+ "development competition, organised by Codefest'18 of IIT"+" (BHU), Varanasi. For registrations, details and prizes you can visit the Codefest "+"website.Appathon is an 8 of IIT (BHU), Varanasi. For registrations, details and prizes you can visit the Codefest website. ","Appathon is an cross-platform application development competition, organised by Codefest'18 of IIT (BHU), Varanasi. For registrations, details and prizes you can visit the Codefest website. "};
     public String slidecontent[]={"your donation makes \nsomeones life ","How are u today","Be a great\n Person Donate"};
    public SliderAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return slideimage.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(ConstraintLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.slide_layout,container,false);
        ImageView iv=view.findViewById(R.id.imageView);
        TextView tv1=view.findViewById(R.id.heading);
        TextView tv2=view.findViewById(R.id.tv2);
         iv.setImageResource(slideimage[position]);
         tv1.setText(slideheading[position]);
         tv2.setText(slidecontent[position]);

         container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

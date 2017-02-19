package comn.example.administrator.zhbj;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
ImageView red_oval;
    int padding_oval;
    LinearLayout pointLayout;
    Button open;
    ViewPager viewPager;
ArrayList<ImageView>arraylist=new ArrayList<>();
   int []imgs=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        open= (Button) findViewById(R.id.open_guide);
        pointLayout= (LinearLayout) findViewById(R.id.point_linear);
   red_oval= (ImageView) findViewById(R.id.imag_redOval);
        init();
        red_oval.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               red_oval.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                padding_oval=pointLayout.getChildAt(1).getLeft()
                        -pointLayout.getChildAt(0).getLeft();
            }
        });
        viewPager= (ViewPager) findViewById(R.id.guide_viewPager);
        viewPager.setAdapter(new MyAdapter());
     viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) red_oval.getLayoutParams();
               params.leftMargin= (int) (positionOffset*padding_oval+position*padding_oval);
                  red_oval.setLayoutParams(params);
         }

         @Override
         public void onPageSelected(int position) {
        if (position>1){
            open.setVisibility(View.VISIBLE);

        }
             else {
            open.setVisibility(View.INVISIBLE);

        }
         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });
    }

    private void init() {
   for (int i=0;i<imgs.length;i++) {
       ImageView imageView=new ImageView(this);
       imageView.setBackgroundResource(imgs[i]);
       arraylist.add(imageView);
       ImageView ii=new ImageView(this);
       ii.setImageResource(R.drawable.gray_oval);
       LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
       , ViewGroup.LayoutParams.WRAP_CONTENT);
       if (i>0){
           param.leftMargin=10;

       }
       ii.setLayoutParams(param);
       pointLayout.addView(ii);
   }
    }

    public class MyAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return arraylist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           container.addView(arraylist.get(position));

            return arraylist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        container.removeView(arraylist.get(position));
        }
    }
}

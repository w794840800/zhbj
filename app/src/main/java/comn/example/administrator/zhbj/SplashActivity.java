package comn.example.administrator.zhbj;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import java.util.IllegalFormatCodePointException;
import java.util.prefs.Preferences;

/**
 * Created by Administrator on 2017/2/19.
 */

public class SplashActivity extends AppCompatActivity {
LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
linearLayout= (LinearLayout) findViewById(R.id.splash_activity);
        //旋转动画
        RotateAnimation rotateAnimation=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        //缩放动画
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        //渐变动画
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        //集合
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
     //   animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        linearLayout.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences sharedPreferences=getSharedPreferences("config",MODE_PRIVATE);
                     //       sharedPreferences.edit().putBoolean("first_enter",true).commit();
                if (!sharedPreferences.getBoolean("first_enter",false)){
                    Intent intent=new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}

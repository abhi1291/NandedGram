package ideate.com.nandedgram.fragment;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import UIComponent.DonutProgress;
import ideate.com.nandedgram.R;
import ideate.com.nandedgram.adapter.SliderAdapter;
import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private String TAG = "Demo";
    Button second, secondTime;
    private String TAG_FRAGMENT = "Second";
    private static final String[] XMEN = {"slider1", "slider2", "slider3", "slider4"};
    private int currentPage = 0;
    Timer swipeTimer = null;
    TextView male, female, total;
    ValueAnimator animator;
    DonutProgress maleProgress, femaleProgress, totalProgress;
    final Handler handler = new Handler();
    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,


    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach:cr frag A");
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView frag : A");
        View view = inflater.inflate(R.layout.frag_home,
                container, false);

        intiit(view);
        final ViewPager mPager = (ViewPager) view.findViewById(R.id.pager);


        ArrayList<Integer> imageList = new ArrayList<>();
        for (int i = 0; i < image_ids.length; i++)
            imageList.add(image_ids[i]);

        mPager.setAdapter(new SliderAdapter(getActivity(), imageList));
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        animator = ValueAnimator.ofInt(0, 4907);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                male.setText(animation.getAnimatedValue().toString());
                maleProgress.setProgress(Integer.parseInt(animation.getAnimatedValue().toString()));
            }
        });
        animator.start();
        animator = ValueAnimator.ofInt(0, 4201);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                female.setText(animation.getAnimatedValue().toString());
                femaleProgress.setProgress(Integer.parseInt(animation.getAnimatedValue().toString()));
            }
        });
        animator.start();

        animator = ValueAnimator.ofInt(0, 9108);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                total.setText(animation.getAnimatedValue().toString());
                totalProgress.setProgress(Integer.parseInt(animation.getAnimatedValue().toString()));
            }
        });
        animator.start();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        if (swipeTimer == null) {
            swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 2500, 2500);
        }


        return view;
    }

    private void intiit(View view) {

        male = view.findViewById(R.id.home_text_male);
        female = view.findViewById(R.id.home_text_female);
        total = view.findViewById(R.id.home_text_total);
        maleProgress = view.findViewById(R.id.home_circular_male);
        femaleProgress = view.findViewById(R.id.home_circular_female);
        totalProgress = view.findViewById(R.id.home_circular_total);


    }
}
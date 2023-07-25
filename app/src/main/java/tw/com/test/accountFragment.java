package tw.com.test;

import static android.content.Context.SENSOR_SERVICE;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class accountFragment extends Fragment {
    private Handler SliderHandler = new Handler();
    private ViewPager2 viewPager2;
    public ScrollView scrollView;
    private View view;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public accountFragment() {
        // Required empty public constructor
    }
    public static accountFragment newInstance(String param1, String param2) {
        accountFragment fragment = new accountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);

        scrollView = view.findViewById(R.id.ScrollView_account);
        scrollView.setOnTouchListener((new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).collectData(event);
                }
                return false;
            }
        }));

        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.image1));
        sliderItems.add(new SliderItem(R.drawable.image2));
        sliderItems.add(new SliderItem(R.drawable.image3));
        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.getChildAt(0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        }
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    page.setScaleY(0.85f + (r * 0.15f));
                }
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                SliderHandler.removeCallbacks(SliderRunnable);
                SliderHandler.postDelayed(SliderRunnable, 3000);
            }
        });
        RecyclerView recyclerView = (RecyclerView) viewPager2.getChildAt(0);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).CollectScrollDataOnViewPager2(recyclerView, "accountFragment的廣告ViewPager2");
        }


        return view;
    }

    public void imageBtn1(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).imageBtn1(view);
        }
    }
    public void imageBtn2(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).imageBtn2(view);
        }
    }
    public void imageBtn3(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).imageBtn3(view);
        }
    }
    public void imageBtn4(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).imageBtn4(view);
        }
    }

    private Runnable SliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        SliderHandler.postDelayed(SliderRunnable, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        SliderHandler.removeCallbacks(SliderRunnable);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                // 使用 smoothScrollTo() 方法可以實現平滑的滾動效果
                // 如果不需要平滑效果，可以使用 scrollTo() 方法
                scrollView.scrollTo(0, 0);
            }
        });
    }
}
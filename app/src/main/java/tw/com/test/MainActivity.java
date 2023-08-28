package tw.com.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public UserData userData;           // 使用者的滑動資料物件
    public String account;              // 使用者帳號
    public SensorManager sensorManager; // 感測器的Manager
    public MySensorEventListener mySensorEventListener; // 感測器的Listener
    public Sensor accelerometer;        // 加速度感測器
    public Sensor gyroscope;            // 陀螺儀感測器
    public Sensor magnetometer;         // 磁力儀感測器
    public int counter = 0;             // 計算秒數ms
    public final int MAX_COUNTER = 1;  // 每10ms紀錄感測器資料
    public boolean isScroll = false;    // 是否正在滑動
    public boolean flag = false;        // 是否MOVE_DOWN開始
    public boolean isFirst = true;      // 是否為MOVE_DOWN的第一筆資料
    public BottomNavigationView navigation;  // 底部導覽欄
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    public PageAdapter pageAdapter;     // ViewPager2的Adapter
    public ViewPager2 viewPager;        // ViewPager2
    public RecyclerView recyclerView;   // ViewPager2中的RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 設定FindViewById */
        SetFindValueById();

        /* 設定Navigation */
        SetNavigation();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /* 從Login頁面接收Account */
        account = (String)getIntent().getExtras().getString("account");
        Log.v("Lin", account);

        /* 建立UserData物件 */
        userData = new UserData();

        /* 設定帳號 */
        userData.setAccount(account);

        /* 設定感測器 */
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensorEventListener = new MySensorEventListener();

        /* 設定ViewPager2的Adapter */
        pageAdapter = new PageAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(pageAdapter);

        /* 設定ViewPager2的左右邊界不可滑動 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        }

        /* 設定ViewPager2的頁面切換 */
        SwitchPage();

        /* 取得ViewPager2中的RecyclerView */
        recyclerView = (RecyclerView) viewPager.getChildAt(0);

        /* 蒐集ViewPager2下的滑動資料 */
        CollectScrollDataOnViewPager2(recyclerView, "頁面切換的ViewPager2");
    }

    public void imageBtn1(View view) {
        viewPager.setCurrentItem(1);
    }

    public void imageBtn2(View view) {
        viewPager.setCurrentItem(2);
    }

    public void imageBtn3(View view) {
        viewPager.setCurrentItem(3);
    }

    public void imageBtn4(View view) {
        viewPager.setCurrentItem(4);
    }

    public class MySensorEventListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                if (isScroll) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];
//                    counter++;
//                    if (counter>=MAX_COUNTER) {
//                        counter = 0;
//                        userData.addAccelerometerDataX(x);
//                        userData.addAccelerometerDataY(y);
//                        userData.addAccelerometerDataZ(z);
//                    }
                    userData.addAccelerometerDataX(x);
                    userData.addAccelerometerDataY(y);
                    userData.addAccelerometerDataZ(z);
                 }
            }
            else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                if (isScroll) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];
//                    counter++;
//                    if (counter>=MAX_COUNTER) {
//                        counter = 0;
//                        userData.addGyroscopeDataX(x);
//                        userData.addGyroscopeDataY(y);
//                        userData.addGyroscopeDataZ(z);
//                    }
                    userData.addGyroscopeDataX(x);
                    userData.addGyroscopeDataY(y);
                    userData.addGyroscopeDataZ(z);
                }
            }
            else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                if (isScroll) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];
//                    counter++;
//                    if (counter >= MAX_COUNTER) {
//                        counter = 0;
//                        userData.addMagnetometerDataX(x);
//                        userData.addMagnetometerDataY(y);
//                        userData.addMagnetometerDataZ(z);
//                    }
                    userData.addMagnetometerDataX(x);
                    userData.addMagnetometerDataY(y);
                    userData.addMagnetometerDataZ(z);
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sensorManager == null) {
            return;
        }

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(mySensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Log.v("Lin", "此設備不支援加速度計");
        }

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null) {
            sensorManager.registerListener(mySensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Log.v("Lin", "此設備不支援陀螺儀");
        }

        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetometer != null) {
            sensorManager.registerListener(mySensorEventListener, magnetometer, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Log.v("Lin", "此設備不支援磁力儀");
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager == null) {
            return;
        }
        sensorManager.unregisterListener(mySensorEventListener);
    }
    public void sendDataToBackend(UserData userData) {
        if (!isPossibleClick(userData))
        {
            JSONObject jsonObject = new JSONObject();
            try {
                // 帳號
                jsonObject.put("account", account);
                // 各項特徵
                JSONObject valueObject = new JSONObject();
                valueObject.put("timestamp",userData.getStartTime());
                valueObject.put("duration",userData.getDuration());
                valueObject.put("startX",userData.getStartXFirst());
                valueObject.put("startY",userData.getStartYFirst());
                valueObject.put("endX",userData.getCurrentXLast());
                valueObject.put("endY",userData.getCurrentYLast());
                valueObject.put("displacement",userData.getDistance());
                valueObject.put("pressureMedian",userData.getCurrentPressureMedian());
                valueObject.put("sizeMedian",userData.getCurrentSizeMedian());
                valueObject.put("averageSpeed",userData.getAverageSpeed());
                valueObject.put("directionOfEndToEndLine",userData.getDirectionOfEndToEndLine());
                valueObject.put("averageDirectionEnsemble",userData.getAverageDirectionEnsemble());
                valueObject.put("AXmax",userData.getADXmax());
                valueObject.put("AXmin",userData.getADXmin());
                valueObject.put("AXmean",userData.getADXmean());
                valueObject.put("AXmedian",userData.getADXmedian());
                valueObject.put("AXSD",userData.getADXSD());
                valueObject.put("AYmax",userData.getADYmax());
                valueObject.put("AYmin",userData.getADYmin());
                valueObject.put("AYmean",userData.getADYmean());
                valueObject.put("AYmedian",userData.getADYmedian());
                valueObject.put("AYSD",userData.getADYSD());
                valueObject.put("AZmax",userData.getADZmax());
                valueObject.put("AZmin",userData.getADZmin());
                valueObject.put("AZmean",userData.getADZmean());
                valueObject.put("AZmedian",userData.getADZmedian());
                valueObject.put("AZSD",userData.getADZSD());
                valueObject.put("GXmax",userData.getGDXmax());
                valueObject.put("GXmin",userData.getGDXmin());
                valueObject.put("GXmean",userData.getGDXmean());
                valueObject.put("GXmedian",userData.getGDXmedian());
                valueObject.put("GXSD",userData.getGDXSD());
                valueObject.put("GYmax",userData.getGDYmax());
                valueObject.put("GYmin",userData.getGDYmin());
                valueObject.put("GYmean",userData.getGDYmean());
                valueObject.put("GYmedian",userData.getGDYmedian());
                valueObject.put("GYSD",userData.getGDYSD());
                valueObject.put("GZmax",userData.getGDZmax());
                valueObject.put("GZmin",userData.getGDZmin());
                valueObject.put("GZmean",userData.getGDZmean());
                valueObject.put("GZmedian",userData.getGDZmedian());
                valueObject.put("GZSD",userData.getGDZSD());
                valueObject.put("MXmax",userData.getMDXmax());
                valueObject.put("MXmin",userData.getMDXmin());
                valueObject.put("MXmean",userData.getMDXmean());
                valueObject.put("MXmedian",userData.getMDXmedian());
                valueObject.put("MXSD",userData.getMDXSD());
                valueObject.put("MYmax",userData.getMDYmax());
                valueObject.put("MYmin",userData.getMDYmin());
                valueObject.put("MYmean",userData.getMDYmean());
                valueObject.put("MYmedian",userData.getMDYmedian());
                valueObject.put("MYSD",userData.getMDYSD());
                valueObject.put("MZmax",userData.getMDZmax());
                valueObject.put("MZmin",userData.getMDZmin());
                valueObject.put("MZmean",userData.getMDZmean());
                valueObject.put("MZmedian",userData.getMDZmedian());
                valueObject.put("MZSD",userData.getMDZSD());

                jsonObject.put("value", valueObject);
            /*
                轉字串輸出
                String jsonString = jsonObject.toString();
                Log.v("Lin", "json " + jsonString);
            */
                String jsonString = jsonObject.toString();
                Log.v("Lin", "json " + jsonString);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            // 傳送到後端
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://aba1one.ddns.net/receive";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // 處理回應
                            Log.v("Lin", "回應: " + response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // 處理錯誤
                            Log.v("Lin", "錯誤: " + error);
                        }
                    });
            requestQueue.add(request);
        }
    }
    public void showLog(UserData userData) {
        Log.v("Lin", "--------------------------------------------------------------------");
        Log.v("Lin", "起始點座標 "+userData.getStartXFirst()+":"+userData.getStartYFirst());
        Log.v("Lin", "結束點座標 "+userData.getCurrentXLast()+":"+userData.getCurrentYLast());
        Log.v("Lin", "時間差 "+userData.getDuration());
        Log.v("Lin", "壓力"+userData.getCurrentPressureMedian());
        Log.v("Lin", "手指大小"+userData.getCurrentSizeMedian());
        Log.v("Lin", "位移 "+userData.getDistance());
        Log.v("Lin", "路徑長 "+userData.getTrajectory());
        Log.v("Lin", "方向角度 "+userData.getDirectionOfEndToEndLine());
        Log.v("Lin", "平均速率 "+userData.getAverageSpeed());
        Log.v("Lin", "平均角度 "+userData.getAverageDirectionEnsemble());
        Log.v("Lin", "加速度計X軸最小值 "+userData.getADXmin());
        Log.v("Lin", "加速度計Y軸最小值 "+userData.getADYmin());
        Log.v("Lin", "加速度計Z軸最小值 "+userData.getADZmin());
        Log.v("Lin", "加速度計X軸最大值 "+userData.getADXmax());
        Log.v("Lin", "加速度計Y軸最大值 "+userData.getADYmax());
        Log.v("Lin", "加速度計Z軸最大值 "+userData.getADZmax());
        Log.v("Lin", "加速度計X軸平均值 "+userData.getADXmean());
        Log.v("Lin", "加速度計Y軸平均值 "+userData.getADYmean());
        Log.v("Lin", "加速度計Z軸平均值 "+userData.getADZmean());
        Log.v("Lin", "加速度計X軸中位數 "+userData.getADXmedian());
        Log.v("Lin", "加速度計Y軸中位數 "+userData.getADYmedian());
        Log.v("Lin", "加速度計Z軸中位數 "+userData.getADZmedian());
        Log.v("Lin", "加速度計X軸標準差 "+userData.getADXSD());
        Log.v("Lin", "加速度計Y軸標準差 "+userData.getADYSD());
        Log.v("Lin", "加速度計Z軸標準差 "+userData.getADZSD());
        Log.v("Lin", "陀螺儀X軸最小值 "+userData.getGDXmin());
        Log.v("Lin", "陀螺儀Y軸最小值 "+userData.getGDYmin());
        Log.v("Lin", "陀螺儀Z軸最小值 "+userData.getGDZmin());
        Log.v("Lin", "陀螺儀X軸最大值 "+userData.getGDXmax());
        Log.v("Lin", "陀螺儀Y軸最大值 "+userData.getGDYmax());
        Log.v("Lin", "陀螺儀Z軸最大值 "+userData.getGDZmax());
        Log.v("Lin", "陀螺儀X軸平均值 "+userData.getGDXmean());
        Log.v("Lin", "陀螺儀Y軸平均值 "+userData.getGDYmean());
        Log.v("Lin", "陀螺儀Z軸平均值 "+userData.getGDZmean());
        Log.v("Lin", "陀螺儀X軸中位數 "+userData.getGDXmedian());
        Log.v("Lin", "陀螺儀Y軸中位數 "+userData.getGDYmedian());
        Log.v("Lin", "陀螺儀Z軸中位數 "+userData.getGDZmedian());
        Log.v("Lin", "陀螺儀X軸標準差 "+userData.getGDXSD());
        Log.v("Lin", "陀螺儀Y軸標準差 "+userData.getGDYSD());
        Log.v("Lin", "陀螺儀Z軸標準差 "+userData.getGDZSD());
        Log.v("Lin", "磁力儀X軸最小值 "+userData.getMDXmin());
        Log.v("Lin", "磁力儀Y軸最小值 "+userData.getMDYmin());
        Log.v("Lin", "磁力儀Z軸最小值 "+userData.getMDZmin());
        Log.v("Lin", "磁力儀X軸最大值 "+userData.getMDXmax());
        Log.v("Lin", "磁力儀Y軸最大值 "+userData.getMDYmax());
        Log.v("Lin", "磁力儀Z軸最大值 "+userData.getMDZmax());
        Log.v("Lin", "磁力儀X軸平均值 "+userData.getMDXmean());
        Log.v("Lin", "磁力儀Y軸平均值 "+userData.getMDYmean());
        Log.v("Lin", "磁力儀Z軸平均值 "+userData.getMDZmean());
        Log.v("Lin", "磁力儀X軸中位數 "+userData.getMDXmedian());
        Log.v("Lin", "磁力儀Y軸中位數 "+userData.getMDYmedian());
        Log.v("Lin", "磁力儀Z軸中位數 "+userData.getMDZmedian());
        Log.v("Lin", "磁力儀X軸標準差 "+userData.getMDXSD());
        Log.v("Lin", "磁力儀Y軸標準差 "+userData.getMDYSD());
        Log.v("Lin", "磁力儀Z軸標準差 "+userData.getMDZSD());
        Log.v("Lin", "加速度大小:  "+userData.getAccelerometerDataX().size());
        Log.v("Lin", "陀螺儀大小:  "+userData.getGyroscopeDataX().size());
        Log.v("Lin", "磁力儀大小:  "+userData.getMagnetometerDataX().size());
    }
    public void SetNavigation() {
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.account:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.transfer:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.bill:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.forex:
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.setup:
                        viewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        };
    }
    public void SetFindValueById() {
        navigation = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPage);
    }

    public void CollectScrollDataOnViewPager2(RecyclerView recyclerView, String who) {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    userData.reset();                                  // 清除資料
                    isScroll = true;                                   // 開始滾動
                    userData.setStartTime(System.currentTimeMillis()); // 記錄起始時間
                    userData.addPressure(event.getPressure());         // 記錄起始點的手指壓力值
                    userData.addSizes(event.getSize());                // 記錄起始點的手指大小值
                    Float x = event.getRawX();                         // 取得X座標
                    Float y = event.getRawY();                         // 取得Y座標
                    userData.setStartXFirst(x);                        // 紀錄起始點的X座標
                    userData.setStartYFirst(y);                        // 紀錄起始點的Y座標
                    userData.addPoints(new PointF(x, y));              // 紀錄X和Y座標
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    userData.addPressure(event.getPressure());         // 記錄每個點的手指壓力值
                    userData.addSizes(event.getSize());                // 記錄每個點的手指大小值
                    PointF lastPoint = userData.getPoints().get(userData.getPoints().size()-1); // 取得前一個點座標
                    Float LastX = lastPoint.x;                         // 取得前一個點的X座標
                    Float LastY = lastPoint.y;                         // 取得前一個點的Y座標
                    Float x = event.getRawX();                         // 記錄目前的X座標
                    Float y = event.getRawY();                         // 記錄目前的Y座標
                    userData.addPoints(new PointF(x, y));              // 紀錄X和Y座標

                    // 計算平均角度，並加入到angles列表
                    if (LastX != null && LastY != null) {
                        userData.addAngles(userData.calculateDirection(LastX, LastY, x, y));
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    isScroll = false;                                  // 停止滾動
                    Float x = event.getRawX();                         // 取得X座標
                    Float y = event.getRawY();                         // 取得Y座標
                    userData.setCurrentXLast(x);                       // 紀錄結束點的X座標
                    userData.setCurrentYLast(y);                       // 紀錄結束點的Y座標
                    PointF lastPoint = userData.getPoints().get(userData.getPoints().size()-1); // 取得前一個點座標
                    Float LastX = lastPoint.x;                         // 取得前一個點的X座標
                    Float LastY = lastPoint.y;                         // 取得前一個點的Y座標
                    userData.addPoints(new PointF(x,y));               // 紀錄X和Y座標

                    // 計算平均角度，並加入到angles列表
                    if (LastX != null && LastY != null) {
                        userData.addAngles(userData.calculateDirection(LastX, LastY, x, y));
                    }

                    // 計算時間差
                    userData.setDuration(System.currentTimeMillis()-userData.getStartTime());
                    // 計算手指壓力的中位數
                    userData.setCurrentPressureMedian(userData.calculateMedian(userData.getPressures()));
                    // 計算手指大小的中位數
                    userData.setCurrentSizeMedian(userData.calculateMedian(userData.getSizes()));
                    // 計算起始點和結束點之間的距離
                    userData.setDistance(userData.calculateDistance(userData.getStartXFirst(), userData.getStartYFirst(), x, y));
                    // 計算路徑長
                    userData.setTrajectory(userData.calculateTrajectoryLength(userData.getPoints()));
                    // 計算起始點和結束點之間的方向角度
                    userData.setDirectionOfEndToEndLine(userData.calculateDirection(userData.getStartXFirst(), userData.getStartYFirst(), x, y));
                    // 計算平均速率
                    userData.setAverageSpeed(userData.getTrajectory()/userData.getDuration());
                    // 計算平均角度
                    userData.setAverageDirectionEnsemble(userData.calculateAverageAngle(userData.getAngles()));
                    // 計算感測器數值- 中位數
                    if (!userData.getAccelerometerDataX().isEmpty()) {
                        userData.setADXmax(Collections.max(userData.getAccelerometerDataX()));
                        userData.setADYmax(Collections.max(userData.getAccelerometerDataY()));
                        userData.setADZmax(Collections.max(userData.getAccelerometerDataZ()));
                        userData.setADXmin(Collections.min(userData.getAccelerometerDataX()));
                        userData.setADYmin(Collections.min(userData.getAccelerometerDataY()));
                        userData.setADZmin(Collections.min(userData.getAccelerometerDataZ()));
                        userData.setADXmean(userData.calculateMean(userData.getAccelerometerDataX()));
                        userData.setADYmean(userData.calculateMean(userData.getAccelerometerDataY()));
                        userData.setADZmean(userData.calculateMean(userData.getAccelerometerDataZ()));
                        userData.setADXmedian(userData.calculateMedian(userData.getAccelerometerDataX()));
                        userData.setADYmedian(userData.calculateMedian(userData.getAccelerometerDataY()));
                        userData.setADZmedian(userData.calculateMedian(userData.getAccelerometerDataZ()));
                        userData.setADXSD(userData.calculateStandardDeviation(userData.getAccelerometerDataX()));
                        userData.setADYSD(userData.calculateStandardDeviation(userData.getAccelerometerDataY()));
                        userData.setADZSD(userData.calculateStandardDeviation(userData.getAccelerometerDataZ()));
                    } else {
                        userData.setADXmax(0.0f);
                        userData.setADYmax(0.0f);
                        userData.setADZmax(0.0f);
                        userData.setADXmin(0.0f);
                        userData.setADYmin(0.0f);
                        userData.setADZmin(0.0f);
                        userData.setADXmean(0.0f);
                        userData.setADYmean(0.0f);
                        userData.setADZmean(0.0f);
                        userData.setADXmedian(0.0f);
                        userData.setADYmedian(0.0f);
                        userData.setADZmedian(0.0f);
                        userData.setADXSD(0.0f);
                        userData.setADYSD(0.0f);
                        userData.setADZSD(0.0f);
                    }
                    if (!userData.getGyroscopeDataX().isEmpty()) {
                        userData.setGDXmax(Collections.max(userData.getGyroscopeDataX()));
                        userData.setGDYmax(Collections.max(userData.getGyroscopeDataY()));
                        userData.setGDZmax(Collections.max(userData.getGyroscopeDataZ()));
                        userData.setGDXmin(Collections.min(userData.getGyroscopeDataX()));
                        userData.setGDYmin(Collections.min(userData.getGyroscopeDataY()));
                        userData.setGDZmin(Collections.min(userData.getGyroscopeDataZ()));
                        userData.setGDXmean(userData.calculateMean(userData.getGyroscopeDataX()));
                        userData.setGDYmean(userData.calculateMean(userData.getGyroscopeDataY()));
                        userData.setGDZmean(userData.calculateMean(userData.getGyroscopeDataZ()));
                        userData.setGDXmedian(userData.calculateMedian(userData.getGyroscopeDataX()));
                        userData.setGDYmedian(userData.calculateMedian(userData.getGyroscopeDataY()));
                        userData.setGDZmedian(userData.calculateMedian(userData.getGyroscopeDataZ()));
                        userData.setGDXSD(userData.calculateStandardDeviation(userData.getGyroscopeDataX()));
                        userData.setGDYSD(userData.calculateStandardDeviation(userData.getGyroscopeDataY()));
                        userData.setGDZSD(userData.calculateStandardDeviation(userData.getGyroscopeDataZ()));
                    } else {
                        userData.setGDXmax(0.0f);
                        userData.setGDYmax(0.0f);
                        userData.setGDZmax(0.0f);
                        userData.setGDXmin(0.0f);
                        userData.setGDYmin(0.0f);
                        userData.setGDZmin(0.0f);
                        userData.setGDXmean(0.0f);
                        userData.setGDYmean(0.0f);
                        userData.setGDZmean(0.0f);
                        userData.setGDXmedian(0.0f);
                        userData.setGDYmedian(0.0f);
                        userData.setGDZmedian(0.0f);
                        userData.setGDXSD(0.0f);
                        userData.setGDYSD(0.0f);
                        userData.setGDZSD(0.0f);
                    }
                    if (!userData.getMagnetometerDataX().isEmpty()){
                        userData.setMDXmax(Collections.max(userData.getMagnetometerDataX()));
                        userData.setMDYmax(Collections.max(userData.getMagnetometerDataY()));
                        userData.setMDZmax(Collections.max(userData.getMagnetometerDataZ()));
                        userData.setMDXmin(Collections.min(userData.getMagnetometerDataX()));
                        userData.setMDYmin(Collections.min(userData.getMagnetometerDataY()));
                        userData.setMDZmin(Collections.min(userData.getMagnetometerDataZ()));
                        userData.setMDXmean(userData.calculateMean(userData.getMagnetometerDataX()));
                        userData.setMDYmean(userData.calculateMean(userData.getMagnetometerDataY()));
                        userData.setMDZmean(userData.calculateMean(userData.getMagnetometerDataZ()));
                        userData.setMDXmedian(userData.calculateMedian(userData.getMagnetometerDataX()));
                        userData.setMDYmedian(userData.calculateMedian(userData.getMagnetometerDataY()));
                        userData.setMDZmedian(userData.calculateMedian(userData.getMagnetometerDataZ()));
                        userData.setMDXSD(userData.calculateStandardDeviation(userData.getMagnetometerDataX()));
                        userData.setMDYSD(userData.calculateStandardDeviation(userData.getMagnetometerDataY()));
                        userData.setMDZSD(userData.calculateStandardDeviation(userData.getMagnetometerDataZ()));
                    } else {
                        userData.setMDXmax(0.0f);
                        userData.setMDYmax(0.0f);
                        userData.setMDZmax(0.0f);
                        userData.setMDXmin(0.0f);
                        userData.setMDYmin(0.0f);
                        userData.setMDZmin(0.0f);
                        userData.setMDXmean(0.0f);
                        userData.setMDYmean(0.0f);
                        userData.setMDZmean(0.0f);
                        userData.setMDXmedian(0.0f);
                        userData.setMDYmedian(0.0f);
                        userData.setMDZmedian(0.0f);
                        userData.setMDXSD(0.0f);
                        userData.setMDYSD(0.0f);
                        userData.setMDZSD(0.0f);
                    }
                    sendDataToBackend(userData);
                    //showLog(userData);
                    Log.v("Lin", "誰: " + who);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent event) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
    public void SwitchPage() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.account);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.transfer);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.bill);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.forex);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.setup);
                        break;
                }
            }
        });
    }
    public void collectData(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 使用者按下手指
                flag = true;
                userData.reset();

                // 開始滾動
                isScroll = true;

                // 記錄起始時間
                userData.setStartTime(System.currentTimeMillis());

                // 記錄起始點的手指壓力值
                userData.addPressure(event.getPressure());

                // 記錄起始點的手指大小值
                userData.addSizes(event.getSize());

                // 紀錄起始點座標
                Float x = event.getRawX();
                Float y = event.getRawY();
                userData.setStartXFirst(x);
                userData.setStartYFirst(y);
                userData.addPoints(new PointF(x, y));

                break;
            case MotionEvent.ACTION_MOVE: // 使用者滑動手指
                if(flag) {
                    // 記錄每個點的手指壓力值
                    userData.addPressure(event.getPressure());

                    // 記錄每個點的手指大小值
                    userData.addSizes(event.getSize());

                    // 取得前一個點
                    PointF lastPoint = userData.getPoints().get(userData.getPoints().size()-1);
                    Float LastX = lastPoint.x;
                    Float LastY = lastPoint.y;

                    // 記錄目前的點，並加入到points列表
                    x = event.getRawX();
                    y = event.getRawY();
                    userData.addPoints(new PointF(x, y));

                    // 計算平均角度，並加入到angles列表
                    if (LastX != null && LastY != null) {
                        userData.addAngles(userData.calculateDirection(LastX, LastY, x, y));
                    }
                }
                else {
                    if (isFirst) {
                        userData.reset();
                        // 記錄起始時間
                        userData.setStartTime(System.currentTimeMillis());
                        // 記錄手指壓力值
                        userData.addPressure(event.getPressure());
                        // 記錄手指大小值
                        userData.addSizes(event.getSize());
                        // 紀錄座標
                        x = event.getRawX();
                        y = event.getRawY();
                        userData.addPoints(new PointF(x, y));
                        // 記錄起始位置
                        userData.setStartXFirst(x);
                        userData.setStartYFirst(y);
                        // 開始滾動
                        isScroll = true;
                        isFirst = false;
                    }else {
                        // 記錄手指壓力值
                        userData.addPressure(event.getPressure());
                        // 記錄手指大小值
                        userData.addSizes(event.getSize());
                        // 紀錄座標
                        x = event.getRawX();
                        y = event.getRawY();
                        // 取得前一個點
                        PointF lastPoint = userData.getPoints().get(userData.getPoints().size()-1);
                        Float LastX = lastPoint.x;
                        Float LastY = lastPoint.y;
                        userData.addAngles(userData.calculateDirection(LastX, LastY, x, y));
                        userData.addPoints(new PointF(x, y));
                    }
                }

                break;
            case MotionEvent.ACTION_UP:  // 使用者放開手指
                // 停止滾動
                isScroll = false;

                // 獲取滾動事件的結束點座標
                x = event.getRawX();
                y = event.getRawY();
                userData.setCurrentXLast(x);
                userData.setCurrentYLast(y);

                // 取得前一個點
                PointF lastPoint = userData.getPoints().get(userData.getPoints().size()-1);
                Float LastX = lastPoint.x;
                Float LastY = lastPoint.y;

                // 紀錄結束點到Points列表
                userData.addPoints(new PointF(x,y));

                // 計算平均角度，並加入到angles列表
                if (LastX != null && LastY != null) {
                    userData.addAngles(userData.calculateDirection(LastX, LastY, x, y));
                }

                // 計算時間差
                userData.setDuration(System.currentTimeMillis()-userData.getStartTime());

                // 計算手指壓力的中位數
                userData.setCurrentPressureMedian(userData.calculateMedian(userData.getPressures()));

                // 計算手指大小的中位數
                userData.setCurrentSizeMedian(userData.calculateMedian(userData.getSizes()));

                // 計算起始點和結束點之間的距離
                userData.setDistance(userData.calculateDistance(userData.getStartXFirst(), userData.getStartYFirst(), x, y));

                // 計算路徑長
                userData.setTrajectory(userData.calculateTrajectoryLength(userData.getPoints()));

                // 計算起始點和結束點之間的方向角度
                userData.setDirectionOfEndToEndLine(userData.calculateDirection(userData.getStartXFirst(), userData.getStartYFirst(), x, y));

                // 計算平均速率
                userData.setAverageSpeed(userData.getTrajectory()/userData.getDuration());

                // 計算平均角度
                userData.setAverageDirectionEnsemble(userData.calculateAverageAngle(userData.getAngles()));

                // 計算感測器數值- 中位數
                if (!userData.getAccelerometerDataX().isEmpty()) {
                    userData.setADXmax(Collections.max(userData.getAccelerometerDataX()));
                    userData.setADYmax(Collections.max(userData.getAccelerometerDataY()));
                    userData.setADZmax(Collections.max(userData.getAccelerometerDataZ()));
                    userData.setADXmin(Collections.min(userData.getAccelerometerDataX()));
                    userData.setADYmin(Collections.min(userData.getAccelerometerDataY()));
                    userData.setADZmin(Collections.min(userData.getAccelerometerDataZ()));
                    userData.setADXmean(userData.calculateMean(userData.getAccelerometerDataX()));
                    userData.setADYmean(userData.calculateMean(userData.getAccelerometerDataY()));
                    userData.setADZmean(userData.calculateMean(userData.getAccelerometerDataZ()));
                    userData.setADXmedian(userData.calculateMedian(userData.getAccelerometerDataX()));
                    userData.setADYmedian(userData.calculateMedian(userData.getAccelerometerDataY()));
                    userData.setADZmedian(userData.calculateMedian(userData.getAccelerometerDataZ()));
                    userData.setADXSD(userData.calculateStandardDeviation(userData.getAccelerometerDataX()));
                    userData.setADYSD(userData.calculateStandardDeviation(userData.getAccelerometerDataY()));
                    userData.setADZSD(userData.calculateStandardDeviation(userData.getAccelerometerDataZ()));
                } else {
                    userData.setADXmax(0.0f);
                    userData.setADYmax(0.0f);
                    userData.setADZmax(0.0f);
                    userData.setADXmin(0.0f);
                    userData.setADYmin(0.0f);
                    userData.setADZmin(0.0f);
                    userData.setADXmean(0.0f);
                    userData.setADYmean(0.0f);
                    userData.setADZmean(0.0f);
                    userData.setADXmedian(0.0f);
                    userData.setADYmedian(0.0f);
                    userData.setADZmedian(0.0f);
                    userData.setADXSD(0.0f);
                    userData.setADYSD(0.0f);
                    userData.setADZSD(0.0f);
                }
                if (!userData.getGyroscopeDataX().isEmpty()) {
                    userData.setGDXmax(Collections.max(userData.getGyroscopeDataX()));
                    userData.setGDYmax(Collections.max(userData.getGyroscopeDataY()));
                    userData.setGDZmax(Collections.max(userData.getGyroscopeDataZ()));
                    userData.setGDXmin(Collections.min(userData.getGyroscopeDataX()));
                    userData.setGDYmin(Collections.min(userData.getGyroscopeDataY()));
                    userData.setGDZmin(Collections.min(userData.getGyroscopeDataZ()));
                    userData.setGDXmean(userData.calculateMean(userData.getGyroscopeDataX()));
                    userData.setGDYmean(userData.calculateMean(userData.getGyroscopeDataY()));
                    userData.setGDZmean(userData.calculateMean(userData.getGyroscopeDataZ()));
                    userData.setGDXmedian(userData.calculateMedian(userData.getGyroscopeDataX()));
                    userData.setGDYmedian(userData.calculateMedian(userData.getGyroscopeDataY()));
                    userData.setGDZmedian(userData.calculateMedian(userData.getGyroscopeDataZ()));
                    userData.setGDXSD(userData.calculateStandardDeviation(userData.getGyroscopeDataX()));
                    userData.setGDYSD(userData.calculateStandardDeviation(userData.getGyroscopeDataY()));
                    userData.setGDZSD(userData.calculateStandardDeviation(userData.getGyroscopeDataZ()));
                } else {
                    userData.setGDXmax(0.0f);
                    userData.setGDYmax(0.0f);
                    userData.setGDZmax(0.0f);
                    userData.setGDXmin(0.0f);
                    userData.setGDYmin(0.0f);
                    userData.setGDZmin(0.0f);
                    userData.setGDXmean(0.0f);
                    userData.setGDYmean(0.0f);
                    userData.setGDZmean(0.0f);
                    userData.setGDXmedian(0.0f);
                    userData.setGDYmedian(0.0f);
                    userData.setGDZmedian(0.0f);
                    userData.setGDXSD(0.0f);
                    userData.setGDYSD(0.0f);
                    userData.setGDZSD(0.0f);
                }
                if (!userData.getMagnetometerDataX().isEmpty()){
                    userData.setMDXmax(Collections.max(userData.getMagnetometerDataX()));
                    userData.setMDYmax(Collections.max(userData.getMagnetometerDataY()));
                    userData.setMDZmax(Collections.max(userData.getMagnetometerDataZ()));
                    userData.setMDXmin(Collections.min(userData.getMagnetometerDataX()));
                    userData.setMDYmin(Collections.min(userData.getMagnetometerDataY()));
                    userData.setMDZmin(Collections.min(userData.getMagnetometerDataZ()));
                    userData.setMDXmean(userData.calculateMean(userData.getMagnetometerDataX()));
                    userData.setMDYmean(userData.calculateMean(userData.getMagnetometerDataY()));
                    userData.setMDZmean(userData.calculateMean(userData.getMagnetometerDataZ()));
                    userData.setMDXmedian(userData.calculateMedian(userData.getMagnetometerDataX()));
                    userData.setMDYmedian(userData.calculateMedian(userData.getMagnetometerDataY()));
                    userData.setMDZmedian(userData.calculateMedian(userData.getMagnetometerDataZ()));
                    userData.setMDXSD(userData.calculateStandardDeviation(userData.getMagnetometerDataX()));
                    userData.setMDYSD(userData.calculateStandardDeviation(userData.getMagnetometerDataY()));
                    userData.setMDZSD(userData.calculateStandardDeviation(userData.getMagnetometerDataZ()));
                } else {
                    userData.setMDXmax(0.0f);
                    userData.setMDYmax(0.0f);
                    userData.setMDZmax(0.0f);
                    userData.setMDXmin(0.0f);
                    userData.setMDYmin(0.0f);
                    userData.setMDZmin(0.0f);
                    userData.setMDXmean(0.0f);
                    userData.setMDYmean(0.0f);
                    userData.setMDZmean(0.0f);
                    userData.setMDXmedian(0.0f);
                    userData.setMDYmedian(0.0f);
                    userData.setMDZmedian(0.0f);
                    userData.setMDXSD(0.0f);
                    userData.setMDYSD(0.0f);
                    userData.setMDZSD(0.0f);
                }
                sendDataToBackend(userData);
                //showLog(userData);
                Log.v("Lin", "scrollview");
                flag = false;
                isFirst = true;
                break;
        }
    }
    public  boolean isPossibleClick(UserData userData) {
        return (userData.getDistance() < 10) && (userData.getDuration() < 100L);
    }
}
package tw.com.test;
import android.graphics.PointF;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserData implements Parcelable {
    private String account; //使用者帳號
    private List<Float> pressures; // 每次滑動中的所有手指壓力值
    private List<Float> sizes; // 每次滑動中的所有手指大小值
    private List<PointF> Points; // 紀錄每次滑動的所有點
    private List<Float> angles; // 每次滑動兩點間的方向角度
    private List<Float> accelerometerDataX; // 加速度計X軸數值
    private List<Float> accelerometerDataY; // 加速度計Y軸數值
    private List<Float> accelerometerDataZ; // 加速度計Z軸數值
    private List<Float> gyroscopeDataX; // 陀螺儀X軸數值
    private List<Float> gyroscopeDataY; // 陀螺儀Y軸數值
    private List<Float> gyroscopeDataZ; // 陀螺儀Z軸數值
    private List<Float> magnetometerDataX; // 磁力儀X軸數值
    private List<Float> magnetometerDataY; // 磁力儀Y軸數值
    private List<Float> magnetometerDataZ; // 磁力儀Z軸數值
    private Float currentPressureMedian; // 手指壓力的中位數
    private Float currentSizeMedian; // 手指大小的中位數
    private Float startXFirst; // 每個滑動事件起始點的X座標
    private Float startYFirst; // 每個滑動事件起始點的Y座標
    private Float currentXLast; //每個滑動事件結束點的X座標
    private Float currentYLast; //每個滑動事件結束點的Y座標
    private Float distance; // 每個滑動事件的距離
    private Float trajectory; // 每次滑動的路徑長
    private Float directionOfEndToEndLine; // 每個滑動事件的方向角度
    private Long startTime; //每個滑動事件的起始時間
    private Long endTime; //每個滑動事件的結束時間
    private Long duration; // 每個滑動事件的時間差
    private Float averageSpeed; // 每次滑動的平均速率
    private Float averageDirectionEnsemble; // 平均方向角度
    private Float ADXmedian; // 加速度計X軸中位數
    private Float ADYmedian; // 加速度計Y軸中位數
    private Float ADZmedian; // 加速度計Z軸中位數
    private Float GDXmedian; // 陀螺儀X軸中位數
    private Float GDYmedian; // 陀螺儀Y軸中位數
    private Float GDZmedian; // 陀螺儀Z軸中位數
    private Float MDXmedian; // 磁力儀X軸中位數
    private Float MDYmedian; // 磁力儀Y軸中位數
    private Float MDZmedian; // 磁力儀Z軸中位數
    private Float ADXmean; // 加速度計X軸平均值
    private Float ADYmean; // 加速度計Y軸平均值
    private Float ADZmean; // 加速度計Z軸平均值
    private Float GDXmean; // 陀螺儀X軸平均值
    private Float GDYmean; // 陀螺儀Y軸平均值
    private Float GDZmean; // 陀螺儀Z軸平均值
    private Float MDXmean; // 磁力儀X軸平均值
    private Float MDYmean; // 磁力儀Y軸平均值
    private Float MDZmean; // 磁力儀Z軸平均值
    private Float ADXmax; // 加速度計X軸最大值
    private Float ADYmax; // 加速度計Y軸最大值
    private Float ADZmax; // 加速度計Z軸最大值
    private Float GDXmax; // 陀螺儀X軸最大值
    private Float GDYmax; // 陀螺儀Y軸最大值
    private Float GDZmax; // 陀螺儀Z軸最大值
    private Float MDXmax; // 磁力儀X軸最大值
    private Float MDYmax; // 磁力儀Y軸最大值
    private Float MDZmax; // 磁力儀Z軸最大值
    private Float ADXmin; // 加速度計X軸最小值
    private Float ADYmin; // 加速度計Y軸最小值
    private Float ADZmin; // 加速度計Z軸最小值
    private Float GDXmin; // 陀螺儀X軸最小值
    private Float GDYmin; // 陀螺儀Y軸最小值
    private Float GDZmin; // 陀螺儀Z軸最小值
    private Float MDXmin; // 磁力儀X軸最小值
    private Float MDYmin; // 磁力儀Y軸最小值
    private Float MDZmin; // 磁力儀Z軸最小值
    private Float ADXSD; // 加速度計X軸標準差
    private Float ADYSD; // 加速度計Y軸標準差
    private Float ADZSD; // 加速度計Z軸標準差
    private Float GDXSD; // 陀螺儀X軸標準差
    private Float GDYSD; // 陀螺儀Y軸標準差
    private Float GDZSD; // 陀螺儀Z軸標準差
    private Float MDXSD; // 磁力儀X軸標準差
    private Float MDYSD; // 磁力儀Y軸標準差
    private Float MDZSD; // 磁力儀Z軸標準差

    public UserData() {
        pressures = new ArrayList<>();
        sizes = new ArrayList<>();
        Points = new ArrayList<>();
        angles = new ArrayList<>();
        accelerometerDataX = new ArrayList<>();
        accelerometerDataY = new ArrayList<>();
        accelerometerDataZ = new ArrayList<>();
        gyroscopeDataX = new ArrayList<>();
        gyroscopeDataY = new ArrayList<>();
        gyroscopeDataZ = new ArrayList<>();
        magnetometerDataX = new ArrayList<>();
        magnetometerDataY = new ArrayList<>();
        magnetometerDataZ = new ArrayList<>();
        account = "";
        currentPressureMedian = 0.0f;
        currentSizeMedian = 0.0f;
        startXFirst = 0.0f;
        startYFirst = 0.0f;
        currentXLast = 0.0f;
        currentYLast = 0.0f;
        distance = 0.0f;
        directionOfEndToEndLine = 0.0f;
        startTime = 1L;
        endTime = 0L;
        duration = 0L;
        trajectory = 0.0f;
        averageSpeed = 0.0f;
        averageDirectionEnsemble = 0.0f;
        ADXmedian = 0.0f;
        ADYmedian = 0.0f;
        ADZmedian = 0.0f;
        GDXmedian = 0.0f;
        GDYmedian = 0.0f;
        GDZmedian = 0.0f;
        MDXmedian = 0.0f;
        MDYmedian = 0.0f;
        MDZmedian = 0.0f;
        ADXmean = 0.0f;
        ADYmean = 0.0f;
        ADZmean = 0.0f;
        GDXmean = 0.0f;
        GDYmean = 0.0f;
        GDZmean = 0.0f;
        MDXmean = 0.0f;
        MDYmean = 0.0f;
        MDZmean = 0.0f;
        ADXmax = 0.0f;
        ADYmax = 0.0f;
        ADZmax = 0.0f;
        GDXmax = 0.0f;
        GDYmax = 0.0f;
        GDZmax = 0.0f;
        MDXmax = 0.0f;
        MDYmax = 0.0f;
        MDZmax = 0.0f;
        ADXmin = 0.0f;
        ADYmin = 0.0f;
        ADZmin = 0.0f;
        GDXmin = 0.0f;
        GDYmin = 0.0f;
        GDZmin = 0.0f;
        MDXmin = 0.0f;
        MDYmin = 0.0f;
        MDZmin = 0.0f;
        ADXSD = 0.0f;
        ADYSD = 0.0f;
        ADZSD = 0.0f;
        GDXSD = 0.0f;
        GDYSD = 0.0f;
        GDZSD = 0.0f;
        MDXSD = 0.0f;
        MDYSD = 0.0f;
        MDZSD = 0.0f;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }
    public void setCurrentPressureMedian(Float CurrentPressureMedian) {
        this.currentPressureMedian = CurrentPressureMedian;
    }
    public void setCurrentSizeMedian(Float CurrentSizeMedian) {
        this.currentSizeMedian = CurrentSizeMedian;
    }
    public void setDirectionOfEndToEndLine(Float DirectionOfEndToEndLine) {
        this.directionOfEndToEndLine = DirectionOfEndToEndLine;
    }
    public void setAverageDirectionEnsemble(Float AverageDirectionEnsemble) {
        this.averageDirectionEnsemble = AverageDirectionEnsemble;
    }
    public void setStartXFirst(Float StartXFirst) { this.startXFirst = StartXFirst; }
    public void setStartYFirst(Float StartYFirst) { this.startYFirst = StartYFirst; }
    public void setCurrentXLast(Float CurrentXLast) { this.currentXLast = CurrentXLast; }
    public void setCurrentYLast(Float CurrentYLast) { this.currentYLast = CurrentYLast; }
    public void setDistance(Float Distance) { this.distance = Distance; }
    public void setTrajectory(Float Trajectory) {this.trajectory = Trajectory;}
    public void setStartTime(Long StartTime) { this.startTime = StartTime; }
    public void setEndTime(Long EndTime) { this.endTime = EndTime; }
    public void setDuration(Long Duration) { this.duration = Duration; }
    public void setAverageSpeed(Float AverageSpeed) { this.averageSpeed = AverageSpeed; }
    public void setADXmedian(Float ADXmedian) { this.ADXmedian = ADXmedian; }
    public void setADYmedian(Float ADYmedian) { this.ADYmedian = ADYmedian; }
    public void setADZmedian(Float ADZmedian) { this.ADZmedian = ADZmedian; }
    public void setGDXmedian(Float GDXmedian) { this.GDXmedian = GDXmedian; }
    public void setGDYmedian(Float GDYmedian) { this.GDYmedian = GDYmedian; }
    public void setGDZmedian(Float GDZmedian) { this.GDZmedian = GDZmedian; }
    public void setMDXmedian(Float MDXmedian) { this.MDXmedian = MDXmedian; }
    public void setMDYmedian(Float MDYmedian) { this.MDYmedian = MDYmedian; }
    public void setMDZmedian(Float MDZmedian) { this.MDZmedian = MDZmedian; }
    public void setADXmean(Float ADXmean) { this.ADXmean = ADXmean; }
    public void setADYmean(Float ADYmean) { this.ADYmean = ADYmean; }
    public void setADZmean(Float ADZmean) { this.ADZmean = ADZmean; }
    public void setGDXmean(Float GDXmean) { this.GDXmean = GDXmean; }
    public void setGDYmean(Float GDYmean) { this.GDYmean = GDYmean; }
    public void setGDZmean(Float GDZmean) { this.GDZmean = GDZmean; }
    public void setMDXmean(Float MDXmean) { this.MDXmean = MDXmean; }
    public void setMDYmean(Float MDYmean) { this.MDYmean = MDYmean; }
    public void setMDZmean(Float MDZmean) { this.MDZmean = MDZmean; }
    public void setADXmax(Float ADXmax)
    {
        this.ADXmax = ADXmax;
    }
    public void setADYmax(Float ADYmax)
    {
        this.ADYmax = ADYmax;
    }
    public void setADZmax(Float ADZmax)
    {
        this.ADZmax = ADZmax;
    }
    public void setGDXmax(Float GDXmax)
    {
        this.GDXmax = GDXmax;
    }
    public void setGDYmax(Float GDYmax)
    {
        this.GDYmax = GDYmax;
    }
    public void setGDZmax(Float GDZmax)
    {
        this.GDZmax = GDZmax;
    }
    public void setMDXmax(Float MDXmax)
    {
        this.MDXmax = MDXmax;
    }
    public void setMDYmax(Float MDYmax)
    {
        this.MDYmax = MDYmax;
    }
    public void setMDZmax(Float MDZmax)
    {
        this.MDZmax = MDZmax;
    }
    public void setADXmin(Float ADXmin) { this.ADXmin = ADXmin; }
    public void setADYmin(Float ADYmin) { this.ADYmin = ADYmin; }
    public void setADZmin(Float ADZmin) { this.ADZmin = ADZmin; }
    public void setGDXmin(Float GDXmin) { this.GDXmin = GDXmin; }
    public void setGDYmin(Float GDYmin) { this.GDYmin = GDYmin; }
    public void setGDZmin(Float GDZmin) { this.GDZmin = GDZmin; }
    public void setMDXmin(Float MDXmin) { this.MDXmin = MDXmin; }
    public void setMDYmin(Float MDYmin) { this.MDYmin = MDYmin; }
    public void setMDZmin(Float MDZmin) { this.MDZmin = MDZmin; }
    public void setADXSD(Float ADXSD)
    {
        this.ADXSD = ADXSD;
    }
    public void setADYSD(Float ADYSD)
    {
        this.ADYSD = ADYSD;
    }
    public void setADZSD(Float ADZSD)
    {
        this.ADZSD = ADZSD;
    }
    public void setGDXSD(Float GDXSD)
    {
        this.GDXSD = GDXSD;
    }
    public void setGDYSD(Float GDYSD)
    {
        this.GDYSD = GDYSD;
    }
    public void setGDZSD(Float GDZSD)
    {
        this.GDZSD = GDZSD;
    }
    public void setMDXSD(Float MDXSD)
    {
        this.MDXSD = MDXSD;
    }
    public void setMDYSD(Float MDYSD)
    {
        this.MDYSD = MDYSD;
    }
    public void setMDZSD(Float MDZSD) { this.MDZSD = MDZSD; }

    public String getAccount() {return  account;}
    public Float getCurrentPressureMedian() {
        return currentPressureMedian;
    }
    public Float getCurrentSizeMedian() {
        return currentSizeMedian;
    }
    public Float getDirectionOfEndToEndLine() {
        return directionOfEndToEndLine;
    }
    public Float getAverageDirectionEnsemble() {
        return averageDirectionEnsemble;
    }
    public Float getStartXFirst() { return startXFirst; }
    public Float getStartYFirst() { return startYFirst; }
    public Float getCurrentXLast() { return currentXLast; }
    public Float getCurrentYLast() { return currentYLast; }
    public Float getDistance() { return distance; }
    public Float getTrajectory() {return trajectory;}
    public Long getStartTime() { return startTime; }
    public Long getEndTime() { return endTime;}
    public Long getDuration() { return duration; }
    public Float getAverageSpeed() { return averageSpeed; }
    public Float getADXmedian() { return ADXmedian; }
    public Float getADYmedian() { return ADYmedian; }
    public Float getADZmedian() { return ADZmedian; }
    public Float getGDXmedian() { return GDXmedian; }
    public Float getGDYmedian() { return GDYmedian; }
    public Float getGDZmedian() { return GDZmedian; }
    public Float getMDXmedian() { return MDXmedian; }
    public Float getMDYmedian() { return MDYmedian; }
    public Float getMDZmedian() { return MDZmedian; }
    public Float getADXmean() { return ADXmean; }
    public Float getADYmean() { return ADYmean; }
    public Float getADZmean() { return ADZmean; }
    public Float getGDXmean() { return GDXmean; }
    public Float getGDYmean() { return GDYmean; }
    public Float getGDZmean() { return GDZmean; }
    public Float getMDXmean() { return MDXmean; }
    public Float getMDYmean() { return MDYmean; }
    public Float getMDZmean() { return MDZmean; }
    public Float getADXmax()
    {
        return ADXmax;
    }
    public Float getADYmax()
    {
        return ADYmax;
    }
    public Float getADZmax()
    {
        return ADZmax;
    }
    public Float getGDXmax()
    {
        return GDXmax;
    }
    public Float getGDYmax()
    {
        return GDYmax;
    }
    public Float getGDZmax()
    {
        return GDZmax;
    }
    public Float getMDXmax()
    {
        return MDXmax;
    }
    public Float getMDYmax()
    {
        return MDYmax;
    }
    public Float getMDZmax()
    {
        return MDZmax;
    }
    public Float getADXmin() { return ADXmin; }
    public Float getADYmin() { return ADYmin; }
    public Float getADZmin() { return ADZmin; }
    public Float getGDXmin() { return GDXmin; }
    public Float getGDYmin() { return GDYmin; }
    public Float getGDZmin() { return GDZmin; }
    public Float getMDXmin() { return MDXmin; }
    public Float getMDYmin() { return MDYmin; }
    public Float getMDZmin() { return MDZmin; }
    public Float getADXSD()
    {
        return ADXSD;
    }
    public Float getADYSD()
    {
        return ADYSD;
    }
    public Float getADZSD()
    {
        return ADZSD;
    }
    public Float getGDXSD()
    {
        return GDXSD;
    }
    public Float getGDYSD()
    {
        return GDYSD;
    }
    public Float getGDZSD()
    {
        return GDZSD;
    }
    public Float getMDXSD()
    {
        return MDXSD;
    }
    public Float getMDYSD()
    {
        return MDYSD;
    }
    public Float getMDZSD() { return MDZSD; }
    public List<Float> getAccelerometerDataX() {return accelerometerDataX;}
    public List<Float> getAccelerometerDataY() {return accelerometerDataY;}
    public List<Float> getAccelerometerDataZ() {return accelerometerDataZ;}
    public List<Float> getGyroscopeDataX() {return gyroscopeDataX;}
    public List<Float> getGyroscopeDataY() {return gyroscopeDataY;}
    public List<Float> getGyroscopeDataZ() {return gyroscopeDataZ;}
    public List<Float> getMagnetometerDataX() {return magnetometerDataX;}
    public List<Float> getMagnetometerDataY() {return magnetometerDataY;}
    public List<Float> getMagnetometerDataZ() {return magnetometerDataZ;}
    public List<Float> getPressures() {return pressures;}
    public List<Float> getSizes() {return sizes;}
    public List<PointF> getPoints() {return Points;}
    public List<Float> getAngles() {return angles;}


    public void addPressure(float pressure)
    {
        pressures.add(pressure);
    }
    public void addSizes(float size)
    {
        sizes.add(size);
    }
    public void addPoints(PointF point)
    {
        Points.add(point);
    }
    public void addAngles(float angle)
    {
        angles.add(angle);
    }
    public void addAccelerometerDataX(float X)
    {
        accelerometerDataX.add(X);
    }
    public void addAccelerometerDataY(float Y)
    {
        accelerometerDataY.add(Y);
    }
    public void addAccelerometerDataZ(float Z)
    {
        accelerometerDataZ.add(Z);
    }
    public void addGyroscopeDataX(float X)
    {
        gyroscopeDataX.add(X);
    }
    public void addGyroscopeDataY(float Y)
    {
        gyroscopeDataY.add(Y);
    }
    public void addGyroscopeDataZ(float Z)
    {
        gyroscopeDataZ.add(Z);
    }
    public void addMagnetometerDataX(float X)
    {
        magnetometerDataX.add(X);
    }
    public void addMagnetometerDataY(float Y)
    {
        magnetometerDataY.add(Y);
    }
    public void addMagnetometerDataZ(float Z)
    {
        magnetometerDataZ.add(Z);
    }
    public float calculateMedian(List<Float> values) {
        if (values.isEmpty()) {
            return 0;
        }
        Collections.sort(values);
        int size = values.size();
        int midIndex = size / 2;
        //資料數量為偶數
        if (size % 2 == 0) {
            float midValue1 = values.get(midIndex - 1);
            float midValue2 = values.get(midIndex);
            return (midValue1 + midValue2) / 2;
        } else {
            return values.get(midIndex);
        }
    }
    // 計算兩點之間的歐幾里得距離
    public float calculateDistance(float startX, float startY, float endX, float endY) {
        float dx = endX - startX;
        float dy = endY - startY;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    // 計算兩點之間的方向角度（弧度）
    public float calculateDirection(float startX, float startY, float endX, float endY) {
        float dx = endX - startX;
        float dy = endY - startY;
        return (float) Math.atan2(dy, dx);
    }

    public float calculateTrajectoryLength(List<PointF> Points) {
        float length = 0;
        int size = Points.size();
        for (int i = 1; i < size; i++) {
            PointF p1 = Points.get(i - 1);
            PointF p2 = Points.get(i);
            float segmentLength = calculateDistance(p1.x, p1.y, p2.x, p2.y);
            length += segmentLength;
        }
        return length;
    }

    // 計算平均角度
    public float calculateAverageAngle(List<Float> angles) {
        float sumSin = 0;
        float sumCos = 0;
        int count = angles.size();

        for (float angle : angles) {
            sumSin += Math.sin(angle);
            sumCos += Math.cos(angle);
        }

        float averageSin = sumSin / count;
        float averageCos = sumCos / count;


        return (float) Math.atan2(averageSin, averageCos);
    }

    // 計算平均值
    public float calculateMean(List<Float> values) {
        float sum = 0;
        int size = values.size();
        for (float value : values) {
            sum += value;
        }
        return sum/size;
    }

    // 計算標準差
    public float calculateStandardDeviation(List<Float> values) {
        float mean = 0;
        mean = calculateMean(values);

        // 計算差的平方和
        float sumOfSquares = 0;
        for (float value : values) {
            float difference = value - mean;
            sumOfSquares += difference * difference;
        }
        // 計算標準差
        float standardDeviation = (float) Math.sqrt(sumOfSquares / values.size());
        return standardDeviation;
    }
    public void reset() {
        pressures = new ArrayList<>();
        sizes = new ArrayList<>();
        Points = new ArrayList<>();
        angles = new ArrayList<>();
        accelerometerDataX = new ArrayList<>();
        accelerometerDataY = new ArrayList<>();
        accelerometerDataZ = new ArrayList<>();
        gyroscopeDataX = new ArrayList<>();
        gyroscopeDataY = new ArrayList<>();
        gyroscopeDataZ = new ArrayList<>();
        magnetometerDataX = new ArrayList<>();
        magnetometerDataY = new ArrayList<>();
        magnetometerDataZ = new ArrayList<>();
        account = "";
        currentPressureMedian = 0.0f;
        currentSizeMedian = 0.0f;
        startXFirst = 0.0f;
        startYFirst = 0.0f;
        currentXLast = 0.0f;
        currentYLast = 0.0f;
        distance = 0.0f;
        directionOfEndToEndLine = 0.0f;
        startTime = 0L;
        endTime = 0L;
        duration = 0L;
        trajectory = 0.0f;
        averageSpeed = 0.0f;
        averageDirectionEnsemble = 0.0f;
        ADXmedian = 0.0f;
        ADYmedian = 0.0f;
        ADZmedian = 0.0f;
        GDXmedian = 0.0f;
        GDYmedian = 0.0f;
        GDZmedian = 0.0f;
        MDXmedian = 0.0f;
        MDYmedian = 0.0f;
        MDZmedian = 0.0f;
        ADXmean = 0.0f;
        ADYmean = 0.0f;
        ADZmean = 0.0f;
        GDXmean = 0.0f;
        GDYmean = 0.0f;
        GDZmean = 0.0f;
        MDXmean = 0.0f;
        MDYmean = 0.0f;
        MDZmean = 0.0f;
        ADXmax = 0.0f;
        ADYmax = 0.0f;
        ADZmax = 0.0f;
        GDXmax = 0.0f;
        GDYmax = 0.0f;
        GDZmax = 0.0f;
        MDXmax = 0.0f;
        MDYmax = 0.0f;
        MDZmax = 0.0f;
        ADXmin = 0.0f;
        ADYmin = 0.0f;
        ADZmin = 0.0f;
        GDXmin = 0.0f;
        GDYmin = 0.0f;
        GDZmin = 0.0f;
        MDXmin = 0.0f;
        MDYmin = 0.0f;
        MDZmin = 0.0f;
        ADXSD = 0.0f;
        ADYSD = 0.0f;
        ADZSD = 0.0f;
        GDXSD = 0.0f;
        GDYSD = 0.0f;
        GDZSD = 0.0f;
        MDXSD = 0.0f;
        MDYSD = 0.0f;
        MDZSD = 0.0f;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.account);
        dest.writeList(this.pressures);
        dest.writeList(this.sizes);
        dest.writeTypedList(this.Points);
        dest.writeList(this.angles);
        dest.writeList(this.accelerometerDataX);
        dest.writeList(this.accelerometerDataY);
        dest.writeList(this.accelerometerDataZ);
        dest.writeList(this.gyroscopeDataX);
        dest.writeList(this.gyroscopeDataY);
        dest.writeList(this.gyroscopeDataZ);
        dest.writeList(this.magnetometerDataX);
        dest.writeList(this.magnetometerDataY);
        dest.writeList(this.magnetometerDataZ);
        dest.writeValue(this.currentPressureMedian);
        dest.writeValue(this.currentSizeMedian);
        dest.writeValue(this.startXFirst);
        dest.writeValue(this.startYFirst);
        dest.writeValue(this.currentXLast);
        dest.writeValue(this.currentYLast);
        dest.writeValue(this.distance);
        dest.writeValue(this.trajectory);
        dest.writeValue(this.directionOfEndToEndLine);
        dest.writeValue(this.startTime);
        dest.writeValue(this.endTime);
        dest.writeValue(this.duration);
        dest.writeValue(this.averageSpeed);
        dest.writeValue(this.averageDirectionEnsemble);
        dest.writeValue(this.ADXmedian);
        dest.writeValue(this.ADYmedian);
        dest.writeValue(this.ADZmedian);
        dest.writeValue(this.GDXmedian);
        dest.writeValue(this.GDYmedian);
        dest.writeValue(this.GDZmedian);
        dest.writeValue(this.MDXmedian);
        dest.writeValue(this.MDYmedian);
        dest.writeValue(this.MDZmedian);
        dest.writeValue(this.ADXmean);
        dest.writeValue(this.ADYmean);
        dest.writeValue(this.ADZmean);
        dest.writeValue(this.GDXmean);
        dest.writeValue(this.GDYmean);
        dest.writeValue(this.GDZmean);
        dest.writeValue(this.MDXmean);
        dest.writeValue(this.MDYmean);
        dest.writeValue(this.MDZmean);
        dest.writeValue(this.ADXmax);
        dest.writeValue(this.ADYmax);
        dest.writeValue(this.ADZmax);
        dest.writeValue(this.GDXmax);
        dest.writeValue(this.GDYmax);
        dest.writeValue(this.GDZmax);
        dest.writeValue(this.MDXmax);
        dest.writeValue(this.MDYmax);
        dest.writeValue(this.MDZmax);
        dest.writeValue(this.ADXmin);
        dest.writeValue(this.ADYmin);
        dest.writeValue(this.ADZmin);
        dest.writeValue(this.GDXmin);
        dest.writeValue(this.GDYmin);
        dest.writeValue(this.GDZmin);
        dest.writeValue(this.MDXmin);
        dest.writeValue(this.MDYmin);
        dest.writeValue(this.MDZmin);
        dest.writeValue(this.ADXSD);
        dest.writeValue(this.ADYSD);
        dest.writeValue(this.ADZSD);
        dest.writeValue(this.GDXSD);
        dest.writeValue(this.GDYSD);
        dest.writeValue(this.GDZSD);
        dest.writeValue(this.MDXSD);
        dest.writeValue(this.MDYSD);
        dest.writeValue(this.MDZSD);
    }

    public void readFromParcel(Parcel source) {
        this.account = source.readString();
        this.pressures = new ArrayList<Float>();
        source.readList(this.pressures, Float.class.getClassLoader());
        this.sizes = new ArrayList<Float>();
        source.readList(this.sizes, Float.class.getClassLoader());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            this.Points = source.createTypedArrayList(PointF.CREATOR);
        }
        this.angles = new ArrayList<Float>();
        source.readList(this.angles, Float.class.getClassLoader());
        this.accelerometerDataX = new ArrayList<Float>();
        source.readList(this.accelerometerDataX, Float.class.getClassLoader());
        this.accelerometerDataY = new ArrayList<Float>();
        source.readList(this.accelerometerDataY, Float.class.getClassLoader());
        this.accelerometerDataZ = new ArrayList<Float>();
        source.readList(this.accelerometerDataZ, Float.class.getClassLoader());
        this.gyroscopeDataX = new ArrayList<Float>();
        source.readList(this.gyroscopeDataX, Float.class.getClassLoader());
        this.gyroscopeDataY = new ArrayList<Float>();
        source.readList(this.gyroscopeDataY, Float.class.getClassLoader());
        this.gyroscopeDataZ = new ArrayList<Float>();
        source.readList(this.gyroscopeDataZ, Float.class.getClassLoader());
        this.magnetometerDataX = new ArrayList<Float>();
        source.readList(this.magnetometerDataX, Float.class.getClassLoader());
        this.magnetometerDataY = new ArrayList<Float>();
        source.readList(this.magnetometerDataY, Float.class.getClassLoader());
        this.magnetometerDataZ = new ArrayList<Float>();
        source.readList(this.magnetometerDataZ, Float.class.getClassLoader());
        this.currentPressureMedian = (Float) source.readValue(Float.class.getClassLoader());
        this.currentSizeMedian = (Float) source.readValue(Float.class.getClassLoader());
        this.startXFirst = (Float) source.readValue(Float.class.getClassLoader());
        this.startYFirst = (Float) source.readValue(Float.class.getClassLoader());
        this.currentXLast = (Float) source.readValue(Float.class.getClassLoader());
        this.currentYLast = (Float) source.readValue(Float.class.getClassLoader());
        this.distance = (Float) source.readValue(Float.class.getClassLoader());
        this.trajectory = (Float) source.readValue(Float.class.getClassLoader());
        this.directionOfEndToEndLine = (Float) source.readValue(Float.class.getClassLoader());
        this.startTime = (Long) source.readValue(Long.class.getClassLoader());
        this.endTime = (Long) source.readValue(Long.class.getClassLoader());
        this.duration = (Long) source.readValue(Long.class.getClassLoader());
        this.averageSpeed = (Float) source.readValue(Float.class.getClassLoader());
        this.averageDirectionEnsemble = (Float) source.readValue(Float.class.getClassLoader());
        this.ADXmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.ADYmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.ADZmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.GDXmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.GDYmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.GDZmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.MDXmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.MDYmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.MDZmedian = (Float) source.readValue(Float.class.getClassLoader());
        this.ADXmean = (Float) source.readValue(Float.class.getClassLoader());
        this.ADYmean = (Float) source.readValue(Float.class.getClassLoader());
        this.ADZmean = (Float) source.readValue(Float.class.getClassLoader());
        this.GDXmean = (Float) source.readValue(Float.class.getClassLoader());
        this.GDYmean = (Float) source.readValue(Float.class.getClassLoader());
        this.GDZmean = (Float) source.readValue(Float.class.getClassLoader());
        this.MDXmean = (Float) source.readValue(Float.class.getClassLoader());
        this.MDYmean = (Float) source.readValue(Float.class.getClassLoader());
        this.MDZmean = (Float) source.readValue(Float.class.getClassLoader());
        this.ADXmax = (Float) source.readValue(Float.class.getClassLoader());
        this.ADYmax = (Float) source.readValue(Float.class.getClassLoader());
        this.ADZmax = (Float) source.readValue(Float.class.getClassLoader());
        this.GDXmax = (Float) source.readValue(Float.class.getClassLoader());
        this.GDYmax = (Float) source.readValue(Float.class.getClassLoader());
        this.GDZmax = (Float) source.readValue(Float.class.getClassLoader());
        this.MDXmax = (Float) source.readValue(Float.class.getClassLoader());
        this.MDYmax = (Float) source.readValue(Float.class.getClassLoader());
        this.MDZmax = (Float) source.readValue(Float.class.getClassLoader());
        this.ADXmin = (Float) source.readValue(Float.class.getClassLoader());
        this.ADYmin = (Float) source.readValue(Float.class.getClassLoader());
        this.ADZmin = (Float) source.readValue(Float.class.getClassLoader());
        this.GDXmin = (Float) source.readValue(Float.class.getClassLoader());
        this.GDYmin = (Float) source.readValue(Float.class.getClassLoader());
        this.GDZmin = (Float) source.readValue(Float.class.getClassLoader());
        this.MDXmin = (Float) source.readValue(Float.class.getClassLoader());
        this.MDYmin = (Float) source.readValue(Float.class.getClassLoader());
        this.MDZmin = (Float) source.readValue(Float.class.getClassLoader());
        this.ADXSD = (Float) source.readValue(Float.class.getClassLoader());
        this.ADYSD = (Float) source.readValue(Float.class.getClassLoader());
        this.ADZSD = (Float) source.readValue(Float.class.getClassLoader());
        this.GDXSD = (Float) source.readValue(Float.class.getClassLoader());
        this.GDYSD = (Float) source.readValue(Float.class.getClassLoader());
        this.GDZSD = (Float) source.readValue(Float.class.getClassLoader());
        this.MDXSD = (Float) source.readValue(Float.class.getClassLoader());
        this.MDYSD = (Float) source.readValue(Float.class.getClassLoader());
        this.MDZSD = (Float) source.readValue(Float.class.getClassLoader());
    }

    protected UserData(Parcel in) {
        this.account = in.readString();
        this.pressures = new ArrayList<Float>();
        in.readList(this.pressures, Float.class.getClassLoader());
        this.sizes = new ArrayList<Float>();
        in.readList(this.sizes, Float.class.getClassLoader());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            this.Points = in.createTypedArrayList(PointF.CREATOR);
        }
        this.angles = new ArrayList<Float>();
        in.readList(this.angles, Float.class.getClassLoader());
        this.accelerometerDataX = new ArrayList<Float>();
        in.readList(this.accelerometerDataX, Float.class.getClassLoader());
        this.accelerometerDataY = new ArrayList<Float>();
        in.readList(this.accelerometerDataY, Float.class.getClassLoader());
        this.accelerometerDataZ = new ArrayList<Float>();
        in.readList(this.accelerometerDataZ, Float.class.getClassLoader());
        this.gyroscopeDataX = new ArrayList<Float>();
        in.readList(this.gyroscopeDataX, Float.class.getClassLoader());
        this.gyroscopeDataY = new ArrayList<Float>();
        in.readList(this.gyroscopeDataY, Float.class.getClassLoader());
        this.gyroscopeDataZ = new ArrayList<Float>();
        in.readList(this.gyroscopeDataZ, Float.class.getClassLoader());
        this.magnetometerDataX = new ArrayList<Float>();
        in.readList(this.magnetometerDataX, Float.class.getClassLoader());
        this.magnetometerDataY = new ArrayList<Float>();
        in.readList(this.magnetometerDataY, Float.class.getClassLoader());
        this.magnetometerDataZ = new ArrayList<Float>();
        in.readList(this.magnetometerDataZ, Float.class.getClassLoader());
        this.currentPressureMedian = (Float) in.readValue(Float.class.getClassLoader());
        this.currentSizeMedian = (Float) in.readValue(Float.class.getClassLoader());
        this.startXFirst = (Float) in.readValue(Float.class.getClassLoader());
        this.startYFirst = (Float) in.readValue(Float.class.getClassLoader());
        this.currentXLast = (Float) in.readValue(Float.class.getClassLoader());
        this.currentYLast = (Float) in.readValue(Float.class.getClassLoader());
        this.distance = (Float) in.readValue(Float.class.getClassLoader());
        this.trajectory = (Float) in.readValue(Float.class.getClassLoader());
        this.directionOfEndToEndLine = (Float) in.readValue(Float.class.getClassLoader());
        this.startTime = (Long) in.readValue(Long.class.getClassLoader());
        this.endTime = (Long) in.readValue(Long.class.getClassLoader());
        this.duration = (Long) in.readValue(Long.class.getClassLoader());
        this.averageSpeed = (Float) in.readValue(Float.class.getClassLoader());
        this.averageDirectionEnsemble = (Float) in.readValue(Float.class.getClassLoader());
        this.ADXmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.ADYmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.ADZmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.GDXmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.GDYmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.GDZmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.MDXmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.MDYmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.MDZmedian = (Float) in.readValue(Float.class.getClassLoader());
        this.ADXmean = (Float) in.readValue(Float.class.getClassLoader());
        this.ADYmean = (Float) in.readValue(Float.class.getClassLoader());
        this.ADZmean = (Float) in.readValue(Float.class.getClassLoader());
        this.GDXmean = (Float) in.readValue(Float.class.getClassLoader());
        this.GDYmean = (Float) in.readValue(Float.class.getClassLoader());
        this.GDZmean = (Float) in.readValue(Float.class.getClassLoader());
        this.MDXmean = (Float) in.readValue(Float.class.getClassLoader());
        this.MDYmean = (Float) in.readValue(Float.class.getClassLoader());
        this.MDZmean = (Float) in.readValue(Float.class.getClassLoader());
        this.ADXmax = (Float) in.readValue(Float.class.getClassLoader());
        this.ADYmax = (Float) in.readValue(Float.class.getClassLoader());
        this.ADZmax = (Float) in.readValue(Float.class.getClassLoader());
        this.GDXmax = (Float) in.readValue(Float.class.getClassLoader());
        this.GDYmax = (Float) in.readValue(Float.class.getClassLoader());
        this.GDZmax = (Float) in.readValue(Float.class.getClassLoader());
        this.MDXmax = (Float) in.readValue(Float.class.getClassLoader());
        this.MDYmax = (Float) in.readValue(Float.class.getClassLoader());
        this.MDZmax = (Float) in.readValue(Float.class.getClassLoader());
        this.ADXmin = (Float) in.readValue(Float.class.getClassLoader());
        this.ADYmin = (Float) in.readValue(Float.class.getClassLoader());
        this.ADZmin = (Float) in.readValue(Float.class.getClassLoader());
        this.GDXmin = (Float) in.readValue(Float.class.getClassLoader());
        this.GDYmin = (Float) in.readValue(Float.class.getClassLoader());
        this.GDZmin = (Float) in.readValue(Float.class.getClassLoader());
        this.MDXmin = (Float) in.readValue(Float.class.getClassLoader());
        this.MDYmin = (Float) in.readValue(Float.class.getClassLoader());
        this.MDZmin = (Float) in.readValue(Float.class.getClassLoader());
        this.ADXSD = (Float) in.readValue(Float.class.getClassLoader());
        this.ADYSD = (Float) in.readValue(Float.class.getClassLoader());
        this.ADZSD = (Float) in.readValue(Float.class.getClassLoader());
        this.GDXSD = (Float) in.readValue(Float.class.getClassLoader());
        this.GDYSD = (Float) in.readValue(Float.class.getClassLoader());
        this.GDZSD = (Float) in.readValue(Float.class.getClassLoader());
        this.MDXSD = (Float) in.readValue(Float.class.getClassLoader());
        this.MDYSD = (Float) in.readValue(Float.class.getClassLoader());
        this.MDZSD = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
}


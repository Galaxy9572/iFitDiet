package com.orange.ifitdiet.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.orange.ifitdiet.activity.MainActivity;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.domain.LocationBean;
import com.orange.ifitdiet.domain.WeatherBean;

/**
 * 定位工具类，使用高德定位SDK
 * Created by 廖俊瑶 on 2016/9/3.
 */
public class LocateUtil {
    private Context context;
    private AMapLocationClient mLocationClient;//声明AMapLocationClient类对象
    private String province;//省
    private String city;//市
    private String district;//区
    private String street;//街道
    private BeanPool beanPool;
    private LocationBean locationBean;//存放定位数据的JavaBean
    private WeatherBean weatherBean;//存放天气数据的JavaBean
    private String weather;//当前天气
    private String temperature;//当前温度
    private TimeUtil timeUtil= (TimeUtil) MainActivity.getUtilPool().getUtilMap().get("timeUtil");

    public LocateUtil(Context context) {
        this.context = context;
        beanPool = MainActivity.getBeanPool();
    }

    /**
     * 搞得地图的定位方法
     *
     * @param context
     */
    public void locate(final Context context) {
        mLocationClient = new AMapLocationClient(context);//初始化定位
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) if (aMapLocation.getErrorCode() == 0) {//定位成功回调信息，设置相关消息
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    String time=timeUtil.getCurrentTime();//定位时间
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                    province = aMapLocation.getProvince();//省信息
                    city = aMapLocation.getCity();//城市信息
                    district = aMapLocation.getDistrict();//城区信息
                    street = aMapLocation.getStreet();//街道信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    Log.e("城市", province + city + district + street);
                    locationBean = new LocationBean(province, city, district, street);
                    beanPool.getBeanMap().put("locationBean", locationBean);
                    getWeatherForecast(LocateUtil.this.context, city);

                } else {//定位失败显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError",
                            "location Error, ErrCode:"
                                    + aMapLocation.getErrorCode() + ", errInfo:"
                                    + aMapLocation.getErrorInfo());
                    Toast.makeText(context, aMapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
                    locationBean = new LocationBean("湖南省", "益阳市", "赫山区", "团圆南路");
                    beanPool.getBeanMap().put("locationBean", locationBean);
                    weatherBean = new WeatherBean("", "");
                    beanPool.getBeanMap().put("weatherBean", weatherBean);
                }
            }
        };//声明定位回调监听器
        mLocationClient.setLocationListener(mLocationListener);//设置定位回调监听
        AMapLocationClientOption mLocationClientOption = null;//声明mLocationOption对象
        mLocationClientOption = new AMapLocationClientOption();//初始化定位参数
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationClientOption.setNeedAddress(true);//设置是否返回地址信息（默认返回地址信息）
        mLocationClientOption.setOnceLocation(false);//设置是否只定位一次,默认为false
        mLocationClientOption.setWifiActiveScan(true);//设置是否强制刷新WIFI，默认为强制刷新
        mLocationClientOption.setMockEnable(false);//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationClientOption.setInterval(60000);//设置定位间隔,单位毫秒,默认为2000ms
        mLocationClient.setLocationOption(mLocationClientOption);//给定位客户端对象设置定位参数
        mLocationClient.startLocation(); //启动定位
    }

    /**
     * 获取当地天气的方法
     * @param context
     * @param city 当前的城市
     */
    public void getWeatherForecast(Context context, String city) {
        WeatherSearchQuery weatherQuery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_LIVE);
        WeatherSearch weatherSearch = new WeatherSearch(context);
        weatherSearch.setQuery(weatherQuery);
        weatherSearch.setOnWeatherSearchListener(new WeatherSearch.OnWeatherSearchListener() {
            @Override
            public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i) {
                if (i == 1000) {
                    LocalWeatherLive liveWeather = localWeatherLiveResult.getLiveResult();
                    weather = liveWeather.getWeather();
                    temperature = liveWeather.getTemperature();
                    weatherBean = new WeatherBean(temperature, weather);
                    beanPool.getBeanMap().put("weatherBean", weatherBean);
                    Log.e("天气", temperature + weather);

                } else {
                    Log.e("", "查询天气失败");
                }
            }

            @Override
            public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

            }
        });
        weatherSearch.searchWeatherAsyn();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

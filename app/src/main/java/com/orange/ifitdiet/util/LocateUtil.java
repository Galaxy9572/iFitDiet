package com.orange.ifitdiet.util;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 廖俊瑶 on 2016/9/3.
 * 定位工具类，使用高德定位SDK
 */
public class LocateUtil {
    private Context context;
    private AMapLocationClient mLocationClient;//声明AMapLocationClient类对象
    private String province;//省
    private String city;//市
    private String district;//区
    private String street;//街道

    public LocateUtil(Context context) {
        this.context=context;
    }

    public void locate(Context context) {
        mLocationClient = new AMapLocationClient(context);//初始化定位
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        aMapLocation.getLatitude();//获取经度
                        aMapLocation.getLongitude();//获取纬度
                        aMapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);//定位时间
                        aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                        province = aMapLocation.getProvince();//省信息
                        city = aMapLocation.getCity();//城市信息
                        district = aMapLocation.getDistrict();//城区信息
                        street = aMapLocation.getStreet();//街道信息
                        aMapLocation.getCityCode();//城市编码
                        aMapLocation.getAdCode();//地区编码
                        mLocationClient.stopLocation();
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError",
                                "location Error, ErrCode:"
                                        + aMapLocation.getErrorCode() + ", errInfo:"
                                        + aMapLocation.getErrorInfo());
                    }
                }
            }
        };//声明定位回调监听器
        mLocationClient.setLocationListener(mLocationListener);//设置定位回调监听
        AMapLocationClientOption mLocationClientOption = null;//声明mLocationOption对象
        mLocationClientOption = new AMapLocationClientOption();//初始化定位参数
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationClientOption.setNeedAddress(true);//设置是否返回地址信息（默认返回地址信息）
        mLocationClientOption.setOnceLocation(true);//设置是否只定位一次,默认为false
        mLocationClientOption.setWifiActiveScan(true);//设置是否强制刷新WIFI，默认为强制刷新
        mLocationClientOption.setMockEnable(false);//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationClientOption.setInterval(2000);//设置定位间隔,单位毫秒,默认为2000ms
        mLocationClient.setLocationOption(mLocationClientOption);//给定位客户端对象设置定位参数
        mLocationClient.startLocation(); //启动定位
        getWeatherForecast(context,city);
        System.out.println(city+"bbbbbbbbbbbbbbbbbbbbbbb");
    }


    public void getWeatherForecast(Context context,String city) {
        WeatherSearchQuery weatherQuery = new WeatherSearchQuery(city,WeatherSearchQuery.WEATHER_TYPE_LIVE);
        WeatherSearch weatherSearch = new WeatherSearch(context);
        weatherSearch.setQuery(weatherQuery);
        weatherSearch.setOnWeatherSearchListener(new WeatherSearch.OnWeatherSearchListener() {
            @Override
            public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i) {
                if(i==1000){
                    LocalWeatherLive liveWeather= localWeatherLiveResult.getLiveResult();
                    System.out.println(liveWeather.getTemperature()+"aaaaaaaaaaaaaaaaaaaaaaa");

                }else{
                    Log.e("","查询天气失败");
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

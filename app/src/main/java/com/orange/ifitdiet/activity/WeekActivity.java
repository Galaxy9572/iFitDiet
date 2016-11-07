package com.orange.ifitdiet.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orange.ifitdiet.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class WeekActivity extends AppCompatActivity {
    LineChartView lineChart_steps, lineChart_heartbeat, lineChart_bloodpressure;
    String[] date_x = {"1", "2", "3", "4", "5", "6", "7"};//X轴的标注
    float[] steps = {8002, 7252, 9540, 6768, 7630, 7412, 7732};//图表的数据点
    float[] heartbeats = {67, 75, 73, 69, 77, 65, 64};//图表的数据点
    float[] bloodpressure_systolic = {120, 110, 115, 112, 112, 108, 127};//图表的数据点，收缩压
    float[] bloodpressure_diastolic = {70, 88, 76, 79, 80, 68, 87};//图表的数据点，舒张压
    private List<PointValue> mPointValues_steps = new ArrayList<>();
    private List<AxisValue> mAxisXValues_steps = new ArrayList<>();
    private List<PointValue> mPointValues_heartbeats = new ArrayList<>();
    private List<AxisValue> mAxisXValues_heartbeats = new ArrayList<>();
    private List<PointValue> mPointValues_bloodpressure_systolic = new ArrayList<>();
    private List<PointValue> mPointValues_bloodpressure_diastolic = new ArrayList<>();
    private List<AxisValue> mAxisXValues_bloodpressure = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        lineChart_steps = (LineChartView) findViewById(R.id.lcv_steps);
        lineChart_heartbeat = (LineChartView) findViewById(R.id.lcv_heartbeats);
        lineChart_bloodpressure = (LineChartView) findViewById(R.id.lcv_bloodpressure);
        initLineChart_steps();//初始化
        initLineChart_heartbeats();
        initLineChart_bloodpressure();
    }


    private void initLineChart_steps() {
        /**
         * 设置X 轴的显示
         */
        for (int i = 0; i < date_x.length; i++) {
            mAxisXValues_steps.add(new AxisValue(i).setLabel(date_x[i]));
        }

        /**
         * 图表的每个点的显示
         */
        for (int i = 0; i < 7; i++) {
            mPointValues_steps.add(new PointValue(i, steps[i]));
        }
        Line line = new Line(mPointValues_steps).setColor(Color.parseColor("#ff8f00"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setName("日期");//X轴标注
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.parseColor("#ff8f00"));  //设置字体颜色
        //axisX.setName("date_x");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues_steps.length
        axisX.setValues(mAxisXValues_steps);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("步数");//y轴标注
        axisY.setTextColor(Color.parseColor("#ff8f00"));
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart_steps.setInteractive(true);
        lineChart_steps.setZoomType(ZoomType.HORIZONTAL);
        lineChart_steps.setMaxZoom((float) 2);//最大方法比例
        lineChart_steps.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart_steps.setLineChartData(data);
        lineChart_steps.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart_steps.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart_steps.setCurrentViewport(v);
    }

    private void initLineChart_heartbeats() {
        /**
         * 设置X 轴的显示
         */
        for (int i = 0; i < date_x.length; i++) {
            mAxisXValues_heartbeats.add(new AxisValue(i).setLabel(date_x[i]));
        }

        /**
         * 图表的每个点的显示
         */
        for (int i = 0; i < 7; i++) {
            mPointValues_heartbeats.add(new PointValue(i, heartbeats[i]));
        }
        Line line = new Line(mPointValues_heartbeats).setColor(Color.parseColor("#e51c23"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setName("日期");//X轴标注
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.parseColor("#e51c23"));  //设置字体颜色
        //axisX.setName("date_x");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues_steps.length
        axisX.setValues(mAxisXValues_heartbeats);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("心率");//y轴标注
        axisY.setTextColor(Color.parseColor("#e51c23"));
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart_heartbeat.setInteractive(true);
        lineChart_heartbeat.setZoomType(ZoomType.HORIZONTAL);
        lineChart_heartbeat.setMaxZoom((float) 2);//最大方法比例
        lineChart_heartbeat.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart_heartbeat.setLineChartData(data);
        lineChart_heartbeat.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart_heartbeat.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart_heartbeat.setCurrentViewport(v);
    }

    private void initLineChart_bloodpressure() {
        /**
         * 设置X 轴的显示
         */
        for (int i = 0; i < date_x.length; i++) {
            mAxisXValues_bloodpressure.add(new AxisValue(i).setLabel(date_x[i]));
        }

        /**
         * 图表的每个点的显示
         */
        for (int i = 0; i < 7; i++) {
            mPointValues_bloodpressure_systolic.add(new PointValue(i, bloodpressure_systolic[i]));
            mPointValues_bloodpressure_diastolic.add(new PointValue(i, bloodpressure_diastolic[i]));
        }
        Line line_systolic = new Line(mPointValues_bloodpressure_systolic).setColor(Color.parseColor("#0277bd"));  //折线的颜色（橙色）
        Line line_diastolic = new Line(mPointValues_bloodpressure_diastolic).setColor(Color.parseColor("#00a9f4"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<>();
        line_systolic.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line_systolic.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line_systolic.setFilled(true);//是否填充曲线的面积
        line_systolic.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line_systolic.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line_systolic.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line_diastolic.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line_diastolic.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line_diastolic.setFilled(true);//是否填充曲线的面积
        line_diastolic.setHasLabels(true);//曲线的数据坐标是否加上备注
        line_diastolic.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line_diastolic.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line_diastolic);
        lines.add(line_systolic);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setName("日期");//X轴标注
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.parseColor("#00a9f4"));  //设置字体颜色
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues_steps.length
        axisX.setValues(mAxisXValues_bloodpressure);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("血压");//y轴标注
        axisY.setTextColor(Color.parseColor("#00a9f4"));
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart_bloodpressure.setInteractive(true);
        lineChart_bloodpressure.setZoomType(ZoomType.HORIZONTAL);
        lineChart_bloodpressure.setMaxZoom((float) 2);//最大方法比例
        lineChart_bloodpressure.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart_bloodpressure.setLineChartData(data);
        lineChart_bloodpressure.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart_bloodpressure.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart_bloodpressure.setCurrentViewport(v);
    }

}

package com.closet.beatriz.closet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by Beatriz on 21/05/2015.
 */

public class AcPieChart extends Activity {


    //add lots of colors for it to choose
    //  private static int[] COLORS = new int[]{Color.RED, Color.BLUE , Color.YELLOW, Color.WHITE, Color.BLACK, Color.YELLOW, Color.YELLOW};

    // private static double[] VALUES = new double[]{10, 11, 12, 13};

    // private static String[] NAME_LIST = new String[]{"A", "B", "C", "D"};

    int[] VALUES;
    private CategorySeries mSeries = new CategorySeries("");

    private DefaultRenderer mRenderer = new DefaultRenderer();

    private GraphicalView mChartView;

    String[] NAME_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);


        //color codes
        String[] COLORS = getResources().getStringArray(R.array.colors);

        Bundle extras = getIntent().getExtras();

        //get the information
        String title = extras.getString("title");
        NAME_LIST = (String[]) extras.get("name");
        VALUES = (int[]) extras.get("value");

        //chart information
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(getResources().getColor(R.color.background));
        //mRenderer.setShowLegend(false);
        mRenderer.setShowLegend(true);
        mRenderer.setChartTitle(title);
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setShowLabels(false);
        //mRenderer.setLabelsTextSize(15);
        //mRenderer.setLegendTextSize(15);
        mRenderer.setMargins(new int[]{20, 30, 15, 0});
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setStartAngle(90);

        for (int i = 0; i < NAME_LIST.length; i++) {
            if (VALUES[i] > 0) {
                mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
                Log.e("TAG", "color count " + i);
                SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
                //renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
                renderer.setColor(Color.parseColor(COLORS[i]));
                mRenderer.addSeriesRenderer(renderer);
            }
        }

        if (mChartView != null) {
            mChartView.repaint();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
            mRenderer.setClickEnabled(true);
            mRenderer.setSelectableBuffer(10);

            mChartView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();

                    if (seriesSelection == null) {
                        Toast.makeText(AcPieChart.this, "No chart element was clicked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AcPieChart.this, "Chart element data point index " + (seriesSelection.getPointIndex() + 1) + " was clicked" + " point value=" + seriesSelection.getValue(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            mChartView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    if (seriesSelection == null) {
                        Toast.makeText(AcPieChart.this, "No chart element was long pressed", Toast.LENGTH_SHORT);
                        return false;
                    } else {
                        Toast.makeText(AcPieChart.this, "Chart element data point index " + seriesSelection.getPointIndex() + " was long pressed", Toast.LENGTH_SHORT);
                        return true;
                    }
                }
            });
            layout.addView(mChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        } else {
            mChartView.repaint();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.action_close):

                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}



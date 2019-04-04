package com.example.mellov2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class TrendsStatsFragment extends Fragment {

    //=============================================




    //=============================================

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trends_stats, container, false);

        GraphView graph = (GraphView) view.findViewById(R.id.graph);

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Time");
        gridLabel.setVerticalAxisTitle("% Bladder Fullness");

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(13);

        //
        DataPoint[] points = new DataPoint[13];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, 100*Math.random());
        }

        //


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        series.setColor(Color.LTGRAY);
        series.setThickness(8);

        graph.addSeries(series);

        return view;
    }
}

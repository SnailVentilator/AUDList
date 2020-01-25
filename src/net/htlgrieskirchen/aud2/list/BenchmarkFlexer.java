/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import net.htlgrieskirchen.aud2.list.benchmarks.Benchmarks;

/**
 *
 * @author fabian
 */
public class BenchmarkFlexer extends Application {

    public static void flex() {
        Benchmarks.initialize();
        launch(new String[0]);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();

        pane.add(generateChart(new Benchmarks.InsertionBenchmark(), 10000, 100), 0, 0);
        pane.add(generateChart(new Benchmarks.ContainsBenchmark(100*100), 100, 100), 1, 0);
        pane.add(generateChart(new Benchmarks.RemoveByIndexBenchmark(), 75, 200), 0, 1);
        pane.add(generateChart(new Benchmarks.RemoveByValueBenchmark(), 75, 200), 1, 1);
        
        pane.getColumnConstraints().add(new ColumnConstraints(){{setHgrow(Priority.ALWAYS);}});
        pane.getColumnConstraints().add(new ColumnConstraints(){{setHgrow(Priority.ALWAYS);}});
        
        pane.getRowConstraints().add(new RowConstraints(){{setVgrow(Priority.ALWAYS);}});
        pane.getRowConstraints().add(new RowConstraints(){{setVgrow(Priority.ALWAYS);}});
        
        stage.setScene(new Scene(pane));
        stage.setTitle("Benchmark Flexer");
        stage.setMaximized(true);
        stage.show();
    }

    private LineChart<Number, Number> generateChart(Benchmarkable benchmarkable, int stepSize, int count) {
        long[][] measures = measure(benchmarkable, stepSize, count);

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Iterations");
        yAxis.setLabel("Time [ms]");
        
        //yAxis.setTickUnit(count);

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(benchmarkable.getTitle());
        chart.setCreateSymbols(false);
        
        //TODO: Add MyLinkedList back in
        for (int i = 0; i < measures.length - 1; i++) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(ListType.values()[i].toString());
            
            for (int j = 0; j < measures[i].length; j++) {
                series.getData().add(new XYChart.Data<>(j * stepSize, measures[i][j]));
            }
            
            chart.getData().add(series);
        }

        return chart;
    }

    private long[][] measure(Benchmarkable benchmarkable, int stepSize, int count) {
        long[][] measures = new long[ListType.values().length][];
        //TODO: Add MyLinkedList back in
        for (int i = 0; i < ListType.values().length - 1; i++) {
            measures[i] = new long[count];
            System.out.print("Measuring " + ListType.values()[i] + "... \t");
            for (int j = 0; j < count; j++) {
                if(j % 10 == 0)
                    System.out.format("%02d ", j);
                measures[i][j] = benchmarkable.execute(ListType.values()[i], stepSize * j);
            }
            System.out.println("Done!");
        }
        return measures;
    }

    public static interface Benchmarkable {

        long execute(ListType type, long size);

        public String getTitle();
    }

    public static enum ListType {
        JavaArrayList, MyArrayList, JavaLinkedList, MyLinkedList
    }
}

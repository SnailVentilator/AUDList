/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author fabian
 */
public class BenchmarkFlexer extends Application {

    public static final boolean timeWaster = false;

    public static void flex() {
        Benchmarks.initialize();
        launch(new String[0]);
    }

    private ProgressBar[][] progressBars = new ProgressBar[timeWaster ? 4 : 3][];

    private Thread[] chartGenerationThreads = new Thread[4];
    private AtomicInteger chartGenerationThreadsIndex = new AtomicInteger(0);

    @Override
    public void start(Stage stage) throws Exception {
        {
            VBox vbox = new VBox(5);
            vbox.setPadding(new Insets(15));

            //Labels
            {
                GridPane labelContainer = new GridPane();
                labelContainer.getColumnConstraints().add(new ColumnConstraints() {
                    {
                        setHgrow(Priority.ALWAYS);
                    }
                });
                labelContainer.getColumnConstraints().add(new ColumnConstraints() {
                    {
                        setHgrow(Priority.ALWAYS);
                    }
                });
                labelContainer.getColumnConstraints().add(new ColumnConstraints() {
                    {
                        setHgrow(Priority.ALWAYS);
                    }
                });
                labelContainer.getColumnConstraints().add(new ColumnConstraints() {
                    {
                        setHgrow(Priority.ALWAYS);
                    }
                });
                labelContainer.add(new Label("Insertion"), 1, 0);
                labelContainer.add(new Label("Contains"), 2, 0);
                labelContainer.add(new Label("Remove By Index"), 3, 0);
                labelContainer.add(new Label("Remove By Value"), 4, 0);
                vbox.getChildren().add(labelContainer);
            }

            for (int i = 0; i < progressBars.length; i++) {
                progressBars[i] = new ProgressBar[4];
                HBox progressContainer = new HBox(5);
                for (int j = 0; j < progressBars[i].length; j++) {
                    ProgressBar progressBar = new ProgressBar(0);
                    progressBar.setPrefSize(250, 25);
                    progressBars[i][j] = progressBar;
                    progressContainer.getChildren().add(progressBar);
                }

                HBox line = new HBox(5);
                line.setAlignment(Pos.CENTER_RIGHT);
                line.getChildren().add(new Label(ListType.values()[i].toString()));
                line.getChildren().add(progressContainer);
                vbox.getChildren().add(line);
            }

            loadingStage = new Stage();
            loadingStage.setTitle("BenchmarkFlexer is loading...");
            loadingStage.setScene(new Scene(vbox));
            loadingStage.show();
        }

        {
            GridPane pane = new GridPane();

            new Thread(() -> {
                LineChart<Number, Number> chartInsert = generateChart(new Benchmarks.InsertionBenchmark(), 50000, 100, 0);
                LineChart<Number, Number> chartContains = generateChart(new Benchmarks.ContainsBenchmark(250 * 100), 250, 100, 1);
                LineChart<Number, Number> chartRemoveByIndex = generateChart(new Benchmarks.RemoveByIndexBenchmark(), 500, 100, 2);
                LineChart<Number, Number> chartRemoveByValue = generateChart(new Benchmarks.RemoveByValueBenchmark(), 500, 100, 3);

                for (Thread chartGenerationThread : chartGenerationThreads) {
                    try {
                        chartGenerationThread.join();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                Platform.runLater(() -> {
                    pane.add(chartInsert, 0, 0);
                    pane.add(chartContains, 1, 0);
                    pane.add(chartRemoveByIndex, 0, 1);
                    pane.add(chartRemoveByValue, 1, 1);

                    Collections.sort(chartInsert.getData(), (o1, o2) -> {
                        return o1.getName().compareTo(o2.getName());
                    });
                    Collections.sort(chartContains.getData(), (o1, o2) -> {
                        return o1.getName().compareTo(o2.getName());
                    });
                    Collections.sort(chartRemoveByIndex.getData(), (o1, o2) -> {
                        return o1.getName().compareTo(o2.getName());
                    });
                    Collections.sort(chartRemoveByValue.getData(), (o1, o2) -> {
                        return o1.getName().compareTo(o2.getName());
                    });
                    
                    NumberAxis yAxis = (NumberAxis) chartInsert.getYAxis();
                    yAxis.setAutoRanging(false);
                    yAxis.setUpperBound(250);

                    loadingStage.hide();
                    stage.show();
                });
            }).start();

            pane.getColumnConstraints().add(new ColumnConstraints() {
                {
                    setHgrow(Priority.ALWAYS);
                }
            });
            pane.getColumnConstraints().add(new ColumnConstraints() {
                {
                    setHgrow(Priority.ALWAYS);
                }
            });

            pane.getRowConstraints().add(new RowConstraints() {
                {
                    setVgrow(Priority.ALWAYS);
                }
            });
            pane.getRowConstraints().add(new RowConstraints() {
                {
                    setVgrow(Priority.ALWAYS);
                }
            });

            stage.setScene(new Scene(pane));
            stage.setTitle("Benchmark Flexer");
            stage.setMaximized(true);
        }
    }
    private Stage loadingStage;

    private LineChart<Number, Number> generateChart(Benchmarkable benchmarkable, int stepSize, int count, int benchmarkIndex) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Elements");
        yAxis.setLabel("Time [ms]");

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(benchmarkable.getTitle());
        chart.setCreateSymbols(false);

        chartGenerationThreads[chartGenerationThreadsIndex.getAndIncrement()] = new Thread(() -> {
            final int measuresCount = measuresCounter.getAndIncrement();
            long[][] localMeasures;
            try {
                localMeasures = measure(benchmarkable, stepSize, count, measuresCount, benchmarkIndex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            AtomicInteger integer = new AtomicInteger();
            for (int i = 0; i < localMeasures.length - (timeWaster ? 0 : 1); i++) {
                new Thread(() -> {
                    int x = integer.getAndIncrement();
                    XYChart.Series<Number, Number> series = new XYChart.Series<>();
                    series.setName(ListType.values()[x].toString());

                    for (int j = 0; j < localMeasures[x].length; j++) {
                        series.getData().add(new XYChart.Data<>(j * stepSize, localMeasures[x][j]));
                    }

                    Platform.runLater(() -> {
                        chart.getData().add(series);
                    });
                }).start();
            }
        }) {
            {
                start();
            }
        };

        return chart;
    }

    private static long[][][] measures = new long[4][][];
    private static AtomicInteger measuresCounter = new AtomicInteger();

    private long[][] measure(Benchmarkable benchmarkable, int stepSize, int count, int measuresCount, int benchmarkIndex) throws InterruptedException {
        final AtomicInteger iCounter = new AtomicInteger();
        measures[measuresCount] = new long[ListType.values().length][];
        Thread[] threads = new Thread[ListType.values().length - (timeWaster ? 0 : 1)];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int iValue = iCounter.getAndIncrement();
                measures[measuresCount][iValue] = new long[count];
                final AtomicInteger atomicJ = new AtomicInteger();
                for (int j = 0; j < count; j++) {
                    if (j % 50 == 0) {
                        atomicJ.set(j);
                        Platform.runLater(() -> {
                            progressBars[iValue][benchmarkIndex].setProgress((double) atomicJ.get() / count);
                        });
                    }
                    measures[measuresCount][iValue][j] = benchmarkable.execute(ListType.values()[iValue], stepSize * j);
                }
                Platform.runLater(() -> {
                    progressBars[iValue][benchmarkIndex].setProgress(1);
                });
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return measures[measuresCount];
    }

    public static interface Benchmarkable {

        long execute(ListType type, long size);

        public String getTitle();
    }

    public static enum ListType {
        JavaArrayList, MyArrayList, JavaLinkedList, MyLinkedList
    }
}

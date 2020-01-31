/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fabian
 */
@SuppressWarnings("PointlessArithmeticExpression")
public class BenchmarkFlexer extends Application {

	public static final boolean timeWaster = true;
	private static final long[][][] measures = new long[4][][];
	private static final AtomicInteger measuresCounter = new AtomicInteger();
	private final ProgressBar[][] progressBars = new ProgressBar[timeWaster ? 4 : 3][];
	private final Thread[] chartGenerationThreads = new Thread[4];
	private final AtomicInteger chartGenerationThreadsIndex = new AtomicInteger(0);
	private Stage loadingStage;

	public static void flex() {
		Benchmarks.initialize();
		launch();
	}

	@Override
	public void start(Stage stage) {
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

			for(int i = 0; i < progressBars.length; i++) {
				progressBars[i] = new ProgressBar[4];
				HBox progressContainer = new HBox(5);
				for(int j = 0; j < progressBars[i].length; j++) {
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
				LineChart<Number, Number> chartInsert = generateChart(new Benchmarks.InsertionBenchmark(), 500, 1000, 0);
				LineChart<Number, Number> chartContains = generateChart(new Benchmarks.ContainsBenchmark(250 * 100), 250, 100, 1);
				LineChart<Number, Number> chartRemoveByIndex = generateChart(new Benchmarks.RemoveByIndexBenchmark(), 25, 1000, 2);
				LineChart<Number, Number> chartRemoveByValue = generateChart(new Benchmarks.RemoveByValueBenchmark(), 25, 1000, 3);

				for(Thread chartGenerationThread : chartGenerationThreads) {
					try {
						chartGenerationThread.join();
					} catch(InterruptedException ex) {
						throw new RuntimeException(ex);
					}
				}

				Platform.runLater(() -> {
					pane.add(chartInsert, 0, 0);
					pane.add(chartContains, 1, 0);
					pane.add(chartRemoveByIndex, 0, 1);
					pane.add(chartRemoveByValue, 1, 1);

					chartInsert.getData().sort(Comparator.comparing(XYChart.Series::getName));
					chartContains.getData().sort(Comparator.comparing(XYChart.Series::getName));
					chartRemoveByIndex.getData().sort(Comparator.comparing(XYChart.Series::getName));
					chartRemoveByValue.getData().sort(Comparator.comparing(XYChart.Series::getName));

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

	private LineChart<Number, Number> generateChart(Benchmarkable benchmarkable, int stepSize, int count, int benchmarkIndex) {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Elements");
		yAxis.setLabel("Time [ns]");

		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle(benchmarkable.getTitle());
		chart.setCreateSymbols(false);

		chartGenerationThreads[chartGenerationThreadsIndex.getAndIncrement()] = new Thread(() -> {
			final int measuresCount = measuresCounter.getAndIncrement();
			long[][] localMeasures;
			try {
				localMeasures = measure(benchmarkable, stepSize, count, measuresCount, benchmarkIndex);
			} catch(InterruptedException ex) {
				throw new RuntimeException(ex);
			}

			AtomicInteger integer = new AtomicInteger();
			for(int i = 0; i < localMeasures.length - (timeWaster ? 0 : 1); i++) {
					int x = integer.getAndIncrement();
					XYChart.Series<Number, Number> series = new XYChart.Series<>();
					series.setName(ListType.values()[x].toString());

					for(long j = 0, previous = localMeasures[x][0]; j < localMeasures[x].length; j++) {
						long yValue = localMeasures[x][(int) j];
						if(yValue > 20000000 && yValue > previous * 2) continue;
						series.getData().add(new XYChart.Data<>(j * stepSize, yValue));
						previous = yValue;
					}

					Platform.runLater(() -> chart.getData().add(series));
				}
		}) {
			{
				start();
			}
		};

		return chart;
	}

	private long[][] measure(Benchmarkable benchmarkable, int stepSize, int count, int measuresCount, int benchmarkIndex) throws InterruptedException {
		final AtomicInteger iCounter = new AtomicInteger();
		measures[measuresCount] = new long[ListType.values().length][];
		Thread[] threads = new Thread[ListType.values().length - (timeWaster ? 0 : 1)];
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				int iValue = iCounter.getAndIncrement();
				measures[measuresCount][iValue] = new long[count];

				final AtomicInteger atomicJ = new AtomicInteger();
				for(int j = 0; j < count; j++) {
					if(j % 50 == 0) {
						atomicJ.set(j);
						Platform.runLater(() -> progressBars[iValue][benchmarkIndex].setProgress((double) atomicJ.get() / count));
					}
					measures[measuresCount][iValue][j] = benchmarkable.execute(ListType.values()[iValue], stepSize * j);
				}
				Platform.runLater(() -> progressBars[iValue][benchmarkIndex].setProgress(1));
			});
			threads[i].start();
		}
		for(Thread thread : threads) {
			thread.join();
		}
		return measures[measuresCount];
	}

	public enum ListType {
		JavaArrayList, MyArrayList, JavaLinkedList, MyLinkedList
	}

	public interface Benchmarkable {

		long execute(ListType type, long size);

		String getTitle();
	}
}

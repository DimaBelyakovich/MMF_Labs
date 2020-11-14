import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChartTime extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    static {
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }

    public BarChartTime(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setBackground(null);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDismissDelay(Integer.MAX_VALUE);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Processor processor = new Processor();
        Calculations calc = new Calculations();

        int tpns = calc.getMinTimeAsyncProc(processor);
        int t2pns = calc.getMinTimeSyncProc(processor);

        dataset.addValue(tpns, "Async", "Async");
        dataset.addValue(t2pns, "Sync", "Sync");
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Performance: Async vs Sync", null,
                "Time", dataset);
        chart.setBackgroundPaint(null);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }

    public static void main(String[] args) {
        Processor processor = new Processor();
        Calculations calc = new Calculations();

        int tpns = calc.getMinTimeAsyncProc(processor);
        int t2pns = calc.getMinTimeSyncProc(processor);

        System.out.println("Async:" + tpns);
        System.out.println("Sync:" + t2pns);

        BarChartTime barChartTime = new BarChartTime("async versus sync");
        barChartTime.pack();
        RefineryUtilities.centerFrameOnScreen(barChartTime);
        barChartTime.setVisible(true);
    }

}
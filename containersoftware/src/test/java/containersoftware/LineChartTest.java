package containersoftware;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class LineChartTest {
	
	LineChart chart;
	ContainerLog log = new ContainerLog();

	@Test
	public void testLineChart() throws FileNotFoundException {
		log.updateDatabase();
		chart = new LineChart("TestApplicationTitle","TestChartTitle",log.getContainers().get(0));
	}

}

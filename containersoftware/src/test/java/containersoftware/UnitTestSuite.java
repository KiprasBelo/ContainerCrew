package containersoftware;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MainMenuTest.class,
		ClientLoginTest.class,
		ClientRegisterTest.class,
		ClientMainMenuTest.class,
		AccountInfoDisplayTest.class,
		AccountInfoEditTest.class,
		AddJourneyTest.class,
		AdminContainerListTest.class,
		AdminLoginTest.class,
		AdminMainMenuTest.class,
		ClientFinderTest.class,
		ContainerEditorTest.class,
		ContainerTrackerTest.class,
		ExtraContainerInfoTest.class,
		FirstPageTest.class,
		LineChartTest.class
		})

public class UnitTestSuite {

}

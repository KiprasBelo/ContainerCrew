package containersoftware;

import java.util.ArrayList;

public class ContainerLog {
	
	ArrayList<Container> containers = new ArrayList<Container>();
	
	public ContainerLog() {}
	
	public void addContainer(Container contain) {
		containers.add(contain);
	}
	
	public ArrayList<Container> getContainers() {
		return containers;
	}

}

package automationMind.karapakkam;

public class PropertyRead {

	public static void main(String[] args) {
		Base bas = new Base();
		bas.writePropertyFile("vinoth", "vinoth123");
		bas.readPropertyFile();
		bas.modifyPropertyFile("vinoth@123");
	}
}


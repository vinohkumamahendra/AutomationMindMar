package automationMind.karapakkam;

public class ExcelREad {
	
	public static void main(String[] args) {
		Base bas = new Base();
		bas.readIrctc();
		bas.WriteExcel();
		bas.readNWriteExcel();
	}

}

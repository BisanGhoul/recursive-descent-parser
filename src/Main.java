import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
//--module-path "C:\Users\hp\Downloads\openjfx-16_windows-x64_bin-sdk\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml
	public static void main(String[] args) {
//		try {
//			File file = new File("C:\\Users\\Asus\\Desktop\\test.txt");
//			PascalScanner lexical_analyzer = new PascalScanner(file);
//			List<Token> tokens = new ArrayList<Token>();
//			tokens=lexical_analyzer.getTokens();
//			for (int i=0; i<tokens.size(); i++) {
//			System.out.println(tokens.get(i).toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		File file = new File("test.txt");

		RecursiveDescentPascalParser classs= new RecursiveDescentPascalParser();
		classs.initialFunc(file);
	}

}

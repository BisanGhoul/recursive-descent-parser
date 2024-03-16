import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

public class Controller {
	@FXML
	TableView tbv;
	@FXML
	TableColumn<String, Token> cl1;
	@FXML
	TableColumn<Integer, Token> cl2;
	@FXML
	TableColumn<Integer, Token> cl3;
	@FXML
//	TextArea errorsTxt;
	Scanner scan;
	File in;

	public void func(File file) {

		try {
//          file = new File("C:\\Users\\Asus\\Desktop\\input.txt");
			PascalScanner lexical_analyzer = new PascalScanner(file);
			tbv.getItems().clear();
//			errorsTxt.clear();
			List<Token> tokens = new ArrayList<Token>();
			tokens.clear();
			tokens=lexical_analyzer.returnTokens();
//			for (int i = 0; i < tokens.size(); i++) {
//
//				System.out.println(tokens.get(i).toString());
//			}

			cl1.setCellValueFactory(new PropertyValueFactory<>("id"));

			cl2.setCellValueFactory(new PropertyValueFactory<>("token"));
			
			cl3.setCellValueFactory(new PropertyValueFactory<>("type"));


			for (Token emp : tokens) {
				tbv.getItems().add(emp);
			
//				System.out.println(emp.toString());
//				if(emp.getId()==1010) {//error
//					errorsTxt.appendText(emp.getToken()+"\n");
//				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		RecursiveDescentPascalParser classs= new RecursiveDescentPascalParser();
		classs.initialFunc(file);
	}

	@FXML
	public void browse() throws FileNotFoundException {
		FileChooser chooser = new FileChooser();
		// chooser.getExtensionFilters().addAll(new ExtensionFilter("txt files",
		// "*.txt"));
		try {
			in = chooser.showOpenDialog(null);
//		System.out.println(in.length());
//		srcFilePath.setText(in.getAbsolutePath());
			func(in);

//			scan = new Scanner(in);
		} catch (java.lang.NullPointerException e2) {
			System.out.println("No file Selected");

		}
	}
}

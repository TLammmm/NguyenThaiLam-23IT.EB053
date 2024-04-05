package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
public class textController implements Initializable{
	@FXML
	    private Menu edit;

    @FXML
    private Label path;
	    @FXML
	    private MenuItem exit;

	    @FXML
	    private Menu file;

	    @FXML
	    private MenuItem open;

	    @FXML
	    private MenuItem save;

	    @FXML
	    private MenuItem saveas;
	    @FXML
	    private MenuItem neww;
	    @FXML
	    private TextArea text;
	    private File currentFile = null;
	    public void open() {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Open File"); 
	        File selectedFile = fileChooser.showOpenDialog(null); 

	        if (selectedFile != null) {
	            try {
	            	 currentFile = selectedFile;
	                StringBuilder content = new StringBuilder();
	                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    content.append(line).append("\n");
	                   
	                }
	                path();
	                reader.close();
	                text.setText(content.toString());
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    
	    public void saveAs() {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Save As");
	        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));

	        File file = fileChooser.showSaveDialog(null);
	        if (file != null) {
	        	currentFile=file;
	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	                writer.write(text.getText());
	                path();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public void save() {
	    	 if (currentFile != null) {
	    		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
	    		        writer.write(text.getText());
	    
	    		    } catch (IOException e) {
	    		        e.printStackTrace();
	    		    }
	    	 } else {
	    	        saveAs();
	    	    }
	    }
	    public void neww() {
	    	
	    	    text.clear(); 
	    	    currentFile=null;
	    	    path.setText("");
	    	}
	    public void exit() {
	        Platform.exit();
	    }
	    	
	    public void path() {
	    	  path.setText(currentFile.getPath());
	    }
	    public void displayFile(File file) {
	    	try {
           	 currentFile = file;
               StringBuilder content = new StringBuilder();
               BufferedReader reader = new BufferedReader(new FileReader(file));
               String line;
               while ((line = reader.readLine()) != null) {
                   content.append(line).append("\n");
                  
               }
               path();
               reader.close();
               text.setText(content.toString());
               
           } catch (IOException e) {
               e.printStackTrace();
           }
	    }
	    

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
		}
	    
}



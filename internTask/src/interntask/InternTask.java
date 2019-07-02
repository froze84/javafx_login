package interntask;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;

/**
 *
 * @author leepeng
 */
public class InternTask extends Application {
    Stage page;
    Properties prop =new Properties();
    HashMap<String,String> map = new HashMap();
    
     public static void main(String[] args) {   
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
       
        page = primaryStage;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setVgap(20);
        grid.setHgap(10);
        
        Label lblName=new Label("name");
        grid.add(lblName,0,0);
        
        Label lblAge=new Label("age");
        grid.add(lblAge,0,1);
        
        
        InputStream fileRead = new FileInputStream("file.properties");
        prop.load(fileRead);
        fileRead.close();
        //System.out.println(prop.getProperty(lblName.getText()));
        
        //HashMap<String,String> map = new HashMap();
        map.put(lblName.getText(),prop.getProperty(lblName.getText()));
        map.put(lblAge.getText(),prop.getProperty(lblAge.getText()));
        map.put("commName",prop.getProperty("commName"));
        map.put("commAge",prop.getProperty("commAge"));
        
        TextField txtName = new TextField();
        txtName.setText(map.get(lblName.getText()));
        grid.add(txtName,1,0);
        
        txtName.setOnMouseClicked(event -> {
        if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
        popup("commName",map.get("commName"));
        }
        });
         
        TextField txtAge = new TextField();
        txtAge.setText(map.get(lblAge.getText()));
        grid.add(txtAge,1,1);
        
        txtAge.setOnMouseClicked(event -> {
        if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
        popup("commAge",map.get("commAge"));
        }
        });
        
        Button btnEnter = new Button("ENTER");
        grid.add(btnEnter,0,2);
        btnEnter.setOnAction(event->{     
            map.put(lblName.getText(),txtName.getText());
            map.put(lblAge.getText(),txtAge.getText());
            //System.out.println("hhhh");
        });
        
        page.setOnCloseRequest(event->{
        try {
                if(null!= map.get(lblName.getText()) || null!=map.get(lblAge.getText())){
                OutputStream fileWrite = new FileOutputStream("file.properties");
                prop.setProperty(lblName.getText(),map.get(lblName.getText()));
                prop.setProperty(lblAge.getText(),map.get(lblAge.getText()));
                prop.setProperty("commName",map.get("commName"));
                prop.setProperty("commAge",map.get("commAge"));               
                prop.store(fileWrite,null);
                //System.out.println("hhh");
                fileWrite.close();
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InternTask.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InternTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            page.close();
        });        
       
        
        
        Scene scene = new Scene(grid,300,250);
        page.setScene(scene);
        page.show();
      
    }
    public void popup(String commKey,String commValue){
        //System.out.println("inside popup commvalue is "+commValue);
        Stage pop = new Stage();
        pop.initModality(Modality.APPLICATION_MODAL);
        
        TextArea commText = new TextArea();
        commText.setWrapText(true);
        commText.setText(commValue);
        
        GridPane commGrid = new GridPane();
        commGrid.setAlignment(Pos.CENTER);
        commGrid.setPadding(new Insets(10));
        commGrid.add(commText,0,0);
        
        pop.setOnCloseRequest(event->{       
            map.put(commKey,commText.getText());
            pop.close();
        });
           
        Scene scene = new Scene(commGrid,300,300);
        pop.setScene(scene);
        pop.show();
    }
   
}

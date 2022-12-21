package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class HelpController extends Controller{

    @FXML
    private BorderPane help1;

    @FXML
    private BorderPane help2;

    @FXML
    private BorderPane help3;

    @FXML
    private BorderPane help4;

    @FXML
    private BorderPane help5;

    @FXML
    private BorderPane help6;

    @FXML
    private BorderPane help7;

    @FXML
    private BorderPane help8;

    @FXML
    private BorderPane help9;

    @FXML
    private BorderPane help10;

    @FXML
    private BorderPane help11;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private ImageView image6;

    @FXML
    private ImageView image7;

    @FXML
    private ImageView image8;
    @FXML
    private ImageView image9;

    @FXML
    private ImageView image10;

    @FXML
    private ImageView image11;




    private ArrayList<BorderPane> images = new ArrayList<>();

    private ArrayList<String> name = new ArrayList<>();

    private ArrayList<ImageView> imageViews = new ArrayList();

    private BorderPane showing;

    private int count;



    public void initialize() throws FileNotFoundException {
        images.add(help1);
        images.add(help2);
        images.add(help3);
        images.add(help4);
        images.add(help5);



        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);
        imageViews.add(image5);





        image1.setImage(new Image("AddAirport.gif"));
        /*
        image2.setImage(new Image("AddPhysical.gif"));
        image3.setImage(new Image("AddLogical.gif"));
        image4.setImage(new Image("AddObs.gif"));
        image5.setImage(new Image("TopdownSideOn.gif"));
        image6.setImage(new Image("SwitchRunwaysAirs.gif"));
        image7.setImage(new Image("tooltips.gif"));
        image8.setImage(new Image("ImportXML.gif"));
        image9.setImage(new Image("ExportXML.gif"));
        image10.setImage(new Image("RedclaredValues.gif"));
        image11.setImage(new Image("Rotate.gif"));

 */

        name.add("AddAirport.gif");
        name.add("AddPhysical.gif");
        name.add("AddLogical.gif");
        name.add("AddObs.gif");
        name.add("TopdownSideOn.gif");



        count=1;

        showing = help1;

    }

    @FXML
    private void changeHelp(ActionEvent event) throws IOException {
        event.consume();
        Scene scene = ((Node) event.getSource()).getScene();
        Iterator<BorderPane> it = images.iterator();
        try {
            while (it.hasNext()) {
                if ((it.next()).equals(showing)) {
                    if (it.hasNext()) {
                        showing = it.next();
                        ImageView img = imageViews.get(count);
                        img = new ImageView();
                        img.setImage(new Image(name.get(count)));
                        images.get(count).setCenter(img);
                        count++;
                    } else {
                        count = 0;
                        ImageView img = imageViews.get(count);
                        img = new ImageView();
                        img.setImage(new Image(name.get(count)));
                        showing = help1;
                        count++;


                    }
                }
            }

            it = images.iterator();
            while (it.hasNext()) {
                BorderPane next = it.next();
                if (next.equals(showing)) {
                    (next).setVisible(true);
                } else {
                    next.setVisible(false);
                }
            }
        }catch(OutOfMemoryError e){
            alertInfo("No more tips, you may close this window");

        }



    }


}

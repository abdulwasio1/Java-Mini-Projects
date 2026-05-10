package com.example.replace;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.CheckBox;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class drawShapeControl implements Initializable {

    @FXML
    Canvas canvas;

    @FXML
    ColorPicker colorPicker;

    @FXML
    Spinner<Integer> spinner;

    @FXML
    CheckBox checkBox;

    String shapeText;
    double currentX, currentY;

    private  GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(colorPicker.getValue());
        gc.setLineWidth(spinner.getValue());
    }

//    public void click(ActionEvent event){
//        shapeText = button.getText();
//    }
    @FXML
    public void pickColor(ActionEvent event){
        gc.setStroke(colorPicker.getValue());
    }
    @FXML
    public void setWidth(MouseEvent event){
        int value = spinner.getValue();
        gc.setLineWidth(value);
    }
    @FXML
    public void selectRectangle(ActionEvent event) {
        shapeText = "rectangle";  // just set the shape!
    }

    @FXML
    public void selectCircle(ActionEvent event) {
        shapeText = "circle";     // just set the shape!
    }
    @FXML
    public void selectRoundRectangle(ActionEvent event) {
        shapeText = "roundRectangle";  // just set the shape!
    }

    @FXML
    public void selectLine(ActionEvent event) {
        shapeText = "line";     // just set the shape!
    }
    @FXML
    public void selectFreeDraw(ActionEvent event) {
        shapeText = "freeDraw";     // just set the shape!
    }
    @FXML
    public void onMousePress(MouseEvent event){
            currentX = event.getX();
            currentY = event.getY();
    }
    @FXML
    public void onMouseDrag(MouseEvent event){
        if ("freeDraw".equals(shapeText)) { // use String first to prevent Null pointer Exception Error
            gc.strokeLine(currentX, currentY, event.getX(), event.getY());
            currentX = event.getX();
            currentY = event.getY();
        }
    }
    public boolean isFill(){
        if (checkBox.isSelected()){
            return true;
        }
        return false;
    }
    @FXML
    public void onMouseRelease(MouseEvent e){

        try {
            double endX = e.getX();
            double endY = e.getY();
            double width = endX - currentX;
            double height = endY - currentY;
            if (shapeText.equals("rectangle")){
                if (isFill()){
                    gc.setFill(colorPicker.getValue());
                    gc.fillRect(currentX, currentY, width, height);
                    gc.strokeRect(currentX, currentY, width, height);
                }else {
                    gc.strokeRect(currentX, currentY, width, height);
                }
            }else if (shapeText.equals("circle")){
                if (isFill()){
                    gc.setFill(colorPicker.getValue());
                    gc.fillOval(currentX, currentY , width , height);
                }else {
                    gc.strokeOval(currentX, currentY , width , height);
                }

            }else if (shapeText.equals("roundRectangle")){
                if (isFill()){
                    gc.setFill(colorPicker.getValue());
                    gc.fillRoundRect(currentX, currentY , width , height , 20 , 20);
                }else {
                    gc.strokeRoundRect(currentX, currentY , width , height , 20 , 20);
                }
            }else if (shapeText.equals("line")){
                gc.strokeLine(currentX, currentY , e.getX(), e.getY());
            }
        }catch (NullPointerException exception){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please Select any shape to draw");
            alert.showAndWait();
        }

    }


    public void clear(ActionEvent event) {
        gc.clearRect(0,0, canvas.getWidth() , canvas.getHeight());
    }
}

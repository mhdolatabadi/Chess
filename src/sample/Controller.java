package sample;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {
    int benchX = 13;
    int benchY = 2;
    Node selectedPiece;

    public void move(MouseEvent mouseEvent) {
        if (!(mouseEvent.getPickResult().getIntersectedNode().getId().equals("empty")) && ((Node) mouseEvent.getPickResult().getIntersectedNode().getUserData()) != null && selectedPiece != null) {
            GridPane.setColumnIndex(((Node) mouseEvent.getPickResult().getIntersectedNode().getUserData()), benchX);
            GridPane.setRowIndex(((Node) mouseEvent.getPickResult().getIntersectedNode().getUserData()), benchY);
            GridPane.setColumnIndex(selectedPiece, GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode()));
            GridPane.setRowIndex(selectedPiece, GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode()));
            mouseEvent.getPickResult().getIntersectedNode().setId("empty");
            if (benchY == 9) {
                benchX++;
                benchY = 2;
            }
            benchY++;
        }
        if (mouseEvent.getPickResult().getIntersectedNode().getId().equals("empty") && selectedPiece != null) {
            mouseEvent.getPickResult().getIntersectedNode().setId("full");
            mouseEvent.getPickResult().getIntersectedNode().setUserData(selectedPiece);
            GridPane.setColumnIndex(selectedPiece, GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode()));
            GridPane.setRowIndex(selectedPiece, GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode()));

            selectedPiece = null;
        }


    }

    public void select(MouseEvent mouseEvent) {
        if (selectedPiece == null)
            selectedPiece = mouseEvent.getPickResult().getIntersectedNode();
    }
}

package sample;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.net.URISyntaxException;

public class Controller {
    int benchX = 13;
    int benchY = 2;
    boolean currentMove = true;
    Node selectedPiece;
    Node toDeletePiece;
    Media move = new Media(getClass().getResource("/move.mp3").toURI().toString());
    MediaPlayer movePlayer = new MediaPlayer(move);

    public Controller() throws URISyntaxException {
    }


    public void initializeLabel(Label label){
        label.setLayoutX(400);
        label.setLayoutY(500);
        label.setTextFill(color);
        label.setStyle("-fx-font-size: 200px;" +
                "-fx-font-family: Broadway");
        label.setVisible(false);

    }


    Label endGame = new Label();
    Color color = new Color(1, 1, 1, 1);

    public void move(MouseEvent mouseEvent) {
        if (selectedPiece != null) {
            if (legalMove(selectedPiece, GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode()), GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode()))) {
                GridPane.setColumnIndex(selectedPiece, GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode()));
                GridPane.setRowIndex(selectedPiece, GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode()));
            }
            selectedPiece.setScaleX(selectedPiece.getScaleX() - 0.25);
            selectedPiece.setScaleY(selectedPiece.getScaleY() - 0.25);
            movePlayer.play();
            movePlayer.stop();
            movePlayer.play();
            movePlayer.stop();
            selectedPiece = null;
        }
    }

    public void select(MouseEvent mouseEvent) {
        initializeLabel(endGame);
        if (selectedPiece == null) {
            selectedPiece = mouseEvent.getPickResult().getIntersectedNode();
            selectedPiece.setScaleX(selectedPiece.getScaleX() + 0.25);
            selectedPiece.setScaleY(selectedPiece.getScaleY() + 0.25);
        } else {
            toDeletePiece = mouseEvent.getPickResult().getIntersectedNode();
            if (toDeletePiece != selectedPiece && legalMove(selectedPiece, GridPane.getColumnIndex(toDeletePiece), GridPane.getRowIndex(toDeletePiece))) {
                GridPane.setColumnIndex(selectedPiece, GridPane.getColumnIndex(toDeletePiece));
                GridPane.setRowIndex(selectedPiece, GridPane.getRowIndex(toDeletePiece));
                GridPane.setColumnIndex(toDeletePiece, benchX);
                GridPane.setRowIndex(toDeletePiece, benchY);
                if (toDeletePiece.getId().endsWith("King")) {
                    if (toDeletePiece.getId().startsWith("black"))
                        endGame.setText("white wins");
                    else {
                        endGame.setText("black wins");
                    }
                    endGame.setVisible(true);
                }
                benchY++;
                if (benchY == 10) {
                    benchY = 2;
                    benchX++;
                }
            }
            selectedPiece.setScaleX(selectedPiece.getScaleX() - 0.25);
            selectedPiece.setScaleY(selectedPiece.getScaleY() - 0.25);
            movePlayer.play();
            movePlayer.stop();
            movePlayer.play();
            movePlayer.stop();
            selectedPiece = null;
            toDeletePiece = null;
        }
    }

    public boolean legalMove(Node node, int goX, int goY) {
        if (currentMove) {
            //white
            switch (node.getId()) {
                case "whiteBishop":
                    if (Math.abs(GridPane.getColumnIndex(node) - goX) == Math.abs(GridPane.getRowIndex(node) - goY)) {
                        currentMove = false;
                        return true;
                    }
                    return false;
                case "whiteRook":
                    if (Math.abs(GridPane.getColumnIndex(node) - goX) == 0 || Math.abs(GridPane.getRowIndex(node) - goY) == 0) {
                        currentMove = false;
                        return true;
                    }
                    return false;
                case "whiteKnight":
                    if ((Math.abs(GridPane.getColumnIndex(node) - goX) == 2 && Math.abs(GridPane.getRowIndex(node) - goY) == 1) || (Math.abs(GridPane.getColumnIndex(node) - goX) == 1 && Math.abs(GridPane.getRowIndex(node) - goY) == 2)) {
                        currentMove = false;
                        return true;
                    }
                    return false;
                case "whiteKing":
                    if ((Math.abs(GridPane.getColumnIndex(node) - goX) <= 1) && (Math.abs(GridPane.getRowIndex(node) - goY) <= 1)) {
                        currentMove = false;
                        return true;
                    }
                    return false;
                case "whitePown":
                    if (GridPane.getRowIndex(node) - goY == 1) {
                        currentMove = false;
                        return true;
                    }
                    return false;
                case "whiteQueen":
                    if ((Math.abs(GridPane.getColumnIndex(node) - goX) == Math.abs(GridPane.getRowIndex(node) - goY)) || (Math.abs(GridPane.getColumnIndex(node) - goX) == 0 || Math.abs(GridPane.getRowIndex(node) - goY) == 0)) {
                        currentMove = false;
                        return true;
                    }
                    return false;
            }
        } else {
            //black
            switch (node.getId()) {
                case "blackBishop":
                    if (Math.abs(GridPane.getColumnIndex(node) - goX) == Math.abs(GridPane.getRowIndex(node) - goY)) {
                        currentMove = true;
                        return true;
                    }
                    return false;
                case "blackRook":
                    if (Math.abs(GridPane.getColumnIndex(node) - goX) == 0 || Math.abs(GridPane.getRowIndex(node) - goY) == 0) {
                        currentMove = true;
                        return true;
                    }
                    return false;
                case "blackKnight":
                    if ((Math.abs(GridPane.getColumnIndex(node) - goX) == 2 && Math.abs(GridPane.getRowIndex(node) - goY) == 1) || (Math.abs(GridPane.getColumnIndex(node) - goX) == 1 && Math.abs(GridPane.getRowIndex(node) - goY) == 2)) {
                        currentMove = true;
                        return true;
                    }
                    return false;
                case "blackKing":
                    if ((Math.abs(GridPane.getColumnIndex(node) - goX) <= 1) && (Math.abs(GridPane.getRowIndex(node) - goY) <= 1)) {
                        currentMove = true;
                        return true;
                    }
                    return false;
                case "blackPown":
                    if (goY - GridPane.getRowIndex(node) == 1 && Math.abs(goX - GridPane.getColumnIndex(node)) == 0) {
                        currentMove = true;
                        return true;
                    }
                    return false;
                case "blackQueen":
                    if ((Math.abs(GridPane.getColumnIndex(node) - goX) == Math.abs(GridPane.getRowIndex(node) - goY)) || (Math.abs(GridPane.getColumnIndex(node) - goX) == 0 || Math.abs(GridPane.getRowIndex(node) - goY) == 0)) {
                        currentMove = true;
                        return true;
                    }
                    return false;
            }
        }
        return false;
    }




}

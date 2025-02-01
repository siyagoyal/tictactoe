import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.stage.Stage; 
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class GameUI extends Application {
    private static String playerOne;
    private static String playerTwo;
    private static char playerChar = 'X';
    
    @Override
    public void start(Stage mainStage) {

        //put in player names
        TextField textfield1 = new TextField();
        textfield1.setFont(Font.font("Arial", 24));
        textfield1.setPromptText("Enter Player 1's Name");

        TextField textfield2 = new TextField();
        textfield2.setFont(Font.font("Arial", 24));
        textfield2.setPromptText("Enter Player 2's Name");
        Button start = new Button("Start");
        start.setStyle("-fx-font-size: 20px; -fx-padding: 10px 25px;");


        VBox enterNames = new VBox(textfield1, textfield2, start);
        enterNames.setAlignment(Pos.CENTER);
        enterNames.setSpacing(10);
        enterNames.setPadding(new Insets(20));

        start.setOnAction(e -> {
            playerOne = textfield1.getText();
            playerTwo = textfield2.getText();
            game(mainStage);
        });

        Scene scene = new Scene(enterNames, 500, 300);
        mainStage.setTitle("Tic-Tac-Toe Game");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void game(Stage mainStage) {
        
        GridPane grid = new GridPane();
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                Button button = new Button();
                Label buttonLabel = new Label("");
                buttonLabel.setFont(Font.font("Arial", 36));
                button.setGraphic(buttonLabel);

                button.setPrefSize(200, 200);
                grid.add(button, i, j);
                button.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
                int row = GridPane.getRowIndex(button);
                int col = GridPane.getColumnIndex(button);
                button.setOnAction(e -> {
                    if (Tictactoe.playCounter % 2 != 0) {
                        playerChar = 'O';
                    }
                    else {
                        playerChar = 'X';
                    }
                    if (Tictactoe.isEmpty(row, col, Tictactoe.getBoard(), playerChar)==false) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Invalid Move");
                        alert.setContentText("This spot is NOT empty. Please choose an empty space.");
                        alert.showAndWait();
                    }
                    else {
                        buttonLabel.setText(String.valueOf(playerChar));
                        if (Tictactoe.checkWin(row, col, Tictactoe.getBoard(), playerChar) == true) {
                            String winner = "";
                            if (playerChar == 'X') {
                                winner = playerOne;
                            }
                            else {
                                winner = playerTwo;
                            }
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle(winner + " Wins!");
                            alert.setContentText("Congratulations, " + winner + "! You've won!");
                            alert.showAndWait();
                            mainStage.close();
                            return;
                        }
                        else {
                            if (Tictactoe.playCounter == 9) {
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Draw!");
                                alert.setContentText("The game has ended in a tie!");
                                alert.showAndWait();
                                mainStage.close();
                                return;
                            }
                        }
                    }
                });
            }
        }

        Scene scene = new Scene(grid, 600, 600);  
        mainStage.setTitle("Tic-Tac-Toe Game");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

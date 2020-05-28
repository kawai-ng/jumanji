import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Jumanji extends Application {	
	static BorderPane pane = new BorderPane(); // main pane
	static GridPane startScreen = new GridPane(); // start screen to enter number of players
	static GridPane infoPane = new GridPane(); // pane for information
	static VBox eventPane = new VBox(); // pane for events
	
	Label[] labelPlayerNames; // array for player-name labels
	TextField[] tfPlayerNames; // array for player-name textfields
	static int numPlayers; // static variable for number of players
	static String[] names; // array for player names
	static int[] square; // array to record current square
	static int turn = 0; // toggle turns
	static Button btDice = new Button("Roll Dice");
	static final int endingSquare = 50; // ending square to win game
	
	@Override
	public void start(Stage primaryStage) {
		StartingScreenThread intro = new StartingScreenThread();
		intro.start();
		
		Scene scene = new Scene(pane, 500, 300);
		primaryStage.setTitle("Jumanji");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/* this thread prompts the player to enter number of players
	 * then displays textfields for names*/
	public class StartingScreenThread extends Thread {
		public synchronized void run() {
			startScreen.setHgap(10);
			startScreen.setVgap(10);
			pane.setTop(startScreen);
			pane.setPadding(new Insets(5, 5, 5, 5));

			// prompt for number of players
			Label labelNumPlayers = new Label("Enter number of players: \n(Hit Enter when done)");
			TextField tfNumPlayers = new TextField();
			startScreen.add(labelNumPlayers, 0, 0);
			startScreen.add(tfNumPlayers, 1, 0);
			
			tfNumPlayers.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					numPlayers = Integer.parseInt(tfNumPlayers.getText());
					
					// initialization of arrays
					startScreen.getChildren().clear();
					names = new String[numPlayers];
					square = new int[numPlayers];
					labelPlayerNames = new Label[numPlayers];
					tfPlayerNames = new TextField[numPlayers];
					
					for (int i = 0; i < numPlayers; i++) {
						labelPlayerNames[i] = new Label("Enter name of Player " + (i+1) + ": ");
						tfPlayerNames[i] = new TextField();
						startScreen.add(labelPlayerNames[i], 0, i);
						startScreen.add(tfPlayerNames[i], 1, i);
						NamesThread naming = new NamesThread();
						naming.start();
					}
					
				}
			});
		}
	}
	
	/* this thread collects names into the array to be displayed */
	public class NamesThread extends Thread {
		public synchronized void run() {
			// Platform.runLater helps run it on the javafx application thread instead of clashing
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	Button btReady = new Button("Ready!");
			    	startScreen.add(btReady, 2, 0);
					
			    	btReady.setOnAction(e -> {
						for (int i = 0; i < numPlayers; i++) {
							names[i] = tfPlayerNames[i].getText();
						}
						startScreen.getChildren().clear();
						GameThread game = new GameThread();
						game.start();
					});	
			    }
			});
		}
	}
	
	/* this thread is where the main game is played*/
	public static class GameThread extends Thread {
		public synchronized void run() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					pane.setTop(infoPane);
					pane.setCenter(eventPane);
					eventPane.setPadding(new Insets(10, 0, 0, 0 ));
					printInfoPane();
					
					// rolling dice and triggering event on action
					btDice.setOnAction(e -> {
						if (turn == numPlayers) turn = 0;
						eventPane.getChildren().clear();
						
						eventPane.getChildren().add(new Text("Player " + (turn+1) + " event:"));
						int randomNumber = (int)(Math.random() * 10);
						square[turn] += randomNumber;
						Text steps = new Text("You moved " + randomNumber + " steps!");
						eventPane.getChildren().add(steps);
						
						eventCall(); // calls for random event
						printInfoPane();
						turn++;
						
						if (square[turn-1] >= endingSquare)
							endGame();
					});
				}
			});
		}
	}
	
	// this method prints the info Pane when needed
	public static void printInfoPane() {
		infoPane.getChildren().clear();
		infoPane.setHgap(60);
		infoPane.add(btDice, 0, 1);
		infoPane.add(new Text("Player " + (turn+1) + "'s turn!"), 0, 0);
		infoPane.getColumnConstraints().add(new ColumnConstraints(100));
		
		for (int i = 0; i < numPlayers; i++) {
			Text text = new Text("Player "+ (i+1) + ": " + names[i] + "\t\tSquare " + square[i]);
			infoPane.add(text, 1, i);
		}
	}
	
	// this method triggers a random event
	public static void eventCall() {
		int eventNumber = (int)(Math.random() * 20) + 1;
		switch (eventNumber) {
			case 1: Events.catpoop(); break;
			case 2: Events.magician(); break;
			case 3: Events.gobby1(); break;
			case 4: Events.gobby2(); break;
			case 5: Events.medal(); break;
			case 6: break;
			case 7: Events.rocketship(); break;
			case 8: Events.transporter(); break;
			case 9: Events.catapult(); break;
			case 10: break;
			case 11: Events.peter(); break;
			case 12: Events.trivia1(); break;
			case 13: Events.trivia2(); break;
			case 14: Events.batter(); break;
			case 15: Events.robbery(); break;
			case 16: Events.doors(); break;
			case 17: break;
			case 18: Events.nameChange(); break;
			case 19: break;
			case 20: Events.anna(); break;
		}
	}
	
	// this method ends the game
	public static void endGame() {
		infoPane.getChildren().clear();
		eventPane.getChildren().clear();
		
		infoPane.add(new Text("Player " + (turn) + " wins!"), 0, 0);
		
		for (int i = 0; i < numPlayers; i++) {
			infoPane.add(new Text("Player "+ (i+1) + ": " + names[i]), 1, i);
			infoPane.add(new Text("Square " + square[i]), 2, i);
		}
	}
}

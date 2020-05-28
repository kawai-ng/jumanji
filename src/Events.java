import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Events{
	static boolean buttonClicked = false;
	static Button btDecision1 = new Button();
	static Button btDecision2 = new Button();
	static Button btDecision3 = new Button();
	static Button btDecision4 = new Button();
	static TextField tfDecision = new TextField();
	
	public static void catpoop() {
		Jumanji.eventPane.getChildren().add(new Text("You stepped on cat poop! Ew! Go back 2 spaces."));
		Jumanji.square[Jumanji.turn] -= 2;
		if (Jumanji.square[Jumanji.turn] < 0) Jumanji.square[Jumanji.turn] = 0;
	}
	
	public static void magician() {
		Jumanji.eventPane.getChildren().add(new Text("You found a wealthy magician! You whoosh 5 spaces!"));
		Jumanji.square[Jumanji.turn] += 5;
	}
	
	public static void gobby1() {
		Jumanji.eventPane.getChildren().add(new Text("You found a Gobby the Goblin! Darn."
				+ "\nHe offers you one of two items."));
		HBox decision = new HBox();
		btDecision1 = new Button("A purple silk scarf");
		btDecision2 = new Button("A blue milky potion");
		decision.getChildren().addAll(btDecision1, btDecision2);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("You got poisoned! Move back 8 spaces."));
			Jumanji.square[Jumanji.turn-1] -= 8;
			if (Jumanji.square[Jumanji.turn-1] < 0) Jumanji.square[Jumanji.turn-1] = 0;
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("You feel dizzy and drift forward 1 space."));
			Jumanji.square[Jumanji.turn-1] += 1;
			Jumanji.printInfoPane();
		});
	}
	
	public static void gobby2() {
		Jumanji.eventPane.getChildren().add(new Text("You found a Gobby the Goblin! Darn."
				+ "\nHe offers you one of two items."));
		HBox decision = new HBox();
		btDecision1 = new Button("An old ragdoll");
		btDecision2 = new Button("A broken mousetrap");
		decision.getChildren().addAll(btDecision1, btDecision2);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("The ragdoll seems to have magical powers!"
					+ "\nYou feel sleepy and wake up 3 spaces ahead!"));
			Jumanji.square[Jumanji.turn-1] += 3;
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Nothing happened. LOL"));
			Jumanji.printInfoPane();
		});
		
	}
	
	public static void medal() {
		Jumanji.eventPane.getChildren().add(new Text("You found an old rusty medal."
				+ "\nYou can call yourself a champion for this turn!"));
	}
	
	public static void nameChange() {
		Jumanji.eventPane.getChildren().add(new Text("You meet Crazy Curtis! He asked what's your favorite fruit?"));
		GridPane decision = new GridPane();
		decision.setHgap(10);
		decision.setVgap(10);
		btDecision1 = new Button("Strawberries");
		btDecision2 = new Button("Peaches");
		btDecision3 = new Button("Lemons");
		btDecision4 = new Button("Apples");
		decision.add(btDecision1, 0, 0);
		decision.add(btDecision2, 0, 1);
		decision.add(btDecision3, 1, 0);
		decision.add(btDecision4, 1, 1);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Crazy Curtis laughs at you and calls you a new name."));
			Jumanji.names[Jumanji.turn-1] = "Red Raymond";
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Crazy Curtis laughs at you and calls you a new name."));
			Jumanji.names[Jumanji.turn-1] = "Pink Panther";
			Jumanji.printInfoPane();
		});
		
		btDecision3.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Crazy Curtis laughs at you and calls you a new name."));
			Jumanji.names[Jumanji.turn-1] = "Sour Susan";
			Jumanji.printInfoPane();
		});
		
		btDecision4.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Crazy Curtis laughs at you and calls you a new name."));
			Jumanji.names[Jumanji.turn-1] = "Little Kid";
			Jumanji.printInfoPane();
		});
	}
	
	public static void rocketship() {
		Jumanji.eventPane.getChildren().add(new Text("You ride on a rocketship and dash forward 10 spaces!"));
		Jumanji.square[Jumanji.turn] += 10;
	}
	
	public static void transporter() {
		Jumanji.eventPane.getChildren().add(new Text("You found a transporter machine. Enter?"));
		HBox decision = new HBox();
		btDecision1 = new Button("Yes");
		btDecision2 = new Button("No");
		decision.getChildren().addAll(btDecision1, btDecision2);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("You've been transported to a new space!"));
			Jumanji.square[Jumanji.turn-1] = (int)(Math.random() * Jumanji.endingSquare);
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Okay. See ya!"));
			Jumanji.printInfoPane();
		});
	}
	
	public static void catapult() {
		Jumanji.eventPane.getChildren().add(new Text("Look a catapult! Enter a number (1-10) that"
				+ " you would like to fly!\n(You could go forwards or backwards..)"));
		btDecision1 = new Button("Launch Me!");
		tfDecision.setPrefWidth(80);
		Jumanji.eventPane.getChildren().addAll(tfDecision, btDecision1);
		btDecision1.setOnAction(e -> {
			int spaces = Integer.parseInt(tfDecision.getText());
			int decision =(int)(Math.random() * 2);
			if (decision == 0) {
				disableButtons();
				Jumanji.eventPane.getChildren().add(new Text("You launched backwards!"));
				Jumanji.square[Jumanji.turn-1] -= spaces;
				if (Jumanji.square[Jumanji.turn-1] < 0) Jumanji.square[Jumanji.turn-1] = 0;
				Jumanji.printInfoPane();
			} 
			else {
				disableButtons();
				Jumanji.eventPane.getChildren().add(new Text("You launched forwards!"));
				Jumanji.square[Jumanji.turn-1] += spaces;
				Jumanji.printInfoPane();
			}
		});
	}
	
	public static void anna() {
		Jumanji.eventPane.getChildren().add(new Text("You are blessed by Anna the Angel!"
				+ "\nShe offers you a blessing."));
		HBox decision = new HBox();
		btDecision1 = new Button("Pick the magic water.");
		btDecision2 = new Button("Pick the golden crown.");
		decision.getChildren().addAll(btDecision1, btDecision2);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("The magic water fizzes and transport you"
					+ " 6 spaces ahead!"));
			Jumanji.square[Jumanji.turn-1] += 6;
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("You get to wear a golden crown. How cute!"));
			Jumanji.printInfoPane();
		});		
	}
	
	public static void trivia1() {
		Jumanji.eventPane.getChildren().add(new Text("Wishy the Wizard is here! He's gonna ask you a question"
				+ " and answer correctly for a reward!\nIn which year is Albert Einstein born?"));
		btDecision1 = new Button("I'm ready");
		tfDecision.setPrefWidth(40);
		Jumanji.eventPane.getChildren().addAll(tfDecision, btDecision1);
		btDecision1.setOnAction(e -> {
			int answer = Integer.parseInt(tfDecision.getText());
			if (answer == 1879) {
				disableButtons();
				int spaces = (int)(Math.random() * 10);
				Jumanji.eventPane.getChildren().add(new Text("Wishy is impressed. You awards you a dice roll"
						+ " and you move forward " + spaces + " spaces!"));
				Jumanji.square[Jumanji.turn-1] += spaces;
				Jumanji.printInfoPane();
			} 
			else {
				disableButtons();
				Jumanji.eventPane.getChildren().add(new Text("Wishy is disappointed and shoves you back 1 space."));
				Jumanji.square[Jumanji.turn-1] += 1;
				Jumanji.printInfoPane();
			}
		});
	}
	
	public static void peter() {
		Jumanji.eventPane.getChildren().add(new Text("You stepped in a trap by Peter the psycho hunter!"
				+ "\nHe forces you to drink one of three potions."));
		HBox decision = new HBox();
		btDecision1 = new Button("The orange potion");
		btDecision2 = new Button("The pink potion");
		btDecision3 = new Button("The purple potion");
		decision.getChildren().addAll(btDecision1, btDecision2, btDecision3);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("You died! Start from the beginning."));
			Jumanji.square[Jumanji.turn-1] = 0;
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("You feel ill and falls back 2 steps."));
			Jumanji.square[Jumanji.turn-1] -= 2;
			if (Jumanji.square[Jumanji.turn-1] < 0) Jumanji.square[Jumanji.turn-1] = 0;
			Jumanji.printInfoPane();
		});		
		
		btDecision3.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Yum. That's grape juice! Peter walks away."));
			Jumanji.printInfoPane();
		});
	}
	
	public static void trivia2() {
		Jumanji.eventPane.getChildren().add(new Text("Wishy the Wizard is here! He's gonna ask you a question"
				+ " and answer correctly for a reward!\nWhat is Canada's capital city?"));
		GridPane decision = new GridPane();
		decision.setHgap(10);
		decision.setVgap(10);
		btDecision1 = new Button("Victoria");
		btDecision2 = new Button("Ottawa");
		btDecision3 = new Button("Toronto");
		btDecision4 = new Button("Calgary");
		decision.add(btDecision1, 0, 0);
		decision.add(btDecision2, 0, 1);
		decision.add(btDecision3, 1, 0);
		decision.add(btDecision4, 1, 1);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Wishy is disappointed and shoves you back 1 space."));
			Jumanji.square[Jumanji.turn-1] -= 1;
			if (Jumanji.square[Jumanji.turn-1] < 0) Jumanji.square[Jumanji.turn-1] = 0;
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			int spaces = (int)(Math.random() * 10);
			Jumanji.eventPane.getChildren().add(new Text("Wishy is impressed. You awards you a dice roll"
					+ " and you move forward " + spaces + " spaces!"));
			Jumanji.square[Jumanji.turn-1] += spaces;
			Jumanji.printInfoPane();
		});
		
		btDecision3.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Wishy is disappointed and shoves you back 1 space."));
			Jumanji.square[Jumanji.turn-1] -= 1;
			if (Jumanji.square[Jumanji.turn-1] < 0) Jumanji.square[Jumanji.turn-1] = 0;
			Jumanji.printInfoPane();
		});
		
		btDecision4.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Wishy is disappointed and shoves you back 1 space."));
			Jumanji.square[Jumanji.turn-1] -= 1;
			if (Jumanji.square[Jumanji.turn-1] < 0) Jumanji.square[Jumanji.turn-1] = 0;
			Jumanji.printInfoPane();
		});
	}
	
	public static void batter() {
		Jumanji.eventPane.getChildren().add(new Text("Trenton accidentally hits you with a baseball bat!"
				+ "\nYou're sent back 8 spaces!"));
		Jumanji.square[Jumanji.turn] -= 8;
		if (Jumanji.square[Jumanji.turn] < 0) Jumanji.square[Jumanji.turn] = 0;
	}
	
	public static void robbery() {
		Jumanji.eventPane.getChildren().add(new Text("You get sedated and wake up naked. Oh no!"));
		Jumanji.names[Jumanji.turn] = "Naked " + Jumanji.names[Jumanji.turn];
	}
	
	public static void doors() {
		Jumanji.eventPane.getChildren().add(new Text("Huh. Two doors appeared. Open one to pass!"));
		HBox decision = new HBox();
		btDecision1 = new Button("The green door");
		btDecision2 = new Button("The brown door");
		decision.getChildren().addAll(btDecision1, btDecision2);
		Jumanji.eventPane.getChildren().add(decision);
		
		btDecision1.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("Wow! A botanical garden!"));
			Jumanji.printInfoPane();
		});
		
		btDecision2.setOnAction(e -> {
			disableButtons();
			Jumanji.eventPane.getChildren().add(new Text("A herd of elephants come out and trample you back 11 spaces."));
			Jumanji.square[Jumanji.turn-1] -= 11;
			if (Jumanji.square[Jumanji.turn-1] < 0) Jumanji.square[Jumanji.turn-1] = 0;
			Jumanji.printInfoPane();
		});
	}
	
	public static void disableButtons() {
		btDecision1.setDisable(true);
		btDecision2.setDisable(true);
		btDecision3.setDisable(true);
		btDecision4.setDisable(true);
	}
}
package pl.edu.agh.to2.example;

import javafx.application.Application;

import java.util.logging.Logger;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class.toString());
	
	public static void main(String[] args) {
		log.info("Hello world");
		Application.launch(App.class);
	}
}

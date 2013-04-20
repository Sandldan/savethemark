package foo.client;

import com.google.gwt.user.client.ui.Button;

// Extend any GWT Widget
public class savethemarkWidget extends Button {

	public savethemarkWidget() {

		// CSS class-name should not be v- prefixed
		setStyleName("savethemark");

		// State is set to widget in savethemarkConnector		
	}

}
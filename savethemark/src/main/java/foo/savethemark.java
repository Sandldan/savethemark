package foo;

import foo.client.savethemarkClientRpc;
import foo.client.savethemarkServerRpc;
import foo.client.savethemarkState;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

// This is the server-side UI component that provides public API 
// for savethemark
public class savethemark extends com.vaadin.ui.AbstractComponent {

	private VerticalLayout bookmarkVerticalLayout = new VerticalLayout();
	private Button button = new Button("Button:D");
	private Window bookmarkWindow = new Window("Bookmarks", bookmarkVerticalLayout);

	// To process events from the client, we implement ServerRpc
	private savethemarkServerRpc rpc = new savethemarkServerRpc() {

		// Event received from client - user clicked our widget
		public void clicked(MouseEventDetails mouseDetails) {
			
			UI.getCurrent().addWindow(bookmarkWindow);
			
		}
	};

	public savethemark() {
		bookmarkVerticalLayout.addComponent(button);
		// To receive events from the client, we register ServerRpc
		registerRpc(rpc);
		getState().text = "Bookmarks";
	}

	// We must override getState() to cast the state to savethemarkState
	@Override
	public savethemarkState getState() {
		return (savethemarkState) super.getState();
	}
}

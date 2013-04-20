package foo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import foo.client.savethemarkClientRpc;
import foo.client.savethemarkServerRpc;
import foo.client.savethemarkState;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

// This is the server-side UI component that provides public API 
// for savethemark
public class savethemark extends com.vaadin.ui.AbstractComponent {

	private VerticalLayout bookmarkVerticalLayout = new VerticalLayout();
	private HorizontalLayout textfieldLayout = new HorizontalLayout();
	private Button saveMarkButton = new Button("Save mark");
	private TextField bookmarkName = new TextField();
	private Window bookmarkWindow = new Window("Bookmarks", bookmarkVerticalLayout);
	private HashMap scrollLocations = new HashMap();
	// To process events from the client, we implement ServerRpc
	private savethemarkServerRpc rpc = new savethemarkServerRpc() {

		// Event received from client - user clicked our widget
		public void clicked(MouseEventDetails mouseDetails) {
			
			if(!UI.getCurrent().getWindows().contains(bookmarkWindow)){
				UI.getCurrent().addWindow(bookmarkWindow);
			}
		}
	};

	public savethemark() {
		bookmarkWindow.setStyleName("bookmark-window");
		bookmarkName.setInputPrompt("Bookmark name");
		bookmarkVerticalLayout.addComponentAsFirst(textfieldLayout);
		textfieldLayout.addComponent(bookmarkName);
		textfieldLayout.addComponent(saveMarkButton);
		saveMarkButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Button button;
				if(!bookmarkName.getValue().isEmpty()){
					button = new Button(bookmarkName.getValue());
				}
				else{
					button = new Button("Bookmark");
				}
				bookmarkName.setValue("");
				button.addClickListener(new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub
						UI.getCurrent().setScrollTop((Integer) scrollLocations.get(event.getButton()));
					}
				});
				bookmarkVerticalLayout.addComponent(button);
				scrollLocations.put(button, UI.getCurrent().getScrollTop());
			}
		});
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

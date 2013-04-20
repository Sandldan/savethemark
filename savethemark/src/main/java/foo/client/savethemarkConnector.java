package foo.client;

import foo.savethemark;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@SuppressWarnings("deprecation")
@Connect(savethemark.class)
public class savethemarkConnector extends AbstractComponentConnector {

	// ServerRpc is used to send events to server. Communication implementation
	// is automatically created here
	savethemarkServerRpc rpc = RpcProxy.create(savethemarkServerRpc.class, this);

	@SuppressWarnings("deprecation")
	public savethemarkConnector() {
		
		// To receive RPC events from server, we register ClientRpc implementation 
		registerRpc(savethemarkClientRpc.class, new savethemarkClientRpc() {
			public void alert(String message) {
				Window.alert(message);
			}
		});

		
		getWidget().addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				// TODO Auto-generated method stub
				rpc.keyPress(event.getUnicodeCharCode());
			}
		});
		
		getWidget().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				rpc.clicked(MouseEventDetailsBuilder.buildMouseEventDetails(event.getNativeEvent(), getWidget().getElement()));
			}
		});
		
		
		
	}

	// We must implement createWidget() to create correct type of widget
	@Override
	protected Widget createWidget() {
		return GWT.create(savethemarkWidget.class);
	}

	
	// We must implement getWidget() to cast to correct type
	@Override
	public savethemarkWidget getWidget() {
		return (savethemarkWidget) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public savethemarkState getState() {
		return (savethemarkState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// State is directly readable in the client after it is set in server
		final String text = getState().text;
		getWidget().setText(text);
	}

}

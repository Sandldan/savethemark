package foo.client;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

// ServerRpc is used to pass events from client to server
public interface savethemarkServerRpc extends ServerRpc {

	// Example API: Widget click is clicked
	public void clicked(MouseEventDetails mouseDetails);
	

}

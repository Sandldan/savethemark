package foo;

import java.util.ArrayList;

import foo.client.savethemarkServerRpc;
import foo.client.savethemarkState;

import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

// This is the server-side UI component that provides public API 
// for savethemark
public class savethemark extends com.vaadin.ui.AbstractComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout bookmarkVerticalLayout = new VerticalLayout();
	private HorizontalLayout textfieldLayout = new HorizontalLayout();
	private ArrayList<Bookmark> bookmarkList = new ArrayList<Bookmark>();
	
	//private Action highLightTextAction = new ShortcutAction("highlighttext", KeyCode.H, new int[] {  });
	
	private Button saveMarkButton = new Button("Save");
	private TextField bookmarkName = new TextField();
	private Window bookmarkWindow = new Window("SaveTheMark", bookmarkVerticalLayout);
	// To process events from the client, we implement ServerRpc
	private savethemarkServerRpc rpc = new savethemarkServerRpc() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// Event received from client - user clicked our widget
		public void clicked(MouseEventDetails mouseDetails) {
			
			if(!UI.getCurrent().getWindows().contains(bookmarkWindow)){
				UI.getCurrent().addWindow(bookmarkWindow);
			}
			else  {
				Page.getCurrent().getJavaScript().execute("var text, sel, range, node; if (typeof window.getSelection != 'undefined') { sel = window.getSelection(); text = window.getSelection().toString(); if (sel.getRangeAt && sel.rangeCount) { range = window.getSelection().getRangeAt(0); range.deleteContents(); node = range.createContextualFragment('<span style=\"background-color: yellow;\">'+text+'</span>'); range.insertNode(node); } } ");
			}
		}

	};

	public savethemark() {
		/*
		UI.getCurrent().addActionHandler(new Handler() {
			
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				
				// TODO Auto-generated method stub
				if(action == highLightTextAction){
					highLightText();
				}
			}

			private void highLightText() {
				// TODO Auto-generated method stub
				Page.getCurrent().getJavaScript().execute("var text, sel, range, node; if (typeof window.getSelection != 'undefined') { sel = window.getSelection(); text = window.getSelection().toString(); if (sel.getRangeAt && sel.rangeCount) { range = window.getSelection().getRangeAt(0); range.deleteContents(); node = range.createContextualFragment('<span style=\"background-color: yellow;\">'+text+'</span>'); range.insertNode(node); } } ");
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				// TODO Auto-generated method stub
				return new Action[] { highLightTextAction };
			}
		});
		*/
		Label js = new Label("<script src='http://code.jquery.com/jquery-1.4.4.js'></script><script>document.onkeypress = function (evt) { console.log('testing ' + evt.keyCode); if (evt.keyCode== 72) { console.log('if running'); var text, sel, range, node; if (typeof window.getSelection != 'undefined') { sel = window.getSelection();  text = window.getSelection().toString(); if (sel.getRangeAt(0) && sel.rangeCount) { range = window.getSelection().getRangeAt(0); range.deleteContents(); node = range.createContextualFragment('<span style=\"background-color: yellow;\">'+text+'</span>'); range.insertNode(node); } } } }</script>");
		js.setContentMode(ContentMode.HTML);
		
		bookmarkWindow.setStyleName("bookmark-window");
		bookmarkWindow.setHeight("300px");
		
		bookmarkName.setInputPrompt("Bookmark name");
		bookmarkVerticalLayout.addComponentAsFirst(textfieldLayout);
		bookmarkVerticalLayout.setWidth("200px");
		textfieldLayout.addComponent(bookmarkName);
		textfieldLayout.addComponent(saveMarkButton);
		
		bookmarkName.setStyleName("nameLabel ");
		saveMarkButton.setStyleName("saveButton button");
		
		bookmarkVerticalLayout.addComponent(js);
		
		saveMarkButton.addClickListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String caption;
				// TODO Auto-generated method stub
				if(!bookmarkName.getValue().isEmpty()){
					caption = bookmarkName.getValue();
				}
				else{
					caption = "Bookmark";
				}
				Bookmark bookmark = new Bookmark(UI.getCurrent().getScrollTop(), caption, true);
				bookmarkName.setValue("");
				
				bookmarkVerticalLayout.addComponent(bookmark);
			}
		});
		// To receive events from the client, we register ServerRpc
		registerRpc(rpc);
		getState().text = "SaveTheMark";
	}
	
	/**
	 * @param component , the component to scroll to
	 * @param caption , the caption of the bookmark button
	 * @param showDeleteButton , show the delete button or not
	 * @return Bookmark , the bookmark created
	 */
	public Bookmark addBookmark(Component component, String caption, boolean showDeleteButton){
		Bookmark bookmark = new Bookmark(component, caption, showDeleteButton);
		bookmarkVerticalLayout.addComponent(bookmark);
		bookmarkList.add(bookmark);
		return bookmark;
	}
	
	/**
	 * @param position , the position in pixels to scroll to
	 * @param caption , the caption of the bookmark button
	 * @param showDeleteButton , show the delete button or not
	 * @return Bookmark , the bookmark created
	 */
	public Bookmark addBookmark(int position, String caption, boolean showDeleteButton){
		Bookmark bookmark = new Bookmark(position, caption, showDeleteButton);
		bookmarkVerticalLayout.addComponent(bookmark);
		bookmarkList.add(bookmark);
		return bookmark;
	}
	
	public void removeBookmark(Bookmark bookmark){
		bookmarkVerticalLayout.removeComponent(bookmark);
	}
	
	/**
	 * @param bookmarkdata , the array recieved from calling saveBookmark
	 * @return true if successful, false if not
	 */
	public boolean loadBookmark(String[] bookmarkdata){
		try{
			Bookmark bookmark = new Bookmark(Integer.parseInt(bookmarkdata[0]), bookmarkdata[1], Boolean.parseBoolean(bookmarkdata[2]));
			bookmarkVerticalLayout.addComponent(bookmark);
			bookmarkList.add(bookmark);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * @param bookmark , the bookmark you want to store NOTE: you can only store bookmarks that use position, bookmark(position, caption, showdeletebutton)
	 * @return the string array containing the data if successful, null if not
	 */
	public String[] saveBookmark(Bookmark bookmark){
		if(bookmark.getPosition() == -1){
			return null;
		}
		String[] tmp = new String[3];
		tmp[0] = "" + bookmark.getPosition();
		tmp[1] = bookmark.getCaption();
		tmp[2] = "" + bookmark.isShowDeleteButton();
		return tmp;
	}
	

	public ArrayList<Bookmark> getBookmarkList() {
		return bookmarkList;
	}

	// We must override getState() to cast the state to savethemarkState
	@Override
	public savethemarkState getState() {
		return (savethemarkState) super.getState();
	}

	

	
}

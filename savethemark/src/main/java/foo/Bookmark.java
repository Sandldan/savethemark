package foo;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class Bookmark extends HorizontalLayout {

	private String description;
	private int position; 
	
	private Button bookmarkButton;
	private Button deletButton;
	
	public Bookmark(int position, String description) {
		this.position = position;
		this.description = description;
		
		bookmarkButton = new Button(description);
		deletButton = new Button("delete");
		
		this.addComponent(bookmarkButton);
		//TODO
		//Add the deletbutton aswell
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Button getBookmarkButton() {
		return bookmarkButton;
	}

	public Button getDeletButton() {
		return deletButton;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

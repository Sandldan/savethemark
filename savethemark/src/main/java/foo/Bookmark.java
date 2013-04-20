package foo;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class Bookmark extends HorizontalLayout {

	private String caption;
	private int position = -1; 
	private Component component;
	private boolean showDeleteButton;
	
	private Button bookmarkButton;
	private Button deletButton;
	
	/**
	 * @param position , the position in pixels to scroll to
	 * @param caption , the caption of the button
	 * @param showDeleteButton , show delete button or not
	 */
	public Bookmark(int position, String caption, boolean showDeleteButton) {
		this.position = position;
		this.caption = caption;
		this.showDeleteButton = showDeleteButton;
		
		bookmarkButton = new Button(caption, new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				UI.getCurrent().setScrollTop(getPosition());
			}
		});
		this.addComponent(bookmarkButton);
		
		if(showDeleteButton){
			deletButton = new Button("delete", new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					((VerticalLayout)getBookmark().getParent()).removeComponent(getBookmark());
				}
			});
			this.addComponent(deletButton);
		}
		bookmarkButton.setStyleName("bookmarkGotoButton button");
		deletButton.setStyleName("bookmarkDeleteButton button");
	}
	
	/**
	 * @param component , the component to scroll to
	 * @param caption , the caption of the bookmark button
	 * @param showDeleteButton , show delete button or not
	 */
	public Bookmark(Component component, String caption, boolean showDeleteButton){
		this.component = component;
		this.caption = caption;
		
		bookmarkButton = new Button(caption, new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				UI.getCurrent().scrollIntoView(getComponent());
			}
		});
		this.addComponent(bookmarkButton);
		bookmarkButton.setStyleName("bookmarkGotoButton button");
		
		if(showDeleteButton){
			deletButton = new Button("delete", new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					((VerticalLayout)getBookmark().getParent()).removeComponent(getBookmark());
				}
			});
			this.addComponent(deletButton);
		}
	}
	
	/** 
	 * 	@return the caption of the bookmark button
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * 	@param caption , the caption to set on the button
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the position in pixels to scroll to
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @param position , the position to scroll to
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * @return the component to scroll to
	 */
	public Component getComponent() {
		return component;
	}

	/**
	 * @param component , the component to scroll to
	 */
	public void setComponent(Component component) {
		this.component = component;
	}

	public boolean isShowDeleteButton() {
		return showDeleteButton;
	}

	/**
	 * @return the bookmark button
	 */
	public Button getBookmarkButton() {
		return bookmarkButton;
	}

	/**
	 * @return the delete button
	 */
	public Button getDeletButton() {
		return deletButton;
	}
	
	/**
	 * @return this class
	 */
	public Bookmark getBookmark(){
		return this;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

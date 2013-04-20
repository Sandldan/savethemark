package foo.demo;

import foo.savethemark;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("savethemark Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @Override
    protected void init(VaadinRequest request) {
    	
    	// Initialize our new UI component
    	final savethemark savethemark = new savethemark();
        savethemark.addBookmark(savethemark, "Top", false);
        savethemark.addBookmark(10, "10px down", true);
    	// Show it in the middle of the screen
    	final HorizontalLayout layout = new HorizontalLayout();
        setContent(layout);

    	final VerticalLayout paddingLeft= new VerticalLayout();
    	final VerticalLayout paddingRight= new VerticalLayout();
    	final VerticalLayout mainContent = new VerticalLayout();
    	
    	layout.setWidth("100%");
    	    	
//    	padding.setWidth(20, Unit.PERCENTAGE);
//    	mainContent.setWidth(60, Unit.PERCENTAGE);
    	paddingLeft.setWidth("100%");
    	paddingRight.setWidth("100%");
    	mainContent.setWidth("100%");
    	
    	

    	final Label label = new Label("Bacon ipsum dolor sit amet pork belly frankfurter beef, spare ribs ribeye kielbasa strip steak venison tail turducken. Venison ham hock pork chop chicken sausage rump bresaola sirloin filet mignon salami ribeye ham. Venison swine beef capicola prosciutto ground round tenderloin ham spare ribs meatball. Bacon meatloaf venison prosciutto. Boudin tri-tip drumstick, shoulder brisket shankle leberkas shank cow. Shank doner ham turkey leberkas shankle. Jerky fatback biltong, short ribs pig frankfurter meatloaf beef ribs. Sausage venison ground round pork strip steak spare ribs pancetta doner pork chop beef hamburger turducken short loin. Chicken pig pork loin, doner andouille corned beef short loin pancetta biltong shankle short ribs turkey. Leberkas chicken shoulder beef ribs.Tail cow tenderloin, ball tip pastrami meatball meatloaf spare ribs sirloin filet mignon corned beef turducken prosciutto. Venison pig turkey doner jerky filet mignon pancetta bresaola hamburger ground round salami flank kielbasa drumstick. Bacon beef drumstick pork chop meatloaf pork short loin beef ribs boudin sausage. Bacon chuck jowl, flank meatball turkey jerky kielbasa. Pastrami chuck venison ground round capicola shoulder meatball beef ribs sausage corned beef pork belly shankle meatloaf salami tri-tip.");
        

		final Label label2 = new Label("Shank bacon meatball tenderloin, ground round biltong ham hock short ribs pork belly capicola boudin frankfurter pork chop flank prosciutto. Meatloaf fatback flank swine. Ball tip sausage jerky, doner pork chop meatball drumstick andouille pork belly pork loin short ribs filet mignon ham. Jerky sausage chicken frankfurter, rump pastrami swine t-bone shankle spare ribs drumstick brisket. Beef short ribs flank, bacon swine andouille jerky chuck cow spare ribs ham hock brisket ball tip corned beef ground round.");
        label2.setWidth("100%");
    	label.setWidth("100%");
        layout.setStyleName("demoContentLayout");

        layout.addComponent(paddingLeft);
        layout.addComponent(mainContent);
        layout.addComponent(paddingRight);
        
    	layout.setExpandRatio(paddingLeft, 1);
    	layout.setExpandRatio(mainContent, 5);
    	layout.setExpandRatio(paddingRight, 1);

        
        mainContent.addComponent(savethemark);
        mainContent.addComponent(label);
        mainContent.addComponent(label2);
                

    }

}

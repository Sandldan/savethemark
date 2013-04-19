package foo.demo;

import foo.savethemark;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
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
            	
    	// Show it in the middle of the screen
    	final VerticalLayout layout = new VerticalLayout();
    	final Label label = new Label("Bacon ipsum dolor sit amet fatback ball tip short loin tail tongue pork belly sirloin pork loin. Capicola flank beef ribs cow biltong short ribs, hamburger tri-tip venison. Pastrami brisket sirloin rump shankle chuck ham hock. Pig sausage ball tip pork loin. Pork jerky chuck venison pork chop sirloin bacon corned beef filet mignon meatball jowl tongue tri-tip doner ball tip. Pancetta beef ribs ribeye chicken, turkey spare ribs fatback shank ham chuck short loin beef ground round capicola meatloaf. Spare ribs shankle salami, meatball kielbasa boudin flank cow.");
        label.setWidth("200px");
        final Label label2 = new Label("Shank bacon meatball tenderloin, ground round biltong ham hock short ribs pork belly capicola boudin frankfurter pork chop flank prosciutto. Meatloaf fatback flank swine. Ball tip sausage jerky, doner pork chop meatball drumstick andouille pork belly pork loin short ribs filet mignon ham. Jerky sausage chicken frankfurter, rump pastrami swine t-bone shankle spare ribs drumstick brisket. Beef short ribs flank, bacon swine andouille jerky chuck cow spare ribs ham hock brisket ball tip corned beef ground round.");
    	label2.setWidth("200px");
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(savethemark);
        layout.addComponent(label);
        layout.addComponent(label2);
        setContent(layout);

    }

}

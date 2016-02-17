package ua.test.my.skills.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements ValueChangeHandler<String> {
	HasWidgets container;
	
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		
		String token = event.getValue();

	    
	}

	private void bind() {
		History.addValueChangeHandler(this);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("list");
		}
		else {
			History.fireCurrentHistoryState();
		}
	}


}

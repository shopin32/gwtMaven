package ua.test.my.skills.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ContactsServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}

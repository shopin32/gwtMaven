package ua.test.my.skills.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import ua.test.my.skills.client.presenter.ContactsPresenter;
import ua.test.my.skills.client.presenter.ContactsPresenter.Display;
import ua.test.my.skills.client.view.ContactView;
import ua.test.my.skills.shared.Contact;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Contacts implements EntryPoint {

	private static final List<Contact> CONTACTS = Arrays.asList(
			new Contact("Petro", "Shopin", "shopin.peter@gmail.com","admin"),
			new Contact("Vasya","Oblomov", "vasya@mail.ru","user"),
			new Contact("Gadya","Ivanovich","tuts@tuts.ua","user"));

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Display disp = new ContactView();
		ContactsPresenter presenter = new ContactsPresenter(CONTACTS, disp);
		presenter.go(RootLayoutPanel.get());
	}

}

package ua.test.my.skills.client.view;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import ua.test.my.skills.client.presenter.ContactsPresenter;
import ua.test.my.skills.client.presenter.ContactsPresenter.Display;
import ua.test.my.skills.shared.Contact;

public class ContactView extends Composite  implements Display {
	
	private static ContactViewUiBinder uiBinder = GWT.create(ContactViewUiBinder.class);

	interface ContactViewUiBinder extends UiBinder<Widget, ContactView> {
	}
	
	final MultiSelectionModel<Contact> multiSelectionModel = new MultiSelectionModel<>();
	
	ContactsPresenter presenter;

	public ContactView() {
		initWidget(uiBinder.createAndBindUi(this));
		button.setEnabled(false);
		
	}

	@UiField
	SplitLayoutPanel panel;
	
	@UiField
	DataGrid<Contact> grid;
	
	@UiField
	HTML html;
	
	@UiField
	Button button;
	
	@UiField
	CheckBox disableGridCheckboxes;
	@UiField
	CheckBox disableGridRoleSelection;
	
	
	@SuppressWarnings("unchecked")
	public void onSelectionChanged(SelectionChangeEvent e){
		Contact selected = ((SingleSelectionModel<Contact>)grid.getSelectionModel()).getSelectedObject();
		if(selected != null && presenter != null)
			presenter.showInfo(selected);
	}

	public ContactView(String firstName) {
		this();
		html.setText(firstName);
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

	@Override
	public void setAdditionalInfo(String someHtml) {
		html.setHTML(someHtml);
	}


	@Override
	public void setPresenter(ContactsPresenter presenter) {
		this.presenter = presenter;
	}


	@Override
	public Set<Contact> getSelectedRows() {
		return multiSelectionModel.getSelectedSet();
	}


	@Override
	public Button getButton() {
		return  button;
	}

	@Override
	public DataGrid<Contact> getDataGrid() {
		return grid;
	}


	@Override
	public void setSelected(Contact contact, boolean value) {
		multiSelectionModel.setSelected(contact, value);
	}


	@Override
	public boolean isSelected(Contact contact) {
		return multiSelectionModel.isSelected(contact);
	}


	@Override
	public void showAllert(String text) {
		Window.alert(text);
	}

	@Override
	public CheckBox getDisablingCheckCell() {
		return disableGridCheckboxes;
	}

	@Override
	public CheckBox getDisablingRoleSelectionCell() {
		return disableGridRoleSelection;
	}
}

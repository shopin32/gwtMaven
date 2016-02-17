package ua.test.my.skills.client.presenter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import ua.test.my.skills.client.DisabledCheckboxCell;
import ua.test.my.skills.client.DisabledSelectionCell;
import ua.test.my.skills.client.presenter.Presenter;
import ua.test.my.skills.shared.Contact;
/**
 * Service to control the logic  of application and wire event handling with 
 * showing data in a viewer
 */
public class ContactsPresenter implements Presenter {
	
	Display view;
	
	List<Contact> contacts;
	
	private static final List<String> roles = Arrays.asList("admin","user");
	
	public ContactsPresenter(List<Contact> contacts, Display view){
		this.contacts = contacts;
		this.view = view;
		bind();
	}
	
	public interface Display{
		/**Returns my custom layout as a Widgit**/
		Widget asWidget();
		/**Show some additional info in the west block**/
		void setAdditionalInfo(String someHtml);
		/**Get the GUI element to add some event handlers**/
		Button getButton();
		/**Get the GUI element to add some event handlers and choose what fields to show**/
		DataGrid<Contact> getDataGrid();
		CheckBox getDisablingCheckCell();
		CheckBox getDisablingRoleSelectionCell();
		/**Return selected items in by the checkboxes**/
		Set<Contact> getSelectedRows();
		/**Set contact selected**/
		void setSelected(Contact contact, boolean value);
		/**Check if the contact is selected**/
		boolean isSelected(Contact contact);
		/**Show some aalert**/
		void showAllert(String text);
		/**Set the presenter**/
		void setPresenter(ContactsPresenter presenter);
	}
	
	@Override
	public void bind() {
		view.setPresenter(this);
		view.asWidget().addDomHandler(new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                if (view.getSelectedRows().size() > 0) {
                	view.getButton().setEnabled(true);
				}else{
					view.getButton().setEnabled(false);
				}
            }
        }, ChangeEvent.getType());
		
		final ListDataProvider<Contact> dataProvider= new ListDataProvider<>(contacts);
		dataProvider.addDataDisplay(view.getDataGrid());

		view.getDataGrid().setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		Column<Contact, Boolean> checkColumn = new Column<Contact, Boolean>(new DisabledCheckboxCell(true,false,view.getDisablingCheckCell())) {
			@Override
			public Boolean getValue(Contact object) {
				return view.isSelected(object);
			}

		};
		checkColumn.setFieldUpdater(new FieldUpdater<Contact, Boolean>() {
			@Override
			public void update(int index, Contact object, Boolean value) {
				view.setSelected(object,value);
	            dataProvider.refresh();
			}
		});
		
		DisabledCheckboxCell headerCheckbox = new DisabledCheckboxCell(true,false,view.getDisablingCheckCell());
		Header<Boolean> selectAllHeader = new Header<Boolean>(headerCheckbox) {

			@Override
			public Boolean getValue() {
				boolean result = contacts.size() > 0;
				for (Contact item : contacts) {
					if (!view.isSelected(item)) {
						result = false;
						break;
					}
				}
				return result;
			}
		};
		selectAllHeader.setUpdater(new ValueUpdater<Boolean>() {

			@Override
			public void update(Boolean value) {
				for (Contact object : contacts) {
					view.setSelected(object,value);
				}
				dataProvider.refresh();
			}
		});
		view.getDataGrid().insertColumn(0,checkColumn, selectAllHeader);


		TextColumn<Contact> id = new TextColumn<Contact>() {

			@Override
			public String getValue(Contact object) {
				return object.getId()+"";
			}
		};
		view.getDataGrid().addColumn(id, "id");
		TextColumn<Contact> name = new TextColumn<Contact>() {

			@Override
			public String getValue(Contact object) {
				return object.getName();
			}
		};
		view.getDataGrid().addColumn(name, "name");

		DisabledSelectionCell categoryCell = new DisabledSelectionCell(roles,view.getDisablingRoleSelectionCell());
		Column<Contact, String> roleColumn = new Column<Contact, String>(categoryCell) {

			@Override
			public String getValue(Contact object) {
				return object.getRole();
			}
		}; 
		view.getDataGrid().addColumn(roleColumn, "role");
		final SingleSelectionModel<Contact> selectionModel = new SingleSelectionModel<>();
		view.getDataGrid().setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Contact selected = selectionModel.getSelectedObject();
				if(selected != null )
					showInfo(selected);
			}
		});
		view.getDataGrid().setSelectionModel(selectionModel);
		view.getDataGrid().setRowData(contacts);
		view.getDataGrid().setWidth("100%");
		
		
		view.getButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				StringBuilder sb = new StringBuilder();
				for(Contact item : view.getSelectedRows())
					sb.append(view.getDataGrid().getVisibleItems().indexOf(item)+ 1).append(",");
				sb.delete(sb.length()-1, sb.length());
				view.showAllert(sb.toString());
			}
		});
		view.getDisablingCheckCell().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dataProvider.refresh();
			}
		});
		
		view.getDisablingRoleSelectionCell().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dataProvider.refresh();
			}
		});
	}

	@Override
	public void go(Panel panel) {
		panel.add(view.asWidget());
	}
	
	public void showInfo(Contact item){
		view.setAdditionalInfo(item.getAdditionalInfoAsHtml());
	}
	
	
}

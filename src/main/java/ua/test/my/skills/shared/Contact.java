package ua.test.my.skills.shared;

public class Contact {
	
	private static int counter = 1;
	
	Integer id;
	
	boolean isSelected;
	
	String name;
	
	String surname;
	
	String role;
	
	String email;
	
	public Contact(){
		this.id = counter++;
	}
	public Contact(String name, String surname){
		this();
		this.name = name;
		this.surname = surname;
	}
	public Contact(String name, String surname, String email){
		this();
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public Contact(String name, String surname,  String email, String role){
		this();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.role = role;
	}
	
	
	
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", surname=" + surname + ", role=" + role + ", email=" + email
				+ "]";
	}
	public String getAdditionalInfoAsHtml() {
		String info = "";
		if(surname!= null && !surname.trim().isEmpty())
			info+= "<div><h4>Surname:\t" + surname+"</div>";
		if(email!= null && !email.trim().isEmpty())
			info+= "<div><h4>"+"email:\t" + email + "</h4></div>";
		return info;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	
}

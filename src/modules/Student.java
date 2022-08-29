package modules;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Student {
	private IntegerProperty order;
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty gender;
	private StringProperty major;
	private StringProperty email;
	private StringProperty phone;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int order, int id, String name, String gender, String major, String email, String phone) {
		super();
		this.order = new SimpleIntegerProperty(order);
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.gender = new SimpleStringProperty(gender);
		this.major = new SimpleStringProperty(major);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
	}

	public int getOrder() {
		return order.get();
	}

	public void setOrder(int order) {
		this.order = new SimpleIntegerProperty(order);
	}

	
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}

	public String getMajor() {
		return major.get();
	}

	public void setMajor(String major) {
		this.major = new SimpleStringProperty(major);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email.get(), gender.get(), id.get(), major.get(), name.get(), order.get(), phone.get());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(email.get(), other.email.get()) && Objects.equals(gender.get(), other.gender.get()) && id.get() == other.id.get()
				&& Objects.equals(major.get(), other.major.get()) && Objects.equals(name.get(), other.name.get()) && order.get() == other.order.get()
				&& Objects.equals(phone.get(), other.phone.get());
	}

}

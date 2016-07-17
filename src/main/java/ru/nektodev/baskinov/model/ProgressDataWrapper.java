package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class ProgressDataWrapper {
	private String name;
	private String student;
	private List<ProgressData> values;

	public ProgressDataWrapper() {
	}

	public ProgressDataWrapper(String name, String student) {
		this.name = name;
		this.student = student;
		this.values = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "ProgressDataWrapper{" +
				"name='" + name + '\'' +
				", student='" + student + '\'' +
				", values=" + values +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProgressDataWrapper that = (ProgressDataWrapper) o;
		return Objects.equal(name, that.name) &&
				Objects.equal(student, that.student) &&
				Objects.equal(values, that.values);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, student, values);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProgressData> getValues() {
		return values;
	}

	public void setValues(List<ProgressData> values) {
		this.values = values;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}
}

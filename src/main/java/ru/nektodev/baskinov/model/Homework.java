package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Homework implements Serializable, Cloneable {

	@Id
	private String id;
	private List<Date> homeworkDates;
	private ImportData importData;

	@Override
	public String toString() {
		return "Homework{" +
				"id='" + id + '\'' +
				", homeworkDates=" + homeworkDates +
				", importData=" + importData +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Homework homework = (Homework) o;
		return Objects.equal(id, homework.id) &&
				Objects.equal(homeworkDates, homework.homeworkDates) &&
				Objects.equal(importData, homework.importData);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, homeworkDates, importData);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Date> getHomeworkDates() {
		return homeworkDates;
	}

	public void setHomeworkDates(List<Date> homeworkDates) {
		this.homeworkDates = homeworkDates;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public ImportData getImportData() {
		return importData;
	}

	public void setImportData(ImportData importData) {
		this.importData = importData;
	}
}

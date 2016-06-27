package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable, Cloneable {

	@Id
	private String id;
	private List<Homework> homeworks;
	private ImportData importData;

	@Override
	public int hashCode() {
		return Objects.hashCode(id, homeworks, importData);
	}

	@Override
	public String toString() {
		return "Task{" +
				"id='" + id + '\'' +
				", homeworks=" + homeworks +
				", importData=" + importData +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return Objects.equal(id, task.id) &&
				Objects.equal(importData, task.importData);
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImportData getImportData() {
		return importData;
	}

	public void setImportData(ImportData importData) {
		this.importData = importData;
	}

	public void setHomeworks(List<Homework> homeworks) {
		this.homeworks = homeworks;
	}

	public List<Homework> getHomeworks() {
		return homeworks;
	}
}

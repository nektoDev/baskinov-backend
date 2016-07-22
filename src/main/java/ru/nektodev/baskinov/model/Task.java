package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable {

	@Id
	private String id;
	private List<Homework> homeworks;
	private ImportParams importParams;

	@Override
	public int hashCode() {
		return Objects.hashCode(id, homeworks, importParams);
	}

	@Override
	public String toString() {
		return "Task{" +
				"id='" + id + '\'' +
				", homeworks=" + homeworks +
				", importParams=" + importParams +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return Objects.equal(id, task.id) &&
				Objects.equal(importParams, task.importParams);
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImportParams getImportParams() {
		return importParams;
	}

	public void setImportParams(ImportParams importParams) {
		this.importParams = importParams;
	}

	public void setHomeworks(List<Homework> homeworks) {
		this.homeworks = homeworks;
	}

	public List<Homework> getHomeworks() {
		return homeworks;
	}
}

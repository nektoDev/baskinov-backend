package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Homework implements Serializable, Cloneable {

	@Id
	private String id;
	private String downloadPath;
	private List<Date> homeworkDates;

	@Override
	public String toString() {
		return "Homework{" +
				"id='" + id + '\'' +
				", downloadPath='" + downloadPath + '\'' +
				", homeworkDates=" + homeworkDates +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Homework that = (Homework) o;
		return Objects.equal(id, that.id) &&
				Objects.equal(downloadPath, that.downloadPath) &&
				Objects.equal(homeworkDates, that.homeworkDates);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, downloadPath, homeworkDates);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
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
}

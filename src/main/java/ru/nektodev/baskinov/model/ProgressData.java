package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

import java.util.Date;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class ProgressData {
	private Date date;
	private Integer value;

	@Override
	public String toString() {
		return "ProgressData{" +
				"date=" + date +
				", value=" + value +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProgressData that = (ProgressData) o;
		return Objects.equal(date, that.date) &&
				Objects.equal(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(date, value);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}

package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class Progress {
	@Id
	private String name;
	private ImportParams importParams;
	private List<ProgressDataWrapper> data;

	@Override
	public String toString() {
		return "Progress{" +
				"name='" + name + '\'' +
				", importParams=" + importParams +
				", data=" + data +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Progress progress = (Progress) o;
		return Objects.equal(name, progress.name) &&
				Objects.equal(importParams, progress.importParams) &&
				Objects.equal(data, progress.data);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, importParams, data);
	}

	public List<ProgressDataWrapper> getData() {
		return data;
	}

	public void setData(List<ProgressDataWrapper> data) {
		this.data = data;
	}

	public ImportParams getImportParams() {

		return importParams;
	}

	public void setImportParams(ImportParams importParams) {
		this.importParams = importParams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

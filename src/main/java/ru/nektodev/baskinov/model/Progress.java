package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class Progress {
	@Id
	private String name;
	private ImportParamsProgress importParams;

	@Override
	public String toString() {
		return "Progress{" +
				"name='" + name + '\'' +
				", importParams=" + importParams +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Progress progress = (Progress) o;
		return Objects.equal(name, progress.name) &&
				Objects.equal(importParams, progress.importParams);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, importParams);
	}

	public ImportParamsProgress getImportParams() {
		return importParams;
	}

	public void setImportParams(ImportParamsProgress importParams) {
		this.importParams = importParams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class Progress {
	@Id
	private String name;
	private ProgressType type;
	private ImportParamsProgress importParams;

	@Override
	public String toString() {
		return "Progress{" +
				"name='" + name + '\'' +
				", type=" + type +
				", importParams=" + importParams +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Progress progress = (Progress) o;
		return Objects.equal(name, progress.name) &&
				type == progress.type &&
				Objects.equal(importParams, progress.importParams);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, type, importParams);
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

	public ProgressType getType() {
		return type;
	}

	public void setType(ProgressType type) {
		this.type = type;
	}
}

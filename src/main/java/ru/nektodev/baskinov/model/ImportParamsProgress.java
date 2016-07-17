package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class ImportParamsProgress extends ImportParams {
	ProgressDataWrapper[] progressDataWrappers;

	public ProgressDataWrapper[] getProgressDataWrappers() {
		return progressDataWrappers;
	}

	public void setProgressDataWrappers(ProgressDataWrapper[] progressDataWrappers) {
		this.progressDataWrappers = progressDataWrappers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		ImportParamsProgress that = (ImportParamsProgress) o;
		return Objects.equal(progressDataWrappers, that.progressDataWrappers);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), progressDataWrappers);
	}
}


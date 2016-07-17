package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class Progress {
	private ImportParams importParams;
	private List<ProgressData> data;
	private List<ProgressData> testData;

	@Override
	public String toString() {
		return "Progress{" +
				"importParams=" + importParams +
				", data=" + data +
				", testData=" + testData +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Progress progress = (Progress) o;
		return Objects.equal(importParams, progress.importParams) &&
				Objects.equal(data, progress.data) &&
				Objects.equal(testData, progress.testData);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(importParams, data, testData);
	}

	public ImportParams getImportParams() {

		return importParams;
	}

	public void setImportParams(ImportParams importParams) {
		this.importParams = importParams;
	}

	public List<ProgressData> getData() {
		return data;
	}

	public void setData(List<ProgressData> data) {
		this.data = data;
	}

	public List<ProgressData> getTestData() {
		return testData;
	}

	public void setTestData(List<ProgressData> testData) {
		this.testData = testData;
	}
}

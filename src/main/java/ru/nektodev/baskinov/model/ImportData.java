package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

import java.util.Map;

public class ImportData {
	private Map<String, String> result;
	private String fileHash;

	@Override
	public String toString() {
		return "ImportData{" +
				"result=" + result +
				", fileHash='" + fileHash + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ImportData that = (ImportData) o;
		return Objects.equal(result, that.result) &&
				Objects.equal(fileHash, that.fileHash);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(result, fileHash);
	}

	public Map<String, String> getResult() {

		return result;
	}

	public void setResult(Map<String, String> result) {
		this.result = result;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
}

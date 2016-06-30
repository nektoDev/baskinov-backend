package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

import java.io.Serializable;

public class ImportParams implements Serializable{
	private String publicKey;
	private String path;

	@Override
	public String toString() {
		return "ImportParams{" +
				"publicKey='" + publicKey + '\'' +
				", path='" + path + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ImportParams that = (ImportParams) o;
		return Objects.equal(publicKey, that.publicKey) &&
				Objects.equal(path, that.path);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(publicKey, path);
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

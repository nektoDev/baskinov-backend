package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

import java.util.Date;
import java.util.List;

public class Homework {
	private Date date;
	private String fileHash;
	private List<HomeworkWord> words;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Homework homework = (Homework) o;
		return Objects.equal(date, homework.date) &&
				Objects.equal(fileHash, homework.fileHash) &&
				Objects.equal(words, homework.words);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(date, fileHash, words);
	}

	@Override
	public String
	toString() {
		return "Homework{" +
				"date=" + date +
				", fileHash='" + fileHash + '\'' +
				", words=" + words +
				'}';
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<HomeworkWord> getWords() {
		return words;
	}

	public void setWords(List<HomeworkWord> words) {
		this.words = words;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
}

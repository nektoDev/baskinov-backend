package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;

import java.util.Date;
import java.util.List;

public class Homework {
	private Date date;
	private List<Word> words;

	@Override
	public String toString() {
		return "Homework{" +
				"date=" + date +
				", words=" + words +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Homework homework = (Homework) o;
		return Objects.equal(date, homework.date) &&
				Objects.equal(words, homework.words);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(date, words);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}
}

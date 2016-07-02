package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Student implements Serializable {

	@Id
	private String id;
	private String name;
	private Task vocabulary;
	private Task pronunciation;
	private String dialect;

	@Override
	public String toString() {
		return "Student{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", vocabulary=" + vocabulary +
				", pronunciation=" + pronunciation +
				", dialect='" + dialect + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return Objects.equal(id, student.id) &&
				Objects.equal(name, student.name) &&
				Objects.equal(vocabulary, student.vocabulary) &&
				Objects.equal(pronunciation, student.pronunciation) &&
				Objects.equal(dialect, student.dialect);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, vocabulary, pronunciation, dialect);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Task getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(Task vocabulary) {
		this.vocabulary = vocabulary;
	}

	public Task getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(Task pronunciation) {
		this.pronunciation = pronunciation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
}

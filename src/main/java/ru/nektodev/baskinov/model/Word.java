package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Data model class
 *
 * @author Tsykin V.A ts.slawa@gmail.com
 */
public class Word implements Serializable {

	@Id
	private String id;
	private Map<String, String> pronunciation;
	private Set<String> translation;
	private Map<String, Integer> countUses;
	private Map<String, Date> firstAppeared;
	private Map<String, Date> lastUsed;

	public Word() {}

	public Word(String student) {
		this.firstAppeared = new HashMap<>();
		this.firstAppeared.put(student, new Date());

		this.lastUsed = new HashMap<>();
		this.lastUsed.put(student, new Date());

		this.countUses = new HashMap<>();
		this.countUses.put(student, 1);
	}

	public Word(String student, String id) {
		this(student);
		this.id = id;
	}

	@Override
	public String toString() {
		return "Word{" +
				"id='" + id + '\'' +
				", pronunciation=" + pronunciation +
				", translation=" + translation +
				", countUses=" + countUses +
				", firstAppeared=" + firstAppeared +
				", lastUsed=" + lastUsed +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word = (Word) o;
		return Objects.equal(id, word.id) &&
				Objects.equal(pronunciation, word.pronunciation) &&
				Objects.equal(translation, word.translation) &&
				Objects.equal(countUses, word.countUses) &&
				Objects.equal(firstAppeared, word.firstAppeared) &&
				Objects.equal(lastUsed, word.lastUsed);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, pronunciation, translation, countUses, firstAppeared, lastUsed);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(Map<String, String> pronunciation) {
		this.pronunciation = pronunciation;
	}

	public Set<String> getTranslation() {
		return translation;
	}

	public void setTranslation(Set<String> translation) {
		this.translation = translation;
	}

	public Map<String, Integer> getCountUses() {
		return countUses;
	}

	public void setCountUses(Map<String, Integer> countUses) {
		this.countUses = countUses;
	}

	public Map<String, Date> getFirstAppeared() {
		return firstAppeared;
	}

	public void setFirstAppeared(Map<String, Date> firstAppeared) {
		this.firstAppeared = firstAppeared;
	}

	public Map<String, Date> getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Map<String, Date> lastUsed) {
		this.lastUsed = lastUsed;
	}
}

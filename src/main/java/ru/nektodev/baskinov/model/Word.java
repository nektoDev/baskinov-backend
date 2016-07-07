package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Objects.equal;

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

	public Word() {
	}

	public Word(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Word{" +
				"id='" + id + '\'' +
				", pronunciation='" + pronunciation + '\'' +
				", translation='" + translation + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word1 = (Word) o;
		return  equal(id, word1.id) &&
				equal(pronunciation, word1.pronunciation) &&
				equal(translation, word1.translation);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, pronunciation, translation);
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
}

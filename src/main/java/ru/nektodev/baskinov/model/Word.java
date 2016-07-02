package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Data model class
 *
 * @author Tsykin V.A ts.slawa@gmail.com
 */
public class Word implements Serializable {

	@Id
	private String word;
	private Map<String, String> pronunciation;
	private Set<String> translation;

	public Word() {
	}

	public Word(String word) {
		this.word = word;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word1 = (Word) o;
		return Objects.equal(word, word1.word) &&
				Objects.equal(pronunciation, word1.pronunciation) &&
				Objects.equal(translation, word1.translation);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(word, pronunciation, translation);
	}

	@Override
	public String toString() {
		return "Word{" +
				"word='" + word + '\'' +
				", pronunciation='" + pronunciation + '\'' +
				", translation='" + translation + '\'' +
				'}';
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
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

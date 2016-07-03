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
	private String word;
	private Map<String, String> pronunciation;
	private Set<String> translation;

	private boolean checked;
	private boolean isAnswerShow;

	public Word() {
	}

	public Word(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "Word{" +
				"word='" + word + '\'' +
				", pronunciation='" + pronunciation + '\'' +
				", translation='" + translation + '\'' +
				", checked=" + checked +
				", isAnswerShow=" + isAnswerShow +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word1 = (Word) o;
		return checked == word1.checked &&
				isAnswerShow == word1.isAnswerShow &&
				equal(word, word1.word) &&
				equal(pronunciation, word1.pronunciation) &&
				equal(translation, word1.translation);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(word, pronunciation, translation, checked, isAnswerShow);
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isAnswerShow() {
		return isAnswerShow;
	}

	public void setAnswerShow(boolean answerShow) {
		isAnswerShow = answerShow;
	}
}

package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

import static com.google.common.base.Objects.equal;

/**
 * Data model class
 *
 * @author Tsykin V.A ts.slawa@gmail.com
 */
public class Word implements Serializable {

	@Id
	private String id;
	private String word;
	private String pronunciation;
	private String translation;
	
	private boolean checked;
	private boolean isAnswerShow;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word1 = (Word) o;
		return checked == word1.checked &&
				isAnswerShow == word1.isAnswerShow &&
				equal(id, word1.id) &&
				equal(word, word1.word) &&
				equal(pronunciation, word1.pronunciation) &&
				equal(translation, word1.translation);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, word, pronunciation, translation, checked, isAnswerShow);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
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

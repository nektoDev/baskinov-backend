package ru.nektodev.baskinov.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class HomeworkWord implements Serializable {

	@Id
	private Integer id;
	private String question;
	private String answer;
	private String wordId;
	private Word word;

	public HomeworkWord() {
	}

	public HomeworkWord(String question, String answer, String wordId) {
		this.question = question;
		this.answer = answer;
		this.wordId = wordId;
	}

	@Override
	public String toString() {
		return "HomeworkWord{" +
				"id='" + id + '\'' +
				", question='" + question + '\'' +
				", answer='" + answer + '\'' +
				", wordId='" + wordId + '\'' +
				", word=" + word +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HomeworkWord that = (HomeworkWord) o;
		return Objects.equal(id, that.id) &&
				Objects.equal(question, that.question) &&
				Objects.equal(answer, that.answer) &&
				Objects.equal(wordId, that.wordId) &&
				Objects.equal(word, that.word);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, question, answer, wordId, word);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getWordId() {
		return wordId;
	}

	public void setWordId(String wordId) {
		this.wordId = wordId;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

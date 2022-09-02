package com.golden.raspberry.awards.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Award implements Serializable {

	@JsonProperty("id")
    Integer id;

	@JsonProperty("year")
    String year;

	@JsonProperty("title")
    String title;

	@JsonProperty("studios")
	String studios;

	@JsonProperty("producers")
	String producers;

	@JsonProperty("winner")
	String winner;

	public Award(Integer id, String year, String title, String studios, String producers, String winner) {
		this.id = id;
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	public Award() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"id='" + id + '\'' +
				", year='" + year + '\'' +
				", title='" + title + '\'' +
				", studios='" + studios + '\'' +
				", producers='" + producers + '\'' +
				", winner='" + winner + '\'' +
				'}';
	}
}
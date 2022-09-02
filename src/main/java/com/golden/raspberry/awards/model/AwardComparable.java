package com.golden.raspberry.awards.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AwardComparable implements Comparable<AwardComparable> {

	String producer;

    Integer interval;

    String previousWin;

	String followingWin;

	public AwardComparable(String producer, Integer interval, String previousWin, String followingWin) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public String getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(String previousWin) {
		this.previousWin = previousWin;
	}

	public String getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(String followingWin) {
		this.followingWin = followingWin;
	}

	@Override
	public int compareTo(AwardComparable o) {
		if(this.interval > o.interval){
			return 1;
		}
		else if(this.interval < o.interval){
			return -1;
		}
		return this.getProducer().compareToIgnoreCase(o.getProducer());
	}
}
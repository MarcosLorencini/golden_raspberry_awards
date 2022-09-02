package com.golden.raspberry.awards.dto;

import java.io.Serializable;

public class AwardMaxDto implements Serializable {

	String producer;

	Integer interval;

	String previousWin;

	String followingWin;



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


}
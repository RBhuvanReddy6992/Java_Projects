package com.example.dto;



	public class Vote {

	    private int voteId;       
	    private int voterId;
	    private String party;

	    // Constructors
	    public Vote() {}

	    public Vote(int voterId, String party) {
	        this.voterId = voterId;
	        this.party = party;
	    }

	    // Getters and Setters
	    public int getVoteId() {
	        return voteId;
	    }

	    public void setVoteId(int voteId) {
	        this.voteId = voteId;
	    }

	    public int getVoterId() {
	        return voterId;
	    }

	    public void setVoterId(int voterId) {
	        this.voterId = voterId;
	    }

	    public String getParty() {
	        return party;
	    }

	    public void setParty(String party) {
	        this.party = party;
	    }
	}




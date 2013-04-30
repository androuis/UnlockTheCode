package com.example.unlockthecode.models;

import java.util.ArrayList;
import java.util.List;

public class CodeTrial {
	
	public enum TrialState {
		OK,
		AOK,
		NOK
	};
	
	private List<String> trialEntries;
	private List<TrialState> trialStates;

	public CodeTrial() {
		trialEntries = new ArrayList<String>();
		trialStates = new ArrayList<CodeTrial.TrialState>();
	}

	public List<String> getTrialEntry() {
		return trialEntries;
	}
	
	public void setTrialEntry(List<String> trialEntry) {
		this.trialEntries = trialEntry;
	}
	
	public List<TrialState> getTrialState() {
		return trialStates;
	}
	
	public void setTrialState(List<TrialState> trialState) {
		this.trialStates = trialState;
	}
	
	public TrialState getState(String trialEntry) {
		int index = trialEntry.indexOf(trialEntry);
		return index != -1 ? 
				trialStates.get(index) :
				null;
	}
	
	public void addFullTrialEntry(String trialEntry, TrialState trialState) {
		trialEntries.add(trialEntry);
		trialStates.add(trialState);
	}
	
	public void addTrialEntry(String trialEntry) {
		trialEntries.add(trialEntry);
	}
	
	public void addTrialState(TrialState trialState) {
		trialStates.add(trialState);
	}

	public String getLastAddedTrialEntry() {
		return trialEntries.get(trialEntries.size() - 1);
	}

	public boolean isAllCorrect() {
		for (TrialState ts: trialStates) {
			if (!ts.equals(TrialState.OK)) {
				return false;
			}
		}
		return true;
	}
}

package com.schlimm.java7.concurrency.forkjoin.pricingengine;

import java.util.ArrayList;
import java.util.List;

import com.schlimm.java7.concurrency.forkjoin.dip.DecomposableInput;

public class Proposal implements DecomposableInput {

	private String vorname;
	private String nachname;
	private String hsn;
	private String tsn;
	private boolean comprehensive;
	private boolean partInsuranceCover;
	private boolean automotiveLiability;

	public Proposal(String vorname, String nachname, String hsn, String tsn, boolean comprehensive,
			boolean partInsuranceCover, boolean automotiveLiability) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.hsn = hsn;
		this.tsn = tsn;
		this.comprehensive = comprehensive;
		this.partInsuranceCover = partInsuranceCover;
		this.automotiveLiability = automotiveLiability;
	}

	public boolean multipleCovers() {
		return ((isAutomotiveLiability() ? 1 : 0) + (isComprehensive() ? 1 : 0) + (isPartInsuranceCover() ? 1 : 0)) > 1;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTsn() {
		return tsn;
	}

	public void setTsn(String tsn) {
		this.tsn = tsn;
	}

	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public boolean isComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(boolean comprehensive) {
		this.comprehensive = comprehensive;
	}

	public boolean isPartInsuranceCover() {
		return partInsuranceCover;
	}

	public void setPartInsuranceCover(boolean partInsuranceCover) {
		this.partInsuranceCover = partInsuranceCover;
	}

	public boolean isAutomotiveLiability() {
		return automotiveLiability;
	}

	public void setAutomotiveLiability(boolean automotiveLiability) {
		this.automotiveLiability = automotiveLiability;
	}

	@Override
	public boolean computeDirectly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DecomposableInput> disassemble() {
		List<DecomposableInput> decomposedProposal = new ArrayList<>();
		if (multipleCovers()) {
			if (isComprehensive())
				decomposedProposal.add(new Proposal(new String(vorname), new String(nachname), new String(hsn),
						new String(tsn), comprehensive, false, false));
			if (isPartInsuranceCover())
				decomposedProposal.add(new Proposal(new String(vorname), new String(nachname), new String(hsn),
						new String(tsn), false, partInsuranceCover, false));
			if (isAutomotiveLiability())
				decomposedProposal.add(new Proposal(new String(vorname), new String(nachname), new String(hsn),
						new String(tsn), false, false, automotiveLiability));
		} else {
			throw new IllegalStateException("Cannot disassemble proposal!");
		}
		return decomposedProposal;
	}

}
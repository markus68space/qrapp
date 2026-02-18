package org.me.qrapp.model;

/*
BEGIN:VCARD
VERSION:3.0
FN:John Doe
TEL:+33123456789
EMAIL:john@example.com
END:VCARD
*/

public class Vcard {
	private String FN;
	private String TEL;
	private String EMAIL;
	
	@Override
	public String toString() {
		String returnString;
		returnString = 
				"BEGIN:VCARD" 			+ System.lineSeparator() +
				"VERSION:3.0" 			+ System.lineSeparator() +
				"FN:" + getFN() 		+ System.lineSeparator() +
				"TEL:" + getTEL() 		+ System.lineSeparator() +
				"EMAIL:" + getEMAIL() 	+ System.lineSeparator() +
				"END:VCARD";
		return returnString;
	}
	
	/* getters and setters */
	
	public String getFN() {
		return FN;
	}
	public void setFN(String fN) {
		FN = fN;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

}

package server;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pytanie implements Serializable {
	private int ID;
	private String Pytanie;
	private String ODP;
	private String A;
	private String B;
	private String C;
	private String D;
	private int ODPDOBRE;
	private int ODPZ£E;
	private String Wyjaœnienie;

	public Pytanie(int iD, String pytanie, String oDP, String a, String b, String c, String d, int oDPDOBRE, int oDPZ£E,
			String wyjaœnienie) {
		super();
		ID = iD;
		Pytanie = pytanie;
		ODP = oDP;
		A = a;
		B = b;
		C = c;
		D = d;
		ODPDOBRE = oDPDOBRE;
		ODPZ£E = oDPZ£E;
		Wyjaœnienie = wyjaœnienie;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPytanie() {
		return Pytanie;
	}

	public void setPytanie(String pytanie) {
		Pytanie = pytanie;
	}

	public String getODP() {
		return ODP;
	}

	public void setODP(String oDP) {
		ODP = oDP;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public String getD() {
		return D;
	}

	public void setD(String d) {
		D = d;
	}

	public int getODPDOBRE() {
		return ODPDOBRE;
	}

	public void setODPDOBRE(int oDPDOBRE) {
		ODPDOBRE = oDPDOBRE;
	}

	public int getODPZ£E() {
		return ODPZ£E;
	}

	public void setODPZ£E(int oDPZ£E) {
		ODPZ£E = oDPZ£E;
	}

	public String getWyjaœnienie() {
		return Wyjaœnienie;
	}

	public void setWyjaœnienie(String wyjaœnienie) {
		Wyjaœnienie = wyjaœnienie;
	}

}
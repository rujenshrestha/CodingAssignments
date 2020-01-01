package projects.softwareengineering.testcasegenerator;

import java.util.ArrayList;

public class Variable {

	private String variable="";
	private ArrayList<Integer> useList = new ArrayList<Integer>();
	private ArrayList<Integer> defList = new ArrayList<Integer>();
	private String type="";
	private boolean exist;
	
	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
	public ArrayList<Integer> getUseList() {
		return useList;
	}

	public void addUseList(int use) {
		this.useList.add(use);
	}

	public ArrayList<Integer> getDefList() {
		return defList;
	}

	public void addDefList(int def) {
		this.defList.add(def);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

}

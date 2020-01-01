package projects.softwareengineering.testcasegenerator;

public class DefinitionUsageSetter {
	
	public void setPrimitiveDataTypeDefUse(Variable var, String stmt, int stmtNo){
		 if(stmt.contains("[=*"+var.getVariable()+"]")){
			 var.addUseList(stmtNo);
			 if(stmt.contains("["+var.getVariable()+"*=]")){
				 var.addDefList(stmtNo);
			 }
		 }else if(stmt.contains("[(*"+var.getVariable()+"*)]")){
			 var.addUseList(stmtNo);
		 }else{
			 var.addDefList(stmtNo);
		 }
	}
	
	public void setStringDataTypeDefUse(Variable var, String stmt){
		stmt = stmt.replaceAll(" +", "");
		if(stmt.contains("["+var.getVariable()+".replace*||.concat(*))]")){
			//define
		}else{
			//use
		}
	
	}
	public void typeDoubleChecker(Variable var, String stmt){
	
	}
	public void typeByteChecker(Variable var, String stmt){
	
	}
	public void typeIntegerChecker(Variable var, String stmt){
	
	}
	public void typeListChecker(Variable var, String stmt){

		if(stmt.contains(var.getVariable()+".add* ||.clear ||.remove* ||.replace* ||.set* ||.sort* ||.trimToSize*")){
			
		}
	
	}
	public void typeMapChecker(Variable var, String stmt){
	
	}
	public void typeSetChecker(Variable var, String stmt){
	
	}
	public void typeVectorChecker(Variable var, String stmt){
		
	}
}

package projects.softwareengineering.testcasegenerator;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TestCaseGenerator {
	
	private static String sourceCode="";
	private static String[] conditions={"if","while","for","switch","&&","||"};
	private static String[] dataTypes={"int","float","char","String","boolean","short","long","double","byte",
										"Integer","List","Map","Set","Vector","int[]","float[]","char[]"};
	private static ArrayList<String> variables = new ArrayList<String>();
	
	public static void main(String[] args){
		TestCaseGenerator test = new TestCaseGenerator();
	
		try {
			test.setSourceCode();
			int cyclomaticComplexity = test.calculateCyclomaticComplexity();
			//test.generateIndependentPath(cyclomaticComplexity);
			test.generateDefUsePath();
			
		} catch (IOException e) {
		}
	}
	
	
	public int calculateCyclomaticComplexity(){
		int cyclomaticComplexity=1;		
		String[] sourceCodeTokens = sourceCode.split("[ (){};]");
		for(String token: sourceCodeTokens){
			System.out.println("Tokens:"+token);
			for(int i=0;i<conditions.length;i++){
				if(token.equalsIgnoreCase(conditions[i])){
					cyclomaticComplexity++;
				}
			}
		}
		System.out.println("Complexity:"+cyclomaticComplexity);
		return cyclomaticComplexity;
	}
	
	
	public void generateIndependentPath(int numberOfIndependentPaths){
		
	}
	
	public void generateDefUsePath(){
		
		HashMap<Integer,String> stmtMap = setSourceCodeIntoMap();

		for(int i=0;i<stmtMap.size();i++){
			String[] tokens = stmtMap.get(i).split("[ =+-/%&&||(){};]");
			for(String token:tokens){
				for(int j=0;j<dataTypes.length;j++){
					if(token.equalsIgnoreCase(dataTypes[j])){
						setVariables(stmtMap.get(i),dataTypes[j]);	
						for(int k=0;k<variables.size();k++){
							System.out.print(variables.get(k));
						}
					}
				}
			}
		}
	}
	
	public void getDefUse(){
		HashMap<String,Integer> def = new HashMap<String,Integer>();
		HashMap<String,Integer> use = new HashMap<String,Integer>();

		HashMap<Integer,String> stmtMap = setSourceCodeIntoMap();
		for(int i=0;i<stmtMap.size();i++){
			
			
		}
		
		
	}
	
	
	public void setVariables(String stmt, String dataType){
		System.out.println("Define Stmt:"+stmt);
		int lastIndex=stmt.lastIndexOf(dataType)+dataType.length()+1;
		String temp = stmt.substring(lastIndex);
		String[] var = temp.split("[,)(=\\d+]");
		for(int i=0;i<var.length;i++){
			System.out.println("var:"+var[i]);
			for(int j=0;j<variables.size();j++){
				if(variables.get(j).equals(var[i])){
					variables.remove(j);
				}
			}
			variables.add(var[i]);
		}
	}
	
	
	public void setSourceCode() throws IOException{
		/*String fileURL = "D:\\Programming\\Eclipse_workbench\\CodingAssignments\\src\\"
					   + "projects\\softwareengineering\\testcasegenerator\\source_code.txt";*/
		String fileURL = "D:\\Programming\\Eclipse_workbench\\Practice\\src\\"
				   + "practice\\solo\\Triangle.java";
		sourceCode = new String(Files.readAllBytes(Paths.get(fileURL)));
		System.out.println("CODE:\n"+sourceCode);
		sourceCode = sourceCode.replaceAll("[\r\n]+", " ");
		sourceCode = sourceCode.replaceAll("\t","");
		sourceCode = sourceCode.replaceAll(" +"," ");
		sourceCode = sourceCode.replaceAll("; ",";");
		sourceCode = sourceCode.replaceAll("\\( +","\\(");
		sourceCode = sourceCode.replaceAll("\\) +","\\)");
		sourceCode = sourceCode.replaceAll("\\{ +","\\{");
		sourceCode = sourceCode.replaceAll("\\} +","\\}");
		System.out.println("CODE:\n"+sourceCode);
	}
	
	
	public HashMap<Integer,String> setSourceCodeIntoMap(){
		String[] stmts = sourceCode.split("[{;]");
		HashMap<Integer,String> stmtMap = new HashMap<Integer,String>();
		for(int i=0;i<stmts.length;i++){
			stmtMap.put(i,stmts[i]);
		}
		for(int i=0;i<stmtMap.size();i++){
			System.out.println(i+".STMT:"+stmtMap.get(i));
		}
		return stmtMap;
	}
	
}

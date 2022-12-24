package KDD.testRunner;

import KDD.utils.ReusableFunction;

public class ExecuteTestRunner {

	public static void main(String[] args) throws Exception{

		ReusableFunction func=new ReusableFunction();
		String[][] keywordsData=func.fetchDataFromExcel();
		
		int rownum=keywordsData.length;
		
		int colnum=keywordsData[0].length;
		
		String TC_Name=keywordsData[0][0];
		
		String TC_Step_No=keywordsData[0][1];
		String TC_Step_Name=keywordsData[0][2];
		String TC_Function=keywordsData[0][3];
		String TC_Locaion=keywordsData[0][4];
		String TC_LV=keywordsData[0][5];
		String TC_Param1=keywordsData[0][6];
		String TC_Execute=keywordsData[0][7];
		
		for(int counter = 1; counter < rownum; counter++){
			
			String function=keywordsData[counter][3];
			
			String locatorBy=keywordsData[counter][4];
			String locatorElement=func.Fetchprop(keywordsData[counter][5]);
			
			String content_param= keywordsData[counter][6];
			String execute=keywordsData[counter][7];
			
			
			System.out.println(function + "\t" + locatorBy + "\t" + locatorElement + "\t" + content_param);
			
			if(execute.equals("Y")){
				switch(function){
				case "launchAppl" :
					func.launchAppl();
					break;
					
				case "fillText" :
					func.fillText(locatorBy, locatorElement, content_param);
					break;
					
				case "click" :
					//func.driverClose();
				break;
				}
			}
		}
	}	
}	
			
			
			
			
		
		
		



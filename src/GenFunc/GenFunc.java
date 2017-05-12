package GenFunc;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GenFunc {

	public static String returnValue = "";

	public static String getTextFromElement(WebElement element)
	{
		try{
			if(element.isDisplayed()) 
				returnValue = element.getText();
		}catch(Exception e){
			System.out.println("getTextFromElement not found.");
    		throw(e);
		}		
		return returnValue;		
	}
	
	public static void clickElement(WebElement element)
	{
		try{
			if(element.isDisplayed()) 
				element.click();
		}catch(Exception e){
			System.out.println("clickElement not found.");
			throw(e);
		}
	}
	
	public static void enterText(WebElement element,String text)
	{
		try{
			if(element.isDisplayed()) 
				element.sendKeys(text);
		}catch(Exception e){
			System.out.println("enterText not found.");
			throw(e);
		}
	}
	
	public static void pressKeys(WebElement element,String keys)
	{
		switch (keys) {
			case "ENTER": if(element.isDisplayed())
								element.sendKeys(Keys.ENTER);
			default: System.out.println("Invalid Keys");
		}	
	}

}
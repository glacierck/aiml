package pt.mleiria.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
	private static final String BUNDLE_NAME = "pt.mleiria.utils.messages";
	
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	private Messages(){
	}
	
	public static String getMessage(final String key){
		try{
			return RESOURCE_BUNDLE.getString(key);
		}catch(MissingResourceException e){
			return "!" + key + "!";
		}
	}

}

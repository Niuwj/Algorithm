package crazy.chapter7;

import java.util.Locale;

public class LocaleList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale[] localelist = Locale.getAvailableLocales();
		
		for(int i = 0; i<localelist.length;i++){
			System.out.println(localelist[i].getDisplayCountry() + "=" + localelist[i].getCountry() + localelist[i].getDisplayLanguage() + "=" + localelist[i].getLanguage());
		}

	}

}

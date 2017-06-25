package converter.convertCurrencies;

import java.util.List;
import java.util.Scanner;

public class UIManager {
	private static Scanner s;
	private static CurrencyManager cm;
	private static String Currency1="" ;
	private static String Currency2="" ;
	private static int displaySettings=5;

	/** 
	 * Eine Instanz der Klasse CurrencyManager wird erstellt.
	 * Abhängig von Eingaben die durch ein switch-statement verglichen werden, werden Ausgaben auf die Konsole ausgegeben.
	 */
	public static void main(String[] args) {
		cm= new CurrencyManager();
    	cm.downloadFilefromWeb();
    	 s= new Scanner(System.in);
    	
    	
  
    	 
    	String sTMP ="";
   
  
    	while(!sTMP.equals("x"))
    	{
    	DisplayInfo();
    	sTMP=s.nextLine();
    	switch (sTMP) {
    	case "0":
			
		   
		   	String tmpInput="";
	    	while(!tmpInput.equals("x"))
	    	{
	    		System.out.println("Einstellungen");
			   	System.out.println("Drücken Sie '0' um die Umrechnungsgebühr zu ändern");
			 	System.out.println("-> Aktuell beträgt die Umrechnungsgebühr "+cm.getFee()+"%");
			 	System.out.println("Drücken Sie '1' zum Ändern der Anzahl der Anzuzeigenden Währungen");
			 	System.out.println("-> Aktuell beträgt die Anzeigeeinstellung "+displaySettings+" Währung(en)");
			   	System.out.println("Bitte wähle eine Möglichkeit oder drücken sie 'x' zum verlassen: ");
	    	tmpInput=s.nextLine();
	    	boolean settingsChanged=false;
	    	switch (tmpInput) {
	    	case "0":
	    	
		 
		   	do {
		   		System.out.println("Wählen Sie die Umrechnungsgebühr in Prozent:");
		   		String sInput=s.nextLine();
		   		try {
		   			
		   			float ammountOfFee=Float.parseFloat(sInput);
		   			cm.setFee(ammountOfFee);
		   			System.out.println("Sie haben die Einstellung erfolgreich geändert");
		   			settingsChanged=true;
				} catch (Exception e) {
					System.out.println("Bitte machen Sie gültige Eingaben");
				}
			   
			} while (!settingsChanged);
		   	break;
		   	
	    	case "1":
		   	 settingsChanged=false;
		   	do {
		   		System.out.println("Wählen Sie wie viele Währungen angezeigt werden sollen:");
		   		String sInput=s.nextLine();
		   		try {
		   			displaySettings=Integer.parseInt(sInput);
		   			System.out.println("Sie haben die Einstellung erfolgreich geändert");
		   			settingsChanged=true;
				} catch (Exception e) {
					System.out.println("Bitte machen Sie gültige Eingaben");
				}
			   
			} while (!settingsChanged);
	    	case "x":
	    		break;
	    	case "X":
	    		break;
	    	default:
	    		System.out.println("Bitte machen Sie gültige Eingaben");
				break;
	    	}
	    	}
			break;
		case "1":
			
		   	System.out.println("Wählen Sie die zu kaufende Währung");
		    Currency1 =SelectCurrency(displaySettings);
		  
			break;

		case "2":
			
			System.out.println("Wählen Sie die zu verkaufende Währung");
		    Currency2 =SelectCurrency(displaySettings);
		  
			break;
			
		case "3":
			 if(Currency1.equals("")||Currency2.equals(""))
			    {
				 System.out.println("Bitte wählen Sie zuerst die zu berechnenden Währungen aus: ");
				 break;
			    }
			float input=-1f;
			while(input<=0)
			{
			System.out.println("Bitte wählen Sie den einzutauschenden Betrag aus: ");
	
			try {
				input= Float.parseFloat(s.nextLine());
			} catch (Exception e) {
				System.out.println("Ihre Eingabe muss eine positive Zahl sein! " + e.getMessage());
			}
			}
			System.out.println("Sie tauschen "+input+" "+Currency2+" gegen "+cm.convertCurrency(Currency2, Currency1, input)+" "+Currency1+". Die Tauschgebühr beträgt "+ cm.getFee()+" Prozent");
			break;
		
			
		case "x":
    		break;
    		
    	case "X":
    		break;

			
		default:
			System.out.println("Bitte machen Sie gültige Eingaben");
			break;
		}

    
       	
    	}
       	
       	
	}
	
	/**
	 * Die Methode "DisplayInfo" zeigt das Hauptmenü für den Benutzer an
	 */
	private static void DisplayInfo() {
		System.out.println("Drücken Sie '0' um die Einstellungen aufzurufen");
		System.out.println("Drücken Sie '1' um die zu kaufende Währung zu ändern");
		if(Currency1.equals(""))
		{
			System.out.println("-> Noch keine Währung ausgewählt");
		}
		else
		{
			System.out.println("-> "+Currency1);
		}
		System.out.println("Drücken Sie '2' um die zu verkaufende Währung zu ändern");
		if(Currency2.equals(""))
		{
			System.out.println("-> Noch keine Währung ausgewählt");
		}
		else
		{
			System.out.println("-> "+Currency2);
		}
		System.out.println("Drücken Sie '3' um die Währungen umzurechnen");
		System.out.println("Drücken Sie 'x' um das Programm zu beenden");
	}

	/**
	 * Abhängig von der Eingabe des Nutzers werden aus der Liste der Währungen alle herausgesucht, die der Suche entprechen.
	 * Bei alleinigem Druck der Enter-Taste werden alle Währungen aus der Liste ausgegeben
	 * @return alle Währungen, die mit der Suche übereinstimmem werden zurückgegeben.
	 */
	private static List<String> SearchCurrenciesToDisplay()
	{
		List<String> sCurrValues;
		do {
    		System.out.println("Suchen Sie nach einer Währung, oder drücken Sie Enter um sich alle Währungen anzeigen zu lassen: ");
    		String sTMP = s.nextLine();
	
    	switch (sTMP) {
		case "":
			 sCurrValues= cm.getNameAndValue();
			break;

		default:
			 sCurrValues= cm.getNameAndValue(sTMP);
			break;
		}
    	if(sCurrValues.size()==0)
    	{
    		System.out.println("Es wurde keine Währung gefunden!");
        	
    	}
    	} while (sCurrValues.size()==0);
		return sCurrValues;
	}
	
	/**
	 * Auswahl der Währung aus der Liste der Suchergebnisse.
	 * @param ammountOfCurrenciesToDisplay: Anzahl der Angezeigten Währungen (Standardwert: 5).
	 * @return Ausgewählte Währung wird zurückgegeben. Bei ungültiger Eingabe wird eine leere String zurückgegeben und die Suche abgebrochen.
	 */
	private static String SelectCurrency(int ammountOfCurrenciesToDisplay)
	{
		boolean needSearch=true;
		while(needSearch)
		{
			List<String> stringToDisplay=SearchCurrenciesToDisplay();
		needSearch=false;
		int iNext=ammountOfCurrenciesToDisplay;
    	int iTMP=-1;
    	String currencyTMP="";
    	
       	while(currencyTMP.equals(""))
    	{
    	iTMP++;
    		for(int i =0;i<iNext;i++)
    		{
    			if(i+iTMP*(iNext-1)<stringToDisplay.size())
    			{
    			System.out.println(Integer.toString(i)+" "+ stringToDisplay.get(i+iTMP*(iNext-1)));
    			}
    			
    		}
    		
    		System.out.println("Bitte wählen Sie eine Währung oder drücken Sie "+Integer.toString(iNext)+" um weitere Währungen anzuzeigen bzw "+Integer.toString(iNext+1)+" um nach einer anderen Währung zu suchen:");
        	int tmp=-1;
    		while(tmp>iNext||tmp<0)
    		{
    			 
    		try {
    			String st=s.nextLine();
        		tmp=Integer.parseInt(st);
        
			} catch (Exception e) {
				System.out.println("Bitte machen Sie gültige Eingaben!");
			}
    		if(tmp+iTMP*(iNext-1)>stringToDisplay.size())
    		{
    			System.out.println("Bitte machen Sie gültige Eingaben!");
    		}
    		else if(tmp<iNext)
    		{
    			currencyTMP=stringToDisplay.get(tmp+iTMP*(iNext-1)).split(":")[0];
    			iNext=tmp;
    			System.out.println(currencyTMP);
    		}
    		if(tmp==iNext+1)
    		{
				System.out.println("Die Suche wurde abgebrochen");
    			needSearch=true;
    			return "";
    		}
    		}

    		
    	}
        return currencyTMP;
		}
		return "";
	}

}


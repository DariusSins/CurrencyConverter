package converter.convertCurrencies;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Darius, Sandro
 *
 *	Die CurrencyManager Klasse enthält eine Liste mit allen Währungen, sowie eine Variable Gebühr(fee).
 */
public class CurrencyManager {
	private List<Currency> liCurrencies;
	private float fee;
	
	/**
	 * @return Gebühr wird zurückgegeben.
	 */
	public float getFee() {
		return fee;
	}
	/**
	 * @param fee Die Gebühr wird festgelegt.
	 */
	public void setFee(float fee) {
		this.fee = fee;
	}
	/**
	 * Im try-block wird die Textdatei mit allen Währungen heruntergeladen und gespeichert.
	 * Im catch-block werden Fehler abgefangen und eine Fehlermeldung ausgegeben.
	 */
	public void downloadFilefromWeb() 
	{
		try {
		URL website = new URL("https://dl.dropboxusercontent.com/s/s00hooh02wrm2q5/Currencies.txt");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("currencys.conv");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		} 
		catch (Exception e) {
			System.out.println("Ein Fehler ist aufgetreten: "+e.getMessage());
		}
	}
	/**
	 * Der Konstruktor übergibt der Klasse CurrencyManager eine Liste mit allen Währungen.
	 */
	public CurrencyManager()
	{
		liCurrencies= new ArrayList<Currency>();
		fee=0;
		init();
	}
	/**
	 * Der Konstruktor übergibt der Klasse CurrencyManager eine Liste mit allen Währungen und der Gebühr.
	 * @param fee: Die von Benutzer eingegebene Umrechnungsgebühr wird übergeben.
	 */
	public CurrencyManager(float fee)
	{
		liCurrencies= new ArrayList<Currency>();
		this.fee=fee;
		init();
	}

	
	/**
	 * Die Methode "refreshCurrencies" ruft die Methode "init" auf 
	 * 
	 * @param fee
	 */
//	public void refreshCurrencies(float fee)
//	{
//		this.fee=fee;
//		init();
//	}

//	public void refreshCurrencies()
//	{
//		init();
//	}
	/**
	 * Die Methode "getNameAndValue" gibt den Namen und den Wert aller Währungen zurück
	 * @return Die Namen und Werte werden als Liste zurückgegeben
	 */
	public List<String> getNameAndValue()
	{
		List<String> currencyTMP = new ArrayList<String>();
		for (Currency currency : liCurrencies) {
			currencyTMP.add(currency.getCurrencyName()+": "+String.valueOf(currency.getSDRValue()));
		}
		return currencyTMP;
	}
	
	/**
	 * Die Methode "getNameAndValue" gibt den Namen und den Wert der Währungen zurück, die den übergebenen String enthalten
	 * @param sContainingString Hier wird die Suche des Benutzers übergeben die zu überprüfen ist
	 * @return Eine Liste mit allen Währungen, die die Suchphrase enthalten wird zurückgegeben
	 */
	public List<String> getNameAndValue(String sContainingString)
	{
		sContainingString=sContainingString.toLowerCase();
		List<String> currencyTMP = new ArrayList<String>();
		for (Currency currency : liCurrencies) {
			if(currency.getCurrencyName().toLowerCase().contains(sContainingString))
			currencyTMP.add(currency.getCurrencyName()+": "+String.valueOf(currency.getSDRValue()));
		}
		return currencyTMP;
	}
	
	/**
	 * Die Methode "convertCurrency" berechnet aus den übergebenen zwei Währungen und einem Betrag den Endbetrag aus,
	 * den der Benutzer nach der umwandlung erhält.
	 * @param from: Name der Währung, die Verkauft werden soll.
	 * @param to: Name der Wärhung, die gekauft werden soll.
	 * @param amount: Betrag, der umgewandelt werden soll.
	 * @return bei nicht valider Eingabe wird -1 zurückgegeben, ansonsten der Betrag nach der Umwandlung.
	 */
	public float convertCurrency(String from, String to, float amount)
	{
		 float flFrom=0;
		 float flTo=0;
		for (Currency temp : liCurrencies) {
			
		
			if(temp.getCurrencyName().equals(from))
			{
				flFrom=temp.getSDRValue();
			}
			if(temp.getCurrencyName().equals(to))
			{
				flTo=temp.getSDRValue();
			}
		}
		if(flFrom!=0&&flTo!=0)
		{
			return (amount/flFrom)*flTo*(1.0f-(fee/100.0f));
		}
		return -1;
	}
	
	
	/**
	 * Die Methode "init" ließt die Währungen Zeile für Zeile aus der Textdatei aus und schreibt sie in
	 * die Liste "liCurrencies"
	 */
	private void init() 
	{
		
		this.liCurrencies.clear();

		try
		{
		BufferedReader br = new BufferedReader(new FileReader("currencys.conv"));
		try {
		
		    String line = br.readLine();

		    while (line != null) {
		    String[] s = line.split(":");
		    liCurrencies.add(new Currency(s[0], Float.valueOf(s[1])));
		    line = br.readLine();
		    }
		  
		}
		catch (Exception ex)
		{
			
		}
		finally {
		    br.close();
		}
		}
		catch (Exception ex)
		{
			
		}
		finally
		{
			
		}
		}
	}

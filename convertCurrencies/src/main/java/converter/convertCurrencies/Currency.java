package converter.convertCurrencies;

/**
 * @author Darius, Sandro
 *
 * Die Currency Klasse ermöglicht die Speicherung der verschiedenen Währungen.
 */
public class Currency {
	 
	private float sdrValue;
	
	private String currencyName;
 
	/**
	 * Konstruktor der  Klasse Currency:
	 * Der Währung wird ein Name, sowie ein Wert übergeben.
	 * 
	 * @param name Name der Währung wird übergeben.
	 * @param value Wert der Währung wird übergeben.
	 */
	public Currency(String name, float value)
	{
		sdrValue= value;
		currencyName=name;
	}
	
	/**
	 * @return SDR-Wert wird zurückgegeben.
	 */
	public float getSDRValue()
	{
		return sdrValue;
	}
	
	/**
	 * @return Name der Währung wird zurückgegeben.
	 */
	public String getCurrencyName()
	{
		return currencyName;
	}
}

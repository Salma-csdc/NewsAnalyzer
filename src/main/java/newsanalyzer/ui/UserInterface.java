package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.security.DomainCombiner;

import newsanalyzer.ctrl.AnalyserExceptions;
import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import javax.xml.transform.Source;


public class UserInterface 
{
	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		System.out.println("ABC");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("technology")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setDomains("com")									// geht das so?
				//.setSourceCountry(Country.at)
				.setSourceCategory(Category.technology)
				.createNewsApi();
		try{
			ctrl.process(newsApi);									// damit ichs in der Klasse Controller übernehmen/ aufrufen kann
		}
		catch(AnalyserExceptions e) {
			System.out.println(e.getMessage());
		}
		catch(MalformedURLException e){
			System.out.println("URL falsch");
		}
		catch(IOException e){
			System.out.println("IOException- unbekannter Fehler");
		}
	}

	public void getDataFromCtrl2(){
		System.out.println("DEF");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.createNewsApi();

		try{
			ctrl.process(newsApi);									// damit ichs in der Klasse Controller übernehmen/ aufrufen kann
		}
		catch(AnalyserExceptions e) {
			System.out.println(e.getMessage());
		}
		catch(MalformedURLException e){
			System.out.println("URL falsch");
		}
		catch(IOException e){
			System.out.println("IOException- unbekannter Fehler");
		}
	}

	public void getDataFromCtrl3(){
		System.out.println("3");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("sports")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.ch)
				.setSourceCategory(Category.sports)
				.createNewsApi();

		try{
			ctrl.process(newsApi);									// damit ichs in der Klasse Controller übernehmen/ aufrufen kann
		}
		catch(AnalyserExceptions e) {
			System.out.println(e.getMessage());
		}
		catch(MalformedURLException e){
			System.out.println("URL falsch");
		}
		catch(IOException e){
			System.out.println("IOException- unbekannter Fehler");
		}
	}
	
	public void getDataForCustomInput() {
		System.out.println("User Imput:");
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice ABC", this::getDataFromCtrl1);
		menu.insert("b", "Choice DEF", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}





    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}

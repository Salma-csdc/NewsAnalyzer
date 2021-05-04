package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;

import newsanalyzer.ctrl.AnalyserExceptions;
import newsanalyzer.ctrl.BuildUrlException;
import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsreader.downloader.Downloader;
import newsreader.downloader.SequentialDownloader;
import newsreader.downloader.UrlException;


public class UserInterface 
{
	private Controller ctrl = new Controller();
	private Controller download = new Controller();
	/*
	private Downloader download = new Downloader() {

		@Override
		public int process(List<String> urls) {
			return 0;
		}
	};

	 */

	public void getDataFromCtrl1(){
		System.out.println("ABC");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("technology")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setDomains("com")
				//.setSourceCountry(Country.at)
				.setSourceCategory(Category.technology)
				.createNewsApi();
		try{
			ctrl.process(newsApi);									// damit ichs in der Klasse Controller übernehmen/ aufrufen kann
		}
		catch(AnalyserExceptions e) {
			System.out.println(e.getMessage());
		}
		catch(MalformedURLException | BuildUrlException e){
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
		catch(MalformedURLException | BuildUrlException e){
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
		catch(MalformedURLException | BuildUrlException e){
			System.out.println("URL falsch");
		}
		catch(IOException e){
			System.out.println("IOException- unbekannter Fehler");
		}
	}


	public void getDataFromCtrl4(){

			NewsApi newsApi = new NewsApiBuilder()
					.setApiKey(Controller.APIKEY)
					.setQ("business")
					.setEndPoint(Endpoint.TOP_HEADLINES)
					.setSourceCategory(Category.sports)
					.createNewsApi();

		try{
			ctrl.downloadUrlToList(newsApi);
		}
		catch(AnalyserExceptions | UrlException e) {
			System.out.println(e.getMessage());
		}
		catch(MalformedURLException | BuildUrlException e){
			System.out.println("URL falsch");
		}
		catch(IOException e){
			System.out.println("IOException- unbekannter Fehler");
		}
	}

	public void getDataForCustomInput() {
		System.out.println("User Imput");
		String read = readLine();
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice ABC", this::getDataFromCtrl1);
		menu.insert("b", "Choice DEF", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput",this::getDataForCustomInput);
		menu.insert("w", "Choice download", this::getDataFromCtrl4);
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

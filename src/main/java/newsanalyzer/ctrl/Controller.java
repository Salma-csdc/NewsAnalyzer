package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsreader.downloader.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "fe84dd9d4e7a42db921bc8de0e17f0c2";

	//public void process(String query, Endpoint endpoint, Category category, Language language, Country country, SortBy sortBy){};			// Daniel's Lösung
	public void downloadUrlToList(NewsApi news) throws IOException, AnalyserExceptions, BuildUrlException, UrlException{
		NewsReponse newsReponse = news.getNews();

		if(newsReponse != null){

			List<Article> articles = newsReponse.getArticles();
			articles.forEach(article -> System.out.println(article.toString()));

			var urls = articles
					.stream()
					.map(Article::getUrl)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			SequentialDownloader sequentialDownloader = new SequentialDownloader();
			ParallelDownloader parallelDownloader = new ParallelDownloader();
			sequentialDownloader.process(urls);
			parallelDownloader.process(urls);
		}
	}

	public void process(NewsApi newsApi) throws IOException, AnalyserExceptions, BuildUrlException {

		System.out.println("Start process");

		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));

			System.out.println("Analysis: ");

			System.out.println("Articles Number:");
			System.out.println(getArticlesNumber(articles));

			System.out.println("Name of Provider with most articles:");
			System.out.println(getNameOfProviderWithMostArticles(articles));

			System.out.println("Shortest Author Name first :");
			getShortestAuthorName(articles).forEach(article -> System.out.println(article.getAuthor()));

			System.out.println("Articles length sorted by Alphabet:");
			getArticlesLengthbyAlphabet(articles).forEach(article -> System.out.println(article.getTitle()));
		}


		System.out.println("End process");
		}

		public long getArticlesNumber(List<Article> data){
			return data.stream().count();
		}

		public String getNameOfProviderWithMostArticles(List<Article> data){
			return data
					.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
					.entrySet()
					.stream()
					.max(Comparator.comparing(Map.Entry::getValue)).get().getKey().getSource().getName();
		}

		public List<Article> getShortestAuthorName(List<Article> data){
			return data
					.stream()
					.filter(article -> Objects.nonNull(article.getAuthor()))
					.sorted(Comparator.comparing(Article::getAuthor))
					.collect(Collectors.toList());
					//.toString();
		}

		public List<Article> getArticlesLengthbyAlphabet(List<Article> data){
			return data
					.stream()
					.sorted(Comparator.comparingInt(Article -> Article.getTitle().length()))
					.collect(Collectors.toList());
		}

		public List <String> saveurl(List <Article> url_of_article) {
			return url_of_article
					.stream()
					.map(Article::getUrl)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
		}

	public Object getData(NewsApi newsApi) {
		
		return null;
	}

}

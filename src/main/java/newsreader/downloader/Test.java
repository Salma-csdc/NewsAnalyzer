package newsreader.downloader;

import java.util.concurrent.Callable;

public class Test implements Callable {
    private Downloader download;
    private String url;

    @Override
    public String call() throws Exception{
        return download.saveUrl2File(url);
    }
    public Test (Downloader download, String url){
        this.download = download;
        this.url = url;
    }
}

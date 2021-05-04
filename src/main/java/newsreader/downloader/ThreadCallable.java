package newsreader.downloader;

import java.util.concurrent.Callable;

public class ThreadCallable implements Callable {
    private Downloader download;
    private String url;

    @Override
    public String call() throws Exception{
        return download.saveUrl2File(url);
    }
    public ThreadCallable(Downloader download, String url){
        this.download = download;
        this.url = url;
    }
}

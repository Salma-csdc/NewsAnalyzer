package newsreader.downloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) throws IOException, UrlException {
        long timer = System.currentTimeMillis();
        int count = 0;
        for (String url : urls) {
            String fileName = saveUrl2File(url);
            if(fileName != null)
                count++;
        }
        System.out.println("Sequential-Timer: " +(System.currentTimeMillis() - timer));
        return count;
    }
}

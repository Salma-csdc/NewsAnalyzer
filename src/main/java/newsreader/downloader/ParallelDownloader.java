package newsreader.downloader;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {
    @Override
    public int process(List<String> urls) {
        long timer = System.currentTimeMillis();
        int count = 0;
        List <Future<String>> futures = new ArrayList<>();

        for(String url : urls){
            if(url == null){
                continue;
            }
            ThreadCallable urlT = new ThreadCallable(this, url);
            ExecutorService executor = Executors.newCachedThreadPool();
            futures.add(executor.submit(urlT));
            //String fileName = saveUrl2File(url);
            //if(fileName != null)
            //    count++;
        }

        for(Future<String> future : futures){
            try{
                String fileName = future.get();
                if(fileName != null)
                    count++;
            }catch (Exception e){
                e.printStackTrace();                                            // Exception
            }
        }
        System.out.println("Parallel-Timer: " +(System.currentTimeMillis() - timer));
        return count;
    }
}

package hello;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheRepo {
    PixelRepository pixelRepository;
    List<Pixel> cachePixel;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    public CacheRepo (PixelRepository pixelRepository) {
        this.pixelRepository = pixelRepository;
        cachePixel = pixelRepository.findAll();
    }

    public List<Pixel> findAll() {
        executor.submit(() -> {
            cachePixel = pixelRepository.findAll();
        });

        return cachePixel;
    }

    public void save(Pixel pixel) {
        executor.submit(() -> {
            pixelRepository.save(pixel);
        });

    }
}

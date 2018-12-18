package hello;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PixelRepository extends CrudRepository<Pixel, Long> {
    List<Pixel> findAll();
}

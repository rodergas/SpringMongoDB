package start.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.Dao.HotelRepository;
import start.Entity.Hotel;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public void insertHotel(Hotel hotel) {
        hotelRepository.insert(hotel);
    }

    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(String id) {
        hotelRepository.deleteById(id);
    }

    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).get();
    }

    public List<Hotel> findByPricePerNightLessThan(int maxPrice) {
        return hotelRepository.findByPricePerNightLessThan(maxPrice);
    }

    public List<Hotel> findByCity(String city) {
        return hotelRepository.findByCity(city);
    }
}

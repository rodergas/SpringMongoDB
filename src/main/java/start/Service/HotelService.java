package start.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.Dao.HotelRepository;
import start.Entity.Hotel;
import start.Entity.QHotel;

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
        Hotel hotel = null;
        if(hotelRepository.findById(id).isPresent()) hotel = hotelRepository.findById(id).get();
        return hotel;
    }

    public List<Hotel> findByPricePerNightLessThan(int maxPrice) {
        return hotelRepository.findByPricePerNightLessThan(maxPrice);
    }

    public List<Hotel> findByCity(String city) {
        return hotelRepository.findByCity(city);
    }

    public List<Hotel> findByCountry(String country) {
        //Create a query class
        QHotel qHotel = new QHotel("hotel");
        //using the query class we can create filters
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);
        //we can pass to the find all method
        return (List<Hotel>) hotelRepository.findAll(filterByCountry);
    }

    public List<Hotel> getRecommended(int maxPrice, int minRating) {

        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);
        return (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));
    }
}

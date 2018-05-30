package start.Controller;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import start.Entity.Hotel;
import start.Entity.QHotel;
import start.Service.HotelService;

import java.util.List;

@RestController

public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/hotels")
    public List<Hotel> getAll(@RequestParam(value = "maxPrice" , required = false, defaultValue= "-1" ) int maxPrice,
                              @RequestParam(value = "city" , required = false) String city,
                              @RequestParam(value = "country" , required = false) String country,
                              @RequestParam(value = "rating" , required = false, defaultValue = "-1") int rating
    ){
        if(maxPrice != -1 && rating != -1) return hotelService.getRecommended(maxPrice, rating);
        else if(maxPrice != -1) return hotelService.findByPricePerNightLessThan(maxPrice);
        else if(city != null) return hotelService.findByCity(city);
        else if(country != null) return hotelService.findByCountry(country);
        else return hotelService.getAllHotels();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hotels")
    public void insertHotel(@RequestBody Hotel hotel ){
        hotelService.insertHotel(hotel);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{id}")
    public void updateHotel(@RequestBody Hotel hotel, @PathVariable String id){
        hotelService.updateHotel(hotel);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{id}")
    public void delete(@PathVariable String id){
        hotelService.deleteHotel(id);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{id}")
    public Hotel getById(@PathVariable String id){
       return hotelService.getHotel(id);
    }

}

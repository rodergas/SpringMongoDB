package start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import start.Dao.HotelRepository;
import start.Entity.Address;
import start.Entity.Hotel;
import start.Entity.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void run(String... args) {
        Hotel marriot = new Hotel("Marriot",
                130,
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("John",7,false),
                        new Review("Anna", 8 , true)
                )
        );

        Hotel ibis = new Hotel("Ibis",
                90,
                new Address("Bucarest", "Romania"),
                Arrays.asList(
                        new Review("Teddy",9,false)
                )
        );

        Hotel nh = new Hotel("NH",
                200,
                new Address("Madrid", "Spain"),
                new ArrayList<>()
        );

        //drop all Hotels
        this.hotelRepository.deleteAll();

        //add out hotels to db
        List<Hotel> hotels = Arrays.asList(marriot,ibis,nh);
        this.hotelRepository.saveAll(hotels);
    }
}

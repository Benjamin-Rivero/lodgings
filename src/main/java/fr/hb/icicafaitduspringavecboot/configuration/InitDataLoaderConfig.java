package fr.hb.icicafaitduspringavecboot.configuration;

import com.github.javafaker.Faker;
import fr.hb.icicafaitduspringavecboot.entity.*;
import fr.hb.icicafaitduspringavecboot.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private static final int NB_BOOKING = 15;
    private static final int NB_REVIEW = 25;
    private final int NB_FAVORITE = 10;
    private final int NB_USER = 50;
    private final int NB_ROOM = 25;
    private final int NB_ADDRESS = 50;
    private final int NB_LODGING = 25;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;
    private final LodgingRepository lodgingRepository;
    private final FavoriteRepository favoriteRepository;
    private final BookingRepository bookingRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public void run(String... args) throws Exception {
//        createAddress();
        createUsers();
        createRooms();
        createLodgings();
        createFavorites();
        createBookings();
        createReviews();
    }

    private void createUsers(){
        Faker faker = new Faker(Locale.of("fr"));

        if(userRepository.count()>=50) return;

        for(int i = 0 ; i<NB_USER ; i++){
            User user = new User();
            user.setFirstName(faker.dog().name());
            user.setLastName(faker.dog().name());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setBirthDate(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));
            user.setVerified(faker.bool().bool());
            user.setCreatedAt(LocalDateTime.of(1900,12,12,12,12));
            user.setRole(faker.dog().breed());
            userRepository.save(user);
            Address address = createRandomAddress(faker);
            address.setUser(user);
            addressRepository.save(address);
            user.getAddresses().add(address);
            userRepository.save(user);
        }
        userRepository.flush();

    }

    private void createRooms(){
        Faker faker = new Faker(Locale.of("fr"));

        if(roomRepository.count() >= NB_ROOM) return;

        for (int i = 0; i<NB_ROOM;i++){
            Room room = new Room();
            room.setType("sî");
            room.setTranslationKey("Une translation key oui oui");
            roomRepository.save(room);
        }
        roomRepository.flush();
    }


//    private void createAddress(){
//        Faker faker = new Faker(Locale.of("fr"));
//
//        if(addressRepository.count() >= NB_ADDRESS) return;
//
//        for (int i = 0; i < NB_ADDRESS; i++) {
//            Address address = createRandomAddress(faker);
//            addressRepository.save(address);
//        }
//        addressRepository.flush();
//    }

    private void createLodgings(){
        Faker faker = new Faker(Locale.of("fr"));

        if(lodgingRepository.count() >= NB_LODGING) return;

        for (int i = 0; i < NB_LODGING; i++) {
            Lodging lodging = new Lodging();
            lodging.setName(faker.name().name());
            lodging.setCapacity((int)Math.ceil(Math.random()*8));
            lodging.setAccessible(faker.bool().bool());
            lodging.setNightPrice((int)Math.ceil(Math.random()*100));
            lodging.setDescription(faker.howIMetYourMother().highFive());
            lodging.setSlug(lodging.getField());
            lodgingRepository.save(lodging);
            Address address = createRandomAddress(faker);
            addressRepository.save(address);
            lodging.setAddress(address);
            lodgingRepository.save(lodging);
        }
        lodgingRepository.flush();
    }

    private Address createRandomAddress(Faker faker) {
        Address address = new Address();
        address.setStreet(faker.address().streetName());
        address.setNumber(faker.address().streetAddressNumber());
        address.setZipCode(faker.address().zipCode());
        address.setCity(faker.address().city());
        address.setCountry("France");
        address.setLongitude((float) ((Math.random() * (8.135000 - 2.315800)) + 2.315800));
        address.setLatitude((float) ((Math.random() * (51.052100 - 42.195800)) + 42.195800));
        address.setMore(faker.address().secondaryAddress());
        address.setBilling(faker.bool().bool());
        return address;
    }

    private void createFavorites(){

        if(favoriteRepository.count() >= NB_FAVORITE) return;

        for (int i = 0; i < NB_FAVORITE; i++) {
            Favorite favorite = new Favorite();
            User user = userRepository.getOneRandom();
            Lodging lodging = lodgingRepository.getOneRandom();
            favorite.setId(new FavoriteId(user.getId(),lodging.getId()));
            favorite.setCreatedAt(LocalDateTime.now());
            favorite.setUser(user);
            favorite.setLodging(lodging);
            favoriteRepository.save(favorite);
        }
        favoriteRepository.flush();
    }

    private void createBookings(){
        Faker faker = new Faker(Locale.of("fr"));

        if(bookingRepository.count() >= NB_BOOKING) return;

        for (int i = 0; i < NB_BOOKING ; i++) {
            Booking booking = new Booking();
            booking.setCreatedAt(LocalDateTime.now());
            booking.setNumber(faker.number().digits(8));
            LocalDateTime started = LocalDateTime.ofInstant(faker.date().future(100, TimeUnit.DAYS).toInstant(),ZoneId.systemDefault());
            booking.setStartedAt(started);
            booking.setFinishedAt(started.plusDays((int)Math.ceil(Math.random()*14)));
            booking.setQuantity((int)Math.ceil(Math.random()*6));
            booking.setUser(userRepository.getOneRandom());
            booking.setLodging(lodgingRepository.getOneRandom());
            bookingRepository.save(booking);
        }
        bookingRepository.flush();
    }

    private void createReviews(){
        Faker faker = new Faker(Locale.of("fr"));

        if(reviewRepository.count() >= NB_REVIEW) return;

        for (int i = 0; i < NB_REVIEW; i++) {
            Review review = new Review();
            review.setCreatedAt(LocalDateTime.now());
            review.setContent(faker.howIMetYourMother().catchPhrase());
            review.setRating((float)Math.random()*5);
            review.setUser(userRepository.getOneRandom());
            review.setLodging(lodgingRepository.getOneRandom());
            reviewRepository.save(review);
        }
        reviewRepository.flush();
    }

}

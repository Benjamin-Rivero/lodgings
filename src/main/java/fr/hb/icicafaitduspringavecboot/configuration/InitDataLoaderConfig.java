package fr.hb.icicafaitduspringavecboot.configuration;

import com.github.javafaker.Faker;
import fr.hb.icicafaitduspringavecboot.dto.*;
import fr.hb.icicafaitduspringavecboot.entity.*;
import fr.hb.icicafaitduspringavecboot.repository.*;
import fr.hb.icicafaitduspringavecboot.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private static final int NB_BOOKING = 50;
    private static final int NB_REVIEW = 100;
    private final int NB_FAVORITE = 25;
    private final int NB_USER = 250;
//    private final int NB_ROOM = 25;
//    private final int NB_ADDRESS = 50;
    private final int NB_LODGING = 100;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;
    private final LodgingRepository lodgingRepository;
    private final FavoriteRepository favoriteRepository;
    private final BookingRepository bookingRepository;
    private final ReviewRepository reviewRepository;
    private final RoomService roomService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AddressService addressService;
    private final LodgingService lodgingService;
    private final BookingService bookingService;


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
            UserCreationDto userCreationDto = new UserCreationDto();
            userCreationDto.setFirstName(faker.name().firstName());
            userCreationDto.setLastName(faker.name().lastName());
            userCreationDto.setEmail(String.format("%s.%s@gmail.com",userCreationDto.getFirstName(),userCreationDto.getLastName()));
            userCreationDto.setPassword(passwordEncoder.encode("12345"));
            userCreationDto.setBirthDate(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));
            User user = userService.create(userCreationDto);
            Address address = addressService.create(createRandomAddress(faker));
            user.getAddresses().add(address);
            userRepository.save(user);
        }
        userRepository.flush();
    }

    private void createRooms(){
        Faker faker = new Faker(Locale.of("fr"));
        List<String> roomTypes = List.of("Kitchen","Bathroom","Master Bedroom","Living Room","Attic","Basement","Garage","Jacuzzi","Pool","1 Bed Bedroom","2 Bed Bedroom");

        if(roomRepository.count() != roomTypes.size()) {
            for (String roomType : roomTypes) {
                RoomDto roomDto = new RoomDto();
                roomDto.setType(roomType);
                roomService.create(roomDto);
            }
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
        Random random = new Random();
        Faker faker = new Faker(Locale.of("fr"));

        if(lodgingRepository.count() >= NB_LODGING) return;

        for (int i = 0; i < NB_LODGING; i++) {
            LodgingDto lodgingDto = new LodgingDto();
            lodgingDto.setName("Gite "+i);
            lodgingDto.setCapacity((int)Math.ceil(Math.random()*8));
            lodgingDto.setNightPrice((int)Math.ceil(Math.random()*100));
            lodgingDto.setDescription(String.valueOf(faker.lorem().words(45)));
            lodgingDto.setAccessible(faker.bool().bool());
            lodgingDto.setAddressDto(createRandomAddress(faker));
            Lodging lodging = lodgingService.create(lodgingDto);
            lodgingRepository.save(lodging);
            lodging.getRooms().add(roomService.findById(1L));
            lodging.getRooms().add(roomService.findById(2L));
            lodging.getRooms().add(roomService.findById(3L));
            lodging.getRooms().add(roomService.findById(4L));
            for(int j = 0; j<Math.random()*4;j++){
                Room room = roomService.findById(random.nextLong(5,11));
                if(lodging.getRooms().stream().noneMatch(r -> Objects.equals(r.getId(), room.getId()))) lodging.getRooms().add(room);
            }
            lodgingRepository.save(lodging);
        }
        lodgingRepository.flush();
    }

    private AddressDto createRandomAddress(Faker faker) {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(faker.address().streetName());
        addressDto.setNumber(faker.address().streetAddressNumber());
        addressDto.setZipCode(faker.address().zipCode());
        addressDto.setCity(faker.address().city());
        addressDto.setCountry("France");
        addressDto.setLongitude((float) ((Math.random() * (8.135000 - 2.315800)) + 2.315800));
        addressDto.setLatitude((float) ((Math.random() * (51.052100 - 42.195800)) + 42.195800));
        addressDto.setMore(String.valueOf(faker.lorem().words(10)));
        return addressDto;
    }

    private void createFavorites(){

        if(favoriteRepository.count() >= NB_FAVORITE) return;

        for (int i = 0; i < NB_FAVORITE; i++) {
            Favorite favorite = new Favorite();
            User user = userService.getOneRandom();
            Lodging lodging = lodgingService.getOneRandom();
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
            BookingDto bookingDto = new BookingDto();
            LocalDateTime started = LocalDateTime.ofInstant(faker.date().future(100, TimeUnit.DAYS).toInstant(),ZoneId.systemDefault());
            bookingDto.setStartedAt(started);
            bookingDto.setQuantity((int)Math.ceil(Math.random()*6));
            bookingDto.setLodgingId(lodgingService.getOneRandom().getId());
            Booking booking = bookingService.create(bookingDto);
        }
        bookingRepository.flush();
    }

    private void createReviews(){
        Faker faker = new Faker(Locale.of("fr"));

        if(reviewRepository.count() >= NB_REVIEW) return;

        for (int i = 0; i < NB_REVIEW; i++) {
            Review review = new Review();
            review.setCreatedAt(LocalDateTime.now());
            review.setContent(String.valueOf(faker.lorem().words(12)));
            review.setRating((float)Math.random()*5);
            review.setUser(userRepository.getOneRandom());
            review.setLodging(lodgingRepository.getOneRandom());
            reviewRepository.save(review);
        }
        reviewRepository.flush();
    }

}

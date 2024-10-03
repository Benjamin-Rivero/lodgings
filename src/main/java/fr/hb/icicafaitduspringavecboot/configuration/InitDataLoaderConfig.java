package fr.hb.icicafaitduspringavecboot.configuration;


import fr.hb.icicafaitduspringavecboot.dto.*;
import fr.hb.icicafaitduspringavecboot.entity.*;
import fr.hb.icicafaitduspringavecboot.repository.*;
import fr.hb.icicafaitduspringavecboot.service.*;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private static final int NB_BOOKING = 50;
    private static final int NB_REVIEW = 100;
    private final int NB_FAVORITE = 25;
    private final int NB_USER = 125;
//    private final int NB_ROOM = 25;
//    private final int NB_ADDRESS = 50;
    private final int NB_LODGING = 50;
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
            userCreationDto.setEmail(String.format("%s.%s@gmail.com",userCreationDto.getFirstName().toLowerCase(),userCreationDto.getLastName().toLowerCase()));
            userCreationDto.setPassword("12345");
            userCreationDto.setBirthDate(faker.timeAndDate().birthday());
            User user = userService.createInit(userCreationDto);
            Address address = addressService.create(createRandomAddress(faker));
            user.getAddresses().add(address);
            userRepository.save(user);
        }
        userRepository.flush();
    }

    private void createRooms(){
        List<String> roomTypes = List.of("Kitchen","Bathroom","Master Bedroom","Living Room","Attic","Basement","Garage","Jacuzzi","Pool","1 Bed Bedroom","2 Bed Bedroom");

        if(roomRepository.count() < roomTypes.size()) {
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
        List<String> alreadyAppeared = new ArrayList<>();

        if(lodgingRepository.count() >= NB_LODGING) return;

        for (int i = 0; i < NB_LODGING; i++) {
            LodgingDto lodgingDto = new LodgingDto();
            String name;
            do {
                name = genererNomGite();
            } while (alreadyAppeared.contains(name));
            alreadyAppeared.add(name);
            lodgingDto.setName(name);
            lodgingDto.setCapacity((int)Math.ceil(Math.random()*8));
            lodgingDto.setNightPrice((int)Math.ceil(Math.random()*100));
            lodgingDto.setDescription(String.valueOf(faker.lorem().paragraph(2)));
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
        addressDto.setLongitude(((Float)(float) ((Math.random() * (8.135000 - 2.315800)) + 2.315800)).toString());
        addressDto.setLatitude(((Float)(float) ((Math.random() * (51.052100 - 42.195800)) + 42.195800)).toString());
        addressDto.setMore(String.valueOf(faker.lorem().paragraph(1)));
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
            LocalDateTime started = LocalDateTime.ofInstant(faker.timeAndDate().future(100, TimeUnit.DAYS),ZoneId.systemDefault());
            bookingDto.setStartedAt(started);
            bookingDto.setQuantity((int)Math.ceil(Math.random()*6));
            bookingDto.setLodgingId(lodgingService.getOneRandom().getId());
            Booking booking = bookingService.create(bookingDto);
            booking.setUser(userService.getOneRandom());
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
            review.setContent(faker.yoda().quote());
            review.setRating((float)Math.random()*5);
            review.setUser(userRepository.getOneRandom());
            review.setLodging(lodgingRepository.getOneRandom());
            reviewRepository.save(review);
        }
        reviewRepository.flush();
    }

    // Liste élargie des adjectifs
    private static final String[] adjectifs = {
            "Chaleureux", "Paisible", "Enchanteur", "Rustique", "Charmant", "Tranquille",
            "Serene", "Cosy", "Élégant", "Authentique", "Vibrant", "Verdoyant",
            "Serein", "Douillet", "Pittoresque", "Magique", "Radieux", "Convivial",
            "Spacieux", "Lumineux", "Sublime", "Agréable", "Apaisant", "Raffiné"
    };

    // Liste élargie des noms
    private static final String[] noms = {
            "Refuge", "Havre", "Nid", "Écrin", "Domaine", "Manoir",
            "Chalet", "Villa", "Gîte", "Cabane", "Ferme", "Château",
            "Résidence", "Logis", "Moulin", "Bastide", "Auberge", "Pavillon",
            "Retraite", "Mas", "Cottage", "Lodge", "Foyer", "Tour"
    };

    // Liste élargie des lieux
    private static final String[] lieux = {
            "des Montagnes", "de la Forêt", "des Collines", "de la Rivière", "du Lac",
            "du Jardin", "des Pins", "du Verger", "du Vignoble", "des Près",
            "du Soleil", "du Marais", "des Falaises", "des Cascades", "de la Plage",
            "du Bois", "du Rocher", "de la Vallée", "de la Clairière", "des Fleurs",
            "des Champs", "du Sentier", "des Étoiles", "de la Mer"
    };

    // Méthode pour générer un nom aléatoire
    public static String genererNomGite() {
        Random random = new Random();
        String adjectif = adjectifs[random.nextInt(adjectifs.length)];
        String nom = noms[random.nextInt(noms.length)];
        String lieu = lieux[random.nextInt(lieux.length)];

        return adjectif + " " + nom + " " + lieu;
    }



}

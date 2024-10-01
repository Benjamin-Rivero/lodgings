package fr.hb.icicafaitduspringavecboot.jsonviews;

public class JsonViewUser {

	public interface UserMinimalView extends FirstName,LastName,Email {}

	public interface UserShowView extends UserMinimalView,Photo,Phone,Birthdate,
			Addresses,JsonViewAddress.AddressMinimalView,
			Bookings,JsonViewBooking.BookingMinimalView,
			Reviews,JsonViewReview.ReviewMinimalView,
			Favorites,JsonViewFavorite.Lodging,
			JsonViewLodging.Name,JsonViewLodging.Slug{}


	public interface Id {
	}

	public interface FirstName {
	}

	public interface Addresses {
	}

	public interface Bookings {
	}

	public interface Favorites {
	}

	public interface Reviews {
	}

	public interface ActivationTokenSentAt {
	}

	public interface ActivationToken {
	}

	public interface Slug {
	}

	public interface Photo {
	}

	public interface Phone {
	}

	public interface CreatedAt {
	}

	public interface Birthdate {
	}

	public interface Roles {
	}

	public interface Password {
	}

	public interface Email {
	}

	public interface LastName {
	}
}

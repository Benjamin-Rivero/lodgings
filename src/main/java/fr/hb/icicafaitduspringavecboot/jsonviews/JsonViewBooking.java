package fr.hb.icicafaitduspringavecboot.jsonviews;

public class JsonViewBooking {

	public interface BookingMinimalView extends Number,StartedAt,FinishedAt {}

	public interface BookingShowView extends BookingMinimalView,Quantity,
			User, JsonViewUser.UserMinimalView,
			Lodging, JsonViewLodging.LodgingMinimalView {}

	public interface Id {
	}

	public interface User {
	}

	public interface Lodging {
	}

	public interface Number {
	}

	public interface CreatedAt {
	}

	public interface StartedAt {
	}

	public interface FinishedAt {
	}

	public interface Quantity {
	}
}

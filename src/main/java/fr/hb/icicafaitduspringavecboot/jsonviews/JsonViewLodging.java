package fr.hb.icicafaitduspringavecboot.jsonviews;

public class JsonViewLodging {

	public interface LodgingMinimalView extends Name,NightPrice,Capacity,Slug {}

	public interface LodgingShowView extends LodgingMinimalView, Description, IsAccessible,
			Medias,JsonViewMedia.Extension,JsonViewMedia.Path,
			Rooms,JsonViewRoom.Type,
			Reviews, JsonViewReview.ReviewMinimalView,
			Address, JsonViewAddress.AddressMinimalView {}

	public interface Bookings {
	}

	public interface Favorites {
	}

	public interface Reviews {
	}

	public interface Address {
	}

	public interface Medias {
	}

	public interface Rooms {
	}

	public interface Slug {
	}

	public interface Description {
	}

	public interface NightPrice {
	}

	public interface IsAccessible {
	}

	public interface Capacity {
	}

	public interface Id {
	}

	public interface Name {
	}
}

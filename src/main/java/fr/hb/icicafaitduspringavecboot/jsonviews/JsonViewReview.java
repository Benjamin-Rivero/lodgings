package fr.hb.icicafaitduspringavecboot.jsonviews;

public class JsonViewReview {

	public interface ReviewMinimalView extends Rating,Content {}

	public interface ReviewShowView extends ReviewMinimalView,
			User, JsonViewUser.UserMinimalView {}

	public interface Id {
	}

	public interface Content {
	}

	public interface Rating {
	}

	public interface User {
	}

	public interface Lodging {
	}

	public interface CreatedAt{}


}

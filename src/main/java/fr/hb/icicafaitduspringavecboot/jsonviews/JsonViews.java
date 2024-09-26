package fr.hb.icicafaitduspringavecboot.jsonviews;

public class JsonViews {

    public interface UserListView {}

    public interface UserShowView extends UserListView, ReviewListView{}

    public interface ReviewListView {}

}

package cat.uab.tq.online_warehouse.articles;

public class NotEnoughArticlesException extends RuntimeException {

    public NotEnoughArticlesException(String string) {
        super(string);
    }

}

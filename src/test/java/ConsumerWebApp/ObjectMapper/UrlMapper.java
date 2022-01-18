package ConsumerWebApp.ObjectMapper;

public class UrlMapper {
	private String url;

    public UrlMapper() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlMapper{" +
                "url='" + url + '\'' +
                '}';
    }
}

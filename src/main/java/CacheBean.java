import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CacheBean {
    private String value;
    private java.util.Date timestamp;

    public CacheBean(String value, Date timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public CacheBean() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

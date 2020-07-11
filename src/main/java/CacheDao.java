import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class CacheDao {
    private final static Map<Integer, String> daoMock = new HashMap<>();
    static {
        daoMock.put(1, "My name is Inigo Montoya.");
        daoMock.put(2, "Gaudeamus igitur juvenes dum sumus!");
        daoMock.put(3, "I'm sorry, Dave. I'm afraid I can't do that.");
        daoMock.put(4, "What... is the air-speed velocity of an unladen swallow?");
        daoMock.put(5, "Beam me up, Scotty!");
        daoMock.put(6, "You are a wizard, Harry");
        daoMock.put(7, "Luke, I'm your father");
        daoMock.put(8, "There is no fate but what we make");
    }

    public String getFromDao (Integer id){
        return daoMock.get(id);
    }

}


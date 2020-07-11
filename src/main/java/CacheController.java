import java.util.*;

public final class CacheController {
    private CacheDao cacheDao;
    private final static Map<Integer, CacheBean> cacheMap = new HashMap<>();

    private final Timer timer;

    private CacheController() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cacheMap.clear();
            }
        }, 0, 1000 * 60 * 5);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                deleteByTimestamp();
            }
        }, 0, 1000);
    }

    private void deleteByTimestamp() {
        Set<Integer> toDelete = new HashSet<>();
        for (Map.Entry<Integer, CacheBean> entry : cacheMap.entrySet()) {
            CacheBean value = entry.getValue();
            long difference = (Calendar.getInstance().getTime().getTime() - value.getTimestamp().getTime());
            if ( difference > 30*1000) {
                toDelete.add(entry.getKey());
            }
        }
        toDelete.forEach(key -> cacheMap.remove(key));
    }

    private static CacheController instance;

    public static CacheController getInstance() {
        if (instance == null)
            instance = new CacheController();

        return instance;
    }


    public String getText(int id) {
        if (!isIdExistsInCache(id)) {
            addTextToCacheMap(id);
            return cacheDao.getFromDao(id);
        }
        return getFromCacheMap(id);
    }

    public boolean isIdExistsInCache(int id) {
        if (cacheMap.get(id) == null) {
            return false;
        }
        return true;
    }

    public void addTextToCacheMap(int id) {
        CacheBean cacheBean = new CacheBean();
        cacheBean.setValue(cacheDao.getFromDao(id));
        cacheBean.setTimestamp(Calendar.getInstance().getTime());
        cacheMap.put(id, cacheBean);
    }

    public String getFromCacheMap(int id) {
        try {
            return cacheMap.get(id).getValue();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void deleteFromCacheMap(int id) {
        cacheMap.remove(id);
    }

}

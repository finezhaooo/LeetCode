package newCoder;

/**
 * @ClassName : single
 * @Description:
 * @Author zhaooo
 * @Date 2021/4/13/20:22
 */
public class Singleton {
    private static Singleton singleton;
    private Singleton(){
    }
    public static Singleton getSingleton(){
        if (singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public synchronized static Singleton getSingleton2(){
        if (singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }

    private volatile static Singleton singleton2;
    public static Singleton getSingleton3(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton2 == null){
                    singleton2 = new Singleton();
                }
            }
        }
        return singleton2;
    }
}

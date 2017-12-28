import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*******************************************************************************
 * @(#)ExcuteMain.java 2013-2-7
 *
 *******************************************************************************/

/**
 * @version $Revision 1.1 $ 2013-2-7 下午5:09:25
 */
public class ExcuteMain {
    private static Logger logger = LoggerFactory.getLogger(ExcuteMain.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        //String sysPath = System.getProperty("user.dir");
        logger.debug("Start to load the log4j.properties");
        //PropertyConfigurator.configure(sysPath + "/src/main/resource/log4j.properties");
        logger.debug("The Main Programe Start");
        try{
            logger.info("监控项目启动，正在配置。");
            //Spring的配置文件
            String path = "conf/applicationContext.xml";
            //Spring配置文件读取后对象ApplicationContext context = 
            new ClassPathXmlApplicationContext(path);
            logger.info("配置完成，开始监控。");
        }catch(Exception ex){
            logger.error("配置失败，无法启动。", ex);
        }
        java.lang.Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                logger.info("监听被关闭。");
            }
        });
    }

}

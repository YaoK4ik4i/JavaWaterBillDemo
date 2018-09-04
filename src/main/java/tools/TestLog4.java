package tools;

import java.io.File;
import java.io.IOException;  
import java.util.Properties;  
  

import org.apache.log4j.Logger;  
import org.apache.log4j.Priority;  
import org.apache.log4j.PropertyConfigurator;  
//import com.xmh.servlet.Log4j.Log4jInit;  

import UI.ConstantsUI;
  
public class TestLog4 {  
    static Logger logger = Logger.getLogger(TestLog4.class.getName());  
    public TestLog4(){}  
    public void testLog()  
    {     
        //同时输出到控制台和一个文件的实例  
        /* 用log4包加载配置文件 */  
        //PropertyConfigurator.configure("\\log4j.properties");  
        /* 用java自带peoperties加载配置文件  */  
//        Properties props=new Properties();  
        try {  
//            System.out.println("++++++properties++++++++");  
//            props.load(Log4jInit.class  
//                    .getClassLoader()  
//                    .getResourceAsStream("log4j.properties")  
//                    );  
        PropertyConfigurator.configure(ConstantsUI.CURRENT_DIR+ File.separator + "logger" + File.separator + "log4j.properties" );
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        logger.debug("2Start of the main()");  
        logger.info("2Just testing a log message with priority set to INFO");  
        logger.warn("2Just testing a log message with priority set to WARN");  
        logger.error("2Just testing a log message with priority set to ERROR");  
        logger.fatal("2Just testing a log message with priority set to FATAL");  
        logger.log(Priority.DEBUG, "Testing a log message use a alternate form");  
        logger.debug("2End of the main()");  
    }  
}  

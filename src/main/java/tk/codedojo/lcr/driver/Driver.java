package tk.codedojo.lcr.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.codedojo.lcr.util.GameManager;

public class Driver {
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        GameManager gameManager = (GameManager) ctx.getBean("gameManagerImpl");
        gameManager.runGame();
    }
}

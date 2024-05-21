package io.github.travisdeshotels.lcr.driver;

import io.github.travisdeshotels.lcr.util.GameManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        GameManager gameManager = (GameManager) ctx.getBean("gameManagerImpl");
        gameManager.runGame();
    }
}

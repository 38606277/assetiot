package system;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import system.db.DbFactory;


@Component
public class ServerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        //初始化system数据库连接
        System.out.println("开始初始化系统数据库!");
        try {
            DbFactory.initializeDB("form");
            System.out.println("初始化系统数据库成功!");
        } catch (Exception ex) {
            System.out.println("初始化系统数据库失败!");
            ex.printStackTrace();
        }
    }
}
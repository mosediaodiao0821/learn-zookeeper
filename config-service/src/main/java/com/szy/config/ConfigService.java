package com.szy.config;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.szy.config.constant.Constant;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigService 
{
    public static void main( String[] args ) throws Exception
    {
    	initZookeeper();
    	SpringApplication.run(ConfigService.class, args);
    }
    
    
    public static void initZookeeper() throws Exception{
    	ZooKeeper zk = new ZooKeeper("127.0.0.1:" + 2181, 3000,
				new Watcher() {
					// 监控所有被触发的事件
					public void process(WatchedEvent event) {
						System.out.println("已经触发了" + event.getType() + "事件！");
					}
				});
    	if(zk.exists(Constant.CONFIG_NODE_ROOT, true) == null){
    		zk.create(Constant.CONFIG_NODE_ROOT, "".getBytes(),
    				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	}
    	if(zk.exists(Constant.CONFIG_NODE_SERVICE, true) == null){
    		zk.create(Constant.CONFIG_NODE_SERVICE, "".getBytes(),
    				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	}
    	if(zk.exists(Constant.CONFIG_NODE_NAME, true) == null){
    		zk.create(Constant.CONFIG_NODE_NAME, "".getBytes(),
    				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	}
		zk.close();
    }
}

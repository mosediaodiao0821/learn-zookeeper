package com.szy.config.controller;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import junit.framework.TestCase;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.szy.config.constant.Constant;
import com.szy.config.model.ConfigModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:*.xml","classpath:*.properties"})
public class ConfigTest {

	@Autowired
	private ConfigModel config;
	
	@Test
	public void testGetConfigs() {
		while(true) {
			String name = "szy" + new Random().nextInt(100);
			try {
				modifyConfig(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String configs =  config.getConfigs();
			assertEquals(name, configs);
		}
//		fail("Not yet implemented");
	}

	public void modifyConfig(String name) throws Exception{
		ZooKeeper zk = new ZooKeeper("127.0.0.1:" + 2181, 3000,
				new Watcher() {
					// 监控所有被触发的事件
					public void process(WatchedEvent event) {
						System.out.println("已经触发了" + event.getType() + "事件！");
					}
				});
		zk.setData(Constant.CONFIG_NODE_NAME, name.getBytes(), -1);
		zk.close();
	}
}

package com.cz.common.util.cache.redisson;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jomalone_jia on 2017/6/28.
 */
public class RedissonConfig {

    private static Logger _log = LoggerFactory.getLogger(RedissonConfig.class);

    private String address;
    private String password;

    private List<String> nodeAddresses = new ArrayList<String>();

    /**
     * Redis master server address
     */
    private String masterAddress;

    /**
     * Redis slave servers addresses
     */
    private Set<String> slaveAddresses = new HashSet<String>();

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNodeAddresses(String ... nodeAddresses) {
        if (nodeAddresses != null) {
            for (int i = 0; i < nodeAddresses.length; i++) {
                this.nodeAddresses.add(nodeAddresses[i]);
            }
        }
    }

    public void setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    public void setSlaveAddresses(String ... slaveAddresses) {
        if (slaveAddresses != null) {
            for (int i = 0; i < slaveAddresses.length; i++) {
                this.slaveAddresses.add(slaveAddresses[i]);
            }
        }
    }

   /* @Bean(name = "redissonClient")*/
    public org.redisson.api.RedissonClient getRedissonClient(){
        Config config = new Config();
        if(!StringUtils.isBlank(address)){
            SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(address);
            if (!StringUtils.isBlank(password)) {
                singleServerConfig.setPassword(password);
            }
        }else if (!nodeAddresses.isEmpty()) {
            ClusterServersConfig serverConfig = config.useClusterServers()
                    .addNodeAddress(nodeAddresses.toArray(new String[] {}));
            if (org.apache.commons.lang3.StringUtils.isNotBlank(password)) {
                serverConfig.setPassword(password);
            }
        } else if (masterAddress != null && !slaveAddresses.isEmpty()) {
            MasterSlaveServersConfig serverConfig = config.useMasterSlaveServers().setMasterAddress(masterAddress)
                    .addSlaveAddress(slaveAddresses.toArray(new String[] {}));
            if (org.apache.commons.lang3.StringUtils.isNotBlank(password)) {
                serverConfig.setPassword(password);
            }
        }
        return Redisson.create(config);
    }
}

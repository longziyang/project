//package com.project.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//@Configuration
//public class AuthShardingJdbcConfig {
//
//    //配置分片规则
//    // 定义数据源
//    Map<String, DataSource> createDataSourceMap() {
//        DruidDataSource dataSource1 = new DruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/order_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("123456");
//        Map<String, DataSource> result = new HashMap<>();
//        result.put("m1", dataSource1);
//        return result;
//    }
//    // 定义主键生成策略
//    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
//        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE","id");
//        return result;
//    }
//
//    // 定义t_order表的分片策略
//    TableRuleConfiguration getOrderTableRuleConfiguration() {
//        TableRuleConfiguration result = new TableRuleConfiguration("t_order","m1.t_order$->{1..2}");
//        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "t_order$->{id % 2 + 1}"));
//        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
//
//        return result;
//    }
//    // 定义sharding-Jdbc数据源
//    @Bean
//    DataSource getShardingDataSource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
//        Properties properties = new Properties();
//        properties.put("sql.show","true");
//        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig,properties);
//    }
//}

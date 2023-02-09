package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerAck {
    public static void main(String[] args) {
        //0 配置
        Properties properties = new Properties();
        //连接 kafka集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092");
        //序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //acks
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        //重试次数，retries,默认是int 最大值，2147483647
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);


        //1.创建生产者
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        //2. 发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<>("first", "atguigu" + i));
        }
        //3.关闭资源
        kafkaProducer.close();
    }
}

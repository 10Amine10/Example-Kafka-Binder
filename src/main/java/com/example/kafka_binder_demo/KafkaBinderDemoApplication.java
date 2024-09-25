package com.example.kafka_binder_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class KafkaBinderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBinderDemoApplication.class, args);
	}

	@Bean
	public Function<String, String> processorBiding() {
		return s -> s + " :: " + System.currentTimeMillis();
	}

	@Bean
	public Consumer<String> consumerBiding() {
		return s -> System.out.println("Data consumer :: " + s.toUpperCase());
	}

	@Bean
	public Supplier<String> producerBinding() {
		return new Supplier<String>() {
			private int count = 0;

			@Override
			public String get() {
				if (count < 100) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					count++;
					return "new data " + count;
				} else {
					return null;
				}
			}
		};
	}

}

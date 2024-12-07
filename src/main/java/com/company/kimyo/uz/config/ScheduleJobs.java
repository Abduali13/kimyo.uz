package com.company.kimyo.uz.config;

import com.company.kimyo.uz.entity.*;
import com.company.kimyo.uz.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleJobs {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrdersRepository ordersRepository;

//    @Scheduled(fixedDelay = 3, initialDelay = 5, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron = "5 * * * * *")
    public void simpleScheduleMethod() {
        System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

    }

    @Scheduled(cron = "* 05 00 1 Jun *")
    public void checkData(){
        if (this.cardRepository.findAllByDeletedAtIsNotNull().stream().mapToInt(Card::getCardId).count() >= 10000){
            this.cardRepository.deleteAll(this.cardRepository.findAllByDeletedAtIsNotNull());
        }
        if (this.userRepository.findAllByDeletedAtIsNotNull().stream().mapToInt(User::getUserId).count() >= 7000){
            this.productRepository.deleteAll(this.productRepository.findAllByDeletedAtIsNotNull());
        }
        if (this.productRepository.findAllByDeletedAtIsNotNull().stream().mapToInt(Product::getProdId).count() >= 50000){
            this.productRepository.deleteAll(this.productRepository.findAllByDeletedAtIsNotNull());
        }
        if (this.categoryRepository.findAllByDeletedAtIsNotNull().stream().mapToInt(Category::getCategoryId).count() >= 10000){
            this.categoryRepository.deleteAll(this.categoryRepository.findAllByDeletedAtIsNotNull());
        }
        if (this.ordersRepository.findAllByDeletedAtIsNotNull().stream().mapToInt(Orders::getOrderId).count() >= 100000){
            this.ordersRepository.deleteAll(this.ordersRepository.findAllByDeletedAtIsNotNull());
        }
    }


}

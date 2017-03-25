package ru.javawebinar.topjava.util;

import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UserMealsUtilTest {

    @Test
    public void testGetFilteredWithExceeded() throws Exception {
        UserMealsUtil util = new UserMealsUtil();

        long start = System.currentTimeMillis();
        List<UserMeal> mealList = new ArrayList<>();
        for (int i = 0; i < 3_000_000; ++i) {
            mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
//            mealList.add(new UserMeal(getRandomTime(), getRandomMeanName(), 100*ThreadLocalRandom.current().nextInt(1, 15)));
        }
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        util.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static LocalDateTime getRandomTime() {
        long minDay = LocalDateTime.of(2015, Month.JANUARY, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long maxDay = LocalDateTime.of(2017, Month.MARCH, 24, 23, 59).toEpochSecond(ZoneOffset.UTC);
        long randomMillis = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(randomMillis), ZoneId.systemDefault());
    }

    private static String getRandomMeanName() {
        switch(ThreadLocalRandom.current().nextInt(0, 3)) {
            case 0: return "Завтрак";
            case 1: return "Обед";
            case 2: return "Ужин";
        }
        throw new IllegalStateException("Random should be between 0 and 2 inclusive");
    }
}

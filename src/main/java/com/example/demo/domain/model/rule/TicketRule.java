package com.example.demo.domain.model.rule;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.TicketTable;

/* チケットの種類とその該当条件 */

public enum TicketRule {
    /* シネマシティズン */
    CINEMA_CITIZEN(TicketTable.CINEMA_CITIZEN, Customer::isCinemaCitizen),
    /* シネマシティズン(60歳以上) */
    ELDER_CINEMA_CITIZEN(TicketTable.ELDER_CINEMA_CITIZEN, c -> c.isCinemaCitizen() && c.isOver60()),
    /* 一般 */
    NORMAL(TicketTable.NORMAL, c -> true),
    /* シニア(70歳以上)*/
    SENIOR(TicketTable.SENIOR, Customer::isOver70),
    /* 学生（大・専） */
    UNIVERSITY_OR_COLLAGE(TicketTable.UNIVERSITY_OR_COLLAGE, Customer::isUniversityOrCollage),
    /* 中・高校生 */
    JUNIOR_OR_HIGHSCHOOL(TicketTable.JUNIOR_OR_HIGHSCHOOL, Customer::isJuniorOrHighSchoolStudent),
    /* 幼児（3才以上）・小学生 */
    CHILD(TicketTable.CHILD, Customer::isChild),
    /* 障がい者（学生以上） */
    DISABLED(TicketTable.DISABLED, Customer::isDisabled),
    /* 障がい者（高校以下） */
    YOUNGD_DISABLED(TicketTable.YOUNG_DISABLED, c -> c.isYoung() && c.isDisabled()),
    /* 2歳未満(無料) */
    BABY(TicketTable.BABY, Customer::isBaby);

    private final TicketTable ticketTable;
    private final Predicate<Customer> condition;

    TicketRule(TicketTable ticketTable, Predicate<Customer> predicate) {
        this.ticketTable = ticketTable;
        this.condition = predicate;
    }

    public static List<TicketTable> matches(Customer customer) {
        return Stream.of(TicketRule.values())
            .filter(t -> t.test(customer))
            .map(t -> t.ticketTable)
            .collect(Collectors.toList());
    }

    private boolean test(Customer customer) {
        return this.condition.test(customer);
    }

}

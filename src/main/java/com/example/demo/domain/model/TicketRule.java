package com.example.demo.domain.model;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TicketRule {
    
    CINEMA_CITIZEN(TicketTable.CINEMA_CITIZEN, Customer::isCinemaCitizen),
    ELDER_CINEMA_CITIZEN(TicketTable.ELDER_CINEMA_CITIZEN, c -> c.isCinemaCitizen() && c.isOver60()),

    NORMAL(TicketTable.NORMAL, c -> true),
    SENIOR(TicketTable.SENIOR, Customer::isOver70),
    /* 学生・子供料金 */
    UNIVERSITY_OR_COLLAGE(TicketTable.UNIVERSITY_OR_COLLAGE, Customer::isUniversityOrCollage),
    JUNIOR_OR_HIGHSCHOOL(TicketTable.JUNIOR_OR_HIGHSCHOOL, Customer::isJuniorOrHighSchoolStudent),
    CHILD(TicketTable.CHILD, Customer::isChild),
    /* 障害者 */
    DISABLED(TicketTable.DISABLED, Customer::isDisabled),
    YOUNGD_DISABLED(TicketTable.YOUNG_DISABLED, c -> c.isYoung() && c.isDisabled()),

    BABY(TicketTable.BABY, Customer::isBaby);

    private final TicketTable ticketTable;
    private final Predicate<Customer> predicate;

    TicketRule(TicketTable ticketTable, Predicate<Customer> predicate) {
        this.ticketTable = ticketTable;
        this.predicate = predicate;
    }

    public static List<TicketTable> matches(Customer customer) {
        return Stream.of(TicketRule.values())
            .filter(t -> t.test(customer))
            .map(t -> t.ticketTable)
            .collect(Collectors.toList());
    }


    private boolean test(Customer customer) {
        return this.predicate.test(customer);
    }

}

package com.example.demo.domain.model;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TicketRule {
    
    CINEMA_CITIZEN(TicketTable.CINEMA_CITIZEN, Attempt::isCinemaCitizen),
    ELDER_CINEMA_CITIZEN(TicketTable.ELDER_CINEMA_CITIZEN, Attempt::isElderyCinemaCitizen),

    NORMAL(TicketTable.NORMAL, Attempt::isNormal),

    SENIOR(TicketTable.SENIOR, Attempt::isSenior),

    /* 学生・子供料金 */
    UNIVERSITY_OR_COLLAGE(TicketTable.UNIVERSITY_OR_COLLAGE, Attempt::isUniversityOrCollage),
    JUNIOR_OR_HIGHSCHOOL(TicketTable.JUNIOR_OR_HIGHSCHOOL, Attempt::isJuniorOrHighSchoolStudent),
    CHILD(TicketTable.CHILD, Attempt::isChild),

    /* 障害者 */
    DISABLED(TicketTable.DISABLED, Attempt::isDisabled),
    YOUNGD_DISABLED(TicketTable.YOUNG_DISABLED, Attempt::isYoungDisabled),

    BABY(TicketTable.BABY, Attempt::isBaby);

    private final TicketTable ticketTable;
    private final Predicate<Attempt> predicate;

    TicketRule(TicketTable ticketTable, Predicate<Attempt> predicate) {
        this.ticketTable = ticketTable;
        this.predicate = predicate;
    }

    public static List<TicketTable> matches(Attempt attempt) {
        return Stream.of(TicketRule.values())
            .filter(t -> t.test(attempt))
            .map(t -> t.ticketTable)
            .collect(Collectors.toList());
    }


    private boolean test(Attempt attempt) {
        return this.predicate.test(attempt);
    }

}

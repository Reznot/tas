package com.webapp.tas.objects;

import lombok.Data;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Date;

import static com.webapp.tas.Tables.GAMES;
import static org.jooq.impl.DSL.*;
import static com.webapp.tas.Tables.REVIEWS;

public @Data class Game {
    int gameid;
    String icon;
    String title;
    String description;
    Date launch_date;
    String publisher;
    String screen;
    String platform;
    String genre;
    //int score = 0;

//TODO mozna dodac generowanie id, jakis uuid
    //TODO dodac parametr score do obiektu, dostepny  tylko z bezposredniego wejscia na profil gry

    @Autowired
    private DSLContext jooq;

    public int getScore(int gameid){
        Record1<BigDecimal> sc = jooq.select(avg(REVIEWS.RATE)).from(REVIEWS).where(GAMES.GAMEID.eq(gameid)).fetchOne();
        BigDecimal record = sc.getValue(sc.field1());
        int score = record.intValue();
        return score;
    }


}

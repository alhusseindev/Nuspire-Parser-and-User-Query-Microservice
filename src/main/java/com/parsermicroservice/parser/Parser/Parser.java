package com.parsermicroservice.parser.Parser;

import javax.persistence.*;

@Entity
@Table
public class Parser{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long parserID;
    @Column
    public String logDateTime;
    @Column
    private String logMessage;

    //Default Constructor
    public Parser(){

    }

    public Parser(Long parserID, String logDateTime, String logMessage){
        this.parserID = parserID;
        this.logDateTime = logDateTime;
        this.logMessage = logMessage;
    }

    //Getters
    public Long getparserID(){
        return this.parserID;
    }

    public String getLogDateTime(){
        return this.logDateTime;
    }

    public String getLogMessage(){
        return this.logMessage;
    }

    //Setters

    public void setParserID(Long parserID){
        this.parserID = parserID;
    }

    public void setLogDateTime(String logDateTime){
        this.logDateTime = logDateTime;
    }

    public void setLogMessage(String logMessage){
        this.logMessage = logMessage;
    }

    public String toString(){
        return "ID:" + this.parserID + "Date/Time:" + this.logDateTime + "Log Message:" + this.logMessage;
    }

}


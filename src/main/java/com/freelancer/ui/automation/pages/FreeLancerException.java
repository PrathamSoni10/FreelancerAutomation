package com.freelancer.ui.automation.pages;

public class FreeLancerException extends Exception{

    public FreeLancerException(String errMessage, Throwable err) { super(errMessage, err); }

    public FreeLancerException(String errMessage){
        super(errMessage);
    }


}

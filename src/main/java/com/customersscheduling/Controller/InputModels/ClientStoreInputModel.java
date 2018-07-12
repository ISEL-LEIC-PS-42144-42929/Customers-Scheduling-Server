package com.customersscheduling.Controller.InputModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ClientStoreInputModel {
    public boolean accepted;
    public int score;
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pojoClasses.Acknowledgement;
import pojoClasses.CustomerRequest;

@Endpoint
public class LoanEligibilityEndpoint
{
    private static final String NAMESPACE = "http://in28minutes.com/students";

    @Autowired
    private LoanEligibilityService service;

    @PayloadRoot(namespace = NAMESPACE,localPart = "CustomerRequest")
    @ResponsePayload
    public Acknowledgement getLoanStatus (@RequestPayload CustomerRequest request)
    {
        return service.checkLoanEligibility(request);
    }
}

package com.example.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ReserveSeatOnBoat implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String money = "0.0";
        String tickerType = "Coach";

        money = (String) delegateExecution.getVariable("money");
        double moneyDouble = Double.parseDouble(money);

        if (moneyDouble >= 10000) {
            tickerType = "First class";
        } else if (moneyDouble >= 5000) {
            tickerType = "Business class";
        } else if (moneyDouble <= 10) {
            tickerType = "Stowaway class";
            throw new BpmnError("Fall_overboard", "A cheap ticket has led to a small wave throwing you overboard");
        }

        delegateExecution.setVariable("ticketType", tickerType);
    }
}

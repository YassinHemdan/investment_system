package com.example.crowdfunding.services.servicesImpl;

import com.example.crowdfunding.dtos.AvailableAreaDTO;
import com.example.crowdfunding.dtos.PaymentDTO;
import com.example.crowdfunding.dtos.WithDrawDTO;
import com.example.crowdfunding.entities.*;
import com.example.crowdfunding.repos.*;
import com.example.crowdfunding.services.Iservices.InvestmentRepo;
import com.example.crowdfunding.services.Iservices.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final RestClient restClient;
    private final PaymentRepo paymentRepo;
    private final PriceHistoryRepo priceHistoryRepo;
    private final DepartmentRepo departmentRepo;
    private final InvestmentRepo investmentRepo;
    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;

    @Autowired
    public PaymentServiceImpl(
            PaymentRepo paymentRepo,
            PriceHistoryRepo priceHistoryRepo,
            DepartmentRepo departmentRepo,
            InvestmentRepo investmentRepo,
            UserRepo userRepo,
            TokenRepo tokenRepo
    ){
        this.tokenRepo = tokenRepo;
        this.userRepo = userRepo;
        this.investmentRepo = investmentRepo;
        this.priceHistoryRepo = priceHistoryRepo;
        this.paymentRepo = paymentRepo;
        this.departmentRepo = departmentRepo;
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }
    @Override
    public String Pay(String username, PaymentDTO paymentDTO) {

        WithDrawDTO withDrawDTO = new WithDrawDTO();

        withDrawDTO.setId(paymentDTO.getCardNumber());

        PriceHistory priceHistory = priceHistoryRepo.findLatestPrice(paymentDTO.getId());
        double department_price = priceHistory.getPrice();

        Department department = departmentRepo.findById(paymentDTO.getId()).orElseThrow();
        AvailableAreaDTO availableAreaDTO = departmentRepo.getAvailableArea(paymentDTO.getId());

        if(availableAreaDTO.getAvailableArea() < paymentDTO.getNumberOfTokens())
            return "Invalid Amount Of Tokens";

        int area = department.getArea();
        double token_price =  department_price /  area;
        double total_amount = token_price * paymentDTO.getNumberOfTokens();

        withDrawDTO.setAmount(total_amount);


        String status =  restClient.put()
                .uri("/api/v1/pay/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .body(withDrawDTO)
                .retrieve()
                .body(String.class);

        assert status != null;
        if(status.equals( "Success")){
            /*
                add the followings:
                1- payment
                2- investment
                3- tokens
             */



            int user_id = userRepo.findByUserName(username).orElseThrow().getId();

            Investment investment = new Investment();
            investment.setDate_of_investment(LocalDate.now());
            investment.setTime_of_investment(LocalTime.now());


            investment.setUser(new UserEntity());
            investment.getUser().setId(user_id);
            investment.setDepartment(new Department());

            investment.getDepartment().setId(paymentDTO.getId());
            investmentRepo.save(investment);


            List<Token> user_tokens = new ArrayList<>();

            System.out.println("3dd el tokens " + paymentDTO.getNumberOfTokens());
            for(int i=0 ; i<paymentDTO.getNumberOfTokens() ; ++i){
                Token token = new Token();
                token.setInvestment(investment);

                token.setUser(new UserEntity());
                token.getUser().setId(user_id);

                token.setDepartment(new Department());
                token.getDepartment().setId(paymentDTO.getId());


                token.setCreateTime(LocalDateTime.now());
                tokenRepo.save(token);
            }

            Payment payment = new Payment();
            payment.setInvestment(investment);
            payment.setUser(new UserEntity());
            payment.getUser().setId(user_id);

            payment.setPaymentDate(LocalDate.now());
            payment.setPaymentDime(LocalTime.now());
            payment.setPaymentType("paypal");
            payment.setCreateTime(LocalDateTime.now());

            paymentRepo.save(payment);
            System.out.println("heere");
            return "Success";
        }
        else {
            return "payment failed, InSufficient Amount";
        }
    }
}

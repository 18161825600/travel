package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.AddTicketRequest;
import com.example.travel.request.IdRequest;
import com.example.travel.request.PageNumRequest;
import com.example.travel.request.UpdateTicketRequest;
import com.example.travel.response.AllTicketResponse;
import com.example.travel.response.TicketResponse;
import com.example.travel.service.TicketService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "insert/ticket")
    public TravelJsonResult<String> insertTicket(@RequestBody AddTicketRequest addTicketRequest){
        Integer integer = ticketService.insertTicket(addTicketRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @DeleteMapping(value = "delete/ticket")
    public TravelJsonResult<String> deleteTicket(@RequestBody IdRequest idRequest){
        Integer integer = ticketService.deleteTicket(idRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PutMapping(value = "update/ticket")
    public TravelJsonResult<String> updateTicket(@RequestBody UpdateTicketRequest updateTicketRequest){
        Integer integer = ticketService.updateTicket(updateTicketRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PostMapping(value = "select/ticket/by/id")
    public TravelJsonResult<TicketResponse> selectTicketById(@RequestBody IdRequest idRequest){
        return TravelJsonResult.ok(ticketService.selectTicketById(idRequest));
    }

    @PostMapping(value = "select/all/ticket")
    public TravelJsonResult<AllTicketResponse> selectAllTicket(@RequestBody PageNumRequest pageNumRequest){
        return TravelJsonResult.ok(ticketService.selectAllTicket(pageNumRequest));
    }

}

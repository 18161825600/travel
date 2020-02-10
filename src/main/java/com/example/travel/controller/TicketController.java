package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.AddTicketRequest;
import com.example.travel.request.UpdateTicketRequest;
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
            return TravelJsonResult.ok("添加成功");
        }else return TravelJsonResult.errorMsg("添加失败");
    }

    @DeleteMapping(value = "delete/ticket")
    public TravelJsonResult<String> deleteTicket( Long id){
        Integer integer = ticketService.deleteTicket(id);
        if(integer==1){
            return TravelJsonResult.ok("删除成功");
        }else return TravelJsonResult.errorMsg("删除失败");
    }

    @PutMapping(value = "update/ticket")
    public TravelJsonResult<String> updateTicket(@RequestBody UpdateTicketRequest updateTicketRequest){
        Integer integer = ticketService.updateTicket(updateTicketRequest);
        if(integer==1){
            return TravelJsonResult.ok("修改成功");
        }else return TravelJsonResult.errorMsg("修改失败");
    }

    @GetMapping(value = "select/ticket/by/id")
    public TravelJsonResult<TicketResponse> selectTicketById(Long id){
        return TravelJsonResult.ok(ticketService.selectTicketById(id));
    }

    @GetMapping(value = "select/all/ticket")
    public TravelJsonResult<PageInfo<TicketResponse>> selectAllTicket(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(ticketService.selectAllTicket(pageNum));
    }

}

package com.cz.web.controller;

import com.cz.api.service.IOrderService;
import com.cz.dto.order.OrderTrackDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by jomalone_jia on 2017/11/24.
 */
@RestController
@RequestMapping("/order")
@Api(value = "/order", description = "Item Controller")
public class OrderController {
    private static Logger _log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService orderService;

    @GetMapping("/list")
    @ApiOperation(value = "order list")
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize
    ) {
        try {
            return ResponseEntity.ok(orderService.listOrders(pageNum, pageSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list orders failed");
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "update order status")
    public ResponseEntity<?> updateStatus(@RequestBody OrderTrackDto orderTrackD)  {
        try {
            orderService.updateStatus(orderTrackD.getOrderId(), orderTrackD.getTrackInformation());
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("update order status failed");
    }
}

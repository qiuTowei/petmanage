package com.neo.web;


import com.neo.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RountController {
    @GetMapping("/")
    public String index() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String toindex() {
        return "index";
    }

    @GetMapping("/adminMain")
    public String toAdminMain() {
        return "adminMain";
    }
    @GetMapping("/main")
    public String toMain() {
        return "main";
    }

    @GetMapping("/userInfo")
    public String toUserInfo() {
        return "userInfo";
    }

    @GetMapping("/comment")
    public String toComment(Comment comment, Model model) {
        model.addAttribute("billID",comment.getBillID());
        return "comment";
    }
    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }
    @GetMapping("/toBookList")
    public String toBookList() {
        return "bookList";
    }
    @GetMapping("/toType")
    public String toType() {
        return "type";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/toCar")
    public String toCar() {
        return "car";
    }
    @GetMapping("/toBill")
    public String toBill() {
        return "bill";
    }

    @GetMapping("/dindan")
    public String todindan() {
        return "orderList";
    }

    @GetMapping("/showMyElective")
    public String showMyElective() {
        return "showMyElective";
    }

      @GetMapping("/toAddFresh")
    public String toAddFresh() {
        return "addFresh";
    }
    @GetMapping("/toAddOrder")
    public String toAddOrder() {
        return "addOrder";
    }


    @GetMapping("/toAddType")
    public String toAddType() {
        return "addType";
    }

    @GetMapping("/toEditType")
    public String toEditType(Type type, Model model) {
        model.addAttribute("typeID",type.getTypeID());
        model.addAttribute("typeName",type.getTypeName());
        return "editType";
    }

    @GetMapping("/toEditFresh")
    public String toEditFresh(Fresh fresh,Model model) {
        model.addAttribute("freshID",fresh.getFreshID());
        model.addAttribute("freshName",fresh.getFreshName());
        model.addAttribute("freshSize",fresh.getFreshSize());
        model.addAttribute("type",fresh.getType());
        model.addAttribute("freshPrice",fresh.getFreshPrice());
        model.addAttribute("url",fresh.getUrl());
        model.addAttribute("freshDetail",fresh.getFreshDetail());
        return "editFresh";
    }

    @GetMapping("/toEditOrder")
    public String toEditOrder(Order order,Model model) {
        model.addAttribute("orderID",order.getOrderID());
        model.addAttribute("orderName",order.getOrderName());
        model.addAttribute("orderMoney",order.getOrderMoney());
        model.addAttribute("remark",order.getRemark());
        model.addAttribute("userID",order.getUserID());
        model.addAttribute("month",order.getMonth());
        return "editOrder";
    }

    @GetMapping("/toEditUser")
    public String toEditUser(User user, Model model) {
        model.addAttribute("userID",user.getUserID());
        model.addAttribute("userName",user.getUserName());
        model.addAttribute("password",user.getPassword());
        model.addAttribute("address",user.getAddress());
        model.addAttribute("receiver",user.getReceiver());
        model.addAttribute("phone",user.getPhone());
        return "editUser";
    }


    @GetMapping("/toFreshList")
    public String toFreshList() {
        return "freshList";
    }

    @GetMapping("/toOrderList")
    public String toOrderList() {
        return "orderList";
    }

    @GetMapping("/toUserList")
    public String toUserList() {
        return "userList";
    }

    @GetMapping("/toCommentList")
    public String toCommentList() {
        return "commentList";
    }
    @GetMapping("/toAddLogistics")
    public String toAddLogistics(Bill bill, Model model) {
        model.addAttribute("billID",bill.getBillID());
        return "addLogistics";
    }

    @GetMapping("/toEditLogistics")
    public String toEditLogistics(Bill bill, Model model) {
        model.addAttribute("logistics",bill.getLogistics());
        model.addAttribute("logisticsID",bill.getLogisticsID());
        model.addAttribute("company",bill.getCompany());
        model.addAttribute("billID",bill.getBillID());
        return "editLogistics";
    }

    @GetMapping("/toLogisticsInfo")
    public String toLogisticsInfo(Bill bill, Model model) {
        model.addAttribute("logistics",bill.getLogistics());
        model.addAttribute("logisticsID",bill.getLogisticsID());
        model.addAttribute("company",bill.getCompany());
        model.addAttribute("billID",bill.getBillID());
        return "logisticsInfo";
    }

    @GetMapping("/toFreshDetail")
    public String toFreshDetail(Fresh fresh, Model model) {
        model.addAttribute("freshID",fresh.getFreshID());
        return "freshDetail";
    }
    @GetMapping("/top")
    public String top() {
        return "top";
    }
}
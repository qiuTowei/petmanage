package com.neo.web;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.neo.domain.Domain;
import com.neo.entity.*;
import com.neo.mapper.UserMapper;
import com.neo.quartz.UserTools;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PageHelper pageHelper;

    @Autowired
    private UserMapper userMapper;

    private Map<String, Object> map = new HashMap<>();

    //显示所有宠物列表
    @RequestMapping("/showFreshList")
    public Map<String, Object> showFreshList(int pageNum, int pageSize, Fresh fresh, HttpSession httpSession) {
        PageHelper.startPage(pageNum, pageSize);
        List<Fresh> freshList = userMapper.showFreshList(fresh);
        PageInfo<Fresh> pageInfo = new PageInfo<>(freshList);
        List<Type> typeList = userMapper.showTypeList();
        map.put("typeList", typeList);
        map.put("pageInfo", pageInfo);
        if (UserTools.isLogin(httpSession)) {
            map.put("isLogin", 1);
        } else {
            map.put("isLogin", 0);
        }
        return map;
    }

    //显示所有用户列表
    @RequestMapping("/showUserList")
    public Map<String, Object> showUserList(User user) {
        List<User> userList = userMapper.showUserList(user);
        map.put("userList", userList);
        return map;
    }

    //显示所有分类列表
    @RequestMapping("/showTypeList")
    public Map<String, Object> showTypeList(Type type, HttpSession httpSession) {
        List<Type> typeList = userMapper.showTypeList();
        map.put("typeList", typeList);
        return map;
    }

    //显示所有留言
    @RequestMapping("/showCommentList")
    public Map<String, Object> showCommentList(Comment comment, HttpSession httpSession) {
        List<Comment> commentList = userMapper.showCommentList(comment);
        map.put("commentList", commentList);
        return map;
    }

    //显示所有留言
    @RequestMapping("/showCommentListByFresh")
    public Map<String, Object> showCommentListByFresh(Comment comment, HttpSession httpSession) {
        comment.setUserID(UserTools.getUserID(httpSession));
        List<Comment> commentList = userMapper.showCommentList(comment);
        map.put("commentList", commentList);
        return map;
    }

    //根据登录状态显示菜单
    @RequestMapping("/getMenu")
    public Map<String, Object> getMenu(HttpSession httpSession) {
        if (UserTools.isLogin(httpSession)) {
            map.put("isLogin", 1);
        } else {
            map.put("isLogin", 0);
        }
        return map;
    }

    //注册
    @RequestMapping("/register")
    public Map<String, Object> register(User user, HttpServletRequest request, HttpSession session) {
        User user1 = userMapper.getRegister(user);
        Map<String, Object> map = new ConcurrentHashMap<>();
        if (user1 == null) {
            userMapper.insert(user);
            map.put("code", 200);
            map.put("message", "注册成功");
        } else {
            map.put("code", 404);
            map.put("message", "改用户已存在");
        }
        return map;
    }

    //添加购物车
    @RequestMapping("/addCar")
    public Map<String, Object> addCar(Car car, HttpSession session) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        User user = (User) session.getAttribute("userSession");
        if (user == null) {
            map.put("code", 404);
            return map;
        }
        car.setUserID(user.getUserID());
        List<Car> carList = userMapper.getCar(car);
        if (carList.size() > 0) {
            userMapper.editCarNum(car);
        } else {
            userMapper.addCar(car);
        }
        map.put("code", 200);
        return map;
    }

    //购物车列表
    @RequestMapping("/showCarList")
    public Map<String, Object> showCarList(Car car, HttpSession session) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        User user = (User) session.getAttribute("userSession");
        if (user == null) {
            map.put("code", 404);
        }
        car.setUserID(user.getUserID());
        int count = 0;
        List<Car> carList = userMapper.showCarList(car);
        if (carList.isEmpty()) {
            map.put("carCode", 0);
        } else {
            map.put("carCode", 1);
        }
        for (Car car1 : carList) {
            count = car1.getFreshPrice() * car1.getNum() + count;
        }
        map.put("code", 200);
        map.put("carList", carList);
        map.put("count", count);
        return map;
    }

    //管理员查看账单
    @RequestMapping("/showBillListByAdmin")
    public Map<String, Object> showBillListByAdmin(Bill bill) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        List<Bill> billList = userMapper.showBillListByAdmin(bill);
        map.put("code", 200);
        map.put("billList", billList);
        return map;
    }

    //下单
    @RequestMapping("/buy")
    public Map<String, Object> buy(@RequestBody Domain domain, HttpSession session) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        User user = (User) session.getAttribute("userSession");
        if (user == null) {
            map.put("code", 404);
        }
        List<Car> carList = domain.getCarList();
        for (Car car : carList) {
            car.setReceiver(domain.getReceiver());
            car.setPhone(domain.getPhone());
            car.setAddress(domain.getAddress());
            car.setBillState("未付款");
            userMapper.buy(car);
            userMapper.deleteCar(car);
        }
        map.put("code", 200);
        return map;
    }

    //用户订单列表
    @RequestMapping("/showBillList")
    public Map<String, Object> showBillList(Bill bill, HttpSession session) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        User user = (User) session.getAttribute("userSession");
        if (user == null) {
            map.put("code", 404);
        }
        bill.setUserID(user.getUserID());
        List<Bill> billList = userMapper.showBillList(bill);
        map.put("code", 200);
        map.put("carList", billList);
        return map;
    }

    //收货
    @RequestMapping("/takeOf")
    public Map<String, Object> takeOf(Bill bill, HttpSession session) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        User user = (User) session.getAttribute("userSession");
        if (user == null) {
            map.put("code", 404);
        }
        bill.setUserID(user.getUserID());
        bill.setBillState("已收货");
        userMapper.updateBillState(bill);
        map.put("code", 200);
        return map;
    }

    //付款
    @RequestMapping("/pay")
    public Map<String, Object> pay(Bill bill) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        bill.setBillState("已付款");
        userMapper.updateBillState(bill);
        map.put("code", 200);
        return map;
    }

    //取消
    @RequestMapping("/cancel")
    public Map<String, Object> cancel(Bill bill) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        bill.setBillState("已取消");
        userMapper.updateBillState(bill);
        map.put("code", 200);
        return map;
    }

    //取消
    @RequestMapping("/reject")
    public Map<String, Object> reject(Bill bill) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        bill.setBillState("已退货");
        userMapper.updateBillState(bill);
        map.put("code", 200);
        return map;
    }

    //发货
    @RequestMapping("/send")
    public Map<String, Object> send(Bill bill) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        bill.setBillState("已发货");
        userMapper.updateBillState(bill);
        userMapper.updateBill(bill);
        map.put("code", 200);
        return map;
    }

    //发货
    @RequestMapping("/updateLogistics")
    public Map<String, Object> updateLogistics(Bill bill) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.updateLogistics(bill);
        map.put("code", 200);
        return map;
    }

    //登陆
    @RequestMapping("/dologin")
    public Map<String, Object> login(User User, HttpServletRequest request, HttpSession session) {
        User = userMapper.getOneByNameAndPass(User);
        Map<String, Object> map = new ConcurrentHashMap<>();
        if (User != null) {
            map.put("code", 200);
            map.put("message", "登录成功");
            saveLoginStatus(request, User);
        } else {
            map.put("code", 404);
            map.put("message", "用户名密码错误");
        }
        return map;
    }

    //退出登录
    @RequestMapping("/quit")
    public Map<String, Object> quit(HttpSession session) {
        session.invalidate();
        map.put("code", 200);
        return map;
    }

    //新增宠物
    @RequestMapping("/addFresh")
    public Map<String, Object> addFresh(Fresh fresh) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.addFresh(fresh);
        map.put("code", 200);
        return map;
    }

    //删除宠物
    @RequestMapping("/deleteFresh")
    public Map<String, Object> deleteFresh(Fresh fresh) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.deleteFresh(fresh);
        map.put("code", 200);
        return map;
    }

    //修改宠物
    @RequestMapping("/editFresh")
    public Map<String, Object> editFresh(Fresh fresh) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.editFresh(fresh);
        map.put("code", 200);
        return map;
    }

    //新增分类
    @RequestMapping("/addType")
    public Map<String, Object> addType(Type type) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.addType(type);
        map.put("code", 200);
        return map;
    }

    //删除分类
    @RequestMapping("/deleteType")
    public Map<String, Object> deleteType(Type type) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.deleteType(type);
        map.put("code", 200);
        return map;
    }

    //修改分类
    @RequestMapping("/editType")
    public Map<String, Object> editFresh(Type type) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.editType(type);
        map.put("code", 200);
        return map;
    }

    //获取用户信息
    @RequestMapping("/getUserInfo")
    public Map<String, Object> getUserInfo(User user1, HttpSession session) {
        user1.setUserID(UserTools.getUserID(session));
        User user = userMapper.getUserByID(user1);
        map.put("user", user);
        map.put("code", 200);
        return map;
    }

    //删除分类
    @RequestMapping("/deleteUser")
    public Map<String, Object> deleteUser(User user) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.deleteUser(user);
        map.put("code", 200);
        return map;
    }

    //更新用户信息
    @RequestMapping("/updateUserInfo")
    public Map<String, Object> updateUserInfo(User user, HttpSession session) {
        if ("".equals(user.getUserID()) || null == user.getUserID()) {
            user.setUserID(UserTools.getUserID(session));
        }
        userMapper.updateUserInfo(user);
        map.put("code", 200);
        return map;
    }

    //删除留言
    @RequestMapping("/deleteComment")
    public Map<String, Object> deleteComment(Comment comment) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.deleteComment(comment);
        map.put("code", 200);
        return map;
    }

    //评价
    @RequestMapping("/comment")
    public Map<String, Object> comment(Comment comment, HttpSession session) {
        comment.setUserID(UserTools.getUserID(session));
        Bill bill = new Bill();
        bill.setBillID(comment.getBillID());
        bill.setBillState("已评价");
        userMapper.updateBillState(bill);
        userMapper.addComment(comment);
        map.put("code", 200);
        return map;
    }

    //删除购物车
    @RequestMapping("/deleteCar")
    public Map<String, Object> deleteCar(Car car, HttpSession session) {
        userMapper.deleteCar(car);
        map.put("code", 200);
        return map;
    }

    //查看详情
    @RequestMapping("/showOrderDetail")
    public Map<String, Object> showOrderDetail(Order order, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Order o = userMapper.showOrderDetail(order);
        map.put("order", o);
        return map;
    }

    //查看详情
    @RequestMapping("/showFreshDetail")
    public Map<String, Object> showFreshDetail(Fresh fresh1, HttpSession session) {
        Fresh fresh = userMapper.showFreshDetail(fresh1);
        map.put("fresh", fresh);
        return map;
    }

    //+1
    @RequestMapping("/plus")
    public Map<String, Object> plus(Car car, HttpSession session) {
        car.setNum(1);
        userMapper.plus(car);
        map.put("code", 200);
        return map;
    }

    //保存登陆信息
    public void saveLoginStatus(HttpServletRequest request, User User) {
        HttpSession session = request.getSession();
        session.setAttribute("userSession", User);
        User = (User) session.getAttribute("userSession");
        String UserID = User.getUserID();
        System.out.println(UserID);
        session.setMaxInactiveInterval(7 * 24 * 60 * 60);
    }

    @RequestMapping("/insertOrder")
    public Map<String, Object> insertOrder(Order order) {
        userMapper.insertOrder(order);
        map.put("code", 200);
        map.put("message", "新增月账单成功");
        return map;
    }

    @RequestMapping("/deleteOrder")
    public Map<String, Object> deleteOrder(Order order) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.deleteOrder(order);
        map.put("code", 200);
        map.put("message", "删除账单成功");
        return map;
    }

    @RequestMapping("/updateOrder")
    public Map<String, Object> updateOrder(Order order) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        userMapper.updateOrder(order);
        map.put("code", 200);
        map.put("message", "修改账单成功");
        return map;
    }

    @RequestMapping("/selectAllOrder")
    public Map<String, Object> selectAllOrder(Order order) {
        List<Order> orderList = userMapper.selectAllOrder(order);
        if (orderList.size() > 0) {
            map.put("code", 200);
            map.put("message", "查询所有账单成功");
            map.put("data", orderList);
        } else {
            map.put("code", 201);
            map.put("message", "暂无月账单数据");
        }

        return map;
    }

    /**
     * 查询个人账单
     *
     * @param
     * @return
     */
    @RequestMapping("/selectAllOrderByUser")
    public Map<String, Object> selectAllOrderByUser(Order order) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        List<Order> orderList = new ArrayList<>();
        if (!StringUtils.isEmpty(order.getUserID())) {
            orderList = userMapper.selectAllOrderByUser(order);
        } else {
            orderList = userMapper.selectAllOrder(order);
        }

        if (orderList.size() > 0) {
            map.put("code", 200);
            map.put("message", "查询个人账单成功");
            map.put("data", orderList);
        } else {
            map.put("code", 201);
            map.put("message", "暂无月账单数据");
        }


        return map;
    }

    /**
     * 查询月账单
     *
     * @param
     * @return
     */
    @RequestMapping("/selectAllOrderBymonth")
    public Map<String, Object> selectAllOrderBymonth(Order order) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        List<Order> orderList = new ArrayList<>();
        if (!StringUtils.isEmpty(order.getMonth())) {
            orderList = userMapper.selectAllOrderBymonth(order);
        } else {
            orderList = userMapper.selectAllOrder(order);
        }

        if (orderList.size() > 0) {
            map.put("code", 200);
            map.put("message", "查询月账单成功");
            map.put("data", orderList);
        } else {
            map.put("code", 201);
            map.put("message", "暂无月账单数据");
        }


        return map;
    }

}
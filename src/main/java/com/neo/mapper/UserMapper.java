package com.neo.mapper;


import com.neo.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

	@Select("SELECT * FROM user WHERE userName = #{userName} and password=#{password}")
	User getOneByNameAndPass(User user);
	@Select("<script>SELECT * FROM fresh where 1=1 <if test='freshName!=\"\" and freshName!=null'> and freshName like \"%\"#{freshName}\"%\" </if><if test='type!=\"\" and type!=null'> and type like \"%\"#{type}\"%\" </if></script>")
	List<Fresh> showFreshList(Fresh fresh);
	@Select("SELECT * FROM fresh where freshID=#{freshID}")
	Fresh showFreshDetail(Fresh fresh);
	@Insert("insert into car(freshID,userID,num) values(#{freshID},#{userID},1)")
	int addCar(Car car);
	@Select("select c.*,f.* from car c left join fresh f on c.freshID=f.freshID where userID=#{userID}")
	List<Car> showCarList(Car car);
	@Insert("insert into bill(billTime,freshID,userID,receiver,phone,address,billState) values(SYSDATE(),#{freshID},#{userID},#{receiver},#{phone},#{address},#{billState})")
	int buy(Car car);
	@Select("select b.billID,DATE_FORMAT(b.billTime,'%Y-%m-%d %H:%i:%s') as billTime,billState,f.freshID,f.freshName,f.freshSize,f.freshPrice,b.logistics,b.logisticsID,b.company from bill b left join fresh f on b.freshID=f.freshID where userID=#{userID} ")
	List<Bill> showBillList(Bill bill);
	@Update("update bill set billState='已收货' where billID=#{billID}")
	int takeOf(Bill bill);

	@Select("SELECT orderID,orderName,orderMoney,remark,DATE_FORMAT(orderDate,'%Y-%m-%d %H:%i:%s') as orderDate,userID,month FROM income")
	List<Order> selectAllOrder(Order order);

//	DATE_FORMAT(b.billTime,'%Y-%m-%d %H:%i:%s') as billTime

	@Select("SELECT orderID,orderName,orderMoney,remark,DATE_FORMAT(orderDate,'%Y-%m-%d %H:%i:%s') as orderDate,userID,month FROM income where userID=#{userID}")
	List<Order> selectAllOrderByUser(Order order);

	@Insert("insert into income(orderName,orderMoney,remark,orderDate,month,userID) values(#{orderName},#{orderMoney},#{remark},SYSDATE(),#{month},#{userID})")
	int insertOrder(Order order);

	@Select("SELECT orderID,orderName,orderMoney,remark,DATE_FORMAT(orderDate,'%Y-%m-%d %H:%i:%s') as orderDate,userID,month FROM income where month=#{month}")
	List<Order> selectAllOrderBymonth(Order order);

	@Select("SELECT *  from income where orderID=#{orderID}")
	Order showOrderDetail(Order order);

	@Delete("delete from income where orderID=#{orderID}")
	int deleteOrder(Order order);

	@Update("update income set orderName=#{orderName},orderMoney=#{orderMoney},remark=#{remark},orderDate=SYSDATE(),month=#{month} where orderID=#{orderID}")
	int updateOrder(Order order);

	@Delete("delete from car where carID=#{carID}")
	int deleteCar(Car car);

	@Select("<script>select b.billID,DATE_FORMAT(b.billTime,'%Y-%m-%d %H:%i:%s') as billTime,billState,f.freshID,f.freshName,f.freshSize,f.freshPrice,b.receiver,b.phone,b.address,u.userName,b.logistics,b.logisticsID,b.company from bill b left join fresh f on b.freshID=f.freshID left join user u on b.userID=u.userID where 1=1 <if test='type!=\"\" and type!=null'> and f.type like \"%\"#{type}\"%\" </if></script>")
	List<Bill> showBillListByAdmin(Bill bill);

	@Update("update bill set billState='已发货' where billID=#{billID}")
	int send(Bill bill);

	@Update("update bill set billState=#{billState} where billID=#{billID}")
	int updateBillState(Bill bill);

	@Update("update bill set logisticsID=#{logisticsID},company=#{company},logistics=#{logistics} where billID=#{billID}")
	int updateBill(Bill bill);


	@Update("update bill set logistics=#{logistics} where billID=#{billID}")
	int updateLogistics(Bill bill);

	@Select("SELECT * FROM user WHERE userName=#{userName}")
	User getRegister(User user);

	@Select("SELECT * FROM user WHERE userID = #{userID}")
	User getUserByID(User user);

	@Insert("insert into user (userName,password) values(#{userName},#{password})")
	int insert(User user);

	@Select("select * from car where userID=#{userID} and freshID=#{freshID}")
	List<Car> getCar(Car car);

	@Select("select * from type")
	List<Type> showTypeList();

	@Select("select * from user")
	List<User> showUserList(User user);

	@Select("<script>select * from comment c left join user u on c.userID=u.userID left join bill b on b.billID=c.billID left join fresh f on f.freshID=b.freshID where 1=1 <if test='freshID!=\"\" and freshID!=null'> and f.freshID=#{freshID} </if></script>")
	List<Comment> showCommentList(Comment comment);

	@Update("update car set num=num+1 where userID=#{userID} and freshID=#{freshID}")
	int editCarNum(Car car);

	@Insert("insert into fresh (freshName,freshSize,freshPrice,url,type,freshDetail) values(#{freshName},#{freshSize},#{freshPrice},#{url},#{type},#{freshDetail	})")
	int addFresh(Fresh fresh);

	@Update("update fresh set freshName=#{freshName},freshSize=#{freshSize},freshPrice=#{freshPrice},url=#{url},type=#{type},freshDetail=#{freshDetail}  where freshID=#{freshID}")
	int editFresh(Fresh fresh);

	@Delete("delete from fresh where freshID=#{freshID}")
	int deleteFresh(Fresh fresh);

	@Insert("insert into type (typeName) values(#{typeName})")
	int addType(Type type);

	@Delete("delete from type where typeID=#{typeID}")
	int deleteType(Type type);

	@Update("update type set typeName=#{typeName} where typeID=#{typeID}")
	int editType(Type type);

	@Update("update user set userName=#{userName}, password=#{password},address=#{address},phone=#{phone},receiver=#{receiver}  where userID=#{userID}")
	int updateUserInfo(User user);


	@Delete("delete from user where userID=#{userID}")
	int deleteUser(User user);

	@Insert("insert into comment (userID,content,billID) values(#{userID},#{content},#{billID})")
	int addComment(Comment comment);

	@Delete("delete from comment where commentID=#{commentID}")
	int deleteComment(Comment comment);

	@Update("update car set num=num+#{num} where carID=#{carID}")
	int plus(Car car);


	@Select("SELECT * FROM comment c " +
			"left join user u on c.userID=u.userID")
	List<Comment> commentList(Comment comment);
}
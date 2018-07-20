package com.lingnan.usersys.usermgr.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import com.lingnan.usersys.common.exception.DaoException;
import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.domain.UserVO;
import com.lingnan.usersys.usermgr.view.AdminFrame;

/**
 * 用户dao接口的实现类
 * @author 曾洁义
 *
 */
public class UserDaoImpl implements UserDao {
	/**
	 * 数据库连接
	 */
	private Connection conn;
	
	/**
	 * 构造方法
	 * @param conn 数据库连接
	 */
	public UserDaoImpl(Connection conn) {
		this.conn = conn;//给属性赋初始化值
	}

	/**
	 * 用户登陆
	 * @param _userName 用户名称
	 * @param _passwd 用户密码
	 * @return 用户信息
	 */
	public UserVO login(String _userName, String _passwd) {
		PreparedStatement prep = null;//声明预编译对象变量，用于进行数据库操作的载体
		ResultSet rs = null;//声明结果集对象变量，用于保存数据库查询结果
		UserVO user = null;//声明用户对象变量，用于保存从结果集中提取出来的数据
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			prep = conn.prepareStatement
					("select * from t_user where userName = ? and passwd = ? and status = 1");
			prep.setString(1, _userName);//调用预编译对象的setXXX方法给？赋值
			prep.setString(2, _passwd);
			rs = prep.executeQuery();//调用预编译对象的executeQuery方法，执行查询操作，赋值给结果集对象变量
			if(rs.next()){//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中
				user = new UserVO();//创建一个新用户对象，赋值给用户对象变量
				/**
				 * 调用结果集对象的getXXX方法，取出各个字段的值
				 * 在调用用户对象的setXXX方法，给属性赋值
				 * 最后新创建的对象中包含了查询结果中的所有字段的值
				 */
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setPasswd(rs.getString("passwd"));
				user.setMailbox(rs.getString("mailbox"));
				user.setPower(rs.getString("power"));
				user.setBirth(rs.getDate("birth"));
				user.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {//如果出现异常，输出异常信息
			throw new DaoException("登陆时SQL语句出现错误",e);//将异常封装为自定义异常类
		}finally{
			DBUtils.closeStatement(rs, prep);//调用数据库工具类，关闭结果集和声明对象
		}
		/**
		 * 返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象值为null
		 */
		return user;
	}
	
	/**
	 * 添加或注册用户
	 * @param user 用户信息
	 * @return 成功返回true，否则返回false
	 */
	public boolean addUser(UserVO user) {
		boolean flag = false;//声明结果变量，并赋初值为假
		/**
		 * 调用UserVO的getXXX方法，获取到用户的信息
		 */
		String userName = user.getUserName();//用户姓名
		String passwd = user.getPasswd();//用户密码
		String mailbox = user.getMailbox();//用户邮箱
		String power = user.getPower();//用户权限
		Date birth = user.getBirth();//用户生日
		PreparedStatement prep = null;//声明预编译对象变量，用于进行数据库操作的载体
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			prep = conn.prepareStatement("insert into t_user(userID, userName, passwd, mailbox, power, birth) values(user_seq.nextval, ?, ?, ?, ?, ?)");
			prep.setString(1, userName);//调用预编译对象的setXXX方法给？赋值
			prep.setString(2, passwd);
			prep.setString(3, mailbox);
			prep.setString(4, power);
			prep.setDate(5, new java.sql.Date(birth.getTime()));//将java.util.Date 转换为 java.sql.Date	
			prep.executeUpdate();//调用预编译对象的executeQuery方法，执行查询操作
			flag = true;//将结果变量赋为真		
		} catch (SQLException e) {//如果出现异常，输出异常信息
			throw new DaoException("添加或注册用户时SQL语句出现错误",e);//将异常封装为自定义异常类
		}finally{
			DBUtils.closeStatement(null, prep);//调用数据库工具类，关闭结果集和声明对象
		}		
		return flag;
	}

	/**
	 * 根据userID删除用户
	 * @param userID 用户ID
	 * @return 成功返回true,失败返回false
	 */
	public boolean deleteUser(int _userID) {
		boolean flag = false;
		PreparedStatement prep = null;	
		try {
			prep = conn.prepareStatement("update t_user set status = 0 where userID = ?");
			prep.setInt(1, _userID);	
			prep.executeUpdate();
			flag = true;		
		} catch (SQLException e) {
			throw new DaoException("删除用户时SQL语句出现错误",e);
		}finally{
			DBUtils.closeStatement(null, prep);
		}		
		return flag;
	}

	/**
	 * 统计用户表的记录数量
	 * @return 返回表的记录总数
	 */
	public int countUserNum() {
		Statement stat = null;
		ResultSet rs = null;
		int count;//声明一个变量用来记录用户表的总记录数
		try {			
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			rs = stat.executeQuery("select count(*) from t_user");//执行SQL语句并处理结果集
			rs.next();
			count = rs.getInt(1);//用户表中的总行数			
		}  catch (SQLException e) {
			throw new DaoException("统计用户表记录数时SQL语句出现错误",e);
		}finally{
			DBUtils.closeStatement(rs, stat);
		}		
		return count;
	}
	
	/**
	 * 分页查询
	 * @param pageNo 需要查询的页码数
	 * @param pageSize 每页显示记录的行数
	 * @return 符合分页要求的用户信息
	 */
	public Vector<UserVO> findUserPage(int pageNo, int pageSize) {
		Vector<UserVO> v = new Vector<UserVO>();//声明一个动态数组用来存放查询到的用户信息
		PreparedStatement prep = null;//声明预编译对象变量，用于进行数据库操作的载体
		ResultSet rs = null;//声明结果集对象变量，用于保存数据库查询结果
		UserVO user = null;//声明用户对象变量，用于保存从结果集中提取出来的数据
		try {			
			prep = conn.prepareStatement("select * from (select t2.*,rownum rn from (select t1.* from t_user t1 order by userID) t2) " +
					"where rn>? and rn<=?");
			prep.setInt(1, (pageNo-1)*pageSize);//最小行数
			prep.setInt(2, pageNo*pageSize);//最大行数
			rs = prep.executeQuery();//执行SQL语句并处理结果集
		
			while(rs.next()){//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中,再加入数组中
				user = new UserVO();//创建一个新用户对象，赋值给用户对象变量
				/**
				 * 调用结果集对象的getXXX方法，取出各个字段的值
				 * 在调用用户对象的setXXX方法，给属性赋值
				 * 最后新创建的对象中包含了查询结果中的所有字段的值
				 */
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setPasswd(rs.getString("passwd"));
				user.setMailbox(rs.getString("mailbox"));
				user.setPower(rs.getString("power"));
				user.setBirth(rs.getDate("birth"));
				user.setStatus(rs.getInt("status"));
				v.add(user);//将对象信息存进数组中
			}
		} catch (SQLException e) {//如果出现异常，输出异常信息
			throw new DaoException("根据userName查询用户信息时SQL语句出现错误",e);//将异常封装为自定义异常类
		}finally{
			DBUtils.closeStatement(rs, prep);//调用数据库工具类，关闭结果集和声明对象
		}
		/**
		 * 返回用动态数组，如果查询结果不为空，该数组中封装了查询结果中的对象数据
		 * 如果查询结果为空，该数组的大小为0
		 */
		return v;
	}
	
	/**
	 * 查找所有用户的信息(当记录大于10条时分页显示查询结果，每页10条)
	 * @return 所有的用户信息
	 */
	public Vector<UserVO> findAllUserPageShow() {
		Scanner reader = new Scanner(System.in);
		Vector<UserVO> v = new Vector<UserVO>();		
		PreparedStatement prep = null;
		ResultSet rs = null;
		int max,min,page,count;
		try {			
			count = this.countUserNum();//表中的总行数			
			if(count%10 == 0){//分页数
				page = count/10;//每页10条数据
			}
			else{
				page = (count/10)+1;
			}						
			max = 10;//最大记录数，即每页的记录数
			min = 0;//最小记录数						
			for(int i=1; i<=page; i++){//分页输出
				v.clear();//初始化数组
				rs = null;
				prep = conn.prepareStatement("select * from (select t2.*,rownum rn from (select t1.* from t_user t1 order by userID) t2) where rn > ? and rn <= ?");
				prep.setInt(1, min);
				prep.setInt(2, max);
				rs = prep.executeQuery();//执行SQL语句并处理结果集	
				while(rs.next()){
					UserVO user = new UserVO();
					/**
					 * 调用结果集对象的getXXX方法，取出各个字段的值
					 * 在调用用户对象的setXXX方法，给属性赋值
					 * 最后新创建的对象中包含了查询结果中的所有字段的值
					 */
					user.setUserID(rs.getInt("userID"));
					user.setUserName(rs.getString("userName"));
					user.setPasswd(rs.getString("passwd"));
					user.setMailbox(rs.getString("mailbox"));
					user.setPower(rs.getString("power"));
					user.setBirth(rs.getDate("birth"));
					user.setStatus(rs.getInt("status"));
					v.add(user);
				}				
				if( v.firstElement() == null){
					System.out.println("记录为空！");
				}				
				if(count<=10){//记录数小于或等于10条时，退出分页
					System.out.println("\n----------------------- *所有用户信息* -------------------------------");
					for(UserVO user1 : v){
						System.out.println(user1.getUserID()+"\t"+user1.getUserName()+"\t"+user1.getPasswd()+"\t"+user1.getMailbox()+"\t"+user1.getPower()+"\t"+user1.getBirth()+"\t"+user1.getStatus());
					}
					System.out.println("-------------------------------------------------------------------\n");
					break;	
				}
				else{//记录大于10条时才分页
					System.out.println("\n数据共由有"+count+"条，分为"+page+"页    ");
					System.out.println("-------------------------- *第"+i+"页* ----------------------------------");
					for(UserVO user1 : v){
						System.out.println(user1.getUserID()+"\t"+user1.getUserName()+"\t"+user1.getPasswd()+"\t"+user1.getMailbox()+"\t"+user1.getPower()+"\t"+user1.getBirth()+"\t"+user1.getStatus());
					}
					System.out.println("--------------------------------------------------------------------");
					
					System.out.println("\n------------------ #请输入分页选择# ---------------------");
					System.out.println("1.上一页                2.下一页                      3.退出分页查询");
					System.out.println("-----------------------------------------------------");
					System.out.print(">>>请选择:");
					int choose;					
					while(true){
						try{
							choose = Integer.parseInt(reader.next());
							break;//中断该循环，进入下一步操作：choose值判断
						} catch(Exception e){
							//出现异常时，提示错误信息，需重新输入
							System.out.println("输入错误，只能输入1~3数字，请重新输入！");
						}
					}						
					/**
					 * 判断用户输入值并进行相应的操作
					 */
					switch(choose){
					case 1://上一页
						if(max == 10){//第一页，无下一页
							System.out.println("这是首页，无上一页......");
							i = i - 1;
						}
						else{
							max = max-10;
							min = min-10;
							i = i - 2;
						}				
						break;
					case 2://下一页
						if(max == page*10){//第一页，无下一页
							System.out.println("这是尾页，无下一页......");
							i = i - 1;
						}
						else{
							max = max+10;
							min = min+10;
						}				
						break;
					case 3://退出分页查询界面
						new AdminFrame(null).searchShow();;
						break;
					default:
						System.out.println("输入错误，只能输入1~3数字，请重新输入！");
					}	
				}						
			}	
		}  catch (SQLException e) {
			throw new DaoException("查询所有用户信息时SQL语句出现错误",e);
		}finally{
			DBUtils.closeStatement(rs, prep);
		}		
		/**
		 * 返回用动态数组，如果查询结果不为空，该数组中封装了查询结果中的对象数据
		 * 如果查询结果为空，该数组的大小为0
		 */
		return v;
	}
	
	/**
	 * 根据userID查询用户信息
	 * @param _userID 用户ID
	 * @return 用户信息
	 */
	public UserVO findUserByUserID(int _userID) {
		PreparedStatement prep = null;//声明预编译对象变量，用于进行数据库操作的载体
		ResultSet rs = null;//声明结果集对象变量，用于保存数据库查询结果
		UserVO user = null;//声明用户对象变量，用于保存从结果集中提取出来的数据
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			prep = conn.prepareStatement("select * from t_user where userID = ?");
			prep.setInt(1, _userID);//调用预编译对象的setXXX方法给？赋值
			rs = prep.executeQuery();//调用预编译对象的executeQuery方法，执行查询操作，赋值给结果集对象变量
			if(rs.next()){//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中
				user = new UserVO();//创建一个新用户对象，赋值给用户对象变量
				/**
				 * 调用结果集对象的getXXX方法，取出各个字段的值
				 * 在调用用户对象的setXXX方法，给属性赋值
				 * 最后新创建的对象中包含了查询结果中的所有字段的值
				 */
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setPasswd(rs.getString("passwd"));
				user.setMailbox(rs.getString("mailbox"));
				user.setPower(rs.getString("power"));
				user.setBirth(rs.getDate("birth"));
				user.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {//如果出现异常，输出异常信息
			throw new DaoException("登陆时SQL语句出现错误",e);//将异常封装为自定义异常类
		}finally{
			DBUtils.closeStatement(rs, prep);//调用数据库工具类，关闭结果集和声明对象
		}
		/**
		 * 返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象值为null
		 */
		return user;
	}
	
	/**
	 * 根据userName查询用户信息
	 * @param _userName 用户名称
	 * @return 符合条件的用户信息
	 */
	public Vector<UserVO> findUserByUserName(String _userName){
		Vector<UserVO> v = new Vector<UserVO>();//声明一个动态数组用来存放查询到的用户信息
		PreparedStatement prep = null;//声明预编译对象变量，用于进行数据库操作的载体
		ResultSet rs = null;//声明结果集对象变量，用于保存数据库查询结果
		UserVO user = null;//声明用户对象变量，用于保存从结果集中提取出来的数据
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			prep = conn.prepareStatement("select * from t_user where userName like ? order by userID");
			prep.setString(1, "%"+_userName+"%");//调用预编译对象的setXXX方法给？赋值
			rs = prep.executeQuery();//调用预编译对象的executeQuery方法，执行查询操作，赋值给结果集对象变量
			while(rs.next()){//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中,再加入数组中
				user = new UserVO();//创建一个新用户对象，赋值给用户对象变量
				/**
				 * 调用结果集对象的getXXX方法，取出各个字段的值
				 * 在调用用户对象的setXXX方法，给属性赋值
				 * 最后新创建的对象中包含了查询结果中的所有字段的值
				 */
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setPasswd(rs.getString("passwd"));
				user.setMailbox(rs.getString("mailbox"));
				user.setPower(rs.getString("power"));
				user.setBirth(rs.getDate("birth"));
				user.setStatus(rs.getInt("status"));
				v.add(user);
			}
		} catch (SQLException e) {//如果出现异常，输出异常信息
			throw new DaoException("根据userName查询用户信息时SQL语句出现错误",e);//将异常封装为自定义异常类
		}finally{
			DBUtils.closeStatement(rs, prep);//调用数据库工具类，关闭结果集和声明对象
		}
		/**
		 * 返回用动态数组，如果查询结果不为空，该数组中封装了查询结果中的对象数据
		 * 如果查询结果为空，该数组的大小为0
		 */
		return v;
	}

	/**
	 * 更新用户信息
	 * @param user 用户信息
	 * @return 成功返回true,失败返回false
	 */
	public boolean updateUser(UserVO user) {
		boolean flag = false;
		int userID = user.getUserID();//用户编号
		String userName = user.getUserName();//用户姓名
		String passwd = user.getPasswd();//用户密码
		String mailbox = user.getMailbox();//用户邮箱
		String power = user.getPower();//用户权限
		Date birth = user.getBirth();//用户生日
		int status = user.getStatus();//用户信息状态
		PreparedStatement prep = null;					
		try {
			prep = conn.prepareStatement("update t_user set userName=?, passwd=?, mailbox=?, power=?, birth=?, status=? where userID=? ");	
			prep.setString(1, userName);	
			prep.setString(2, passwd);
			prep.setString(3, mailbox);
			prep.setString(4, power);
			prep.setDate(5, new java.sql.Date(birth.getTime()));//将java.util.Date 转换为 java.sql.Date
			prep.setInt(6, status);
			prep.setInt(7, userID);
			prep.executeUpdate();
			flag = true;		
		} catch (SQLException e) {
			throw new DaoException("更新用户信息时SQL语句出现错误",e);//将异常封装为自定义异常类
		}finally{
			DBUtils.closeStatement(null, prep);
		}		
		return flag;
	}

	/**
	 * 根据信息状态查询用户信息
	 * @param _status 信息状态(1为有效，0为无效，2为全部信息)
	 * @return 返回符合信息状态的用户信息
	 */
	public Vector<UserVO> findAllUser(int _status) {
		Scanner reader = new Scanner(System.in);
		Vector<UserVO> v = new Vector<UserVO>();		
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {	
				if(_status == 2){
					prep = conn.prepareStatement("select * from t_user order by userID");
				}
				else{
					prep = conn.prepareStatement("select * from t_user where status = ? order by userID");
					prep.setInt(1, _status);
				}				
				rs = prep.executeQuery();//执行SQL语句并处理结果集	
				while(rs.next()){
					UserVO user = new UserVO();
					/**
					 * 调用结果集对象的getXXX方法，取出各个字段的值
					 * 在调用用户对象的setXXX方法，给属性赋值
					 * 最后新创建的对象中包含了查询结果中的所有字段的值
					 */
					user.setUserID(rs.getInt("userID"));
					user.setUserName(rs.getString("userName"));
					user.setPasswd(rs.getString("passwd"));
					user.setMailbox(rs.getString("mailbox"));
					user.setPower(rs.getString("power"));
					user.setBirth(rs.getDate("birth"));
					user.setStatus(rs.getInt("status"));
					v.add(user);
				}											
		}  catch (SQLException e) {
			throw new DaoException("查询所有用户信息时SQL语句出现错误",e);
		}finally{
			DBUtils.closeStatement(rs, prep);
		}		
		/**
		 * 返回用动态数组，如果查询结果不为空，该数组中封装了查询结果中的对象数据
		 * 如果查询结果为空，该数组的大小为0
		 */
		return v;
	}

	
	
	
}

package com.lingnan.usersys.usermgr.view;

import java.util.Scanner;
import java.util.Vector;

import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 管理员视图类
 * 用于用户管理：权限分为普通用户和管理员
 * @author 曾洁义
 *
 */
public class AdminFrame extends NormalFrame {
	/**
	 * 带参的构造方法
	 * @param 父类的用户对象
	 */
	public AdminFrame(UserVO user) {
		super(user);
	}
	
	/**
	 * 用户登陆成功后的界面显示
	 */
	public void loginSuccShow(){
		String power = user.getPower();
		String userName = user.getUserName();
		System.out.println(userName+"您好，您的权限是"+power+"......");
		System.out.println("----------------------------------------");
		if(power.equals("管理员")){
			this.show();
		}
		else{//如果是普通用户，去到普通用户的主窗体
			new NormalFrame(user).show();
		}
	}

	/**
	 * 管理员用户主窗体
	 */
	public void show(){
		Scanner reader = new Scanner(System.in);		
		while(true){
			System.out.println("\n+----------- #管理员登陆主窗体# ----------+");
			System.out.println("|              1.添加用户                                       |");
			System.out.println("|              2.删除用户                                       |");
			System.out.println("|              3.修改用户                                       |");
			System.out.println("|              4.查询用户                                       |");
			System.out.println("|              5.退出登陆                                       |");
			System.out.println("+-------------------------------------+");
			System.out.print(">>>请选择:");
			int choose = -1;
			while(true){
				try{
					choose = Integer.parseInt(reader.next());
					break;//中断该循环，进入下一步操作：choose值判断
				} catch(Exception e){
					//出现异常时，提示错误信息，需重新输入
					System.out.println("输入错误，只能输入1~5数字，请重新输入！");
				}
			}
			
			/**
			 * 判断用户输入值并进行相应的操作
			 */
			switch(choose){
			case 1://添加用户
				new IndexFrame().addShow("添加用户");
				break;
			case 2://删除用户
				this.deleteShow();
				break;
			case 3://修改用户
				new IndexFrame().updateShow("管理员", user);
				break;
			case 4://查询用户
				new IndexFrame().searchShow();//去到查询子菜单
				break;
			case 5:
				System.out.println("退出登陆成功......");
				new IndexFrame().show();
				break;
			default:
				System.out.println("输入错误，只能输入1~5数字，请重新输入！");
			}
		}
	}
	
	/**
	 * 管理员删除用户界面
	 */
	public void deleteShow() {
		Scanner reader = new Scanner(System.in);
		UserController uc = new UserController();
		try{
				System.out.println("\n------------ *管理员删除用户* ------------");
				System.out.print(">>请输入用户编号：");
				
				int userID = Integer.parseInt(reader.next());			
				if(new UserController().doFindUserByUserID(userID) !=null ){//查询表中是否有该用户
					user.setUserID(userID);
				}
				else{
					System.out.println("无该用户信息......");
					new AdminFrame(user).show();//回到管理员主窗体
				}
				
				if(uc.doDeleteUser(userID)){
					System.out.println("删除用户成功......");
				}
				else{
					System.out.println("删除用户失败......");
				}
				System.out.println("--------------------------------------");
				this.show();
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}
	}
	
	/**
	 * 管理员查询所有用户信息(当记录大于10条时分页显示查询结果，每页10条)界面
	 */
	public void finAllUserPageShow(){
		UserController uc = new UserController();
		Vector<UserVO> v = new Vector<UserVO>();
		v = uc.doFindAllUserPageShow();
		new IndexFrame().searchShow();//回到查询子菜单
	}
	
	/**
	 * 管理员根据信息状态(1为有效，0为无效，2为全部信息)查询用户信息界面
	 */
	public void finAllUserShow(){
		Scanner reader = new Scanner(System.in);
		UserController uc = new UserController();
		Vector<UserVO> v = new Vector<UserVO>();
		System.out.println("\n----------------------- *根据信息状态查询用户信息* ------------------------");		
		int status;		
		while(true){
			System.out.print(">>请输入信息状态(1为有效，0为无效，2为全部信息)：");
			while(true){
				try{				
					status = Integer.parseInt(reader.next());
					if(status ==0 || status == 1 || status == 2){
						break;
					}else{
						System.out.println("输入信息状态错误！");
						System.out.print(">>请重新输入信息状态(1为有效，0为无效，2为全部信息)：");
					}					
				} catch(Exception e){
					//出现异常时，提示错误信息，需重新输入
					System.out.println("输入信息状态错误！");
					System.out.print(">>请重新输入信息状态(1为有效，0为无效，2为全部信息)：");
				}
			}					
			
			v = uc.doFindAllUser(status);
			
			if(v.size()==0){
				System.out.println("该信息状态的记录为空......");
				System.out.println("---------------------------------------------------------------------");
				new IndexFrame().searchShow();//回到查询子菜单
			}			
			else{
				/**
				 * 判断用户输入值并进行相应的操作
				 */
				switch(status){
				case 0:			
					System.out.println("\n查询成功，无效的的记录有"+v.size()+"条......");
					break;
				case 1:
					System.out.println("\n查询成功，有效的的记录有"+v.size()+"条......");
					break;
				case 2:
					System.out.println("\n查询成功，共有用户记录"+v.size()+"条......");
					break;
				default:
					System.out.println("信息状态输入错误!");
				}		
			}	
			System.out.println("---------------------------------------------------------------------");
			for(UserVO user : v){
				System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());
			}
			System.out.println("---------------------------------------------------------------------");
			new IndexFrame().searchShow();//回到查询子菜单
		}					
	}
	
	/**
	 * 管理员根据userID查询用户信息界面
	 */
	public void finUserByUserIDShow(){
		Scanner reader = new Scanner(System.in);
		UserController uc = new UserController();
		UserVO user =null;
		while(true){
			try{
				System.out.print(">>请输入用户编号：");
				int _userID = Integer.parseInt(reader.next());
				user = uc.doFindUserByUserID(_userID);
				break;
			} catch(Exception e){
				System.out.println("输入有误，请重新输入！");
			}
		}				
		if(user !=null){
			System.out.println("\n------------------------ *查询结果* -----------------------------------");
			System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());		
			System.out.println("---------------------------------------------------------------------");
		}
		else{
			System.out.println("无该记录存在......");
		}
		new IndexFrame().searchShow();//回到查询子菜单
	}
	
	/**
	 * 管理员根据userName查询用户信息界面
	 */
	public void finUserByUserNameShow(){
		Scanner reader = new Scanner(System.in);
		Vector<UserVO> v = new Vector<UserVO>();
		UserController uc = new UserController();
		System.out.print(">>请输入用户名称（或名称关键字）：");
		String userName = reader.next();
		v = uc.doFindUserByUserName(userName);
		if(!v.isEmpty()){
			System.out.println("\n查询成功，用户名称中含"+userName+"的记录有"+v.size()+"条......");
			System.out.println("---------------------------------------------------------------------");
			for(UserVO user : v){
				System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());
			}
			System.out.println("---------------------------------------------------------------------");
		}
		else{
			System.out.println("无该记录存在......");
		}
		new IndexFrame().searchShow();//回到查询子菜单
	}
	
	/**
	 * 管理员分页查询界面
	 */
	public void finUserPageShow(){
		Scanner reader = new Scanner(System.in);
		Vector<UserVO> v = new Vector<UserVO>();
		UserController uc = new UserController();
		int pageNo = 0,pageSize = 0;
		while(true){
			try{
				System.out.print(">>请输入需要查询的页码数：");
				pageNo = Integer.parseInt(reader.next());
				System.out.print(">>请输入每页显示记录的行数：");
				pageSize = Integer.parseInt(reader.next());
				break;
			} catch(Exception e) {
				System.out.println("输入有误，请重新输入！");
			}		
		}				
		v = uc.doFindUserPage(pageNo, pageSize);
		if(!v.isEmpty()){
			System.out.println("\n----------------------- *第"+pageNo+"页        每页显示"+pageSize+"条记录* -----------------------");
			for(UserVO user : v){
				System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());
			}
			System.out.println("---------------------------------------------------------------------");
		}
		else{
			System.out.println("无该记录存在......");
		}
		new IndexFrame().searchShow();//回到查询子菜单
	}
}

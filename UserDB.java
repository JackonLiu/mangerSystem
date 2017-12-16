package mangerSystem;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
class UserDB{
	Connection conn = null;//数据库连接对象
	Statement stmt = null; //执行SQL语句会话对象
	PreparedStatement pstmt = null;//执行带参数的会话对象
	ResultSet rs = null; //结果集
	int i=2;
	//连接Mysql数据库
	public boolean ConnectMysql(){
		boolean flag = false;
		try{//捕获异常
			//1）装载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//设置数据库的url，驱动；数据库的绝对地址
			//2）连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","837216129");
			flag=true;
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}
	//登录验证，正确返回true，否则false	 
		public boolean isLoginSuccess(String userName,String password){
			boolean flag = false;
			if(ConnectMysql()){
			try {
				//创建会话
				stmt = conn.createStatement();
				//直接获取变量的值作为参数传递给sql
				String sql = "select * from user where name='"
						+userName+"' and password='"+password+"'";
				//执行查询
				rs = stmt.executeQuery(sql); //执行查询
				if(rs.next()) flag= true;
				else flag= false;
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    }
			return flag;
		} 
	//获取用户名,返回用户名列表	 
	public ArrayList<String> getusrName(){
		ArrayList<String> userList = new ArrayList<String>();
		if(ConnectMysql()){//连接数据库
		try {
			//创建会话
			stmt = conn.createStatement();
			String sql = "select name from user";
			//执行查询
			rs = stmt.executeQuery(sql); //执行查询
			//显示查询结果
			while(rs.next())
			{
				userList.add(rs.getString("name"));
			}
			return userList;
	   } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();stmt.close();conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
		}
		return userList;
	}
	//获取用户名,返回用户名列表	 
	public ArrayList<String> getusrInfo(String userName){
		ArrayList<String> userList = new ArrayList<String>();
		if(ConnectMysql()){//连接数据库
			try {
				//创建会话
				stmt = conn.createStatement();
				String sql = "select name,password,role from user"
						+" where name='"+userName+"'";
				//执行查询
				rs = stmt.executeQuery(sql); //执行查询
				//显示查询结果
				while(rs.next())
				{
					userList.add(rs.getString("name"));
					userList.add(rs.getString("password"));
					userList.add(rs.getString("role"));
				}
				return userList;
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally{
				try {
					rs.close();stmt.close();conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userList;
	}
	//增加用户记录,返回，0-失败，1-成功，-1-用户名存在
	public int insertUserInfo(String userName,String password,String role) throws UnsupportedEncodingException{
		int result = 0;
		if(ConnectMysql()){//连接数据库
		  try {
			//创建会话
			stmt = conn.createStatement();
			//判断用户名是否存在
			String sql = "select * from user where name='"+userName+"'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) result = -1;
			else{
			 sql = "insert into user(name,password,role) "
					+"values('"+userName+"','"+password+"','"+role+"')";
			 //执行查询
			// userName=new String(userName.getBytes("iso-8859-1"),"UTF-8");
			 result = stmt.executeUpdate(sql); //执行查询
			//显示查询结果
			}
			rs.close();
			stmt.close();
		  } catch (SQLException e) {
			e.printStackTrace();
		 }
		}
		return result;
	}
	
	//插入员工基本信息
    public int insertInfo(String staffNo,String  staffName,
    		String  staffClass,String staffPosition,String staffBasicSalary){
    	int result = 0;
		if(ConnectMysql()){//连接数据库
		  try {
			//创建会话
			stmt = conn.createStatement();
			//判断员工编号是否存在
			String sql = "select * from staffBasicInfo where staffNo='"+staffNo+"'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) result = -1;
			else{
			 sql = "insert into staffBasicInfo(staffNo,staffName,staffClass,staffPosition,staffBasicSalary) "
					+"values('"+staffNo+"','"+staffName+"','"+staffClass+"','"+staffPosition+"','"+staffBasicSalary+"')";
			 //执行查询
			 result = stmt.executeUpdate(sql); //执行查询
			//显示查询结果
			}
			rs.close();
			stmt.close();
		  } catch (SQLException e) {
			e.printStackTrace();
		 }
		}
		return result;
    	
    }
	
	
	
	//删除指定用户
	public int deleteUserInfo(String userName){
		int result = 0;
		if(ConnectMysql()){//连接数据库
		 try {
			//创建会话
			stmt = conn.createStatement();
			
			//直接获取变量的值作为参数传递给sql
			String sql = "delete from user where name='"
					+userName+"'";
			//执行查询
			result = stmt.executeUpdate(sql); //执行查询
			//显示查询结果
			stmt.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		 }
		}
		return result;
	}
	//修改指定用户的信息，当用户名和密码都正确后，且新密码、确认密码一致，则做修改操作
	public int updateUserInfo(String userName,String password,String newPassword){
		int result = 0;
		if(ConnectMysql()){//连接数据库
		try {
			//直接获取变量的值作为参数传递给sql
			String sql = "select * from user where name=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2,password);
			rs = pstmt.executeQuery();
			if (!rs.next()) result = -1;
			else{
			sql = "update user set password=? where name=?";
			//执行查询
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2,userName);
			result = pstmt.executeUpdate(); //执行查询
			//显示查询结果
			}
			pstmt.close();conn.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	  }
	return result;
   }
	public int insertSalary(String staffNo, String year, String month,String LeftDutyDays,double DutyReward ,
			double saleNum ,double saleReward,double projectReward, double staffBasicSalary,double totalSalary) {
		int result = 0;
		if(ConnectMysql()){//连接数据库
		  try {
			//创建会话
			stmt = conn.createStatement();
				
			String sql = "insert into staffSalaryInfo(staffNo,year,month,LeftDutyDays,DutyReward,"
			 		+ "projectReward,saleNum,saleReward,staffBasicSalary,totalSalary) "
					+"values('"+staffNo+"','"+year+"','"+month+"','"+LeftDutyDays+"','"+DutyReward+"','"+projectReward+"','"+saleNum+"','"+saleReward+"','"+staffBasicSalary+"','"+totalSalary+"')";
			 //执行查询
			 result = stmt.executeUpdate(sql); //执行查询
			//显示查询结果
		
			rs.close();
			stmt.close();
		  } catch (SQLException e) {
			e.printStackTrace();
		 }
		}
		return result;
    	
	}
	
	//查找staffsalaryinfo数据库中的信息并处理
	public Object [][] selectsalaryinfo(String staffNo,String staffName,String Year,String month) {
		
		ArrayList<String> salaryList = new ArrayList<String>();
		Object [][]list = new Object[30][9];
		int m=0;
		if(ConnectMysql()){//连接数据库
			try {
				String sql=null;
				//创建会话
				stmt = conn.createStatement();
				if(staffName!=null){
				i=1;
				System.out.println("where staffNo=");
				ArrayList<String> no=selectInfo(null,staffName);
				sql= "select * from staffsalaryinfo"
						+" where staffNo='"+no.get(0)+"'";
				}
				if(staffNo!=null&&Year==null){
				System.out.println("staffNo!=null&&Year==null");
					sql= "select * from staffsalaryinfo"
							+" where staffNo='"+staffNo+"'";
				}
				if(staffNo!=null&&Year!=null){
					sql= "select * from staffsalaryinfo where staffNo='"+staffNo+"' and Year='"+Year+"' and month='"+month+"'";
					System.out.println("where staffNo='"+staffNo+"' and Year='"+Year+"' and ");
				}
				//执行查询
				rs = stmt.executeQuery(sql); //执行查询
				//显示查询结果
				while(rs.next())
				{
					list[m][0]=rs.getString("staffNo");
					list[m][1]=rs.getString("Year");
					list[m][2]=rs.getString("month");
					list[m][3]=rs.getString("DutyReward");
					list[m][4]=String.valueOf(rs.getDouble("ProjectReward"));
					list[m][5]=String.valueOf(rs.getDouble("saleNum"));
					list[m][6]=String.valueOf(rs.getObject("saleReward"));
					list[m][7]=String.valueOf(rs.getObject("staffBasicSalary"));
					list[m][8]=String.valueOf(rs.getObject("totalSalary"));
					m++;
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();stmt.close();conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	public Object[] selectallStaffNo(String table1) {
		Object []list = new Object[30];
		if(ConnectMysql()){//连接数据库
			try {
				//创建会话
				stmt = conn.createStatement();
				String sql = "select staffNo from "+table1;
				//执行查询
				rs = stmt.executeQuery(sql); //执行查询
				//显示查询结果
				int i=0;
				while(rs.next())
				{
					list[i]=rs.getString("staffNo");
					i++;
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();stmt.close();conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	 public Object [][] selectall(String startyear, String startmonth, String endyear, String endmonth) {
			//查找staffsalaryinfo数据库中的所有信息并处理
		 	Object [][]list = new Object[30][9];
			if(ConnectMysql()){//连接数据库
				try {
					stmt = conn.createStatement();
					String sql = "select staffNo,Year,month,DutyReward,ProjectReward,saleNum,saleReward,staffBasicSalary,totalSalary from staffsalaryinfo where CONCAT(Year,month) >='"+startyear+startmonth+"'  and  CONCAT(Year,month) <='"+endyear+endmonth+"' ORDER BY concat(Year,month) DESC;";

					rs = stmt.executeQuery(sql); //执行查询
					int i=0;
					while(rs.next())
					{  
						list[i][0]=rs.getString("staffNo");
						list[i][1]=rs.getString("Year");
						list[i][2]=rs.getString("month");
						list[i][3]=rs.getString("DutyReward");
						list[i][4]=String.valueOf(rs.getDouble("ProjectReward"));
						list[i][5]=String.valueOf(rs.getDouble("saleNum"));
						list[i][6]=String.valueOf(rs.getObject("saleReward"));
						list[i][7]=String.valueOf(rs.getObject("staffBasicSalary"));
						list[i][8]=String.valueOf(rs.getObject("totalSalary"));
						i++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						rs.close();stmt.close();conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return list;
		}
	 
	 public ArrayList<String> selectName(String staffClass) {
			//查找staffbasicinfo数据库中的Name信息并处理
		 ArrayList<String> name = new ArrayList<String>();
		
			if(ConnectMysql()){//连接数据库
				try {
					//创建会话
					stmt = conn.createStatement();
					String sql = "select staffName from staffbasicinfo"
							+" where staffClass='"+staffClass+"'";
					rs = stmt.executeQuery(sql); //执行查询
					//显示查询结果\
					while(rs.next())
					{
						name.add(rs.getString("staffName"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						rs.close();stmt.close();conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return name;
		}
	 
	 	//修改员工信息
		public int updateInfo(String staffNo, String staffName,  String staffClass,String staffPosition, String staffBasicSalary) {
			
				int result = 0;
				if(ConnectMysql()){//连接数据库
				try {
					stmt = conn.createStatement();
				
						String sql = "UPDATE staffbasicinfo SET staffName='"+staffName+"',staffClass='"+staffClass+"',"
						 		+ "staffPosition='"+staffPosition+"',staffBasicSalary ='"+staffBasicSalary+"' WHERE staffNo='"+staffNo+"'";
							 result = stmt.executeUpdate(sql); //执行查询
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			  }
		
			return result;
		   
			
		}
		//删除员工信息
		public int deleteInfo(String staffNo) {
			int result = 0;
			if(ConnectMysql()){//连接数据库
			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM staffBasicinfo WHERE staffNo='"+staffNo+"'";
			
				 result = stmt.executeUpdate(sql); //执行查询
				 stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }

		return result;
			
			}
		

		//修改员工某个月的工资信息
		public int updateSalary(String staffNo, String year,String month, String LeftDutyDays,
				double DutyReward ,double saleNum ,double saleReward,
				double ProjectReward,double staffBasicSalary, double totalSalary) {
			int result = 0;
			if(ConnectMysql()){//连接数据库
			try {
				stmt = conn.createStatement();
				String sql = "UPDATE staffsalaryinfo SET LeftDutyDays= '"+LeftDutyDays+"',DutyReward= '"+DutyReward+"' ,saleNum= '"+saleNum+"' ,saleReward= '"+saleReward+"',"
						+ "ProjectReward = '"+ProjectReward+"',staffBasicSalary = '"+staffBasicSalary+"', totalSalary = '"+totalSalary+"' "
						+ "WHERE staffNo ='"+staffNo+"'  and year = '"+year+"' and month = '"+month+"'";
					 result = stmt.executeUpdate(sql); //执行查询
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }

	return result;
			
		}
		//删除该员工指定月份工资或所有工资。
		public int deletesalaryInfo(String staffNo,String Year,String month) {
			int result = 0;
			String sql;
			if(ConnectMysql()){//连接数据库
			try {
				stmt = conn.createStatement();
				if(month==null){
					sql = "DELETE FROM staffsalaryinfo WHERE staffNo='"+staffNo+"'";
				}
				else{
				sql = "DELETE FROM staffsalaryinfo WHERE staffNo='"+staffNo+"' and Year='"+Year+"' and  month='"+month+"'";
				}
				 result = stmt.executeUpdate(sql); //执行查询
				 stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
		return result;
			
			}
		
		public ArrayList<String> selectInfo(String staffNo,String staffName) {
			ArrayList<String> staffList = new ArrayList<String>();
			String sql;
			if(ConnectMysql()){//连接数据库
				try {
					//创建会话
				
					stmt = conn.createStatement();
					if(staffNo==null){
						System.out.println("selectInfo");
					sql = "select staffNo,staffName,staffClass,staffPosition,staffBasicSalary  from staffbasicinfo"
							+" where staffName='"+staffName+"'";}
					else{
						sql = "select staffNo,staffName,staffClass,staffPosition,staffBasicSalary  from staffbasicinfo"
								+" where staffNo='"+staffNo+"'";
					}
					//执行查询
				
					rs = stmt.executeQuery(sql); 
					while(rs.next())
					{
						staffList.add(rs.getString("staffNo"));
						staffList.add(rs.getString("staffName"));
						staffList.add(rs.getString("staffClass"));
						staffList.add(rs.getString("staffPosition"));
						staffList.add(rs.getString("staffBasicSalary"));
					}
					return staffList;
				} catch (SQLException e) {
				
					e.printStackTrace();
				}finally{
					if(i==2){
					try {
						rs.close();stmt.close();conn.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
				}}
			}
			return staffList;
	}
}


class Admin
{
    
    private int Student_id;
    private String Student_Name;
    private int Total_Marks;
    private String category;
    public Admin(int Student_id,String Student_Name,int Total_Marks,String category)
    {
        super();
        this.Student_id = Student_id;
        this.Student_Name = Student_Name;
        this.Total_Marks = Total_Marks;
        this.category = category;

    }
    public Admin(String Student_Name,int Total_Marks,String category)
    {
        super();
        this.Student_Name = Student_Name;
        this.Total_Marks = Total_Marks;
        this.category = category;

    }
    public int get_StudentId()
    {
        return Student_id;
    }
    public void set_StudentId(int Student_id)
    {
        this.Student_id = Student_id;
    }
    public int get_TotalMarks()
    {
        return Total_Marks;
    }
    public void set_TotalMarks(int Total_Marks)
    {
        this.Total_Marks = Total_Marks;
        
    }
    public String get_StudentName()
    {
        return Student_Name;
    }
    public void set_StudentName(String Student_Name)
    {
          this.Student_Name = Student_Name;
        
    }
     public String get_category()
    {
        return category;
    }
    public void set_category(String category)
    {
          this.category = category;
        
    }

    

}
 class Admin_DataBase_Object extends Admin{
   private String jdbc_url="";
   private String jdbc_username="";
   private String jdbc_password="";

   private static final String ADD="INSERT INTO students"+"(Student_Name,Total_Marks,category)  VALUES " + "(?,?,?);";
   private static final String UPDATE="update students set Student_Name=?,Total_Marks=?,Category=? where Student_id=?;";
   private static final String DELETE="delete from Students where Student_id=?;";
   private static final String Show_ALL="select * from Students";

   protected Connection getConnection()
   {
       Connection conn=null;
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           conn=DriverManager.getConnection(jdbc_url,jdbc_username,jdbc_password);

       }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       catch(ClassNotFoundException e)
       {
           e.printStackTrace();
       }
       return conn;

   }
   public void insert_student(Admin student)throws SQLException
   {
       try(Connection conn=getConnection(); Statement stmt=conn.Statement(ADD);)
       {
           Statement.setString(1,student.get_StudentName());
           Statement.setString(2,student.get_TotalMarks());
           Statement.setString(3,student.get_category());
           conn.executeUpdate();

       }catch (Exception e)
       {
           e.printStackTrace();
       }


   }
    public boolean Update_student(Admin student)throws SQLException
   {
       try(Connection conn=getConnection(); Statement stmt=conn.Statement(UPDATE);)
       {
           Statement.setString(1,student.get_StudentName());
           Statement.setString(2,student.get_TotalMarks());
           Statement.setString(3,student.get_category());
           Statement.setString(4,student.get_StudentId());
           rowUpdated = conn.executeUpdate()>0;

       }
       return rowUpdated;


   }
   public Admin selectAllStudents()
   {
      List<Admin> students=new ArrayList<>();
      
       try(Connection conn=getConnection(); Statement stmt=conn.Statement(Show_ALL);)
       {   
           System.out.println(stmt);
           Resultset rs=stmt.executeQuery();
           while(rs.next())
           {
               int Student_id=rs.getInt("Student_id");
               String Student_Name=rs.getString("Student_Name");
               int Total_Marks=rs.getInt("Total_Marks");
               String category=rs.getString("category");
               students.add(new Admin(Student_id,Student_Name,Total_Marks,category));
           }
           
           

       }catch (SQLException e)
       {
           e.printStackTrace();
       }

       return students;
   }

   public boolean deleteStudent(int Student_id)throws SQLException{
       boolean rowDeleted;
        try(Connection conn=getConnection(); Statement stmt=conn.Statement(DELETE);)
       {
          
          stmt.setInt(1,Student_id);
          rowDeleted=stmt.executeUpdate()>0;

       }
       return rowDeleted;


   }
   
   

}
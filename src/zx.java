import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class zx extends JFrame implements ActionListener{
	public JFrame f,f2,f3,f4,f5,f6,f0,f10,f20;              
	private JButton b,b1,b3,b4,b7,b8,b9,b0;
	private JLabel l1,l2,l3,l4,l5,l6,l7,l0,l11,l22,l33,l44;
	private JTextField t1,t3,t5,t6,t0,t7;
	private JPasswordField t2,t4;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	zx(){
		f=new JFrame("LOGIN PAGE");    // Login Page Frame 
		
		l1=new JLabel("NAME");              // Name 
		l1.setBounds(100, 50, 70, 40);
		
		l2=new JLabel("PASSWORD");         // Password
		l2.setBounds(100, 90, 70, 40);
		
		l3=new JLabel("EMAIL");           //Email
		l3.setBounds(100,130,70,40);
		
		l4=new JLabel("CONFIRM PASS");     // Confirm Pass
		l4.setBounds(100,170,120,40);
		
		t1=new JTextField();
		t1.setBounds(210, 50, 100, 30);        //Name Field
		
		t2=new JPasswordField();              // Password Field
	    t2.setBounds(210,90,100,30);
	    
	    t3=new JTextField();                //Email Field
		t3.setBounds(210, 130, 100, 30);
		
		t4=new JPasswordField();
	    t4.setBounds(210,170,100,30);          //Confirm Password Field
		
		b=new JButton("LOGIN");
		b.setBounds(200,220,100,40);
		b.addActionListener(this);             // Login button functioning
		
		b1=new JButton("SIGN UP");
		b1.setBounds(310,220,100,40);
		b1.addActionListener(this);           // Sign up Button Functioning
		
		invissible();
		
		f.add(l1);f.add(l2);f.add(l3);f.add(l4);
		f.add(t1);f.add(t2);f.add(t3);f.add(t4);
		f.add(b);f.add(b1);
		f.setSize(500,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // For Closing the program
		f.setLayout(null);
		f.setVisible(true);
		
		connection();
		
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() ==b) {    // IF login button press the entire working works
			invissible();
			
	    String Name = t1.getText();
			char[]Password = t2.getPassword();
			String pass=new String(Password);
			String query="select * from login where Name= ? AND Password =?";
			
			try {
				pst =con.prepareStatement(query);
				pst.setString(1, Name);
				pst.setString(2, pass);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(f, "LOGIN SUCCESSFULL");
					t1.setText("");
					t2.setText(""); 
					f2();
				f.dispose();
					}
				else {
					JOptionPane.showMessageDialog(f, "LOGIN FAILED");
					setting();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}}if (ae.getSource() == b1) {  // IF sign up button press the sign up frame opens
				vissible();
				signup();
	            }}
	public void connection() { //MY SQL Connectivity
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root","6969");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void signup() {   
		String Name=t1.getText();
		String Email=t3.getText();
		char[]Password=t2.getPassword();
		String pass=new String(Password);
		char[]confirmPassword=t4.getPassword();
		String confirmPass=new String(confirmPassword);
		
		if(!pass.equals(confirmPass)) {
			JOptionPane.showMessageDialog(f, "PASSWORD DO NOT MATCH");
			return;
		}
		if(Name.isEmpty()||Email.isEmpty()||pass.isEmpty()||confirmPass.isEmpty()) {
			JOptionPane.showMessageDialog(f, "FILL THE DETAILS PLZ");
			return;
			
			}
		String checkquery="select * from login where Name = ? OR Password = ? OR email =?";
		try {
			pst=con.prepareStatement(checkquery);
			pst.setString(1,Name);
			pst.setString(2, pass);
			pst.setString(3, Email);
		    rs=pst.executeQuery();
			
		    if(rs.next()) {
		    JOptionPane.showMessageDialog(f,"ALREADY SIGNUP PZZ LOGIN");
		    setting();
		    invissible();
		    return;
		    }}
		catch(SQLException e) {
			e.printStackTrace();
		}
		String query="insert into login (Name,Password,Email) values(?,?,?)";
		try {
			pst=con.prepareStatement(query);
			pst.setString(1, Name);
			pst.setString(2, pass);
			pst.setString(3, Email);
			int result=pst.executeUpdate();
			
			if(result > 0) {
				JOptionPane.showMessageDialog(f, "SIGN UP SUCCESSFUL");
				setting();
				invissible();
				}
			else {
				JOptionPane.showMessageDialog(f, "SIGN UP FAILED");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			}}
	public void setting() {
		t1.setText("");
	    t2.setText("");
	    t3.setText("");
	    t4.setText("");
	    t3.setText("");
		t5.setText("");
		t6.setText("");
		t0.setText("");
	}
	private void vissible() {
		t3.setVisible(true);
		t4.setVisible(true);
		l3.setVisible(true);
		l4.setVisible(true);
	}
    private void invissible() {
    	t3.setVisible(false);
		t4.setVisible(false);
		l3.setVisible(false);
		l4.setVisible(false);
		
}
    public void f2() {
    	f2=new JFrame("HOME PAGE");
    	JLabel l3=new JLabel("STUDENTS");
    	l3.setBounds(50,50,150,40);
    	JButton b3=new JButton("ADD ");
    	b3.setBounds(40,100,100,40);
    	b3.addActionListener( new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			f3();
    			f2.dispose();
    		}
    	});
        JButton b4=new JButton("DELETE");
    	b4.setBounds(150,100,100,40);
    	b4.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			f5();
    			f2.dispose();
    		}
    	});
    	JButton b5=new JButton("EDIT" );
    	b5.setBounds(260,100,100,40);
    	b5.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			f6();
    			f2.dispose();
    		}
    	});
    	
    	JLabel l6=new JLabel("TEACHERS");
    	l6.setBounds(50,150,150,40);
    	JButton b6=new JButton("ADD ");
    	b6.setBounds(40,180,100,40);
    	b6.addActionListener( new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			f4();
    			f2.dispose();
    		}
    	});
    	JButton b7=new JButton("DELETE");
    	b7.setBounds(150,180,100,40);
    	b7.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			f0();
    			f2.dispose();
    		}
    	});
    	JButton b8=new JButton("EDIT" );
    	b8.setBounds(260,180,100,40);
     	b8.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			f10();
    			f2.dispose();
    		}
    	});
    	b=new JButton("EXIT");
		b.setBounds(180,300,100,40);
		b.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			f2.dispose();
    		}
    	});
    	f2.add(b);
    	f2.add(l3);f2.add(b3);
    	f2.add(b4);f2.add(b5);
    	f2.add(l6);f2.add(b6);
    	f2.add(b7);f2.add(b8);
    	f2.setSize(400,400);
    	f2.setLayout(null);
        f2.setVisible(true);
        }
    
    public void f3() {
    	f3=new JFrame("STUDENT");
    	l0=new JLabel("ID");
    	l0.setBounds(100,10,70,40);
    	t0 = new JTextField();
        t0.setBounds(210, 10, 100, 30);
    	
    	l1=new JLabel("NAME");
		l1.setBounds(100, 50, 70, 40);
		t1 = new JTextField();
        t1.setBounds(210, 50, 100, 30);
        l2 = new JLabel("COURSE");
        l2.setBounds(100, 90, 70, 40);
        t3 = new JTextField();
        t3.setBounds(210, 90, 100, 30);
        l5 = new JLabel("FEE");
        l5.setBounds(100, 130, 70, 40);
    	t5 = new JTextField();
        t5.setBounds(210, 130, 100, 30);
        l6 = new JLabel("CONTACT");
        l6.setBounds(100, 170, 70, 40);
    	t6 = new JTextField();
        t6.setBounds(210, 170, 100, 30);
        b9=new JButton("EXIT");
        b9.setBounds(180,300,100,40);
        b9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f2();
        		f3.dispose();
        	}
        });
        
        b8=new JButton("RESET");
        b8.setBounds(230,250,120,40);
        b8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	setting();
        	}
        });
        
        b7 = new JButton("SUBMIT");
        b7.setBounds(100, 250, 120, 40);
        b7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String name=t1.getText();
        			String course=t3.getText();
        			String fee=t5.getText();
        			String contact=t6.getText();
        			String id=t0.getText();
        			
        			if(name.isEmpty()||course.isEmpty()||fee.isEmpty()||contact.isEmpty()) {
        				JOptionPane.showMessageDialog(f3,"FILL ALL FIELD");
        				return;
        				
        			}
        			String query="insert into Student (Name ,Course,Fee,Contact,ID) value(?,?,?,?,?)";
        			pst=con.prepareStatement(query);
        			pst.setString(1, name);
        			pst.setString(2, course);
        			pst.setString(3, fee);
        			pst.setString(4, contact);
        			pst.setString(5, id);
        			
        			int result=pst.executeUpdate();
        			if(result>0) {
        				JOptionPane.showMessageDialog(f3, "Student Added Succesful");
        			}
        			else {
        				JOptionPane.showMessageDialog(f3, "Failed to Add");
        			}
        		}catch(SQLException ex) {
        			ex.printStackTrace();
        			}
        	}});
        f3.add(l0);f3.add(t0);f3.add(b8);f3.add(b9);
        f3.add(l1);f3.add(t1);f3.add(l2);f3.add(t3);f3.add(l5);f3.add(t5);
    	f3.add(l6);f3.add(t6);f3.add(b7);
    	f3.setSize(500,400);
    	f3.setLayout(null);
    	f3.setVisible(true);
        }
    public void f4() {
    	f4=new JFrame("Teacher");
    	l0=new JLabel("ID");
    	l0.setBounds(100,10,70,40);
    	t0 = new JTextField();
        t0.setBounds(210, 10, 100, 30);
    	
    	l1=new JLabel("NAME");
		l1.setBounds(100, 50, 70, 40);
		t1 = new JTextField();
        t1.setBounds(210, 50, 100, 30);
        l2 = new JLabel("DEPT");
        l2.setBounds(100, 90, 70, 40);
        t3 = new JTextField();
        t3.setBounds(210, 90, 100, 30);
        l5 = new JLabel("SALARY");
        l5.setBounds(100, 130, 70, 40);
    	t5 = new JTextField();
        t5.setBounds(210, 130, 100, 30);
        l6 = new JLabel("CONTACT");
        l6.setBounds(100, 170, 70, 40);
    	t6 = new JTextField();
        t6.setBounds(210, 170, 100, 30);
        b9=new JButton("EXIT");
        b9.setBounds(180,300,100,40);
        b9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f2();
        		f4.dispose();
        	}
        });
        
        b8=new JButton("RESET");
        b8.setBounds(230,250,120,40);
        b8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	setting();
        	}
        });
        
        b7 = new JButton("SUBMIT");
        b7.setBounds(100, 250, 120, 40);
        b7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String id=t0.getText();
        			String name=t1.getText();
        			String dept=t3.getText();
        			String salary=t5.getText();
        			String contact=t6.getText();
        			
        			
        			if(name.isEmpty()||dept.isEmpty()||salary.isEmpty()||contact.isEmpty()||id.isEmpty()) {
        				JOptionPane.showMessageDialog(f4,"FILL ALL FIELD");
        				return;
        				
        			}
        			String query="insert into Teacher (ID,Name ,Dept,Salary,Contact) value(?,?,?,?,?)";
        			pst=con.prepareStatement(query);
        			pst.setString(1, id);
        			pst.setString(2, name);
        			pst.setString(3, dept);
        			pst.setString(4, salary);
        			pst.setString(5, contact);
        			
        			int result=pst.executeUpdate();
        			if(result>0) {
        				JOptionPane.showMessageDialog(f4, "Teacher Added Succesful");
        			}
        			else {
        				JOptionPane.showMessageDialog(f4, "Failed to Add");
        			}
        		}catch(SQLException ex) {
        			ex.printStackTrace();
        			JOptionPane.showMessageDialog(f4,"ERROR OCCURED");
        			}
        	}});
        f4.add(l0);f4.add(t0);f4.add(b8);f4.add(b9);
        f4.add(l1);f4.add(t1);f4.add(l2);f4.add(t3);f4.add(l5);f4.add(t5);
    	f4.add(l6);f4.add(t6);f4.add(b7);
    	
    	f4.setSize(500,400);
    	f4.setLayout(null);
    	f4.setVisible(true);
    	
    }
    public void f5() {
    	f5=new JFrame("Delete");
    	l11=new JLabel("ID");
    	l11.setBounds(20,50,50,40);
    	t1 = new JTextField();
        t1.setBounds(70, 50, 100, 30);
    	l22=new JLabel("NAME");
    	l22.setBounds(20,100,50,40);
        t3 = new JTextField();
        t3.setBounds(70, 100, 100, 30);
        b0=new JButton("SEARCH");
        b0.setBounds(200,100,120,40);
        b0.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id =t1.getText();
        		String name=t3.getText();
        		if(id.isEmpty()&&name.isEmpty()) {
        			JOptionPane.showMessageDialog(f5,"PLZ ENTER ID OR NAME");
        			return;
        		}
        		try {
        			String searchquery="select * from Student where ID= ? OR Name=?";
        			pst=con.prepareStatement(searchquery);
        			pst.setString(1, id);
        			pst.setString(2, name);
        			rs=pst.executeQuery();
        		if(rs.next()) {
        			int confirm=JOptionPane.showConfirmDialog(f5, "ARE YOU SURE YOU WANT TO DELETE RECORD ?","DELETE RECORD"
        					,JOptionPane.YES_NO_OPTION);
        			if(confirm == JOptionPane.YES_OPTION) {
        				String deletequery="delete from Student where ID= ? OR Name=?";
        				pst=con.prepareStatement(deletequery);
        				pst.setString(1, id);
        				pst.setString(2, name);
        				int result =pst.executeUpdate();
        				
        				if(result>0) {
        					JOptionPane.showMessageDialog(f5, "RECORD DELETED ");
        					t1.setText("");
        					t3.setText("");
        				}
        				else {
        					JOptionPane.showMessageDialog(f5, "FAILED TO DELETE");
        					
        				}}
        		else {
					JOptionPane.showMessageDialog(f5, "RECORD NOT FOUND");
        		}
        		}}
        		catch(SQLException ex) {
        			ex.printStackTrace();
        					JOptionPane.showMessageDialog(f5,"ERROR OCCURED");
        				}}
        		});
        b9=new JButton("EXIT");
        b9.setBounds(180,300,100,40);
        b9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f2();
        		f5.dispose();
        	}
        });
    	
    	f5.add(l11);f5.add(l22);f5.add(t1);f5.add(t3); f5.add(b0);f5.add(b9);
    	f5.setSize(500,400);
    	f5.setLayout(null);
    	f5.setVisible(true);
    }
    	
    	 public void f6() {

    	     	
        	 f6=new JFrame("Modify");
        	 l44=new JLabel("Change the name if you want");
        	 l44.setBounds(200,150,300,60);
        	 l11=new JLabel("ID");
         	l11.setBounds(20,50,50,40);
         	t1 = new JTextField();
             t1.setBounds(80, 50, 100, 30);
         	l22=new JLabel("NAME");
         	l22.setBounds(20,100,50,40);
             t3 = new JTextField();
             t3.setBounds(80, 100, 100, 30);
             b0=new JButton("SEARCH");
             b0.setBounds(200,100,100,40);
             l2 = new JLabel("COURSE");
             l2.setBounds(20, 150, 70, 40);
             t7 = new JTextField();
             t7.setBounds(80, 150, 100, 30);
             l5 = new JLabel("FEE");
             l5.setBounds(20, 200, 70, 40);
         	t5 = new JTextField();
             t5.setBounds(80, 200, 100, 30);
             l6 = new JLabel("CONTACT");
             l6.setBounds(20, 250, 70, 40);
         	t6 = new JTextField();
             t6.setBounds(80, 250, 100, 30);
             JButton b5=new JButton("UPDATE");
             b5.setBounds(320,100,100,40);
             l44.setVisible(false);
             l2.setVisible(false);
             t7.setVisible(false);
             l5.setVisible(false);
             t5.setVisible(false);
             l6.setVisible(false);
             t6.setVisible(false);
             
             
             b5.addActionListener(new ActionListener() {
            	 public void actionPerformed(ActionEvent ae) {
            		 connection();
            		 updates();
            		
            	 }
             });
             b0.addActionListener(new ActionListener(){
            	 public void actionPerformed(ActionEvent ae) {
            		 connection();
            		 student();
            		 l44.setVisible(true);
            		 l2.setVisible(true);
           	         t7.setVisible(true);
           	         l5.setVisible(true);
           	         t5.setVisible(true);
           	         l6.setVisible(true);
           	         t6.setVisible(true);
           	         }});
                	 
                		
             
        	 b9=new JButton("EXIT");
             b9.setBounds(180,300,100,40);
             b9.addActionListener(new ActionListener() {
             	public void actionPerformed(ActionEvent e) {
             		f2();
             		f6.dispose();
             	}
             });f6.add(b5);f6.add(l44);
             f6.add(l2);f6.add(t7);f6.add(l5);f6.add(t5);f6.add(l6);f6.add(t6);
             f6.add(b9);f6.add(l11);f6.add(t1);f6.add(t3);f6.add(b0);f6.add(l22);
        	 f6.setSize(500,400);
        	 f6.setLayout(null);
        	 f6.setVisible(true);
        }
         public void student() {
        	 String ID = t1.getText().trim();
    		 String Name=t3.getText().trim();
    	
    		 
    		 if (ID.isEmpty()||Name.isEmpty()) {
    	            JOptionPane.showMessageDialog(f6, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
    	            return;
    	        }

    		 String query="select *from student where ID=? AND Name=?";
    		 
    	 try{
    		 pst=con.prepareStatement(query);
    		 pst.setString(1,ID);
    		 pst.setString(2, Name);
    			/*
    			 * pst.setString(3, Dept); pst.setString(4, Salary); pst.setString(5, Contact);
    			 */
    		 rs=pst.executeQuery();
    		 if(rs.next()) {
    			 
    			 JOptionPane.showMessageDialog(f6, "ITEM FETCHED");
    			 
    		 }
    		 else {
    			 JOptionPane.showMessageDialog(f6, "ITEM NOT FETCHED");
    			 
    		  }}
    	 catch(SQLException e) {
    	    e.printStackTrace();
    	 } }
         public void updates() {
        	 String ID = t1.getText().trim();
             String Name = t3.getText().trim();
        	 String Course=t7.getText().trim();
    		 String Fee=t5.getText().trim();
    		 String Contact=t6.getText().trim();

             if (ID.isEmpty() || Name.isEmpty()||Course.isEmpty()||Fee.isEmpty()||Contact.isEmpty()) {
                 JOptionPane.showMessageDialog(f6, "Please fill all field!", "Warning", JOptionPane.WARNING_MESSAGE);
                 return;
             }

             int choice = JOptionPane.showConfirmDialog(f6, "Are you sure you want to update?", "Confirm", JOptionPane.YES_NO_OPTION);
             if (choice == JOptionPane.YES_OPTION) {
                 String updateQuery = "UPDATE student SET Name = ?,Course = ? ,Fee = ?,Contact = ? where ID =?";
                 try {
                     pst = con.prepareStatement(updateQuery);
                     pst.setString(1, Name);
                     pst.setString(5, ID);
                     pst.setString(2, Course);
                     pst.setString(3, Fee); 
                     pst.setString(4, Contact);
                     
                     int updated = pst.executeUpdate();

                     if (updated > 0) {
                         JOptionPane.showMessageDialog(f6, "Student Record Updated Successfully!");
                         t1.setText("");
                         t3.setText("");
                         t7.setText("");
                         t5.setText("");
                         t6.setText("");
                     } else {
                         JOptionPane.showMessageDialog(f6, "Update Failed!");
                     }
                 } catch (SQLException e) {
                	 JOptionPane.showMessageDialog(f6, "Error Updating Data!");
                     e.printStackTrace();
                 }
             }
         }
         
     
     public void f0() {
     	f0=new JFrame("Delete");
     	l11=new JLabel("ID");
     	l11.setBounds(20,50,50,40);
     	t1 = new JTextField();
         t1.setBounds(70, 50, 100, 30);
     	l22=new JLabel("NAME");
     	l22.setBounds(20,100,50,40);
         t3 = new JTextField();
         t3.setBounds(70, 100, 100, 30);
         b0=new JButton("SEARCH");
         b0.setBounds(200,100,120,40);
         b0.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		String id =t1.getText();
         		String name=t3.getText();
         		if(id.isEmpty()&&name.isEmpty()) {
         			JOptionPane.showMessageDialog(f0,"PLZ ENTER ID OR NAME");
         			return;
         		}
         		try {
         			String searchquery="select * from Teacher where ID= ? AND Name=?";
         			pst=con.prepareStatement(searchquery);
         			pst.setString(1, id);
         			pst.setString(2, name);
         			rs=pst.executeQuery();
         		if(rs.next()) {
         			int confirm=JOptionPane.showConfirmDialog(f0, "ARE YOU SURE YOU WANT TO DELETE RECORD ?","DELETE RECORD"
         					,JOptionPane.YES_NO_OPTION);
         			if(confirm == JOptionPane.YES_OPTION) {
         				String deletequery="delete from Teacher where ID= ? AND Name=?";
         				pst=con.prepareStatement(deletequery);
         				pst.setString(1, id);
         				pst.setString(2, name);
         				int result =pst.executeUpdate();
         				
         				if(result>0) {
         					JOptionPane.showMessageDialog(f0, "RECORD DELETED ");
         					t1.setText("");
         					t3.setText("");
         				}
         				else {
         					JOptionPane.showMessageDialog(f0, "FAILED TO DELETE");
         					
         				}}
         		else {
 					JOptionPane.showMessageDialog(f0, "RECORD NOT FOUND");
         		}
         		}}
         		catch(SQLException ex) {
         			ex.printStackTrace();
         					JOptionPane.showMessageDialog(f0,"ERROR OCCURED");
         				}}
         		});
         b9=new JButton("EXIT");
         b9.setBounds(180,300,100,40);
         b9.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		f2();
         		f0.dispose();
         	}
         });
     	
     	f0.add(l11);f0.add(l22);f0.add(t1);f0.add(t3); f0.add(b0);f0.add(b9);
     	f0.setSize(500,400);
     	f0.setLayout(null);
     	f0.setVisible(true);
     }	 
     
     public void f10() {

     	
    	 f10=new JFrame("Modify");
    	 l44=new JLabel("Change the name if you want");
    	 l44.setBounds(200,150,300,60);
    	 l11=new JLabel("ID");
     	l11.setBounds(20,50,50,40);
     	t1 = new JTextField();
         t1.setBounds(80, 50, 100, 30);
     	l22=new JLabel("NAME");
     	l22.setBounds(20,100,50,40);
         t3 = new JTextField();
         t3.setBounds(80, 100, 100, 30);
         b0=new JButton("SEARCH");
         b0.setBounds(200,100,100,40);
         l2 = new JLabel("DEPT");
         l2.setBounds(20, 150, 70, 40);
         t7 = new JTextField();
         t7.setBounds(80, 150, 100, 30);
         l5 = new JLabel("SALARY");
         l5.setBounds(20, 200, 70, 40);
     	t5 = new JTextField();
         t5.setBounds(80, 200, 100, 30);
         l6 = new JLabel("CONTACT");
         l6.setBounds(20, 250, 70, 40);
     	t6 = new JTextField();
         t6.setBounds(80, 250, 100, 30);
         JButton b5=new JButton("UPDATE");
         b5.setBounds(320,100,100,40);
         l44.setVisible(false);
         l2.setVisible(false);
         t7.setVisible(false);
         l5.setVisible(false);
         t5.setVisible(false);
         l6.setVisible(false);
         t6.setVisible(false);
         
         
         b5.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent ae) {
        		 connection();
        		 update();
        		
        	 }
         });
         b0.addActionListener(new ActionListener(){
        	 public void actionPerformed(ActionEvent ae) {
        		 connection();
        		 teacher();
        		 l44.setVisible(true);
        		 l2.setVisible(true);
       	         t7.setVisible(true);
       	         l5.setVisible(true);
       	         t5.setVisible(true);
       	         l6.setVisible(true);
       	         t6.setVisible(true);
       	         }});
            	 
            		
         
    	 b9=new JButton("EXIT");
         b9.setBounds(180,300,100,40);
         b9.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		f2();
         		f10.dispose();
         	}
         });f10.add(b5);f10.add(l44);
         f10.add(l2);f10.add(t7);f10.add(l5);f10.add(t5);f10.add(l6);f10.add(t6);
         f10.add(b9);f10.add(l11);f10.add(t1);f10.add(t3);f10.add(b0);f10.add(l22);
    	 f10.setSize(500,400);
    	 f10.setLayout(null);
    	 f10.setVisible(true);
    }
     public void teacher() {
    	 String ID = t1.getText().trim();
		 String Name=t3.getText().trim();
	
		 
		 if (ID.isEmpty()||Name.isEmpty()) {
	            JOptionPane.showMessageDialog(f10, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

		 String query="select *from Teacher where ID=? AND Name=?";
		 
	 try{
		 pst=con.prepareStatement(query);
		 pst.setString(1,ID);
		 pst.setString(2, Name);
			/*
			 * pst.setString(3, Dept); pst.setString(4, Salary); pst.setString(5, Contact);
			 */
		 rs=pst.executeQuery();
		 if(rs.next()) {
			 
			 JOptionPane.showMessageDialog(f10, "ITEM FETCHED");
			 
		 }
		 else {
			 JOptionPane.showMessageDialog(f10, "ITEM NOT FETCHED");
			 
		  }}
	 catch(SQLException e) {
	    e.printStackTrace();
	 } }
     public void update() {
    	 String ID = t1.getText().trim();
         String Name = t3.getText().trim();
    	 String Dept=t7.getText().trim();
		 String Salary=t5.getText().trim();
		 String Contact=t6.getText().trim();

         if (ID.isEmpty() || Name.isEmpty()||Dept.isEmpty()||Salary.isEmpty()||Contact.isEmpty()) {
             JOptionPane.showMessageDialog(f10, "Please fill all field!", "Warning", JOptionPane.WARNING_MESSAGE);
             return;
         }

         int choice = JOptionPane.showConfirmDialog(f10, "Are you sure you want to update?", "Confirm", JOptionPane.YES_NO_OPTION);
         if (choice == JOptionPane.YES_OPTION) {
             String updateQuery = "UPDATE Teacher SET Name = ?,Dept = ? ,Salary = ?,Contact = ? where ID =?";
             try {
                 pst = con.prepareStatement(updateQuery);
                 pst.setString(1, Name);
                 pst.setString(5, ID);
                 pst.setString(2, Dept);
                 pst.setString(3, Salary); 
                 pst.setString(4, Contact);
                 
                 int updated = pst.executeUpdate();

                 if (updated > 0) {
                     JOptionPane.showMessageDialog(f10, "Teacher Record Updated Successfully!");
                     t1.setText("");
                     t3.setText("");
                     t7.setText("");
                     t5.setText("");
                     t6.setText("");
                 } else {
                     JOptionPane.showMessageDialog(f10, "Update Failed!");
                 }
             } catch (SQLException e) {
            	 JOptionPane.showMessageDialog(f10, "Error Updating Data!");
                 e.printStackTrace();
             }}}
public static void main(String args[]) {
	new zx();
}}

package com.ruiyang.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends Frame implements ActionListener, WindowListener
{
	private JTextField displayField;         //计算结果显示区
	private String lastCommand;           //保存+,-,*,/,=命令0
	private double result;               //保存计算结果
	private boolean start;           //判断是否为数字的开始
	private Dialog dialog;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_0;
	private JButton button_plus;
	private JButton button_minus;
	private JButton button_multiply;
	private JButton button_divide;
	private JButton button_dot;
	private JButton button_equal;
	private JButton button_AC;

	
	public Calculator()       //构造方法设置布局、为按钮注册事件监听器
	{
		super( "My Calculator" );
		this.setLocation( 350,150 );
		this.setSize( 250,300 );
		this.setResizable( false );
//		GridLayout(rows, columns)
		this.setLayout( new GridLayout( 5,1 ) );
//		this.addmyMenu();                   //调用成员方法添加菜单
		displayField = new JTextField( "0", 14 );
//		this.add( displayField );
		displayField.setEditable( true );
		displayField.setHorizontalAlignment(JTextField.RIGHT);

		start = true;
		result = 0;
		lastCommand = "=";
//
		button_AC = new JButton("AC");
		button_1 = new JButton( "1" );
		button_2 = new JButton( "2" );
		button_3 = new JButton( "3" );
		button_4 = new JButton( "4" );
		button_5 = new JButton( "5" );
		button_6 = new JButton( "6" );
		button_7 = new JButton( "7" );
		button_8 = new JButton( "8" );
		button_9 = new JButton( "9" );
		button_0 = new JButton( "0" );
		button_dot=new JButton( "." );
		button_divide = new JButton( "/" );//除
		button_multiply = new JButton( "*" );//乘法
		button_minus = new JButton( "-" );
		button_plus = new JButton( "+" );
		button_equal = new JButton( "=" );
		
		JPanel panel0 = new JPanel();
		this.add( panel0 );
		panel0.setLayout( new BorderLayout() );
		panel0.add(displayField, BorderLayout.WEST);
		panel0.add(button_AC, BorderLayout.EAST);
		panel0.setVisible(true);
	
		JPanel panel1 = new JPanel();
		panel1.setLayout( new GridLayout( 1,4,4,4 ) );
		this.add( panel1 );
		panel1.add( button_7 );
		panel1.add( button_8 );
		panel1.add( button_9 );
		panel1.add( button_divide );
		
		JPanel panel2 = new JPanel();
		panel2.setLayout( new GridLayout( 1,4,4,4 ) );
		this.add(panel2);
		panel2.add( button_4 );
		panel2.add( button_5 );
		panel2.add( button_6 );
		panel2.add( button_multiply );
		
		JPanel panel3 = new JPanel();
		panel3.setLayout( new GridLayout( 1,4,4,4 ) );
		this.add(panel3);
		panel3.add( button_1 );
		panel3.add( button_2 );
		panel3.add( button_3 );
		panel3.add( button_minus );
		
		JPanel panel4 = new JPanel();
		panel4.setLayout( new GridLayout( 1,4,4,4 ) );
		this.add(panel4);
		panel4.add( button_0 );
		panel4.add( button_dot );
		panel4.add( button_equal );
		panel4.add( button_plus );


		button_AC.addActionListener( this );
//		button_cancel.addActionListener( this );
		button_7.addActionListener( this );
		button_8.addActionListener( this );
		button_9.addActionListener( this );
//		button_log.addActionListener( this );
		button_divide.addActionListener( this );
		button_4.addActionListener( this );
		button_5.addActionListener( this );
		button_6.addActionListener( this );
//		button_tan.addActionListener( this );
		button_multiply.addActionListener( this );
		button_1.addActionListener( this );
		button_2.addActionListener( this );
		button_3.addActionListener( this );
//		button_cos.addActionListener( this );
		button_minus.addActionListener( this );
		button_0.addActionListener( this );
		button_dot.addActionListener( this );
		button_equal.addActionListener( this );
		button_plus.addActionListener( this );
//				
		this.addWindowListener( new WinClose() );      //注册窗口监听器
		this.setVisible( true );
	}

	public void actionPerformed(ActionEvent e)       //按钮的单击事件处理方法
	{
		if(	
				e.getSource().equals( button_1 )||e.getSource().equals( button_2 )|| 
				e.getSource().equals( button_3 )||e.getSource().equals( button_4 )||
				e.getSource().equals( button_5 )|| e.getSource().equals( button_6 )||
				e.getSource().equals( button_7 )|| e.getSource().equals( button_8 )||
				e.getSource().equals( button_9 ) ||e.getSource().equals( button_0 )||
				e.getSource().equals( button_dot )
		  )
		{      //非运算符的处理方法
			String input = e.getActionCommand();
			if ( start )
			{
				displayField.setText("");
				start = false;
				displayField.setText(displayField.getText() + input);
			}
			else
			{
				displayField.setText(displayField.getText() + input);
			}
		}
		else if ( e.getSource().equals( button_AC ) )
		{
			displayField.setText("0");
			start = true;
			lastCommand = "=";
			result = 0;
		}
		else
		{
			start = true;
			String command = e.getActionCommand();
			if (lastCommand.equals("="))
			{
				calculate(Double.parseDouble(displayField.getText()));
				lastCommand = command;
			}
			else 
			{
				calculate(Double.parseDouble(displayField.getText()));
				String str_result = resultProcessing(result);
				displayField.setText("" + str_result);
				lastCommand = command;
			}
		}
	}
	//如果结果是整数，处理结果不显示小数点后的0
	public String resultProcessing( double result)
	{
		String return_result = "" + result ;
		if (return_result.endsWith(".0"))
		{
			return return_result.replace(".0", "");
		}		
		return return_result;
	}
	
	public void calculate( double x )          //各运算符的具体运算方法
    {
		if ( lastCommand.equals( "+" ) ) 
			result += x;    
		else if (lastCommand.equals( "-" ) ) 
			result -= x;
		else if ( lastCommand.equals( "*" ) ) 
			result *= x;   
		else if ( lastCommand.equals( "/" ) ) 
			result /= x;
		else if ( lastCommand.equals( "=" ) ) 
			result = x;
     }	 
	
	public void windowClosing( WindowEvent e )
    {
        if( e.getSource() == dialog )
            dialog.setVisible( false );           //隐藏对话框
        else
            System.exit( 0 ); 
    }

    public void windowOpened( WindowEvent e )         {  }
    public void windowActivated( WindowEvent e )      {  }
    public void windowDeactivated( WindowEvent e )    {  }
    public void windowClosed( WindowEvent e )         {  }
    public void windowIconified( WindowEvent e )      {  }
    public void windowDeiconified( WindowEvent e )    {  }
		
	public static void main( String args[] )          
	{
		Calculator calculator = new Calculator();
	}

}

class WinClose implements WindowListener
{
	public void windowClosing( WindowEvent e )    //单击窗口关闭按钮时触发并执行实现窗口监听器接口中的方法
	{
		System.exit( 0 );          //结束程序运行
	}
	public void windowOpened( WindowEvent e ){ }
	public void windowActivated( WindowEvent e ){}
	public void windowDeactivated( WindowEvent e){ }
	public void windowClosed( WindowEvent e ){ }
	public void windowIconified( WindowEvent e ){ }
	public void windowDeiconified( WindowEvent e ){ }
}

package com.example.zhishui.myapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageProceed {

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String resultString = "I327;$0$:1838610;$1$:0.870283;-0.008921;-0.008759;-0.492393;$2$:0.104760;-0.011698;0.011896;$3$:0.000000;0.000000;-0.037386;$4$:-0.006147;0.000278;0.002046;$5$:39.90171000000054;116.40171000000055;134.933624;0.002426;0;$6$:0;0;0;$7$:0;0;0;0;7857;-9821;$8$:0.000000;0.000000;0.000000;$9$:1;$a$:170;$b$:0;$c$:0;$d$:0;$e$:0;$f$:0;";
//		String testString = "$:0;0;0;$7$:0;0;0;0;7857;-9821;$8$:0.000000;0.000000;0.000000;$9$:1;$a$:169;$b$:0;$c$:0;$d$:0;$e$:0;$f$:0;I327;$0$:1838610;$1$:0.870283;-0.008921;-0.008759;-0.492393;$2$:0.104760;-0.011698;0.011896;$3$:0.000000;0.000000;-0.037386;$4$:-0.006147;0.000278;0.002046;$5$:aa.90171000000054;116.40171000000055;134.933624;0.002426;0;$6$:0;0;0;$7$:0;0;0;0;7857;-9821;$8$:0.000000;0.000000;0.000000;$9$:1;$a$:170;$b$:0;$c$:0;$d$:0;$e$:0;$f$:0;I327;$0$:1838610;$1$:0.870283;-0.008921;-0.008759;-0.492393;$2$:0.104760;-0.011698;0.011896;$3$:0.000000;0.000000;-0.037386;$4$:-a.006147;0.000278;0.002046;$5$:39.90171000000054;116.40171000000055;134.933624;0.002426;0;$6$:0;0;0;$7$:0;0;0;0;7857;-9821;$8$:0.000000;0.000000;0.000000;$9$:1;$a$:170;$b$:0;$c$:0;$d$:0;$e$:0;$f$:0;I328;$0$:1838610;$1$:0.870283;-0.008921;-0.008759;-0.492393;$2$:0.104760;-0.011698;0.011896;$3$:0.000000;0.000000;-0.037386;$4$:-0.006147;0.000278;0.002046;$5$:39.90171000000054;1161.40171000000055;134.933624;0.002426;0;$6$:0;0;0;$7$:0;0;0;0;7857;-9821;$8$:0.000000;0.000000;0.000000;$9$:1;$a$:170;$b$:0;$c$:0;$d$:0;$e$:0;$f$:0;";
//
//		byte[] test = testString.getBytes();
//		System.out.println(checkstdPackage("I328;$0$:1838610;$1$:0.870283;-0.008921;-0.008759;-0.492393;$2$:0.104760;-0.011698;0.011896;$3$:0.000000;0.000000;-0.037386;$4$:-0.006147;0.000278;0.002046;$5$:39.90171000000054;1161.40171000000055;134.933624;0.002426;0;$6$:0;0;0;$7$:0;0;0;0;7857;-9821;$8$:0.000000;0.000000;0.000000;$9$:1;$a$:170;$b$:0;$c$:0;$d$:0;$e$:0;$f$:0;"));
//		System.out.println(GetStdPackage(test));
//	}

	//getPackage from buffer (select a complete standard package from a lot of data)
	static String GetStdPackage(byte[] test)
	{
		/*
		 * 	cs:					(String)rawdata
		 * 	standString:		(String)resultdata
		 * 	start:				(int)the index of 'I' appears in rawdata String
		 * 	length:				(int)the length of the this package
		 */
		
		String cs = new String(test , 0 , test.length);
		String standString = null;
		//int i = 0;
		do
		{
			/*
			i++;
			System.out.println(i);
			System.out.println(cs);
			*/
			int start = cs.indexOf('I');
			int length;
			if(start != -1)
			{
				if(cs.length() > 4)
				{
					//get lengths
					String tempLength = cs.substring(start+1, start+4);
					Pattern pattern = Pattern.compile("[0-9]{3}");
					if(pattern.matcher(tempLength).matches())
						length = Integer.parseInt(tempLength);
					else {
						standString = null;
						break;
					}
				}
				else
				{
					//ERROR:if cannot get package
					standString = null;
					break;
				}
				
				if(cs.length() > start + length - 1)
				{
					//ERROR:if can get package
					standString = "I" + cs.substring(start+5, start+length);
					//System.out.println("select:"+standString);
				}
				else
				{
					//ERROR:if cannot get package
					standString = null;
					break;
				}
				cs = cs.substring(start+length);
			}
			else
			{
				//ERROR:if cannot get package
				standString = null;
				break;
			}
		}while(checkstdPackage(standString) == false);
		
		//System.out.println("finalresult:"+standString);
		return standString;
	}

	//check if this String is complete and satisfy all need with standard format.
	static boolean checkstdPackage(String rawString)
	{
		/*
		 *   float/double:	\\-{0,1}[0-9]+(\\.[0-9]+){0,1};  （小数点及其后可有可无，小数点前至少一位，若有小数点，小数点后至少一位，负号可有可无）    				\\-{0,1}\\d+(\\.{0,1}\\d+){0,1}\\;
		 *   int:			\\-{0,1}[0-9]+;
		 *   Packagehead:	\\$0\\$:
		 *
		 *	basic element:     . - [ ] ( ) ? < > $ ^ | { } \      (需用 \\ + 自己 匹配)
		 */
		Pattern pattern = Pattern.compile("I" +
				"\\$0\\$:\\-{0,1}[0-9]+;" +
				"\\$1\\$:\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};" +
				"\\$2\\$:\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};" +
				"\\$3\\$:\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};" +
				"\\$4\\$:\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};" +
				"\\$5\\$:\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+;" +
				"\\$6\\$:\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};" +
				"\\$7\\$:\\-{0,1}[0-9]+;\\-{0,1}[0-9]+;\\-{0,1}[0-9]+;\\-{0,1}[0-9]+;\\-{0,1}[0-9]+;\\-{0,1}[0-9]+;" +
				"\\$8\\$:\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};\\-{0,1}[0-9]+(\\.[0-9]+){0,1};" +
				"\\$9\\$:\\-{0,1}[0-9]+;" +
				"\\$a\\$:\\-{0,1}[0-9]+;" +
				"\\$b\\$:\\-{0,1}[0-9]+;" +
				".*"
				);
		Matcher matcher = pattern.matcher(rawString);
		return matcher.matches();
	}
}
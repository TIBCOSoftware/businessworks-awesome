package tcizendesk.module.common;

public class ChangeUnitTestData {
public static int getTestCaseNumber(int current)
{
	return current + 1;
}
public static String getAppendedString(String oldstr,String current)
{
	return oldstr + " :: " + current;
}

}

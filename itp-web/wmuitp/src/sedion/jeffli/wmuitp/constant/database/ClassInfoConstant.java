package sedion.jeffli.wmuitp.constant.database;

public class ClassInfoConstant 
{
	public static String CLASS_ONE 		= "求新楼1";
	public static String CLASS_TWO 		= "求新楼2";
	public static String CLASS_THREE 	= "求新楼3";
	public static String CLASS_FOUR 	= "求新楼4";
	public static String CLASS_FIVE 	= "求新楼5";

	public static String getClassNameFromIP(String ip) 
	{
		switch (ip) {
		case "1":
			return CLASS_ONE;
		case "2":
			return CLASS_TWO;
		case "3":
			return CLASS_THREE;
		case "4":
			return CLASS_FOUR;
		case "5":
			return CLASS_FIVE;
		default:
			break;
		}
		return null;
	}
}

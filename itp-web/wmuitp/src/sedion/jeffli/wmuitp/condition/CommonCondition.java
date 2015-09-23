package sedion.jeffli.wmuitp.condition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is a class that generate hql condition from json
 * different condition have different getHQL() method and getValue() method
 * naother important interface is ConditionService.condGenerateHQL()
 * by using jackson's ObjectMapper I can convert json into a condition object with values
 * the judgement flds[i].get(this) != null means a filed has been assigned
 * 
 * @author HY
 *
 */
public abstract class CommonCondition {

	@SuppressWarnings("rawtypes")
	public Map<String, List<Object>> getConditions() throws IllegalArgumentException, IllegalAccessException {
		Map<String, List<Object>> conditions = new HashMap<>();
		List<Object> temp_values = new ArrayList<Object>();
		List<Object> values = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		Class cls = this.getClass();
		// get all private fields
		Field[] flds = cls.getDeclaredFields();
		if (flds != null) {
			for (int i = 0; i < flds.length; i++) {
				if (flds[i].get(this) != null) {// jackson进行映射时会对应赋值,此时是判断已经赋值的
					System.out.println("flds[i].get(new String())="+flds[i].get(new String()));
					System.out.println("flds[i].getName()="+flds[i].getName());
					System.out.println("(String) flds[i].get(this))="+(String) flds[i].get(this));
					hql.append(getHQL(flds[i].getName(), (String) flds[i].get(this)));
					temp_values = getValue(flds[i].getName(), (String) flds[i].get(this));
					for (Object value : temp_values) {
						values.add(value);
					}
					
				}System.out.println("hql:"+hql);
			}
		}
		conditions.put(hql.toString(), values);
		return conditions;
	}

	protected abstract String getHQL(String fieldName, String fieldValue);

	protected abstract List<Object> getValue(String fieldName, String fieldValue);

}

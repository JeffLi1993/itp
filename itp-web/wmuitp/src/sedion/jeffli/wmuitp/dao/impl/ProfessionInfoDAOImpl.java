package sedion.jeffli.wmuitp.dao.impl;


import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.ProfessionInfoDAO;
import sedion.jeffli.wmuitp.entity.ProfessionInfo;

@Repository
public class ProfessionInfoDAOImpl  extends GenericHibernateDAOImpl<ProfessionInfo> implements ProfessionInfoDAO
{
	private static final String All_PROFESSIONNFOS = "FROM ProfessionInfo AS pi ";
	
	@Override
	public ProfessionInfo getprofessionInfoByProfessionInfoAndCollege(String professionInfoName, String collegeName) {
		StringBuilder hql = new StringBuilder();
		hql.append(All_PROFESSIONNFOS).append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND pi.piProfession='").append(professionInfoName).append("' ");
		hql.append(" AND pi.piCollege='").append(collegeName).append("' ");
		
		return getUniqueResultByHQL(hql.toString());
	}
	
}
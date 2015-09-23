package sedion.jeffli.wmuitp.dao;

import sedion.jeffli.wmuitp.base.GenericHibernateDAO;
import sedion.jeffli.wmuitp.entity.ProfessionInfo;

public interface ProfessionInfoDAO extends GenericHibernateDAO<ProfessionInfo>
{
	ProfessionInfo getprofessionInfoByProfessionInfoAndCollege(String professionInfoName,String collegeName);
}

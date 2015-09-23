package sedion.jeffli.wmuitp.dao.impl;


import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.dao.PaperInfoDAO;
import sedion.jeffli.wmuitp.entity.PaperInfo;

@Repository
public class PaperInfoDAOImpl extends GenericHibernateDAOImpl<PaperInfo> implements PaperInfoDAO
{
}
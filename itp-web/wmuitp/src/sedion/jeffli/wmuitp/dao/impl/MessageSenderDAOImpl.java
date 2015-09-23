package sedion.jeffli.wmuitp.dao.impl;


import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.dao.MessageSenderDAO;
import sedion.jeffli.wmuitp.entity.MessageSender;

@Repository
public class MessageSenderDAOImpl extends GenericHibernateDAOImpl<MessageSender> implements MessageSenderDAO
{}
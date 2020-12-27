package com.whut.work.ticket.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.ticket.dao.ITicketDao;
import com.whut.work.ticket.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketDaoImpl extends BaseDaoImpl<Ticket> implements ITicketDao {
    public TicketDaoImpl(){
        super(Ticket.class);
    }

}

package com.brown3qqq.cstatour.configuration;

import com.brown3qqq.cstatour.dao.Impl.TicketsRepositoryimpl;
import com.brown3qqq.cstatour.dao.TicketRepository;
import com.brown3qqq.cstatour.pojo.Column;
import com.brown3qqq.cstatour.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
/**
 * @Classname TimeAuto
 * @Description TODO
 * @Date 2019/2/15 19:49
 * @Created by CQ
 */
@EnableScheduling
public class TimeAuto {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketsRepositoryimpl ticketsRepositoryimpl;

    //每天23点执行一次:0 0 23 * * ?
    @Scheduled(cron = "0 */1 * * * ?")
    public void TimeAutoClear(){
        System.out.println("定时任务,清理tickets" + new Date());

        Iterable<Ticket> it = ticketRepository.findAll();
        Iterator<Ticket> iterator = it.iterator();

        while (iterator.hasNext()){

            Ticket ticket = iterator.next();

            Date nowDate = new Date();
            nowDate.setTime(nowDate.getTime() - (86400*1000) );

            if (ticket.getExpired().before(nowDate)){
                ticketRepository.delete(ticket);
            }

        }

    }
}

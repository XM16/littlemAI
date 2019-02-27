package com.hust.xiaomo16.service.impl;

import com.hust.xiaomo16.entity.ReservationDetail;
import com.hust.xiaomo16.repository.ReservationRepository;
import com.hust.xiaomo16.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public void create(ReservationDetail reservationDetail) {
             reservationRepository.save(reservationDetail);
    }

    @Override
    public void cancel(String userID) {
            reservationRepository.deleteReservationDetailByUserId(userID);
    }

    @Override
    public List<ReservationDetail> findList() {
            return reservationRepository.findAll();
    }

    @Override
    public Integer findOne(Integer tableType) {
        List<ReservationDetail> reservationDetails = new ArrayList<ReservationDetail>();
        reservationDetails = reservationRepository.findAll();
        for (ReservationDetail rd : reservationDetails) {
            if (rd.getTableType() == tableType && rd.getStatus().equals("排队中")) {
                return rd.getQueNum();
            }
        }
        return 0;
    }

    @Override
    public void updateStatus(ReservationDetail rd) {
                   reservationRepository.save(rd);
    }
    @Override
    public ReservationDetail findReservation(String reservaId) {
      return  reservationRepository.findReservationDetailByReservaId(reservaId);
    }

    @Override
    public Integer findTheLast(Integer tableType) {
        List<ReservationDetail> reservationDetails=reservationRepository.findListByTableType(tableType);
        int len=reservationDetails.size();
        int i;
        for(i=len-1;i>=0;i--) {
            if (reservationDetails.get(i).getStatus().equals("排队中"))
                return reservationDetails.get(i).getQueNum();
        }
        return 0;
    }
}

package com.sparta.reserq.service;

import com.sparta.reserq.domain.follower.FollowerRepository;
import com.sparta.reserq.dto.FollowerDto;
import com.sparta.reserq.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
//import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final EntityManager em;

//    @Transactional(readOnly = true)//셀렉트만할거라
//    public List<FollowerDto> 구독리스트(int principalId, int pageUserId){
//
//        List<FollowerDto> followerDtos = result.list(query, FollowerDto.class);
//        return followerDtos;
//    }

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {
        try{
            followerRepository.mSubscribe(fromUserId, toUserId);
        }catch (Exception e){
            throw new CustomApiException("이미 구독중입니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) {
        followerRepository.mUnSubscribe(fromUserId, toUserId);
    }
}



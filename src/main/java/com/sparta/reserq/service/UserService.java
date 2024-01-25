package com.sparta.reserq.service;

import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import com.sparta.reserq.dto.UserProfileDto;
import com.sparta.reserq.ex.CustomException;
import com.sparta.reserq.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Value("${file.path}")
    private String uploadFolder; //롬복아닌 org로 yml에 있는 path값 챙겨오기
    @Transactional
    public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile){
        UUID uuid =UUID.randomUUID();
        String imageFileName =uuid+"_"+ profileImageFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try{
            Files.write(imageFilePath,profileImageFile.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        User userEntity = userRepository.findById(principalId).orElseThrow(()->{
            throw  new CustomException("유저를 찾을수 없습니다.");
        });
        userEntity.setProfileImageUrl(imageFileName);
        return userEntity;
    }


//    @Transactional(readOnly = true)
//    public UserProfileDto 회원프로필(int pageUserId, int principalId){
//        UserProfileDto dto =new UserProfileDto();
//        User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
//            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
//        });
//        //userEntity.getImages().get(0);
//        dto.setUser(userEntity);
//        dto.setPageOwnerState(pageUserId==principalId);
//        dto.setImageCount(userEntity.getImages().size());
//        int subscribeState =subscribeRepository.mSubscribeState(principalId,pageUserId);
//        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
//
//        dto.setSubscribeState(subscribeState==1);
//        dto.setSubscribeCount(subscribeCount);
//
//        return dto;
//    }
//    @Transactional
//    public User 회원수정(int id, User user){
//        User userEntity = userRepository.findById(id).orElseThrow(()-> new CustomValidationApiException("찾을수 없는 id입니다."));
//        //2.영속화 된 오브젝트 수정-더티체킹(업데이트 완료)
//        userEntity.setName(user.getName());
//        String rawPassword = user.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        userEntity.setPassword(encPassword);
//        userEntity.setBio(user.getBio());
//        userEntity.setWebsite(user.getWebsite());
//        userEntity.setPhone(user.getPhone());
//        userEntity.setGender(user.getGender());
//        return userEntity;
//
//    }


}

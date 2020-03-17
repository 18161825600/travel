package com.example.travel.service.impl;

import com.example.travel.bo.ScenicSpotCommentBo;
import com.example.travel.bo.ScenicSpotImgsListBo;
import com.example.travel.dao.CommentDao;
import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.TicketDao;
import com.example.travel.dao.UserDao;
import com.example.travel.pojo.Comment;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.Ticket;
import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.*;
import com.example.travel.service.ScenicSpotService;
import com.example.travel.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ScenicSpotServiceImpl implements ScenicSpotService {

    @Autowired
    private ScenicSpotDao scenicSpotDao;
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Integer insertScenicSpot(AddScenicSpotRequest addScenicSpotRequest) {
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(addScenicSpotRequest,scenicSpot);
        scenicSpot.setCreateTime(new Date());
        List<String> imgs = addScenicSpotRequest.getImgs();
        if(!CollectionUtils.isEmpty(imgs)){
            Map<String,Object> hashMap = new HashMap<>();
            for(String img :imgs){
                String uuid = UUID.randomUUID().toString();
                hashMap.put(uuid,img);
            }
            JSONObject json = new JSONObject(hashMap);
            scenicSpot.setImgs(json.toString());
        }
        return scenicSpotDao.insertScenicSpot(scenicSpot);
    }

    @Override
    public Integer deleteScenicSpot(IdRequest idRequest) {
        return scenicSpotDao.deleteScenicSpot(idRequest.getId());
    }

    @Override
    public Integer updateScenicSpot(UpdateScenicRequest updateScenicRequest) {
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(updateScenicRequest.getId());
        BeanUtils.copyProperties(updateScenicRequest,scenicSpot);
        scenicSpot.setUpdateTime(new Date());
        List<String> imgs = updateScenicRequest.getImgs();
        if(!CollectionUtils.isEmpty(imgs)){
            HashMap<String,String> hashMap = new HashMap<>();
            for(String img :imgs){
                String uuid = UUID.randomUUID().toString();
                hashMap.put(uuid,img);
            }
            scenicSpot.setImgs(JsonUtils.objectToJson(hashMap));
        }
        return scenicSpotDao.updateScenicSpot(scenicSpot);
    }

    @Override
    public <T>T selectScenicSpotById(Long id) {
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(id);
        return (T)changeScenicSpotResponse(scenicSpot);
    }

    @Override
    public ChannelDimensionResponse getChannelDimensionById(IdRequest idRequest) {
        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(idRequest.getId());
        ChannelDimensionResponse response = new ChannelDimensionResponse();
        BeanUtils.copyProperties(scenicSpot,response);
        return response;
    }

//    @Override
//    public List<ChannelDimensionResponse> selectChannelDimensionById() {
//        List<ScenicSpot> scenicSpots = scenicSpotDao.selectAllScenicSpot();
//        List<ChannelDimensionResponse> list = new ArrayList<>();
//        for (ScenicSpot scenicSpot : scenicSpots) {
//            ChannelDimensionResponse channelDimensionResponse = new ChannelDimensionResponse();
//            BeanUtils.copyProperties(scenicSpot,channelDimensionResponse);
//            list.add(channelDimensionResponse);
//        }
//        return list;
//    }

    @Override
    public <T>T selectOneLikeScenicSpotName(String scenicSpotName) {
        ScenicSpot scenicSpot = scenicSpotDao.selectOneLikeScenicSpotName(scenicSpotName);
        return changeScenicSpotResponse(scenicSpot);
    }

    @Override
    public AllScenicSpotResponse selectAllScenicSpot(PageNumRequest pageNumRequest) {
        PageHelper.startPage(pageNumRequest.getPageNum(),10);
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectAllScenicSpot();
        PageInfo<ScenicSpot> pageInfo = new PageInfo<>(scenicSpots);

        List<ScenicSpot> scenicSpotList = pageInfo.getList();
        AllScenicSpotResponse allScenicSpotResponse = changeAllScenicSpotResponse(scenicSpotList);
        allScenicSpotResponse.setTotal(scenicSpotDao.countAllScenicSpot());
        return allScenicSpotResponse;
    }

    @Override
    public AllScenicSpotResponse selectSomeByScenicSpotName(SelectScenicSpotNameRequest selectScenicSpotNameRequest) {
        PageHelper.startPage(selectScenicSpotNameRequest.getPageNum(),10);
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectSomeByScenicSpotName(selectScenicSpotNameRequest.getScenicSpotName());
        PageInfo<ScenicSpot> pageInfo = new PageInfo<>(scenicSpots);

        List<ScenicSpot> scenicSpotList = pageInfo.getList();
        AllScenicSpotResponse allScenicSpotResponse = changeAllScenicSpotResponse(scenicSpotList);
        allScenicSpotResponse.setTotal(scenicSpotDao.countByScenicSpotName(selectScenicSpotNameRequest.getScenicSpotName()));

        return allScenicSpotResponse;
    }

    @Override
    public AllScenicSpotResponse selectSomeByScenicSpotAddress(SelectScenicSpotAddressRequest selectScenicSpotAddressRequest) {
        PageHelper.startPage(selectScenicSpotAddressRequest.getPageNum(),10);
        ScenicSpot scenicSpot = new ScenicSpot();
        if(selectScenicSpotAddressRequest.getScenicSpotAddress()==0){
            scenicSpot.setScenicSpotAddress("临潼区");
        }else if(selectScenicSpotAddressRequest.getScenicSpotAddress()==1){
            scenicSpot.setScenicSpotAddress("碑林区");
        }else if(selectScenicSpotAddressRequest.getScenicSpotAddress()==2){
            scenicSpot.setScenicSpotAddress("雁塔区");
        }else if(selectScenicSpotAddressRequest.getScenicSpotAddress()==3){
            scenicSpot.setScenicSpotAddress("灞桥区");
        }else if(selectScenicSpotAddressRequest.getScenicSpotAddress()==4){
            scenicSpot.setScenicSpotAddress("新城区");
        }
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectSomeByScenicSpotAddress(scenicSpot.getScenicSpotAddress());
        PageInfo<ScenicSpot> pageInfo = new PageInfo<>(scenicSpots);

        List<ScenicSpot> scenicSpotList = pageInfo.getList();
        AllScenicSpotResponse allScenicSpotResponse = changeAllScenicSpotResponse(scenicSpotList);
        allScenicSpotResponse.setTotal(scenicSpotDao.countByScenicSpotAddress(scenicSpot.getScenicSpotAddress()));
        return allScenicSpotResponse;
    }

    @Override
    public AllScenicSpotResponse selectSomeByScenicSpotTypes(SelectScenicSpotTypesRequest selectScenicSpotTypesRequest) {
        PageHelper.startPage(selectScenicSpotTypesRequest.getPageNum(),10);
        ScenicSpot scenicSpot = new ScenicSpot();
        if(selectScenicSpotTypesRequest.getScenicSpotTypes()==1){
            scenicSpot.setScenicSpotTypes("人文");
        }else if(selectScenicSpotTypesRequest.getScenicSpotTypes()==2){
            scenicSpot.setScenicSpotTypes("休闲");
        }else if(selectScenicSpotTypesRequest.getScenicSpotTypes()==3){
            scenicSpot.setScenicSpotTypes("自然");
        }else if(selectScenicSpotTypesRequest.getScenicSpotTypes()==4){
            scenicSpot.setScenicSpotTypes("其他");
        }
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectSomeByScenicSpotTypes(scenicSpot.getScenicSpotTypes());
        PageInfo<ScenicSpot> pageInfo = new PageInfo<>(scenicSpots);

        List<ScenicSpot> scenicSpotList = pageInfo.getList();
        AllScenicSpotResponse allScenicSpotResponse = changeAllScenicSpotResponse(scenicSpotList);
        allScenicSpotResponse.setTotal(scenicSpotDao.countByScenicSpotTypes(scenicSpot.getScenicSpotTypes()));

        return allScenicSpotResponse;
    }

    @Override
    public AllScenicSpotResponse threeInOne(ThreeInOneRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<ScenicSpot> scenicSpots = chooseScenicSpot(request.getChoose());
        PageInfo<ScenicSpot> pageInfo = new PageInfo<>(scenicSpots);

        List<ScenicSpot> scenicSpotList = pageInfo.getList();
        AllScenicSpotResponse allScenicSpotResponse = changeAllScenicSpotResponse(scenicSpotList);
        allScenicSpotResponse.setTotal(chooseScenicSpotTotal(request.getChoose()));
        return allScenicSpotResponse;
    }

    private <T>T changeScenicSpotResponse(ScenicSpot scenicSpot){
        Ticket ticket = ticketDao.selectTicketByScenicId(scenicSpot.getId());
        if(ticket.getAdultTicketPrice()!=0.0 ) {
            ScenicByIdResponse scenicByIdResponse = new ScenicByIdResponse();
            scenicByIdResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
            scenicByIdResponse.setScenicSpotSynopsis(scenicSpot.getScenicSpotSynopsis());
            scenicByIdResponse.setScenicSpotAddress(scenicSpot.getScenicSpotAddress());
            scenicByIdResponse.setScenicSpotDescribe(scenicSpot.getScenicSpotDescribe());
            scenicByIdResponse.setSLongitude(scenicSpot.getsLongitude());
            scenicByIdResponse.setSDimension(scenicSpot.getsDimension());
            scenicByIdResponse.setImg(scenicSpot.getImg());

            List<ScenicSpotImgsListBo> scenicSpotImgsListBos = changeScenicSpotImgsListBo(scenicSpot);
            scenicByIdResponse.setScenicSpotImgsListBos(scenicSpotImgsListBos);

            scenicByIdResponse.setAdultNumber(ticket.getAdultNumber());
            scenicByIdResponse.setAdultTicketPrice(ticket.getAdultTicketPrice());
            scenicByIdResponse.setChildrenNumber(ticket.getChildrenNumber());
            scenicByIdResponse.setChildrenTicketPrice(ticket.getChildrenTicketPrice());
            scenicByIdResponse.setTicketName(ticket.getTicketName());
            scenicByIdResponse.setTicketDescribe(ticket.getTicketDescribe());

            List<Comment> comments = commentDao.selectCommentByScenicId(scenicSpot.getId());
            scenicByIdResponse.setScenicSpotCommentBos(changeScenicSpotCommentBo(comments));

            return (T)scenicByIdResponse;
        }else {
            FreeScenicByIdResponse freeScenicByIdResponse = new FreeScenicByIdResponse();
            freeScenicByIdResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
            freeScenicByIdResponse.setScenicSpotAddress(scenicSpot.getScenicSpotAddress());
            freeScenicByIdResponse.setScenicSpotDescribe(scenicSpot.getScenicSpotDescribe());
            freeScenicByIdResponse.setScenicSpotSynopsis(scenicSpot.getScenicSpotSynopsis());
            freeScenicByIdResponse.setImg(scenicSpot.getImg());

            List<ScenicSpotImgsListBo> scenicSpotImgsListBos = changeScenicSpotImgsListBo(scenicSpot);
            freeScenicByIdResponse.setScenicSpotImgsListBos(scenicSpotImgsListBos);

            freeScenicByIdResponse.setSDimension(scenicSpot.getsDimension());
            freeScenicByIdResponse.setSLongitude(scenicSpot.getsLongitude());

            freeScenicByIdResponse.setTicketDescribe(ticket.getTicketDescribe());

            List<Comment> comments = commentDao.selectCommentByScenicId(scenicSpot.getId());
            freeScenicByIdResponse.setScenicSpotCommentBos(changeScenicSpotCommentBo(comments));

            return (T)freeScenicByIdResponse;
        }

    }

    private List<ScenicSpotCommentBo> changeScenicSpotCommentBo(List<Comment> comments){
        List<ScenicSpotCommentBo> list = new ArrayList<>();
        Integer number = 1;
        for (Comment comment : comments) {
            ScenicSpotCommentBo scenicSpotCommentBo = new ScenicSpotCommentBo();
            scenicSpotCommentBo.setNumber(number);
            scenicSpotCommentBo.setComment(comment.getComment());
            scenicSpotCommentBo.setCreateTime(comment.getCreateTime());

            User user = userDao.selectUserById(comment.getUserId());
            scenicSpotCommentBo.setNickName(user.getNickName());
            scenicSpotCommentBo.setImgUrl(user.getImgUrl());
            list.add(scenicSpotCommentBo);
            number = number+1;
        }
        return list;
    }

    private AllScenicSpotResponse changeAllScenicSpotResponse(List<ScenicSpot> scenicSpotList){
        AllScenicSpotResponse allScenicSpotResponse = new AllScenicSpotResponse();
        List<ScenicSpotResponse> list = new ArrayList<>();
        for(ScenicSpot scenicSpot : scenicSpotList){
            ScenicSpotResponse scenicSpotResponse = new ScenicSpotResponse();
            BeanUtils.copyProperties(scenicSpot,scenicSpotResponse);
            list.add(scenicSpotResponse);
        }
        allScenicSpotResponse.setScenicSpotResponses(list);
        return allScenicSpotResponse;
    }

    private List<ScenicSpotImgsListBo> changeScenicSpotImgsListBo(ScenicSpot scenicSpot){
        StringBuffer s = new StringBuffer();
        s.append("["+scenicSpot.getImgs()+"]");

        int temp = 1;
        List<ScenicSpotImgsListBo> scenicSpotImgsListBos = new ArrayList<>();
        List<Map<String,String>> mapList = JSONArray.parseObject(s.toString(),List. class );
        for (Map<String,String> map : mapList){
            for (Map.Entry entry : map.entrySet()){
                ScenicSpotImgsListBo scenicSpotImgsListBo = new ScenicSpotImgsListBo();
                scenicSpotImgsListBo.setImgId(temp);
                scenicSpotImgsListBo.setImgUrl(entry.getValue().toString());
                scenicSpotImgsListBos.add(scenicSpotImgsListBo);
                temp = temp+1;
            }
        }
        return scenicSpotImgsListBos;
    }

    private List<ScenicSpot> chooseScenicSpot(Integer choose){
        List<ScenicSpot> scenicSpots = new ArrayList<>();
        if(choose==1){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotAddress("临潼区");
        }else if(choose==2){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotAddress("碑林区");
        }else if(choose==3){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotAddress("雁塔区");
        }else if(choose==4){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotAddress("灞桥区");
        }else if(choose==5){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotAddress("新城区");
        }else if(choose==6){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotTypes("人文");
        }else if(choose==7){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotTypes("休闲");
        }else if(choose==8){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotTypes("自然");
        }else if(choose==9){
            scenicSpots = scenicSpotDao.selectSomeByScenicSpotTypes("其他");
        }else {
            scenicSpots=scenicSpotDao.selectAllScenicSpot();
        }
        return scenicSpots;
    }

    private Integer chooseScenicSpotTotal(Integer choose){
        Integer total;
        if(choose==1){
            total = scenicSpotDao.countByScenicSpotAddress("临潼区");
        }else if(choose==2){
            total = scenicSpotDao.countByScenicSpotAddress("碑林区");
        }else if(choose==3){
            total = scenicSpotDao.countByScenicSpotAddress("雁塔区");
        }else if(choose==4){
            total = scenicSpotDao.countByScenicSpotAddress("灞桥区");
        }else if(choose==5){
            total = scenicSpotDao.countByScenicSpotAddress("新城区");
        }else if(choose==6){
            total = scenicSpotDao.countByScenicSpotTypes("人文");
        }else if(choose==7){
            total = scenicSpotDao.countByScenicSpotTypes("休闲");
        }else if(choose==8){
            total = scenicSpotDao.countByScenicSpotTypes("自然");
        }else if(choose==9){
            total = scenicSpotDao.countByScenicSpotTypes("其他");
        }else {
            total=scenicSpotDao.countAllScenicSpot();
        }
        return total;
    }
}

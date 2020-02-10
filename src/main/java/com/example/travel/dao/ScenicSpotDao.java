package com.example.travel.dao;

import com.example.travel.mapper.ScenicSpotMapper;
import com.example.travel.pojo.ScenicSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ScenicSpotDao {

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    public Integer insertScenicSpot(ScenicSpot scenicSpot){
        return scenicSpotMapper.insert(scenicSpot);
    }

    public Integer deleteScenicSpot(Long id){
        return scenicSpotMapper.deleteByPrimaryKey(id);
    }

    public Integer updateScenicSpot(ScenicSpot scenicSpot){
        return scenicSpotMapper.updateByPrimaryKeySelective(scenicSpot);
    }

    public ScenicSpot selectScenicSpotById(Long id){
        return scenicSpotMapper.selectByPrimaryKey(id);
    }

    public ScenicSpot selectOneByScenicSpotName(String scenicSpotName){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andEqualTo("scenicSpotName",scenicSpotName);
        return scenicSpotMapper.selectOneByExample(example);
    }

    public List<ScenicSpot> selectAllScenicSpot(){
        return scenicSpotMapper.selectAll();
    }

    public List<ScenicSpot> selectSomeByScenicSpotName(String scenicSpotName){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotName","%"+scenicSpotName+"%");
        return scenicSpotMapper.selectByExample(example);
    }

    public List<ScenicSpot> selectSomeByScenicSpotAddress(String scenicSpotAddress){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotAddress","%"+scenicSpotAddress+"%");
        return scenicSpotMapper.selectByExample(example);
    }

    public Integer countAllScenicSpot(){
        Example example = new Example(ScenicSpot.class);
        return scenicSpotMapper.selectCountByExample(example);
    }

    public Integer countByScenicSpotName(String scenicSpotName){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotName","%"+scenicSpotName+"%");
        return scenicSpotMapper.selectCountByExample(example);
    }

    public Integer countByScenicSpotAddress(String scenicSpotAddress){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotAddress",scenicSpotAddress);
        return scenicSpotMapper.selectCountByExample(example);
    }
}

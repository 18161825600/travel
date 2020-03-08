package com.example.travel.dao;

import com.example.travel.mapper.FavoriteMapper;
import com.example.travel.pojo.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class FavoriteDao {

    @Autowired
    private FavoriteMapper favoriteMapper;

    public Integer insertFavorite(Favorite favorite){
        return favoriteMapper.insert(favorite);
    }

    public Integer deleteFavorite(List<Long> ids){
        Example example = new Example(Favorite.class);
        example.createCriteria().andIn("id",ids);
        return favoriteMapper.deleteByExample(example);
    }

    public Favorite selectFavoriteById(Long id){
        return favoriteMapper.selectByPrimaryKey(id);
    }

    public Favorite selectFavoriteByUserIdAndScenicId(Long userId,Long scenicSpotId){
        Example example = new Example(Favorite.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("scenicSpotId",scenicSpotId);
        return favoriteMapper.selectOneByExample(example);
    }

    public List<Favorite> selectAllFavorite(){
        return favoriteMapper.selectAll();
    }

    public List<Favorite> selectFavoriteByUserId(Long userId){
        Example example = new Example(Favorite.class);
        example.createCriteria().andEqualTo("userId",userId);
        return favoriteMapper.selectByExample(example);
    }

    public List<Favorite> selectFavoriteByScenicId(Long scenicSpotId){
        Example example = new Example(Favorite.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return favoriteMapper.selectByExample(example);
    }

    public Integer countAllFavorite(){
        Example example = new Example(Favorite.class);
        return favoriteMapper.selectCountByExample(example);
    }

    public Integer countFavoriteByUserId(Long userId){
        Example example = new Example(Favorite.class);
        example.createCriteria().andEqualTo("userId",userId);
        return favoriteMapper.selectCountByExample(example);
    }

    public Integer countFavorteByScenicId(Long scenicSpotId){
        Example example = new Example(Favorite.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return favoriteMapper.selectCountByExample(example);
    }

}

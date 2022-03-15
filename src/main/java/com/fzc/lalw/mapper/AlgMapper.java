package com.fzc.lalw.mapper;

import com.fzc.lalw.domain.Alg;

/**
* @author PerfectFu
* @description 针对表【alg】的数据库操作Mapper
* @createDate 2022-03-11 18:54:26
* @Entity com.fzc.lalw.domain.Alg
*/
public interface AlgMapper {
    int addAlg(Alg alg);
    Alg getAlgById(Integer id);
    Alg[] getAlg(Integer index, Integer length); //这个给出基本信息就行，content不需要给
    Alg[] getAlgByUserId(Integer userId, Integer index, Integer length);
    int modifyAlg(Alg newAlg);
    int deleteAlg(Integer[] ids);

    Integer getTotalNum(Integer id);
}

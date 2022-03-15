package com.fzc.lalw.mapper;

import com.fzc.lalw.domain.Record;

/**
* @author PerfectFu
* @description 针对表【record】的数据库操作Mapper
* @createDate 2022-03-11 18:58:33
* @Entity com.fzc.lalw.domain.Record
*/
public interface RecordMapper {

    Integer addRecord(Record record);
    Record getRecordById(Integer id);
    Record[] getRecord(Integer index, Integer length);
    Record[] getRecordByUserId(Integer userId, Integer index, Integer length);
//    int modifyRecord(Record newRecord);
    Integer deleteRecordById(Integer id);
    Integer deleteRecord(Integer[] ids);


}

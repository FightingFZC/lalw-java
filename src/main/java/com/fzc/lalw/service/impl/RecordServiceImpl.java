package com.fzc.lalw.service.impl;

import com.fzc.lalw.domain.Record;
import com.fzc.lalw.mapper.RecordMapper;
import com.fzc.lalw.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    RecordMapper recordMapper;
    @Override
    public Boolean add(Record record) {
        int num = recordMapper.addRecord(record);
        return num != 0;
    }

    @Override
    public Integer delete(Integer[] ids) {
        return recordMapper.deleteRecord(ids);
    }

    @Override
    public Record[] get(Integer userId, Integer index, Integer length) {
        return recordMapper.getRecordByUserId(userId, index, length);
    }
}

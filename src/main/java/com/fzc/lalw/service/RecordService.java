package com.fzc.lalw.service;

import com.fzc.lalw.domain.Record;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface RecordService {
    Boolean add(Record record);
    Integer delete(Integer[] ids);
    Record[] get(Integer userId, Integer index, Integer length);
}

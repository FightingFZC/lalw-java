package com.fzc.lalw.service;

import com.fzc.lalw.domain.Alg;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface AlgService {
    Boolean add(Alg alg);
    Boolean upload(MultipartFile file, Alg alg);
    String docToHtml(File file);
    String docxToHtml(File file);
    String textToHtml(File file);
    String formatHtml(String html);

    Integer deleteAlg(Integer[] ids);
    Boolean modifyAlg(Alg modifiedAlg);

    Boolean download(Integer id, String type, HttpServletResponse response) throws IOException;
    Boolean htmlToDoc(String html, OutputStream stream);
    Boolean htmlToDocx(String html, OutputStream stream);
    Boolean htmlToText(String html, OutputStream stream);

    Alg[] get(Integer index, Integer length);
    Alg[] getByUserId(Integer userId, Integer index, Integer length);
    Alg get(Integer id);

    Integer getTotalNum(Integer id);
}

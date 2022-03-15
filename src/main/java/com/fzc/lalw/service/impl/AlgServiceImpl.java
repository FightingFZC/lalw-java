package com.fzc.lalw.service.impl;

import com.fzc.lalw.domain.Alg;
import com.fzc.lalw.mapper.AlgMapper;
import com.fzc.lalw.service.AlgService;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */
@Service
public class AlgServiceImpl implements AlgService {
    @Resource
    private AlgMapper algMapper;

    @Override
    public Boolean add(Alg alg) {
        int num = algMapper.addAlg(alg);
        return num != 0;
    }

    //TODO 上传
    @Override
    public Boolean upload(MultipartFile file, Alg alg) {
        Boolean state;
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("NovelController.imp方法：后缀名为：" + suffix);
        /*临时文件，之后文件的转换以File类为参数*/
        File temp = null;
        try {
            temp = new File(ResourceUtils.
                    getURL("classpath:").getPath() + "static/temp/" + fileName);
            if (!temp.exists()) {
                /*不存在就创建目录，创建文件*/
                temp.mkdirs();
                temp.createNewFile();
            }
            file.transferTo(temp);

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*根据不同的后缀名选择不同的方法去处理*/
        String novelData = null;
        try {
            if (".docx".equals(suffix)) {
                novelData = docxToHtml(temp);
            }else if (".doc".equals(suffix)) {
                novelData = docToHtml(temp);
            }else if (".txt".equals(suffix)) {
                novelData = textToHtml(temp);
            }
            alg.setContent(novelData);
            state = add(alg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (temp != null) {
                // 最后把临时文件删掉
                /*记一笔，由于没把流给关掉，导致删不掉，所以流得看到就关*/
                System.out.println("NovelController.imp方法：临时文件删除" + temp.delete());
            }
        }

        return state;
    }

    @Override
    public String docToHtml(File file) {

        HWPFDocument document = null;
        Document html = null;
        WordToHtmlConverter converter = null;
        StringWriter writer = null;
        Transformer transformer = null;
        FileInputStream inputStream = null;
        try {

            inputStream = new FileInputStream(file);
            document = new HWPFDocument(inputStream);
            html =
                    XMLHelper.getDocumentBuilderFactory().newDocumentBuilder().newDocument();

            converter = new WordToHtmlConverter(html);
            converter.processDocument(document);

            transformer = TransformerFactory.newInstance().newTransformer();
            writer = new StringWriter();
            transformer.transform(new DOMSource(converter.getDocument()),
                    new StreamResult(writer));


        } catch (IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return formatHtml(writer.toString());
    }

    @Override
    public String docxToHtml(File file) {
        //1. 创建document对象
        XWPFDocument document = null;
        //2. 创建xml-html转换器对象
        XHTMLConverter xhtmlConverter = null;
        //3. 创建一个输出流来接收转换后的html文本
        StringWriter writer = null;
        //4. 创建一个输入流读取临时文件
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            //1. 创建document对象
            document = new XWPFDocument(inputStream);
            //2. 创建xml-html转换器对象
            xhtmlConverter = new XHTMLConverter();
            //3. 创建一个输出流来接收转换后的html文本
            writer = new StringWriter();
            //4. 转换
            xhtmlConverter.convert(document, writer, XHTMLOptions.getDefault());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return formatHtml(writer.toString());
    }

    @Override
    public String textToHtml(File file) {
        /*由于要经常拼接字符串，用StringBuilder会比较好*/
        StringBuilder html = new StringBuilder();
        BufferedReader reader = null;
        //1. 用一个BufueredReader读取file
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            //2. 读一行就放到一个<p></p>里面
            while ((line = reader.readLine()) != null) {
                html.append("<p>");
                html.append(line);
                html.append("</p>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return html.toString();
    }

    @Override
    public String formatHtml(String html) {
        /*去回车，去换行*/
        html = html.trim();
        html = html.replaceAll("\n", "");
        html = html.replaceAll("\r", "");

        /*匹配完body需要去除body标签*/
        String patternString = "<body[^>]*>[\\S\\s]*?</body>";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(html);
        System.out.println("NovelServiceImpl.getBody方法：是否找到" + matcher.find());

        String content = matcher.group();
        String patBodyBeg = "<body[^>]*>";
        String patBodyEnd = "</body>";

        /*去除前标签*/
        pattern = Pattern.compile(patBodyBeg);
        matcher = pattern.matcher(content);
        content = matcher.replaceAll("");

        /*去除尾标签*/
        pattern = Pattern.compile(patBodyEnd);
        matcher = pattern.matcher(content);
        content = matcher.replaceAll("");

        return content;
    }

    @Override
    public Integer deleteAlg(Integer[] ids) {
        return algMapper.deleteAlg(ids);
    }

    @Override
    public Boolean modifyAlg(Alg modifiedAlg) {
        modifiedAlg.setLastModified((new SimpleDateFormat("yyyy-MM-dd " +
                "HH:mm:ss")).format(new Date()));
        int num = algMapper.modifyAlg(modifiedAlg);
        return num != 0;
    }

    //TODO 下载
    @Override
    public Boolean download(Integer id, String type, HttpServletResponse response) throws IOException {
        Alg alg = algMapper.getAlgById(id);
        Boolean state = null;
        // 根据不同的type，调用不同的方法
        // 调用Service获取POIFSFileSystem
        if ("docx".equals(type)) {
            response.setContentType("application/vnd" +
                    ".openxmlformats-officedocument.wordprocessingml.document");
            state = htmlToDocx(alg.getContent(), response.getOutputStream());
        } else if ("doc".equals(type)) {
            //这一行太tm重要了，不加就不晓得搞了什么东西
            response.setContentType("application/msword");
            state = htmlToDoc(alg.getContent(), response.getOutputStream());

        } else if ("txt".equals(type)) {
            response.setContentType("text/plain");
            state = htmlToText(alg.getContent(), response.getOutputStream());
        }
        return null;
    }

    @Override
    public Boolean htmlToDoc(String html, OutputStream stream) {
        html = wrapNoBodyHtml(html);
        ByteArrayInputStream bais = null;
        POIFSFileSystem fileSystem = null;
        try {
            bais = new ByteArrayInputStream(html.getBytes());
            fileSystem = new POIFSFileSystem();
            DirectoryEntry directoryEntry = fileSystem.getRoot();
            directoryEntry.createDocument("WordDocument", bais);
            fileSystem.writeFilesystem(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } if (bais != null) {
            try {
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Boolean htmlToDocx(String html, OutputStream stream) {
        //把数据库里存储的包起来就不会报错了，或者用个div套起来
        //不能理解，明明上面说的The fragment should be one or more block level objects
        html = wrapNoBodyHtml(html);
        try {
            //1. 实例化 docx4j 对象
            WordprocessingMLPackage mlPackage =
                    WordprocessingMLPackage.createPackage();
            XHTMLImporterImpl importer = new XHTMLImporterImpl(mlPackage);
            //加载html文档
            mlPackage.getMainDocumentPart().getContent().addAll(importer.convert(html, null));
            //用输出流来保存
            mlPackage.save(stream);
        } catch (Docx4JException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    @Override
    public Boolean htmlToText(String html, OutputStream stream) {
        /*
          就这样吧，不然根据是不是块标签来判断要不要换行贼麻烦……
          要是复杂点有什么float样式就更是了
          */
        String text = html.replaceAll("\\<.*?\\>", "\n");
        try {
            stream.write(text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String wrapNoBodyHtml(String html) {
        StringBuilder wraped = new StringBuilder();
        wraped.append("<html><head>");
        wraped.append("<META content=\"charset=UTF-8\"></META>");
        wraped.append("</head><body>");
        wraped.append(html);
        wraped.append("</body></html>");

        return wraped.toString();
    }

    @Override
    public Alg[] get(Integer index, Integer length) {
        return algMapper.getAlg(index, length);
    }

    @Override
    public Alg[] getByUserId(Integer userId, Integer index, Integer length) {
        return algMapper.getAlgByUserId(userId, index, length);
    }

    @Override
    public Alg get(Integer id) {
        Alg alg = algMapper.getAlgById(id);
        System.out.println(alg);
        return algMapper.getAlgById(id);
    }

    @Override
    public Integer getTotalNum(Integer id) {
        return algMapper.getTotalNum(id);
    }


}

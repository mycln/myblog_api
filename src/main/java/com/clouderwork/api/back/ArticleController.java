package com.clouderwork.api.back;

import com.clouderwork.common.CommResult;
import com.clouderwork.pojo.TArticle;
import com.clouderwork.service.ArticleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author changlina
 * @Contact
 * @Description
 * @Date Created in 2018/11/22
 */
@CrossOrigin(origins = "*")
@RestController
@Api(description = "S05 发布文章接口")
@RequestMapping(value = "/back/article")
public class ArticleController {
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "S0501 发布文章 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/publish", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult add(
            @ApiParam(value = "文章标题", required = true) @RequestParam(value = "title") String title,
            @ApiParam(value = "文章内容", required = true) @RequestParam(value = "content") String content,
            @ApiParam(value = "分类名称") @RequestParam(value = "categoryId",required = false) String categoryId,
            @ApiParam(value = "标签名称") @RequestParam(value = "labelId",required = false) String labelId
    ) {
        articleService.add(title,content,categoryId,labelId);
        return CommResult.ok();
    }

    @ApiOperation(value = "S0502 查询所有类别 状态:已完成", response = CommResult.class)
    @GetMapping(value = "/getAllCategorys", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult getAllCategorys() {
        return CommResult.ok(articleService.getAllCategorys());
    }

    @ApiOperation(value = "S0503 根据类别pid查询类别 状态:已完成", response = CommResult.class)
    @GetMapping(value = "/getCategoryVoByPid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult getCategoryVoByPid(
            @ApiParam(value = "categoryPid", required = true) @RequestParam(value = "categoryPid") Byte categoryPid
    ) {
//        return CommResult.ok(articleService.getAllCategorysByPid(categoryPid));
        return CommResult.ok(articleService.getCategoryVoByPid(categoryPid));
    }

    @ApiOperation(value = "S0504 根据分类pid查询最新文章 状态:已完成", response = CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下",response= TArticle.class)
    })
    @GetMapping(value = "/getListByCategoryPid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult getListByCategoryPid(
            @ApiParam(value = "分类pid", required = true) @RequestParam(value = "categoryPid") Byte categoryPid,
            @ApiParam(value = "查询页数") @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(value = "每页条数") @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        return CommResult.ok(articleService.getListByCategoryPid(categoryPid,pageNum,pageSize));
    }

    @ApiOperation(value = "S0505 根据分类查询最新文章 状态:已完成", response = CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下",response= TArticle.class)
    })
    @GetMapping(value = "/getListByCategoryId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult getListByCategoryId(
            @ApiParam(value = "分类id", required = true) @RequestParam(value = "categoryId") Long categoryId,
            @ApiParam(value = "查询页数")@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(value = "每页条数")@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        return CommResult.ok(articleService.getListByCategoryId(categoryId,pageNum,pageSize));
    }


    @ApiOperation(value = "S0506 根据文章id查询文章信息 状态：已完成", response = CommResult.class)
    @GetMapping(value = "/getArticleById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult getArticleById(
            @ApiParam(value = "文章id", required = true) @RequestParam(value = "id") Long id) {
        TArticle article = articleService.getArticleById(id);
        return CommResult.ok(article);
    }
}

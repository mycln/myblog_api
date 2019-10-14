package com.clouderwork.service;

import com.clouderwork.annotation.ServiceLogs;
import com.clouderwork.dao.TArticleMapper;
import com.clouderwork.dao.TCategoryArticleMapper;
import com.clouderwork.dao.TCategoryMapper;
import com.clouderwork.dao.TLabelArticleMapper;
import com.clouderwork.pojo.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.clouderwork.pojo.vo.CategoryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @Author xuqiang
 * @Contact QQ、手机号或者云沃客账号
 * @Description
 * @Date Created in 2017/11/18
 */

@Service
public class ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private TArticleMapper articleMapper;
    @Autowired
    private TLabelArticleMapper laMapper;
    @Autowired
    private TCategoryArticleMapper caMapper;
    @Autowired
    private TCategoryMapper tCategoryMapper;

    /**
     * 发布文章
     */
	@Transactional
	public void add(String title, String content,String categoryId,String labelId){
        TArticle article = new TArticle();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor("常丽娜");
        Integer times = Integer.valueOf(new SimpleDateFormat("yyyyMMddHH").format(new Date()));
        article.setCtime(times);
        article.setUtime(times);
        articleMapper.insertSelective(article);

        if(!StringUtils.isEmpty(categoryId)){
            TCategoryArticle ca = new TCategoryArticle();
            ca.setArticleId(article.getId());
            ca.setCategoryId(Long.valueOf(categoryId));
            saveCategoryArticle(ca);
        }

        if(!StringUtils.isEmpty(labelId)){
            TLabelArticle la = new TLabelArticle();
            la.setArticleId(article.getId());
            la.setLabelId(Long.valueOf(labelId));
            saveTLabelArticle(la);
        }

	}

    /**
     * 查询所有类别
     * @return
     */
	public List<TCategory> getAllCategorys(){
        TCategoryExample example = new TCategoryExample();
        TCategoryExample.Criteria cri = example.createCriteria().andIdIsNotNull();
        cri.andPidNotEqualTo(Byte.valueOf("0"));
        return tCategoryMapper.selectByExample(example);
    }

    /**
     * 根据类别pid查询类别
     * @param pid
     * @return
     */
    public List<TCategory> getAllCategorysByPid(Byte pid){
        TCategoryExample example = new TCategoryExample();
        TCategoryExample.Criteria cri = example.createCriteria();
        cri.andPidEqualTo(pid);
        return tCategoryMapper.selectByExample(example);
    }

    public List<CategoryVo> getCategoryVoByPid(Byte pid){
        List<CategoryVo> lst = new ArrayList<>();
        List<TCategory> tCategories = getAllCategorysByPid(pid);
        for (TCategory tCategory : tCategories){
            CategoryVo vo = new CategoryVo();
            BeanUtils.copyProperties(tCategory,vo);

            TCategoryArticleExample example = new TCategoryArticleExample();
            example.createCriteria().andCategoryIdEqualTo(tCategory.getId());
            List<TCategoryArticle> list = caMapper.selectByExample(example);
            vo.setArticleNum(list.size());

            lst.add(vo);
        }
        return lst;
    }

    /**
     * 根据文章id查询文章信息
     * @param id
     * @return
     */
    public TArticle getArticleById(Long id){
        return articleMapper.selectByPrimaryKey(id);
    }

	/**
	 * 保存分类文章映射
	 * @param ca
	 */
	@Transactional
	@ServiceLogs(description = "保存分类文章映射表")
	public void saveCategoryArticle(TCategoryArticle ca){
		caMapper.insertSelective(ca);
	}
	
	/**
	 * 保存保存标签文章映射
	 * @param la
	 */
	@Transactional
	@ServiceLogs(description = "保存标签文章映射表")
	public void saveTLabelArticle(TLabelArticle la){
		laMapper.insertSelective(la);
	}

    @ServiceLogs(description = "根据分类pid查询最新文章")
    public PageInfo<TArticle> getListByCategoryPid(Byte categoryPid, Integer pageNum, Integer pageSize){
        List<TCategory> categories = getAllCategorysByPid(categoryPid);
        List<Long> categorieIds = Lists.newArrayList();
        for (TCategory tCategory : categories){
            categorieIds.add(tCategory.getId());
        }
        if(CollectionUtils.isEmpty(categorieIds)){
            throw new IllegalArgumentException("无法获取分类信息");
        }

//        PageHelper.startPage(pageNum,pageSize);
        List<TArticle> list = this.getArt(categorieIds,null, pageNum, pageSize);
        return new PageInfo<TArticle>(list);
    }

    public PageInfo<TArticle> getListByCategoryId(Long categoryId, Integer pageNum, Integer pageSize){
        List<TArticle> list = this.getArt(null,categoryId, pageNum, pageSize);
        return new PageInfo<TArticle>(list);
    }

    private List<TArticle> getArt(List<Long> categorieIds, Long categoryId,Integer pageNum, Integer pageSize){
        TCategoryArticleExample example = new TCategoryArticleExample();
        if(categoryId != null){
            example.createCriteria().andCategoryIdEqualTo(categoryId);
        } else {
            example.createCriteria().andCategoryIdIn(categorieIds);
        }
        List<TCategoryArticle> lst = caMapper.selectByExample(example);
        List<Long> aids = Lists.newArrayList();
        for (TCategoryArticle article : lst){
            aids.add(article.getArticleId());
        }
        if(CollectionUtils.isEmpty(aids)){
            throw new IllegalArgumentException("当前分类下无文章");
        }

        TArticleExample tArticleExample = new TArticleExample();
        tArticleExample.createCriteria().andIdIn(aids);
        tArticleExample.setOrderByClause("utime asc");
//        List<TArticle> list = articleMapper.selectByExample(tArticleExample);
        PageHelper.startPage(pageNum,pageSize);
        List<TArticle> list = articleMapper.selectByExampleWithBLOBs(tArticleExample);
        System.out.println("总共的条数："+list.size());
        return list;
    }
 }

package cn.edu.ncut.dao;

import cn.edu.ncut.model.Article;
import org.springframework.stereotype.Repository;

/**
 * @author NikoBelic
 * @create 09:35
 */
@Repository
public class ArticleDaoImpl extends MongoDaoSupport<Article> implements ArticleDao
{
}

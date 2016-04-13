package cn.edu.ncut.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author NikoBelic
 * @create 15:19
 */
public class MongoDaoSupport<T> implements MongoBase<T>
{
    @Resource
    MongoTemplate mongoTemplate;

    protected Class<?> entityClass;
    protected String entityName;
    protected Method getIdMethod;

    public Class<T> getEntityClass() {
        Type parentType = getClass().getGenericSuperclass();
        if (parentType instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) parentType;
            return (Class<T>) ptype.getActualTypeArguments()[0];
        }
        return null;
    }

    public String getEntityName() {
        return this.entityClass.getSimpleName();
    }

    @Override
    public void insert(Object object, String collectionName) {
        mongoTemplate.insert(object, collectionName);
    }

    @Override
    public T findOne(Map params, String collectionName) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(params.get("id"))), getEntityClass(), collectionName);
    }

    @Override
    public List<T> findAll(String collectionName) {
        List<T> result = mongoTemplate.findAll(getEntityClass(), collectionName);
        return result;
    }

    @Override
    public void update(Map params, String collectionName) {
        mongoTemplate.upsert(new Query(Criteria.where("id").is(params.get("id"))), new Update().set("name", params.get("name")), getEntityClass(), collectionName);

    }

    @Override
    public void createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
    }

    @Override
    public void remove(Map params, String collectionName) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(params.get("id"))), getEntityClass(), collectionName);
    }

    @Override
    public void removeCollection(String collectionName) {
        mongoTemplate.dropCollection(collectionName);
    }
}

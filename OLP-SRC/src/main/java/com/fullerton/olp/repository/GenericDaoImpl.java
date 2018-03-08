package com.fullerton.olp.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.Validate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.TypedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.fullerton.olp.model.base.BaseModel;
import com.fullerton.olp.repository.support.SearchTemplate;

/**
 * Partial Hibernate implementation of the {@link GenericDao} interface.<br>
 * The exceptions are converted to generic spring unchecked exceptions.
 */
public class GenericDaoImpl<E, PK extends Serializable> extends SimpleJpaRepository<E, PK> implements GenericDao<E, PK>{

	private final EntityManager entityManager;
	private Class<E> type;
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	public GenericDaoImpl(Class<E> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
		// TODO Auto-generated constructor stub
	}
	
	public GenericDaoImpl(JpaMetamodelEntityInformation<E, PK> jmei, EntityManager em) {
		// TODO Auto-generated constructor stub
		super(jmei, em);
		this.type = jmei.getJavaType();
		this.entityManager = em;
	}
	 
	/**
	 * {@inheritDoc}
	 */
	public E get(E entity) {
		if (entity == null) {
			return null;
		}

		//PK id = entity.getId();
//		if (id == null) {
//			return null;
//		}
		return getOne(null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void refresh(E entity) {
		get(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<E> find(E entity, SearchTemplate searchTemplate) {
		Validate.notNull(entity, "The passed entity cannot be null");

		SearchTemplate localSearchTemplate = getLocalSearchTemplate(searchTemplate);

//		if (localSearchTemplate.hasNamedQuery()) {
//			return (List<E>) getNamedQueryUtil().findByNamedQuery(localSearchTemplate, entity);
//		}

		//localSearchTemplate.addOrderBy("modifiedOn", DESC);
		//localSearchTemplate.addOrderBy("createdOn", DESC);

		Criteria criteria = getCriteria(entity, localSearchTemplate);
		HibernateUtil.applyPaginationAndOrderOnCriteria(criteria, searchTemplate);

		List<E> entities = (List<E>) criteria.list();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returned " + entities.size() + " elements");
		}

		return entities;
	}

	/**
	 * {@inheritDoc}
	 */
	public int findCount(E entity, SearchTemplate searchTemplate) {
		Validate.notNull(entity, "The entity cannot be null");
		SearchTemplate localSearchTemplate = getLocalSearchTemplate(searchTemplate);

//		if (localSearchTemplate.hasNamedQuery()) {
//			return getNamedQueryUtil().numberByNamedQuery(localSearchTemplate, entity).intValue();
//		}

		/*
		 * if(hibernateFilterContext.isFilterEnabled("accountFilter") &&
		 * !UserAccount.class.equals(type)){
		 * hibernateFilterContext.disableFilter("accountFilter"); }
		 */
		// if(hibernateFilterContext.isFilterEnabled("userFilter") &&
		// !User.class.equals(type)){
		// hibernateFilterContext.disableFilter("userFilter");
		// }
		Criteria criteria = getCriteria(entity, searchTemplate);
		criteria.setProjection(Projections.rowCount());

		Number count = (Number)criteria.uniqueResult();

		if (count != null) {
			return count.intValue();
		} else {
			LOG.warn("findCount returned null!");
			return 0;
		}
	}

	/**
	 * Create a criteria with caching enabled
	 *
	 * @param entity
	 *            the entity to use in search by Example
	 * @param searchTemplate
	 *            the specific parameters such as named queries, extra infos,
	 *            limitations, order, ...
	 * @return an hibernate criteria
	 */
	protected Criteria getCriteria(E entity, SearchTemplate searchTemplate) {

		Criteria criteria = getCurrentSession().createCriteria(type);
		setUpCacheOnCriteria(criteria, searchTemplate);
		
	    
		//TOOD create criteria for inner objects and add to criteria;
		
		List<Criterion> criterions = getCriterions(entity, searchTemplate);
		for (Criterion criterion : criterions) {
			criteria.add(criterion); // AND
		}

		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object object = field.get(entity);
				if (object != null && isInstanceOfBaseModel(field)) {
					List<Criterion> innerCriterions = getCriteria(((BaseModel)object));
					criteria.createAlias(getVariableName(object), getVariableName(object));
					for (Criterion criterion : innerCriterions) {
						criteria.add(criterion);
					}
				    
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return criteria;
	}
	

	private boolean isInstanceOfBaseModel(Field field) {
		if (field.getType().getSuperclass() == null) {
			return false;
		}
		Class<?> klass = field.getType().getSuperclass();
		//for (Class<?> klass : classes) {
			if(BaseModel.class.equals(klass)) {
				return true;
			}
		//}
		return false;
	}
	protected Session getCurrentSession() {
		return (Session) entityManager.getDelegate();
	}

	protected void setUpCacheOnCriteria(Criteria criteria, SearchTemplate searchTemplate) {
		if (searchTemplate.isCacheable()) {
			criteria.setCacheable(true);

			if (searchTemplate.hasCacheRegion()) {
				criteria.setCacheRegion(searchTemplate.getCacheRegion());
			} else {
				criteria.setCacheRegion(getCacheRegion());
			}
		}
	}

	protected List<Criterion> getCriterions(E entity, SearchTemplate searchTemplate) {
		List<Criterion> criterions = new ArrayList<Criterion>();

		// search by date range
		Criterion dateCriterion = getByDateCriterion(searchTemplate);
		if (dateCriterion != null) {
			criterions.add(dateCriterion);
		}

		// search by example
		Criterion exampleCriterion = getByExampleCriterion(entity, searchTemplate);
		if (exampleCriterion != null) {
			criterions.add(exampleCriterion);
		}

		// search by pattern
		Criterion patternCriterion = getByPatternCriterion(searchTemplate);
		if (patternCriterion != null) {
			criterions.add(patternCriterion);
		}

		// additional criterion (for example isNull criterion on x-to-many
		// association)
		for (Criterion c : searchTemplate.getCriterions()) {
			criterions.add(c);
		}
		return criterions;
	}

	protected Criterion getByDateCriterion(SearchTemplate searchTemplate) {
		return HibernateUtil.constructDate(searchTemplate);
	}

	protected Criterion getByExampleCriterion(E entity, SearchTemplate searchTemplate) {
		Criterion example = HibernateUtil.constructExample(entity, searchTemplate);
		List<Criterion> extras = getByExampleExtraCriterions(entity, searchTemplate);

		if (extras != null && extras.size() > 0) {
			
			Junction conjunction = Restrictions.conjunction();
			for (Criterion extra : extras) {
				conjunction.add(extra);
			}
			conjunction.add(example);
			return conjunction;
		} else {
			return example;
		}
	}

	protected Criterion getByPatternCriterion(SearchTemplate searchTemplate) {
		return HibernateUtil.constructPattern(type, searchTemplate);
	}

	/**
	 * Subclass may provide extra criterion. Used along with search by example.
	 * Default implementation does nothing. Principal use case is to add
	 * criterion so the id fields part of the composite primary key are included
	 * in a search by example.
	 *
	 * @param entity
	 * @param searchTemplate
	 * @return a criterion that will be appended (AND) to the example criterion.
	 *         Null if no criterion should be appended.
	 */
	protected List<Criterion> getByExampleExtraCriterions(E entity, SearchTemplate searchTemplate) {
		
		return null;
	}

	private String getVariableName(Object entity){
		return String.valueOf(entity.getClass().getSimpleName().charAt(0)).toLowerCase() + entity.getClass().getSimpleName().substring(1);
	}

	 

	private List<Criterion>  getCriteria(BaseModel model) {
		List<Criterion> list = new ArrayList<>();
		try {
			Field[] fields = model.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object object = field.get(model);
				
				if (object != null ) {
					if(!Modifier.isStatic(field.getModifiers()) && !isInstanceOfBaseModel(field) ) {
//						System.out.println("Variable Name :->"+getVariableName(model));
//						System.out.println("Field Name :->"+field.getName());
//						System.out.println("Field :->"+field.get(model));
//						System.out.println("Field Type :->"+field.getType());
						if(String.class.isAssignableFrom(field.getType())){
							list.add(Expression.like(getVariableName(model)+"."+field.getName(),"%"+field.get(model)+"%"));
						} else {
							list.add(Expression.eq(getVariableName(model)+"."+field.getName(),field.get(model)));
						}
						
					} 
				}
			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Create a new search template, taking into account the passed
	 * searchTemplate and the cacheable and cacheRegion properties of this
	 * instance.
	 */
	public SearchTemplate getLocalSearchTemplate(SearchTemplate searchTemplate) {
		if (searchTemplate == null) {
			SearchTemplate localSearchTemplate = new SearchTemplate();
			localSearchTemplate.setCacheable(getCacheable());
			localSearchTemplate.setCacheRegion(getCacheRegion());
			return localSearchTemplate;
		}

		SearchTemplate localSearchTemplate = new SearchTemplate(searchTemplate);

		if (searchTemplate.isCacheable() == null) {
			localSearchTemplate.setCacheable(getCacheable());
		}

		if (!searchTemplate.hasCacheRegion()) {
			localSearchTemplate.setCacheRegion(getCacheRegion());
		}

		return localSearchTemplate;
	}

	 
	// ------------------------------------------
	// Dependencies
	// ------------------------------------------
//	@Autowired
//	private NamedQueryUtil namedQueryUtil;
//
//	public void setNamedQueryUtil(NamedQueryUtil namedQueryUtil) {
//		this.namedQueryUtil = namedQueryUtil;
//	}
//
//	public NamedQueryUtil getNamedQueryUtil() {
//		return namedQueryUtil;
//	}

	// ------------------------------------------
	// Configuration
	// ------------------------------------------
	private boolean cacheable = true;
	private String cacheRegion;

	/**
	 * Set the default cacheable property to be used when the searchTemplate
	 * argument is null or do not specify one.
	 */
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	public boolean getCacheable() {
		return cacheable;
	}

	/**
	 * Set the default cacheRegion property to use when the searchTemplate
	 * argument is null or do not specify one.
	 */
	public void setCacheRegion(String cacheRegion) {
		this.cacheRegion = cacheRegion;
	}

	public String getCacheRegion() {
		return cacheRegion;
	}

	/*@Override
	public void save(E entity) {
		super.save(entity);
		
	}
*/

}